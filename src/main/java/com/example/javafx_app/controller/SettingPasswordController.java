package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.BankManager;
import com.example.javafx_app.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

public class SettingPasswordController {

    @FXML
    private PasswordField OldPasswordField;

    @FXML
    private PasswordField NewPasswordField;

    @FXML
    private PasswordField NewPasswordAgainField;

    @FXML
    private Text OldPasswordErrorLog;

    @FXML
    private Text NewPasswordLog;

    @FXML
    private Text NewPasswordAgainLog;

    // Xử lý khi nhấn nút "Quay lại"
    @FXML
    private void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting.fxml");
    }

    // Xử lý khi nhấn nút "Hoàn thành"
    @FXML
    private void HoanThanh(ActionEvent event) {
        // Reset lỗi
        OldPasswordErrorLog.setText("");
        NewPasswordLog.setText("");
        NewPasswordAgainLog.setText("");
        Account currentAccount = BankManager.Settings.getCurrentAccount() ;
        String oldPass = OldPasswordField.getText().trim();
        String newPass = NewPasswordField.getText().trim();
        String newPassAgain = NewPasswordAgainField.getText().trim();
        if(!oldPass.equals(currentAccount.getPassword())) {
            OldPasswordErrorLog.setText("Mật khẩu cũ không đúng");
            return ;
        }
        String newPassLogText = BankManager.VerifySignUpInformation.validatePassword(newPass) ;
        if(!(newPassLogText==null)) {
            NewPasswordLog.setText(newPassLogText);
            return;
        }
        if(!newPassAgain.equals(newPass)) {
            NewPasswordAgainLog.setText("Mật khẩu nhập lại không đúng");
            return ;
        }
        // check het cac loi
        currentAccount.setPassword(newPass);
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
    }
}
