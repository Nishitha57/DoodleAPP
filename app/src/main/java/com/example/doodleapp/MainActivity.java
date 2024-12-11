package com.example.doodleapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DoodleView doodleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDoodleView();
        setupButtons();
    }

    private void initializeDoodleView() {
        doodleView = findViewById(R.id.doodleView);
    }

    private void setupButtons() {
        setupClearButton();
        setupBrushSizeButton();
        setupColorPickerButton();
        setupUndoButton();
        setupRedoButton();
    }

    private void setupClearButton() {
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(view -> {
            doodleView.clearCanvas(); // Clear the drawing canvas
        });
    }

    private void setupBrushSizeButton() {
        Button brushSizeButton = findViewById(R.id.brushSizeButton);
        brushSizeButton.setOnClickListener(view -> {
            doodleView.setBrushSize(20); // Set brush size to 20
        });
    }

    private void setupColorPickerButton() {
        Button colorPickerButton = findViewById(R.id.colorPickerButton);
        colorPickerButton.setOnClickListener(view -> {
            doodleView.setBrushColor(Color.RED); // Set brush color to RED
        });
    }

    private void setupUndoButton() {
        Button undoButton = findViewById(R.id.undoButton);
        undoButton.setOnClickListener(view -> {
            doodleView.undo(); // Perform undo action
        });
    }

    private void setupRedoButton() {
        Button redoButton = findViewById(R.id.redoButton);
        redoButton.setOnClickListener(view -> {
            doodleView.redo(); // Perform redo action
        });
    }
}