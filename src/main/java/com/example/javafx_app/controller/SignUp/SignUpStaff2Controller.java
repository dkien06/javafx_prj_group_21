package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.User.Staff;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class SignUpStaff2Controller {

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private Text PasswordErrorLog;

    @FXML
    private PasswordField PasswordAgainTextField;

    @FXML
    private Text PasswordAgainErrorLog;

    @FXML
    private TextField PINTextField;

    @FXML
    private Text PINErrorLog;

    @FXML
    private Button complete_btn;

    @FXML
    private Button btn_return;

    @FXML
    private void Complete(ActionEvent event) {
        String password = PasswordTextField.getText();
        String passwordAgain = PasswordAgainTextField.getText();
        String pin = PINTextField.getText();
        //kiem tra tai khoan
        BankManager.PasswordState passwordState = BankManager.checkNewPassword(password),
                passwordAgainState = BankManager.checkPasswordAgain(password, passwordAgain);
        BankManager.PINState pinState = BankManager.checkNewPIN(pin);
        PasswordErrorLog.setText(passwordState.getLabel());
        PasswordAgainErrorLog.setText(passwordAgainState.getLabel());
        PINErrorLog.setText(pinState.getLabel());
        if (passwordState == BankManager.PasswordState.RIGHT && passwordAgainState == BankManager.PasswordState.RIGHT &&
                pinState == BankManager.PINState.RIGHT) {
            UserManager.getInstance().addUser(UserManager.getInstance().getSignUpUser());
            AccountManager.getInstance().addAccountForStaff((Staff) UserManager.getInstance().getSignUpUser(),password,pin);
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "login_scene.fxml");
        }
    }
    @FXML
    private void returnToSignUpStaff1(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"SignUpScene/signup_staff1_scene.fxml");
    }
}
