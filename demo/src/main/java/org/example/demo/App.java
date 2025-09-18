package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 500);

        HelloController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Aplicatie JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
