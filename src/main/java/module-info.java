module com.example.javafx_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.javafx_app to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app;
    opens com.example.javafx_app.controller to javafx.fxml;
    opens com.example.javafx_app.controller.setting to javafx.fxml;
    opens com.example.javafx_app.controller.Transaction to javafx.fxml;
    exports com.example.javafx_app.Manager;
    opens com.example.javafx_app.Manager to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app.User;
    opens com.example.javafx_app.User to javafx.fxml, javafx.graphics,java.base;
    exports com.example.javafx_app.Account;
    opens com.example.javafx_app.Account to javafx.fxml, javafx.graphics;
    opens com.example.javafx_app.controller.SignUp to javafx.fxml;


}