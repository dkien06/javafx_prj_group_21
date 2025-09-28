package com.example.javafx_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view1.fxml")));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setTitle("Hello World");
       stage.setResizable(false);
       stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}