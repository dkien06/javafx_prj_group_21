package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.User.User;
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
    private boolean checkMainAcccount(String cccd, String password){
        User user = UserManager.getInstance().findUserByCitizenID(cccd);
        if(user == null){ return  false; }
        return true;
    }
    @FXML
    public void NextToForm2(ActionEvent event) {


    }

    @FXML
    public void backTo3SignUpOption(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "/com/example/javafx_app/SignUpScene/signup_3option_scene.fxml");
    }
}
