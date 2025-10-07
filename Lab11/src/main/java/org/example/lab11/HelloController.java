package org.example.lab11;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloController {

    @FXML private Canvas canvas;
    @FXML private CheckBox cbDotted;
    @FXML private Button btnReset, btnRandom;

    private final double[][] pts = new double[4][2];
    private final double R = 6; // raza punctelor
    private int dragged = -1;

    @FXML
    public void initialize() {
        // inițializare puncte
        pts[0] = new double[]{100, 400};
        pts[1] = new double[]{250, 100};
        pts[2] = new double[]{650, 100};
        pts[3] = new double[]{800, 400};

        draw();

        cbDotted.setOnAction(e -> draw());
        btnReset.setOnAction(e -> resetPoints());
        btnRandom.setOnAction(e -> randomPoints());

        // evenimente mouse pentru drag
        canvas.setOnMousePressed(e -> {
            if (e.getButton() != MouseButton.PRIMARY) return;
            dragged = findPoint(e.getX(), e.getY());
        });

        canvas.setOnMouseReleased(e -> dragged = -1);

        canvas.setOnMouseDragged(e -> {
            if (dragged != -1) {
                pts[dragged][0] = e.getX();
                pts[dragged][1] = e.getY();
                draw();
            }
        });
    }

    private void resetPoints() {
        pts[0] = new double[]{100, canvas.getHeight() - 120};
        pts[1] = new double[]{canvas.getWidth() * 0.25, 80};
        pts[2] = new double[]{canvas.getWidth() * 0.75, 80};
        pts[3] = new double[]{canvas.getWidth() - 100, canvas.getHeight() - 120};
        draw();
    }

    private void randomPoints() {
        double w = canvas.getWidth(), h = canvas.getHeight();
        pts[0] = new double[]{50, h - 50};
        pts[1] = new double[]{w * 0.3, Math.random() * (h * 0.6)};
        pts[2] = new double[]{w * 0.6, Math.random() * (h * 0.6)};
        pts[3] = new double[]{w - 50, h - 50};
        draw();
    }

    private int findPoint(double x, double y) {
        for (int i = 0; i < 4; i++) {
            if (Math.hypot(x - pts[i][0], y - pts[i][1]) < R * 3) return i;
        }
        return -1;
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = canvas.getWidth(), h = canvas.getHeight();
        gc.setFill(Color.web("#fdfdfd"));
        gc.fillRect(0, 0, w, h);

        // linii de ghid
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        gc.setLineDashes(4, 8);
        gc.beginPath();
        gc.moveTo(pts[0][0], pts[0][1]);
        gc.lineTo(pts[1][0], pts[1][1]);
        gc.lineTo(pts[2][0], pts[2][1]);
        gc.lineTo(pts[3][0], pts[3][1]);
        gc.stroke();
        gc.closePath();
        gc.setLineDashes();

        // curba Bézier
        gc.setStroke(Color.web("#2b6fbf"));
        gc.setLineWidth(3);
        if (cbDotted.isSelected()) gc.setLineDashes(12, 10);
        gc.beginPath();
        gc.moveTo(pts[0][0], pts[0][1]);
        gc.bezierCurveTo(
                pts[1][0], pts[1][1],
                pts[2][0], pts[2][1],
                pts[3][0], pts[3][1]
        );
        gc.stroke();
        gc.closePath();
        gc.setLineDashes();

        // puncte
        for (int i = 0; i < 4; i++) {
            drawPoint(gc, pts[i][0], pts[i][1], i);
        }

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font(12));
        gc.fillText("P0", pts[0][0] + 8, pts[0][1] - 8);
        gc.fillText("P1", pts[1][0] + 8, pts[1][1] - 8);
        gc.fillText("P2", pts[2][0] + 8, pts[2][1] - 8);
        gc.fillText("P3", pts[3][0] + 8, pts[3][1] - 8);
    }

    private void drawPoint(GraphicsContext gc, double x, double y, int index) {
        Color color = switch (index) {
            case 0 -> Color.GREEN;
            case 3 -> Color.RED;
            default -> Color.ORANGE;
        };
        gc.setFill(color);
        gc.fillOval(x - R, y - R, R * 2, R * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - R, y - R, R * 2, R * 2);
    }
}
