package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HelloController {

    @FXML
    private Label helloName;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtNume;

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setNameBtn() {
        String input = txtNume.getText();
        helloName.setText("Salutare " + input + "!");
    }

    @FXML
    protected void handleOpen() {
        if (primaryStage == null) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Deschide fișier");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fișiere text", "*.txt"),
                new FileChooser.ExtensionFilter("Toate fișierele", "*.*")
        );

        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                String content = Files.readString(file.toPath());
                welcomeText.setText(content);
            } catch (IOException e) {
                welcomeText.setText("Eroare la citirea fișierului!");
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void handleClose() {
        welcomeText.setText("Aplicația se închide...");
        System.exit(0);
    }

    @FXML
    protected void handleAbout() {
        welcomeText.setText("Aceasta este o aplicație JavaFX cu MenuBar.");
    }
}
