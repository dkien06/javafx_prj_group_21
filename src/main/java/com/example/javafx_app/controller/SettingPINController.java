package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.BankManager;
import com.example.javafx_app.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class SettingPINController {

    @FXML
    private TextField OldPinField;

    @FXML
    private TextField NewPinField;

    @FXML
    private TextField NewPinAgainField;

    @FXML
    private Text OldPinErrorLog;

    @FXML
    private Text NewPinLog;

    @FXML
    private Text NewPinAgainLog;

    // Nút "Quay lại"
    @FXML
    private void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"setting.fxml");
    }

    // Nút "Hoàn thành"
    @FXML
    private void HoanThanh(ActionEvent event) {
        // Reset lỗi
        OldPinErrorLog.setText("");
        NewPinLog.setText("");
        NewPinAgainLog.setText("");

        // Lấy tài khoản hiện tại
        Account currentAccount = BankManager.Settings.getCurrentAccount();

        // Lấy dữ liệu người dùng nhập
        String oldPin = OldPinField.getText().trim();
        String newPin = NewPinField.getText().trim();
        String newPinAgain = NewPinAgainField.getText().trim();

        // Kiểm tra mã PIN cũ
        if (!oldPin.equals(currentAccount.getPIN())) {
            OldPinErrorLog.setText("Mã PIN cũ không đúng");
            return;
        }

        // Kiểm tra mã PIN mới (qua hàm validate riêng)
        String newPinLogText = BankManager.VerifySignUpInformation.validatePIN(newPin);
        if (newPinLogText != null) {
            NewPinLog.setText(newPinLogText);
            return;
        }

        // Kiểm tra nhập lại mã PIN
        if (!newPinAgain.equals(newPin)) {
            NewPinAgainLog.setText("Mã PIN nhập lại không khớp");
            return;
        }

        // ✅ Nếu vượt qua tất cả kiểm tra
        currentAccount.setPIN(newPin);


        // Chuyển về trang chính
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "home_scene.fxml");
    }


}
