package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.GENDER;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class SignUpNew1 implements Initializable {

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ... (logic lấy existingAccount)
        fullNameErrorLog.setText("");
        dateOfBirthErrorLog.setText("");
        genderErrorLog.setText("");
        phoneNumberErrorLog.setText("");
        emailErrorLog.setText("");
        citizenIDErrorLog.setText("");
        genderChoiceBox.getItems().addAll("Male", "Female", "Other");
    }
    @FXML
    private void TiepTuc(ActionEvent event) {
        String fullName = fullNameField.getText();
        LocalDate dateOfBirth = dateOfBirthDatePicker.getValue();
        String gender = genderChoiceBox.getValue();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();
        String citizenID = citizenIDField.getText();
        Map<String, BankManager.SignUpInformationState> infoStates = new HashMap<>();
        infoStates= BankManager.CheckAllSignUpInfo(fullName,dateOfBirth,gender,email,phoneNumber,citizenID) ;
        fullNameErrorLog.setText(BankManager.SignUpInformationState.
                getErrorMessageForFullName(infoStates.get("fullName")));
        dateOfBirthErrorLog.setText(BankManager.SignUpInformationState.
                getErrorMessageForDateOfBirth(infoStates.get("dateOfBirth")));
        genderErrorLog.setText(BankManager.SignUpInformationState.
                getErrorMessageForGender(infoStates.get("gender")));
        phoneNumberErrorLog.setText(BankManager.SignUpInformationState.
                getErrorMessageForPhoneNumber(infoStates.get("phoneNumber")));
        emailErrorLog.setText(BankManager.SignUpInformationState.
                getErrorMessageForEmail(infoStates.get("email")));
        citizenIDErrorLog.setText(BankManager.SignUpInformationState.
                getErrorMessageForCitizenID(infoStates.get("citizenID")));
        boolean isAccepted = true;
        System.out.println(infoStates.toString());
        for(BankManager.SignUpInformationState state : infoStates.values()){
            if(state!=BankManager.SignUpInformationState.RIGHT) isAccepted = false;
        }
        if(isAccepted){
            GENDER Gender = UserManager.stringToGender(gender);
            UserManager.getInstance().setSignUpUser(new Customer(fullName,dateOfBirth,Gender,
                    phoneNumber,email,citizenID));
            SceneUtils.switchScene(mainStage,
                    "SignUpScene/signup_new_customer2_scene.fxml");
            System.out.println(UserManager.getInstance().getSignUpUser().getFullName());
        }
    }

    @FXML
    private void returnToLoginScene(ActionEvent event) {
        SceneUtils.switchScene(mainStage,
                "SignUpScene/signup_3option_scene.fxml");
    }
}
