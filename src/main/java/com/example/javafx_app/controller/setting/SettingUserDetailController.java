package com.example.javafx_app.controller.setting;

import com.example.javafx_app.*;
import com.example.javafx_app.Manager.BankManager;
import com.example.javafx_app.Manager.UserManager;
import com.example.javafx_app.User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingUserDetailController implements Initializable {
    @FXML
    private TextField PhoneNumberTextField ,EmailTextField ;
    @FXML
    private Text SettingPhoneNumberLog;
    @FXML
    private Text SettingEmailLog;
    private User currentUser = UserManager.getInstance().getCurrentUser();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentUser == null) {
            System.out.println("No current user");
        }
        assert currentUser != null;
        String currentEmail =  currentUser.getEmail();
        EmailTextField.setText(currentEmail);
        String currentPhoneNumber = currentUser.getPhoneNumber();
        PhoneNumberTextField.setText(currentPhoneNumber);
    }
    @FXML
    public void QuayLai(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting.fxml");
    }
    public void HoanThanh(ActionEvent event){
        boolean isValidPhoneNumber = false;
        switch (BankManager.checkSignUpPhoneNumber(PhoneNumberTextField.getText())){
            case EMPTY:
                SettingPhoneNumberLog.setText("Vui lòng nhập số điện thoại");
                break;
            case EXISTED:
                SettingPhoneNumberLog.setText("Số điện thoại đã tồn tại");
                break;
            case WRONG_FORM:
                SettingPhoneNumberLog.setText("Số điện thoại của bạn không hợp lệ");
                break;
            case WRONG_SIZE:
                SettingPhoneNumberLog.setText("Số điện thoại của bạn phải đúng 10 chữ số");
                break;
            case RIGHT:
                SettingPhoneNumberLog.setText("");
                isValidPhoneNumber = true;
                break;
        }
        boolean isValidEmail = false;
        switch (BankManager.checkSignUpEmail(EmailTextField.getText())){
            case EMPTY:
                SettingEmailLog.setText("Vui lòng nhập email của bạn");
                break;
            case EXISTED:
                SettingEmailLog.setText("Email này đã tồn tại");
                break;
            case WRONG_FORM:
                SettingEmailLog.setText("Email của ban không hợp lệ");
                break;
            case RIGHT:
                SettingEmailLog.setText("");
                isValidEmail = true;
                break;
        }
        if(isValidPhoneNumber && isValidEmail){
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
        }
    }
}
