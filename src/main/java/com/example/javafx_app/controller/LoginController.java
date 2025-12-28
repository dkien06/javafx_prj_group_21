package com.example.javafx_app.controller;

import com.example.javafx_app.DataPersistence;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoginController implements Initializable {

    @FXML
    private TextField CCCDField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Label WrongLoginLabel;
    @FXML
    private ChoiceBox<String> accountType;
    @FXML private CheckBox RememberPass ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Nạp các loại tài khoản vào ChoiceBox (hiển thị bằng tên tiếng Việt từ toString)
        if(!DataPersistence.savedAccountID.isEmpty()){
            CCCDField.setText(DataPersistence.savedAccountID);
            RememberPass.setSelected(true);
        }
        if(!DataPersistence.savedPassword.isEmpty()){
            PasswordField.setText(DataPersistence.savedPassword);
        }
        for (ACCOUNT_TYPE type : ACCOUNT_TYPE.values()) {
            accountType.getItems().add(type.toString());
        }
        if(!DataPersistence.accountType.isEmpty()){
            accountType.setValue(DataPersistence.accountType);
        }

        // Chọn mặc định là CHECKING
        else accountType.setValue(ACCOUNT_TYPE.CHECKING.toString());
    }

    @FXML
    void DangNhap(ActionEvent event) {
        String CCCD = CCCDField.getText();
        String password = PasswordField.getText();
        String selectedLabel = accountType.getValue(); // Lấy giá trị chuỗi người dùng chọn
        if(Objects.equals(password, "")) {
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
            if(RememberPass.isSelected()) {
                DataPersistence.savedAccountID = CCCDField.getText();
                DataPersistence.savedPassword = PasswordField.getText();
                DataPersistence.accountType= selectedLabel ;
                System.out.println("Check");
            }
            else {
                DataPersistence.savedAccountID = "" ;
                DataPersistence.savedPassword = "" ;
                DataPersistence.accountType = selectedLabel;
            }
            SceneUtils.switchScene(mainStage,
                    AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
        }
        else WrongLoginLabel.setText("Tài khoản hoặc mật khẩu bị sai");
    }

    @FXML
    void TaoTaiKhoanMoi(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "SignUpScene/signup_3option_scene.fxml");
    }
    @FXML
    public void QuenMatKhau(MouseEvent event) {
        SceneUtils.switchScene(mainStage,"forget_password.fxml");
    }
}