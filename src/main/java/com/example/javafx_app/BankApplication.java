package com.example.javafx_app;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.exception.NonExistedPathException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.stage.Stage;
import java.io.IOException;

public class BankApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            Constant.mainStage = stage;
            ExampleUser.init();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScenes/checking_account_home_scene.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("21stBank");
            stage.setResizable(false);
            stage.show();
        }
        catch (IllegalStateException e){
            NonExistedPathException.throwException();
        }
    }
    public static void main(String[] args) {launch();}//11/10/2025
}
