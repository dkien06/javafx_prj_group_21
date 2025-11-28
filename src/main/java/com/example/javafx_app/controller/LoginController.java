package com.example.javafx_app.controller;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField CCCDField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Label WrongLoginLabel;
    @FXML
    private ChoiceBox<String> accountType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Nạp các loại tài khoản vào ChoiceBox (hiển thị bằng tên tiếng Việt từ toString)
        for (ACCOUNT_TYPE type : ACCOUNT_TYPE.values()) {
            accountType.getItems().add(type.toString());
        }
        // Chọn mặc định là CHECKING
        accountType.setValue(ACCOUNT_TYPE.CHECKING.toString());
    }

    @FXML
    void DangNhap(ActionEvent event) {
        String CCCD = CCCDField.getText();
        String password = PasswordField.getText();
        String selectedLabel = accountType.getValue(); // Lấy giá trị chuỗi người dùng chọn
        if(password=="") {
            WrongLoginLabel.setText("Bạn chưa nhập mật khẩu");
            return ;
        }
        // 1. Chuyển đổi từ String (Label) ngược lại thành Enum ACCOUNT_TYPE
        ACCOUNT_TYPE selectedType = ACCOUNT_TYPE.CHECKING; // Mặc định
        for (ACCOUNT_TYPE type : ACCOUNT_TYPE.values()) {
            if (type.toString().equals(selectedLabel)) {
                selectedType = type;
                break;
            }
        }
        if(AccountManager.getInstance().logIn(CCCD, password, selectedType)) {
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                    AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
        }
        else WrongLoginLabel.setText("Tài khoản hoặc mật khẩu bị sai");
    }

    @FXML
    void TaoTaiKhoanMoi(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "SignUpScene/signup_3option_scene.fxml");
    }
}