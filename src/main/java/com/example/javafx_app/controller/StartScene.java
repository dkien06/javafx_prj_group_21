package com.example.javafx_app.controller;

import com.example.javafx_app.DataPersistence;
import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class StartScene implements Initializable {

    @FXML
    private TextField PhoneTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private DatePicker DatePicker;
    @FXML
    private Text PhoneLog , EmailLog , DateLog ;

    // Hàm sẽ được gọi khi click nút (gán trực tiếp trên SceneBuilder)
    // Sau nay se them khong the chọn ngày trước ngày cuối cùng tức là khôgn thể đi ngược thời gian
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // code runs here automatically
        PhoneLog.setText("");
        EmailLog.setText("");
        DateLog.setText("");
        DatePicker.setValue(DataPersistence.lastAppUsageDate) ;
    }
    @FXML
    private void NextToLogin(ActionEvent event) {
        if(EmailTextField.getText().equals("-") && PhoneTextField.getText().equals("-")){
            ExampleUser.setCheat(true);
            Pair<Parent, LoginController> scene = SceneUtils.getRootAndController("login_scene.fxml");
            scene.getValue().loadCheat();
            SceneUtils.switchScene(mainStage,scene.getKey());
        }
        BankManager.SignUpInformationState emailState = BankManager.checkSignUpEmail(EmailTextField.getText());
        BankManager.SignUpInformationState phoneState = BankManager.checkSignUpPhoneNumber(PhoneTextField.getText());
        LocalDate today = DatePicker.getValue() ;
        System.out.println(emailState+" "+phoneState);
        boolean checkEmail = true , checkPhone = true, checkDate = true ;
        if(emailState != BankManager.SignUpInformationState.EXISTED && emailState != BankManager.SignUpInformationState.RIGHT) {
            EmailLog.setText("Email không đúng định dạng");
            checkEmail = false ;

        }
        if(phoneState != BankManager.SignUpInformationState.EXISTED &&
                phoneState != BankManager.SignUpInformationState.RIGHT){
            PhoneLog.setText("Số điện thoại không đúng định dạng");
            checkPhone = false ;

        }
      if(today.isBefore(DataPersistence.lastAppUsageDate)) {
           DateLog.setText("Không thể đặt thời gian trước thời điểm cuối cùng");
           checkDate = false ;
       }

        if(checkDate&&checkEmail&&checkPhone){
            BankManager.setCurrentPhoneNumber(PhoneTextField.getText());
            BankManager.setCurrentDate(DatePicker.getValue());
            BankManager.setCurrentEmail(EmailTextField.getText());
            BankManager.updateInformation();
            SceneUtils.switchScene(mainStage,"login_scene.fxml");
        }
    }
    @FXML
    public void Exit(ActionEvent event) {
        System.exit(0);
    }
}
