module com.example.javafx_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;

    opens com.example.javafx_app.controller.SignUp to javafx.fxml;
    opens com.example.javafx_app to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app;
    opens com.example.javafx_app.controller to javafx.fxml;
    opens com.example.javafx_app.controller.setting to javafx.fxml;
    opens com.example.javafx_app.controller.Transaction to javafx.fxml;
    opens com.example.javafx_app.controller.Bill to javafx.fxml;
    exports com.example.javafx_app.manager;
    opens com.example.javafx_app.manager to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app.object;
    opens com.example.javafx_app.object to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app.util;
    opens com.example.javafx_app.util to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app.object.Account;
    opens com.example.javafx_app.object.Account to javafx.fxml, javafx.graphics;
    exports com.example.javafx_app.object.User;
    opens com.example.javafx_app.object.User to javafx.fxml, javafx.graphics;
    opens com.example.javafx_app.controller.homeScene to javafx.fxml;
    opens com.example.javafx_app.controller.saving to javafx.fxml;
}