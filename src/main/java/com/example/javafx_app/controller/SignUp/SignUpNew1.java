package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

public class SignUpNew1 {

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

    @FXML
    private Button continue_btn;

    @FXML
    private Button btn_return;

    @FXML
    private Label thong_tin_ca_nhan;

    @FXML
    private void TiepTuc(ActionEvent event) {
        switch (BankManager.checkSignUpFullName(fullNameField.getText())){
            case EMPTY:
                fullNameErrorLog.setText("Vui lòng nhập họ tên đầy đủ");
            case RIGHT:
                fullNameErrorLog.setText("");
        }

        switch (BankManager.checkSignUpDateOfBirth(dateOfBirthDatePicker.getValue())){
            case EMPTY:
                dateOfBirthErrorLog.setText("Vui lòng chọn ngày sinh");
            case RIGHT:
                dateOfBirthErrorLog.setText("");
        }

        switch (BankManager.checkSignUpGender(genderChoiceBox.getValue())){
            case EMPTY:
                genderErrorLog.setText("Vui lòng chọn ngày sinh");
            case RIGHT:
                genderErrorLog.setText("");
        }

        switch (BankManager.checkSignUpEmail(emailField.getText())){
            case EMPTY:
                emailErrorLog.setText("Vui lòng nập email của bạn");
            case WRONG_FORM:
                emailErrorLog.setText("Email không họp lệ");
            case EXISTED:
                emailErrorLog.setText("Email đã tồn tại");
            case RIGHT:
                emailErrorLog.setText("");
        }

        switch (BankManager.checkSignUpPhoneNumber(phoneNumberField.getText())){
            case EMPTY:
                phoneNumberErrorLog.setText("Vui lòng nhập số điện thoại của bạn");
            case WRONG_FORM:
                phoneNumberErrorLog.setText("Số điện thoại của bạn không hợp lệ");
            case WRONG_SIZE:
                phoneNumberErrorLog.setText("Số điện thoại của bạn phải có 10 chữ số");
            case EXISTED:
                phoneNumberErrorLog.setText("Số điện thoại đã tồn tại");
            case RIGHT:
                phoneNumberErrorLog.setText("");
        }

        switch (BankManager.checkSignUpCitizenID(citizenIDField.getText())){
            //Từ từ đã
        }
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "SignUpScene/signup_new_customer2_scene.fxml");
    }

    @FXML
    private void returnToLoginScene(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "SignUpScene/signup_3option_scene.fxml");
    }
}
