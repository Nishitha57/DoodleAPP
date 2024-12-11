ppackage com.example.doodleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Stack;

public class DoodleView extends View {
    private Paint paint;
    private ArrayList<Line> lines;
    private Line currentLine;
    private Stack<Line> undoneLines;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializePaint();
        lines = new ArrayList<>();
        undoneLines = new Stack<>();
    }

    private void initializePaint() {
        paint = new Paint();
        paint.setColor(0xFF000000); // Default to black
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10); // Default brush size
    }

    public void setBrushSize(float size) {
        paint.setStrokeWidth(size);
    }

    public void setBrushColor(int color) {
        paint.setColor(color);
    }

    public void clearCanvas() {
        lines.clear();
        undoneLines.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw all lines
        for (Line line : lines) {
            drawLine(canvas, line);
        }
    }

    private void drawLine(Canvas canvas, Line line) {
        paint.setColor(line.color);
        paint.setStrokeWidth(line.size);
        canvas.drawPath(line.path, paint); // Draw the path
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startNewLine(event);
                return true;

            case MotionEvent.ACTION_MOVE:
                updateCurrentLine(event);
                invalidate(); // Redraw the view
                return true;

            case MotionEvent.ACTION_UP: // Optional: Handling lift-up event
                currentLine = null; // Cleanup
                return true;

            default:
                return false;
        }
    }

    private void startNewLine(MotionEvent event) {
        currentLine = new Line(paint.getColor(), paint.getStrokeWidth());
        currentLine.path.moveTo(event.getX(), event.getY());
        lines.add(currentLine);
        undoneLines.clear(); // Clear the undo stack on starting a new line
    }

    private void updateCurrentLine(MotionEvent event) {
        if (currentLine != null) {
            currentLine.path.lineTo(event.getX(), event.getY());
        }
    }

    public void undo() {
        if (!lines.isEmpty()) {
            Line line = lines.remove(lines.size() - 1); // Remove the last line
            undoneLines.push(line); // Push it to undone lines stack
            invalidate(); // Redraw the view
        }
    }

    public void redo() {
        if (!undoneLines.isEmpty()) {
            Line line = undoneLines.pop(); // Pop from undone lines
            lines.add(line); // Add it back to lines
            invalidate(); // Redraw the view
        }
    }
}