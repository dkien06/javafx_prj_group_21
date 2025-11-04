package com.example.javafx_app;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.util.DialogUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class BankApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ExampleUser.init();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login_scene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("21stBank");
        stage.setResizable(false);
        stage.show();
        int exitCode = DialogUtils.dialogButton(
                "Câu hỏi quan trọng!",
                "Mày bị gay, đúng không?",
                "Trả lời cho thật vào:))",
                "Có, tao bị gay", "Không, tao bị gay","Chắc chắn, tao bị gay","Thỉnh thoảng tao bị gay");
        if(exitCode >= 0){
            DialogUtils.dialog(Alert.AlertType.CONFIRMATION,"","Mày bị gay","");
        }
        else DialogUtils.dialog(Alert.AlertType.ERROR,"","Mày giấu gay à?","");
    }
    public static void main(String[] args) {
        launch();
    }

}
