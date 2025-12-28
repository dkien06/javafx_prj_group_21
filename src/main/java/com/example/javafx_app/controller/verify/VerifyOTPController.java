package com.example.javafx_app.controller.verify;

import com.example.javafx_app.controller.ForgetPassword;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class VerifyOTPController implements Initializable {

    @FXML
    private TextField otpField;

    @FXML
    private Text otpErrorLog;

    @FXML
    private Text countdownText;

    @FXML
    private Label OTPLabel ;

    // Giữ lại các hằng số cần thiết cho flow và timer
    private static final int INITIAL_COUNTDOWN = 30;
    private int currentCountdown;
    private Timeline timeline;
    boolean isValid = ((ForgetPassword.MethodVerify.equals("PhoneNumber")&&ForgetPassword.identity.equals(BankManager.getCurrentPhoneNumber()))
                        || (ForgetPassword.MethodVerify.equals("Email")&&ForgetPassword.identity.equals(BankManager.getCurrentEmail())) ) ;
    private String OTP = BankManager.generateOTP() ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OTPLabel.setVisible(false);
        System.out.println(isValid);
        GuiLaiOTP();
    }

    /**
     * Phương thức khởi động hoặc khởi động lại đồng hồ đếm ngược. (LOGIC TIMER)
     */
    private void startCountdown() {
        currentCountdown = INITIAL_COUNTDOWN;
        // KHÔNG DISABLE NÚT GỬI LẠI THEO YÊU CẦU
        countdownText.setText(currentCountdown + " giây");

        if (timeline != null) {
            timeline.stop();
        }

        // Thiết lập Timeline đếm ngược mỗi giây
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    currentCountdown--;
                    if (currentCountdown > 0) {
                        countdownText.setText(currentCountdown + " giây");
                    } else {
                        timeline.stop();
                        countdownText.setText("Hết hạn!");
                        otpErrorLog.setText("Mã OTP đã hết hạn.");
                    }
                })
        );
        timeline.setCycleCount(INITIAL_COUNTDOWN);
        timeline.play();
    }

    @FXML
    public void GuiLaiOTP() {
        // LOGIC TIMER: Gọi lại đồng hồ
        startCountdown();
        OTP = BankManager.generateOTP();
        if(isValid){
            OTPLabel.setVisible(true);
            OTPLabel.setText("Mã OTP của bạn là : "+OTP);
        }
    }


    @FXML
    public void Huy(ActionEvent event) {
        // LOGIC TIMER: Dừng đồng hồ
        if (timeline != null) timeline.stop();

        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "forget_password.fxml");
    }

    @FXML
    public void XacNhan(ActionEvent event) {
        otpErrorLog.setText("");
        String inputOTP = otpField.getText();

        // LOGIC TIMER: Kiểm tra xem mã đã hết hạn chưa
        if (currentCountdown <= 0) {
            otpErrorLog.setText("Mã OTP đã hết hạn. Vui lòng gửi lại.");
            return;
        }

        if (inputOTP.isEmpty()) {
            otpErrorLog.setText("Vui lòng nhập mã OTP.");
            return;
        }
        if(!inputOTP.equals(OTP)){
            otpErrorLog.setText("OTP không đúng");
            return;
        }
        // Hoan thanh

        ForgetPassword.accountToUpdate.setPassword(ForgetPassword.passwordToUpdate);
        SceneUtils.switchScene(mainStage, "login_scene.fxml");
    }
}