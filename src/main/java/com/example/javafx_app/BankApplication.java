package com.example.javafx_app;

import com.example.javafx_app.Account.Account;
import com.example.javafx_app.Manager.AccountManager;
import com.example.javafx_app.Manager.UserManager;
import com.example.javafx_app.User.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankApplication extends Application {
    //Tài khoản ảo (xóa cũng được):)
    public static User susUser = new User(
            "Nguyễn Văn A",
            LocalDate.of(1970,1,1),
            User.stringToGender("MALE"),
            "0123456789",
            "NguyenVanA@gmail.com",
            "010203008386"
    );
    public  static User susUser1 = new User(
            "Trần Thị B",
            LocalDate.of(1975,4,30),
            User.stringToGender("FEMALE"),
            "0987654321",
            "TranThiB@vnu.edu.vn",
            "010203004953"
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

        UserManager.getInstance().addUser(susUser);
        UserManager.getInstance().addUser(susUser1);
        UserManager.getInstance().addUser(susUser2);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
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
