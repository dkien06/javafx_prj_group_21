package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpExisting2 implements Initializable {

    @FXML
    private ChoiceBox<String> account_type;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ... (logic lấy existingAccount)

        PasswordErrorLog.setText("");
        PasswordAgainErrorLog.setText("");
        PINErrorLog.setText("");
        Customer currentCustomer = (Customer) UserManager.getInstance().getCurrentUser() ;
        if(!AccountManager.getInstance().isExistingSavingAccount(currentCustomer)){
            account_type.getItems().add(ACCOUNT_TYPE.SAVING.toString());
        }
        if(!AccountManager.getInstance().isExistLoanAccount(currentCustomer)){
            account_type.getItems().add(ACCOUNT_TYPE.LOAN.toString());
        }
        account_type.setValue(account_type.getItems().getFirst());
    }
    @FXML
    private void HoanThanh(ActionEvent event) {
        String accountType = account_type.getValue() ;
        String password = PasswordTextField.getText();
        String passwordAgain = PasswordAgainTextField.getText();
        String pin = PINTextField.getText();
        //kiem tra tai khoan
        BankManager.PasswordState passwordState = BankManager.checkNewPassword(password),
                                  passwordAgainState = BankManager.checkPasswordAgain(password,passwordAgain);
        BankManager.PINState pinState = BankManager.checkNewPIN(pin) ;
        System.out.println(passwordState+" "+passwordAgainState+" "+pinState+" "+passwordAgain+" "+password);
        PasswordErrorLog.setText(passwordState.getLabel());
        PasswordAgainErrorLog.setText(passwordAgainState.getLabel());
        PINErrorLog.setText(pinState.getLabel());
        if(passwordState== BankManager.PasswordState.RIGHT&&passwordAgainState== BankManager.PasswordState.RIGHT&&
            pinState == BankManager.PINState.RIGHT){
            AccountManager.getInstance().addAccountForCustomer((Customer) UserManager.getInstance().getCurrentUser(),accountType,
            password,pin);
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
        }

    }

    @FXML
    private void returnToForm1(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"SignUpScene/signup_existing_customer1_scene.fxml");
    }
}
