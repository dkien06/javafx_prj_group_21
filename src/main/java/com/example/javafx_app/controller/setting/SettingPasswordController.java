package com.example.javafx_app.controller.setting;

import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import static com.example.javafx_app.config.Constant.mainStage;

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
        SceneUtils.switchScene(mainStage,"setting/setting.fxml");
    }

    // Xử lý khi nhấn nút "Hoàn thành"
    @FXML
    private void HoanThanh(ActionEvent event) {
        // Reset lỗi
        OldPasswordErrorLog.setText("");
        NewPasswordLog.setText("");
        NewPasswordAgainLog.setText("");
        Account currentAccount = AccountManager.getInstance().getCurrentAccount();
        String oldPass = OldPasswordField.getText();
        String newPass = NewPasswordField.getText();
        String newPassAgain = NewPasswordAgainField.getText();
        boolean isOldPasswordMatched = false;
        boolean isNewPasswordValid = false;
        boolean isNewPasswordAgainValid = false;
        if(oldPass.isEmpty())OldPasswordErrorLog.setText("Vui lòng nhập mật khẩu hiện tại của bạn");
        else if(!currentAccount.isPasswordMatched(oldPass)) OldPasswordErrorLog.setText("Mật khẩu cũ không đúng");
        else isOldPasswordMatched = true;
        if(isOldPasswordMatched){
            switch (BankManager.checkNewPassword(newPass)){
                case EMPTY:
                    NewPasswordLog.setText("Vui lòng nhận mật khẩu");
                    break;
                case WEAK:
                    NewPasswordLog.setText("Mật khẩu yếu: cần ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.");
                    break;
                case RIGHT:
                    NewPasswordLog.setText("");
                    isNewPasswordValid = true;
                    break;
                default:
                    break;
            }
            if(isNewPasswordValid){
                switch (BankManager.checkPasswordAgain(newPass,newPassAgain)){
                    case EMPTY:
                        NewPasswordAgainLog.setText("Vui lòng nhập lại mật khẩu.");
                        break;
                    case NOT_MATCHED:
                        NewPasswordAgainLog.setText("Mật khẩu nập lại không khớp.");
                        break;
                    case RIGHT:
                        NewPasswordAgainLog.setText("");
                        isNewPasswordAgainValid = true;
                        break;
                    default:
                        break;
                }
            }
            else NewPasswordAgainField.setText("");
        }
        else{
            NewPasswordLog.setText("");
        }
        // check het cac loi
        if(isNewPasswordAgainValid){
            currentAccount.setPassword(newPass);
            SceneUtils.switchScene(mainStage,
                    AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
        }
    }
}
