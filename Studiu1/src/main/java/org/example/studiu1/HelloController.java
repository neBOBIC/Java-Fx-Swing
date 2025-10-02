package org.example.studiu1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.DirectoryChooser;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.File;

public class HelloController {

    @FXML private Label welcomeText;

    // Image Viewer
    @FXML private ImageView imageView;
    @FXML private ListView<String> listView;
    @FXML private Slider zoomSlider;
    @FXML private Button nextBtn, prevBtn, folderBtn;

    private ImageController controller;
    private ObservableList<String> imageNames;

    // Drawing
    @FXML private AnchorPane drawingPane;
    private double lastX, lastY;

    // Animation
    @FXML private AnchorPane animationPane;

    // Login
    @FXML private TextField userField;
    @FXML private PasswordField passField;
    @FXML private Button loginBtn;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        welcomeText.setText("Welcome to Advanced JavaFX App!");

        // --- Image Viewer ---
        controller = new ImageController("images");
        imageView.setImage(controller.getCurrentImage());

        imageNames = FXCollections.observableArrayList();
        for (Image img : controller.getImages()) {
            imageNames.add(img.getUrl().substring(img.getUrl().lastIndexOf("/") + 1));
        }
        listView.setItems(imageNames);

        zoomSlider.valueProperty().addListener((obs, oldVal, newVal) -> imageView.setFitWidth(newVal.doubleValue()));

        nextBtn.setOnAction(e -> updateImage(controller.nextImage()));
        prevBtn.setOnAction(e -> updateImage(controller.prevImage()));
        folderBtn.setOnAction(e -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File folder = chooser.showDialog(imageView.getScene().getWindow());
            if (folder != null) {
                controller = new ImageController(folder.getAbsolutePath());
                imageNames.clear();
                for (Image img : controller.getImages()) {
                    imageNames.add(img.getUrl().substring(img.getUrl().lastIndexOf("/") + 1));
                }
                updateImage(controller.getCurrentImage());
            }
        });

        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            controller.setImageByIndex(index);
            updateImage(controller.getCurrentImage());
        });

        // --- Drawing ---
        drawingPane.setOnMousePressed(e -> {
            lastX = e.getX();
            lastY = e.getY();
        });
        drawingPane.setOnMouseDragged(e -> {
            Line line = new Line(lastX, lastY, e.getX(), e.getY());
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);
            drawingPane.getChildren().add(line);
            lastX = e.getX();
            lastY = e.getY();
        });

        // --- Animation ---
        Circle circle = new Circle(20, Color.RED);
        circle.setCenterY(100);
        animationPane.getChildren().add(circle);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(3), circle);
        tt.setFromX(0);
        tt.setToX(400);
        tt.setCycleCount(TranslateTransition.INDEFINITE);
        tt.setAutoReverse(true);
        tt.play();

        // --- Login ---
        loginBtn.setOnAction(e -> {
            if (userField.getText().equals("student") && passField.getText().equals("1234")) {
                statusLabel.setText("Login successful!");
            } else {
                statusLabel.setText("Wrong credentials");
            }
        });
    }

    private void updateImage(Image img) {
        imageView.setImage(img);
        listView.getSelectionModel().select(controller.getImages().indexOf(img));
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    // Optional: keyboard events
    @FXML
    private void onKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case R -> welcomeText.getScene().getRoot().setStyle("-fx-background-color: #ffaaaa;");
            case G -> welcomeText.getScene().getRoot().setStyle("-fx-background-color: #aaffaa;");
            case B -> welcomeText.getScene().getRoot().setStyle("-fx-background-color: #aaaaff;");
        }
    }
}
