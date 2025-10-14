package com.example.javafx_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.stage.Stage;

import java.io.IOException;

public class BankApplication extends Application {
    //Test thôi
    public static Account susAccount = new Account(
            "Tran Thi B",
            "010203004953",
            "83864953",
            "TranThiB@1975",
            1000000,
            "VND",
            "123456");
    public static Account susAccount1 = new Account(
            "Nguyen Van A",
            "010203008386",
            "49538386",
            "NguyenVanA#1970",
            2000000,
            "VND",
            "010170");
    @Override
    public void start(Stage stage) throws IOException {
        //Test thôi, bỏ đi cũng éo sao
        AccountManager.getInstance().getAccountList().add(susAccount);
        AccountManager.getInstance().getAccountList().add(susAccount1);
        AccountManager.getInstance().getAccountList().add(AccountManager.getInstance().getCurrentAccount());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home_scene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("21stBank");
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
