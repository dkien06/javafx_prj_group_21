module com.example.javafx_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.javafx_app to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app;
    opens com.example.javafx_app.controller to javafx.fxml;
    opens com.example.javafx_app.controller.setting to javafx.fxml;
    opens com.example.javafx_app.controller.Transaction to javafx.fxml;
}