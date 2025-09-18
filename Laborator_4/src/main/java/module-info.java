module org.example.laborator_4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.laborator_4 to javafx.fxml;
    exports org.example.laborator_4;
}