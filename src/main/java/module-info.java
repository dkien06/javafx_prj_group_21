module com.example.javafx_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javafx_app to javafx.fxml;
    exports com.example.javafx_app;
    opens com.example.javafx_app.controller to javafx.fxml;
}