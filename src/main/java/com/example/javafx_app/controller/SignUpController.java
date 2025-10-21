package com.example.javafx_app.controller;

import com.example.javafx_app.BankManager;
import com.example.javafx_app.BankManager.VerifySignUpInformation;
import com.example.javafx_app.SceneUtils;
import com.example.javafx_app.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    void returnToLoginScene(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
        BankManager.ResetNewUserAndAcc();
    }
    @FXML
    private TextField fullName;
    @FXML
    private Text fullNameErrorLog;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private Text dateOfBirthErrorLog;
    @FXML
    private ChoiceBox<String> gender;
    String[] genderType = {"MALE","FEMALE","OTHER"};
    @FXML
    private Text genderErrorLog;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Text phoneNumberErrorLog;
    @FXML
    private TextField email;
    @FXML
    private Text emailErrorLog;
    @FXML
    private TextField citizenID;
    @FXML
    private Text citizenIDErrorLog;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gender.getItems().addAll(genderType);
        // khởi taọ lại giá trị User
        if(BankManager.newUser!=null){
            fullName.setText(BankManager.newUser.getFullName());
            dateOfBirth.setValue(BankManager.newUser.getDateOfBirth());
            phoneNumber.setText(BankManager.newUser.getPhoneNumber());
            email.setText(BankManager.newUser.getEmail());
            citizenID.setText(BankManager.newUser.getCitizenID());
            if(BankManager.newUser.getGender()==UserInfo.GENDER.MALE){ gender.setValue("MALE");}
            else if(BankManager.newUser.getGender()==UserInfo.GENDER.FEMALE){ gender.setValue("FEMALE");}
            else{ gender.setValue("OTHER");}
        }
        // Đặt tất cả text là rỗng
        phoneNumberErrorLog.setText("");
        emailErrorLog.setText("");
        citizenIDErrorLog.setText("");
        genderErrorLog.setText("");
        dateOfBirthErrorLog.setText("");
        fullNameErrorLog.setText("");
    }
    @FXML
    void TiepTuc(ActionEvent event){
        //Check họ tên
        boolean isFullNameValid = VerifySignUpInformation.isFullNameValid(fullName.getText());
        if(!isFullNameValid){
            fullNameErrorLog.setText("Vui lòng nhập họ tên");
        }
        else fullNameErrorLog.setText("");
        //Check ngày sinh
        boolean isDateOfBirthValid = VerifySignUpInformation.isDateOfBirthValid(dateOfBirth.getValue());
        if(!isDateOfBirthValid){
            dateOfBirthErrorLog.setText("Vui lòng điền ngày sinh");
        }
        else dateOfBirthErrorLog.setText("");
        //Check giới tính
        boolean isGenderValid = VerifySignUpInformation.isGenderValid(gender.getValue());
        if(!isGenderValid){
            genderErrorLog.setText("Vui lòng điền giới tính");
        }
        else genderErrorLog.setText("");
        //Check gmail
        boolean isGmailValid = false;
        VerifySignUpInformation.EmailState gmailState = VerifySignUpInformation.isGmailValid(email.getText());
        switch (gmailState){
            case EMPTY:
                emailErrorLog.setText("Vui lòng nhập email của bạn");
                break;
            case WRONG_FORM:
                emailErrorLog.setText("Email của bạn không hợp lệ");
                break;
            case RIGHT:
                isGmailValid = true;
                emailErrorLog.setText("");
                break;
        }
        if(isGmailValid){
            if(VerifySignUpInformation.isEmailExisted(email.getText())){
                emailErrorLog.setText("Email này đã tồn tại");
                isGmailValid = false;
            }
        }
        //Check số điện thoại
        boolean isPhoneNumberValid = false;
        VerifySignUpInformation.PhoneNumberState phoneNumberState = VerifySignUpInformation.isPhoneNumberVaid(phoneNumber.getText());
        switch (phoneNumberState){
            case EMPTY:
                phoneNumberErrorLog.setText("Vui lòng nhập số điện thoại");
                break;
            case WRONG_SIZE:
                phoneNumberErrorLog.setText("Số điện thoại của bạn phải đúng 10 chữ số");
                break;
            case WRONG_FORM:
                phoneNumberErrorLog.setText("Số điện thoại của bạn không hợp lệ");
                break;
            case RIGHT:
                isPhoneNumberValid = true;
                phoneNumberErrorLog.setText("");
                break;
        }
        if(isPhoneNumberValid&&VerifySignUpInformation.isPhoneNumberExisted(phoneNumber.getText())){
            phoneNumberErrorLog.setText("Số điện thoại này đã tồn tại");
            isPhoneNumberValid = false;
        }
        //Check số CCCD
        boolean isCitizenNumberValid = false;
        VerifySignUpInformation.CitizenIDState citizenIDState = VerifySignUpInformation.isCitizenIDVaid(citizenID.getText());
        switch (citizenIDState){
            case EMPTY:
                citizenIDErrorLog.setText("Vui lòng nhập số CCCD");
                break;
            case WRONG_FORM:
                citizenIDErrorLog.setText("Số CCCD không hợp lệ");
                break;
            case RIGHT:
                isCitizenNumberValid = true;
                citizenIDErrorLog.setText("");
                break;
        }
        if(isCitizenNumberValid&&VerifySignUpInformation.isCitizenIDExisted(citizenID.getText())){
            isCitizenNumberValid = false;
            citizenIDErrorLog.setText("CCCD này đã tồn tại");
        }
        if(isFullNameValid && isDateOfBirthValid && isGenderValid && isGmailValid && isPhoneNumberValid
                && isCitizenNumberValid){
            UserInfo.GENDER Gender ;
            // Khoi tao gia tri cho new user
            if(gender.getValue().equals("MALE")){ Gender = UserInfo.GENDER.MALE; }
            else if(gender.getValue().equals("FEMALE")){ Gender = UserInfo.GENDER.FEMALE; }
            else if(gender.getValue().equals("OTHER")){ Gender = UserInfo.GENDER.OTHER; }
            else Gender = UserInfo.GENDER.OTHER;
            BankManager.newUser = new UserInfo(fullName.getText(),dateOfBirth.getValue(),Gender,
                    phoneNumber.getText(),email.getText(),citizenID.getText()) ;
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"signup_scene2.fxml");
        }
    }
}
