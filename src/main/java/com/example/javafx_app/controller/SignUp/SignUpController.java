package com.example.javafx_app.controller.SignUp;

import com.example.javafx_app.Manager.BankManager;
import com.example.javafx_app.Manager.BankManager.SignUpInformationState;
import com.example.javafx_app.SceneUtils;
import com.example.javafx_app.User.User;
import com.example.javafx_app.Manager.UserManager;
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

public class SignUpController implements Initializable {
    @FXML
    void returnToLoginScene(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"login_scene.fxml");
        UserManager.getInstance().setCurrentUser(null);
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        genderChoiceBox.getItems().addAll(genderType);
        // khởi taọ lại giá trị User
        if(UserManager.getInstance().getCurrentUser() !=null){
            fullNameField.setText(UserManager.getInstance().getCurrentUser().getFullName());
            dateOfBirthDatePicker.setValue(UserManager.getInstance().getCurrentUser().getDateOfBirth());
            phoneNumberField.setText(UserManager.getInstance().getCurrentUser().getPhoneNumber());
            emailField.setText(UserManager.getInstance().getCurrentUser().getEmail());
            citizenIDField.setText(UserManager.getInstance().getCurrentUser().getCitizenID());
            if(UserManager.getInstance().getCurrentUser().getGender()== User.GENDER.MALE){ genderChoiceBox.setValue("MALE");}
            else if(UserManager.getInstance().getCurrentUser().getGender()== User.GENDER.FEMALE){ genderChoiceBox.setValue("FEMALE");}
            else{ genderChoiceBox.setValue("OTHER");}
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
            case EXISTED:
                emailErrorLog.setText("Email này đã tồn tại");
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
            case EXISTED:
                phoneNumberErrorLog.setText("Số điện thoại này đã tồn tại");
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
            UserManager.getInstance().setCurrentUser(new User());
            UserManager.getInstance().getCurrentUser().setFullName(fullNameField.getText());
            UserManager.getInstance().getCurrentUser().setDateOfBirth(dateOfBirthDatePicker.getValue());
            UserManager.getInstance().getCurrentUser().setGender(User.stringToGender(genderChoiceBox.getValue()));
            UserManager.getInstance().getCurrentUser().setPhoneNumber(phoneNumberField.getText());
            UserManager.getInstance().getCurrentUser().setEmail(emailField.getText());
            UserManager.getInstance().getCurrentUser().setCitizenID(citizenIDField.getText());

            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"signup_new_customer2_scene.fxml");
        }
    }
}
