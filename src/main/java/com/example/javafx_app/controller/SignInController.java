package com.example.javafx_app.controller;

import com.example.javafx_app.BankManager.VerifySignUpInformation;
import com.example.javafx_app.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    void returnToLoginScene(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
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
    /*@FXML
    private TextField email;*/
    @FXML
    private TextField citizenID;
    @FXML
    private Text citizenIDErrorLog;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(genderType);
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
        else dateOfBirthErrorLog.setText("");
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
        if(isFullNameValid && isDateOfBirthValid && isGenderValid && isPhoneNumberValid && isCitizenNumberValid){
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
        }
    }
}
