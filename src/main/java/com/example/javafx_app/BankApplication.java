package com.example.javafx_app;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.config.ExampleUser;// THÊM IMPORT NÀY
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.stage.Stage;
import java.io.IOException;

public class BankApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Constant.mainStage = stage;

        // B1: Tải dữ liệu khi ứng dụng bắt đầu
        //DataPersistence.loadAllData(); // THÊM LỆNH NÀY (sẽ tự động khởi tạo dữ liệu giả nếu file không tồn tại)
        ExampleUser.init();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScenes/staff_home_scene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("21stBank");
        stage.setResizable(false);
        stage.show();
    }

    @Override // Thêm phương thức stop để lưu dữ liệu khi ứng dụng đóng
    public void stop() {
        //DataPersistence.saveAllData();
        System.out.println("Ứng dụng đang đóng và dữ liệu đã được lưu.");
    }

    public static void main(String[] args) {launch();}//11/10/2025
}