package com.example.javafx_app;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.config.ExampleUser;// THÊM IMPORT NÀY
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
            // B1: Tải dữ liệu khi ứng dụng bắt đầu
            DataPersistence.loadAllData(); //Fun fact: Nó load data ảo vcl:)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
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

    @Override // Thêm phương thức stop để lưu dữ liệu khi ứng dụng đóng
    public void stop() {
        DataPersistence.saveAllData();
        System.out.println("Ứng dụng đang đóng và dữ liệu đã được lưu.");
    }

    public static void main(String[] args) {launch();}//11/10/2025
}