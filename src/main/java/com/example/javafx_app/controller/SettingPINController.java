package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.AccountManager;
import com.example.javafx_app.BankManager;
import com.example.javafx_app.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
        Account currentAccount = AccountManager.getInstance().getCurrentAccount();

        // Lấy dữ liệu người dùng nhập
        String oldPin = OldPinField.getText();
        String newPin = NewPinField.getText();
        String newPinAgain = NewPinAgainField.getText();

        boolean isOldPinMatched = false;
        boolean isNewPinValid = false;
        boolean isNewPinAgainValid = false;
        // Kiểm tra mã PIN cũ
        if(oldPin.isEmpty()) OldPinErrorLog.setText("Vui lòng nhập mã PIN hiện tại của bạn");
        else if(currentAccount.isPinMatched(oldPin)) OldPinErrorLog.setText("Mã PIN hiện tại không đúng");
        else isOldPinMatched = true;

        if(isOldPinMatched){
            switch (BankManager.checkNewPIN(newPin)){
                case EMPTY:
                    NewPinLog.setText("Mã PIN không được để trống");
                    break;
                case WRONG_FORM:
                    NewPinLog.setText("Mã PIN phải là 6 chữ số");
                    break;
                case RIGHT:
                    NewPinLog.setText("");
                    isNewPinValid = true;
                    break;
            }
            if(isNewPinValid){
                if(newPinAgain.isEmpty())NewPinAgainLog.setText("Vui lòng xác nhận laại mã PIN");
                else if(newPinAgain.equals(newPin))NewPinAgainLog.setText("Mã PIN nhập lại không khớp");
                else isNewPinAgainValid = true;
            }
            else NewPinLog.setText("");
        }
        else NewPinLog.setText("");

        // ✅ Nếu vượt qua tất cả kiểm tra
        if(isNewPinAgainValid)currentAccount.setPIN(newPin);

        // Chuyển về trang chính
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "home_scene.fxml");
    }


}
