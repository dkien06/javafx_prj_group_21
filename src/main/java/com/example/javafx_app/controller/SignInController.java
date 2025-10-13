package com.example.javafx_app.controller;

import com.example.javafx_app.BankManager;
import com.example.javafx_app.BankManager.SignUpInformationState;
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
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    void returnToLoginScene(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
    }
    @FXML
    private TextField fullNameField;
    @FXML
    private Text fullNameErrorLog;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private Text dateOfBirthErrorLog;
    @FXML
    private ChoiceBox<String> genderChoiceBox;
    private final String[] genderType = {"MALE","FEMALE","OTHER"};
    @FXML
    private Text genderErrorLog;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Text phoneNumberErrorLog;
    @FXML
    private TextField emailField;
    @FXML
    private Text emailErrorLog;
    @FXML
    private TextField citizenIDField;
    @FXML
    private Text citizenIDErrorLog;
    //Biến để lưu trữ thông tin đăng kí(Tạm thời như này đi)
    static UserInfo signInUserInfo = new UserInfo();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderChoiceBox.getItems().addAll(genderType);
    }
    @FXML
    void TiepTuc(ActionEvent event) {
        String fullName = fullNameField.getText();
        LocalDate dateOfBirth = dateOfBirthDatePicker.getValue();
        String gender = genderChoiceBox.getValue();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String citizenID = citizenIDField.getText();

        //Lấy mảng check thông tin đã đúng chưa
        Map<String, SignUpInformationState> informationStateMap = BankManager.CheckAllSignUpInfo(fullName,dateOfBirth,gender,email,phoneNumber,citizenID);

        //Log lỗi họ tên (Dùng switch case cho ngầu thôi, if else cũng đc:)))
        boolean isFullNameValid = false;
        switch (informationStateMap.get("fullName")){
            case EMPTY:
                fullNameErrorLog.setText("Vui lòng nhập họ tên");
                break;
            case RIGHT:
                isFullNameValid = true;
                fullNameErrorLog.setText("");
                break;
        }

        //Log lỗi ngày sinh
        boolean isDateOfBirthValid = false;
        switch (informationStateMap.get("dateOfBirth")){
            case EMPTY:
                dateOfBirthErrorLog.setText("Vui lòng điền ngày sinh");
                break;
            case RIGHT:
                isDateOfBirthValid = true;
                dateOfBirthErrorLog.setText("");
                break;
        }

        //Log lỗi giới tính
        boolean isGenderValid = false;
        switch (informationStateMap.get("gender")){
            case EMPTY:
                genderErrorLog.setText("Vui lòng điền giới tính");
                break;
            case RIGHT:
                isGenderValid = true;
                genderErrorLog.setText("");
                break;
        }

        //Log lỗi email
        boolean isGmailValid = false;
        switch (informationStateMap.get("email")){
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

        //Log lỗi số điện thoại
        boolean isPhoneNumberValid = false;
        switch (informationStateMap.get("phoneNumber")){
            case EMPTY:
                phoneNumberErrorLog.setText("Vui lòng nhập số điện thoại");
                break;
            case WRONG_FORM:
                phoneNumberErrorLog.setText("Số điện thoại của bạn không hợp lệ");
                break;
            case WRONG_SIZE:
                phoneNumberErrorLog.setText("Số điện thoại của bạn phải đúng 10 chữ số");
                break;
            case RIGHT:
                isPhoneNumberValid = true;
                phoneNumberErrorLog.setText("");
                break;
        }

        //Log lỗi CCCD
        boolean isCitizenIDValid = false;
        switch (informationStateMap.get("citizenID")){
            case EMPTY:
                citizenIDErrorLog.setText("Vui lòng nhập số CCCD");
                break;
            case WRONG_FORM:
                citizenIDErrorLog.setText("Số CCCD không hợp lệ");
                break;
            case RIGHT:
                isCitizenIDValid = true;
                citizenIDErrorLog.setText("");
                break;
        }

        if(isFullNameValid && isDateOfBirthValid && isGenderValid && isGmailValid && isPhoneNumberValid && isCitizenIDValid){
            signInUserInfo.setFullName(fullNameField.getText());
            signInUserInfo.setDateOfBirth(dateOfBirthDatePicker.getValue());
            signInUserInfo.setGender(UserInfo.stringToGender(genderChoiceBox.getValue()));
            signInUserInfo.setPhoneNumber(phoneNumberField.getText());
            signInUserInfo.setEmail(emailField.getText());
            signInUserInfo.setCitizenID(citizenIDField.getText());

            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
        }
    }
}
