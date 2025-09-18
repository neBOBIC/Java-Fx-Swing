package org.example.laborator_4;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController {
    @FXML
    private ListView<String> list;
    ObservableList<String> items = FXCollections.observableArrayList(
            "Paris", "Berlin", "Chicago", "Dallas", "Chisinau", "Orhei", "Dortmund", "Helsinki", "Praga", "Moscova", "Bucuresti"
    );

    @FXML
    private Label label;

    @FXML
    public void initialize(){
        list.setPrefHeight(170);
        list.setItems(items);

        list.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                label.setText("Selectat: " + newVal);
            }
        });
    }
}