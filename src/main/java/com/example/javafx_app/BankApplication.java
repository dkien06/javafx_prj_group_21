package com.example.javafx_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class BankApplication extends Application {
    //Tài khoản ảo (xóa cũng được):)
    public static Account susAccount = new Account(
            "Nguyen Van A",
            "010203008386",
            "49538386",
            "NguyenVanA#1970",
            2000000,
            "VND",
            "010170");
    public static User susUser = new User(
            "Nguyễn Văn A",
            LocalDate.of(1970,1,1),
            User.stringToGender("MALE"),
            "0123456789",
            "NguyenVanA@gmail.com",
            "010203008386"
    );
    public static Account susAccount1 = new Account(
            "Tran Thi B",
            "010203004953",
            "83864953",
            "TranThiB@1975",
            1000000,
            "VND",
            "123456");
    public  static User susUser1 = new User(
            "Trần Thị B",
            LocalDate.of(1975,4,30),
            User.stringToGender("FEMALE"),
            "0987654321",
            "TranThiB@vnu.edu.vn",
            "010203004953"
    );
    public static Account susAccount2 = new Account(
            "Ngo Duc C",
            "020406006769",
            "12345678",
            "TaoBiGay%2008",
            500000,
            "VND",
            "112233"
    );
    public static User susUser2 = new User(
            "Ngô Đức C",
            LocalDate.of(2007,3,6),
            User.stringToGender("OTHER"),
            "0135792468",
            "C.ND@sis.hust.edu.vn",
            "020406006769"
    );
    @Override
    public void start(Stage stage) throws IOException {
        //Thêm mấy tài khoản ảo vào thôi:)
        AccountManager.getInstance().getAccountList().add(susAccount);
        AccountManager.getInstance().getAccountList().add(susAccount1);
        AccountManager.getInstance().getAccountList().add(susAccount2);

        UserManager.getInstance().addUser(susUser);
        UserManager.getInstance().addUser(susUser1);
        UserManager.getInstance().addUser(susUser2);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login_scene.fxml"));
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
