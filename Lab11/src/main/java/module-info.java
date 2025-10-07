module org.example.lab11 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab11 to javafx.fxml;
    exports org.example.lab11;
}