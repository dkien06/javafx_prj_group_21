package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import static com.example.javafx_app.config.Constant.mainStage;

public class SignUpNew2 {

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
                passwordAgainState = BankManager.checkPasswordAgain(password,passwordAgain);
        BankManager.PINState pinState = BankManager.checkNewPIN(pin) ;
        PasswordErrorLog.setText(passwordState.getLabel());
        PasswordAgainErrorLog.setText(passwordAgainState.getLabel());
        PINErrorLog.setText(pinState.getLabel());
        if(passwordState== BankManager.PasswordState.RIGHT&&passwordAgainState== BankManager.PasswordState.RIGHT&&
                pinState == BankManager.PINState.RIGHT){
            UserManager.getInstance().addUser(UserManager.getInstance().getSignUpUser());
            AccountManager.getInstance().addAccountForCustomer((Customer) UserManager.getInstance().getSignUpUser(),
                    ACCOUNT_TYPE.CHECKING.toString(), password,pin);
            SceneUtils.switchScene(mainStage,"login_scene.fxml");
        }
    }

    @FXML
    private void returnToSignUpNew1(ActionEvent event) {
        SceneUtils.switchScene(mainStage,
                "SignUpScene/signup_new_customer1_scene.fxml");
    }
}
