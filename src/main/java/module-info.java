module com.example.callculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.callculator to javafx.fxml;
    exports com.example.callculator;
}