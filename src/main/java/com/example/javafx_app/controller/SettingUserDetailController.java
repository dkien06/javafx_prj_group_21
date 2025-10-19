package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.BankManager;
import com.example.javafx_app.SceneUtils;
import com.example.javafx_app.UserInfo;
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
    private Text SetingPhoneNumberLog ,SetingEmailLog ;
    private UserInfo currentUser = BankManager.Settings.getCurrentUser();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentUser == null) {
            System.out.println("No current user");
        }
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
        Boolean isValidPhoneNumber=true,isValidEmail=true;
        if(!PhoneNumberTextField.getText().equals(currentUser.getPhoneNumber())){
             isValidPhoneNumber= BankManager.Settings.changePhone(PhoneNumberTextField.getText());
            if(!isValidPhoneNumber){
                SetingPhoneNumberLog.setText("Số điện thoại đã tồn tại");
            }
            // them logic phan kiem tra so dien thoai hop le 
        }
        if(!EmailTextField.getText().equals(currentUser.getEmail())){
            isValidEmail = BankManager.Settings.changeEmail(EmailTextField.getText());
            if(!isValidEmail){
                SetingEmailLog.setText("Email này đã tồn tại");
            }
            else{
                BankManager.Settings.changeEmail(EmailTextField.getText());
            }
        }
        if(isValidPhoneNumber && isValidEmail){
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
        }
    }
}
