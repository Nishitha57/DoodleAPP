import android.graphics.Path;

public class Line {
    public Path path;
    public int color;
    public float size;

    public Line(int color, float size) {
        this.color = color;
        this.size = size;
        this.path = new Path(); // Initialize the Path object
    }
}