package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.List;

public class SignUpExisting1 {
    @FXML
    private TextField CCCDField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Text PasswordLog, CCCDLog;
    @FXML
    public void NextToForm2(ActionEvent event) {
        boolean isExist = true;
        String CCCD = CCCDField.getText();
        String Password = PasswordField.getText();
        BankManager.SignUpInformationState CCCDState = BankManager.checkSignUpCitizenID(CCCD) ;
        if(CCCDState.equals(BankManager.SignUpInformationState.EMPTY)){
            CCCDLog.setText("Bạn chưa nhập CCCD");
            isExist = false;
        }
        else if(CCCDState.equals(BankManager.SignUpInformationState.WRONG_FORM)){
            CCCDLog.setText("CCCD bị sai định dạng");
            isExist = false;
        }
        if(Password.equals("")){
            PasswordLog.setText("Bạn chưa nhập mật khẩu");
        }
        else {
            List<Account> accounts = AccountManager.getInstance().findAccountFromCitizenID(CCCD);
            if(accounts.size()==3) PasswordLog.setText("Bạn đã có só lượng tài khoản tối đa");
            else if(accounts.isEmpty()) PasswordLog.setText("CCCD hoặc mật khẩu không đúng");
        }
        if(isExist)SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "SignUpScene/signup_existing_customer2_scene.fxml");
    }

    @FXML
    public void backTo3SignUpOption(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "/com/example/javafx_app/SignUpScene/signup_3option_scene.fxml");
    }
}
