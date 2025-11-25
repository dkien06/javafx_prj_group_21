package com.example.javafx_app.controller.SignUp;

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
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "SignUpScene/signup_new_customer2_scene.fxml");
    }

    @FXML
    private void returnToLoginScene(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),
                "SignUpScene/signup_3option_scene.fxml");
    }
}
