package com.example.javafx_app.controller;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.BankManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.util.SceneUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class ForgetPassword implements Initializable {

    @FXML
    private ChoiceBox<String> accountTypeChoiceBox; // TRƯỜNG MỚI
    @FXML
    private ChoiceBox<String> recoveryMethodChoiceBox;
    @FXML
    private TextField identityField;
    @FXML
    private PasswordField NewPasswordField;
    @FXML
    private PasswordField ConfirmNewPasswordField;

    @FXML
    private Text AccountTypeErrorLog; // TRƯỜNG MỚI
    @FXML
    private Text RecoveryMethodErrorLog;
    @FXML
    private Text IdentityErrorLog;
    @FXML
    private Text NewPasswordErrorLog;
    @FXML
    private Text ConfirmNewPasswordErrorLog;
    public static Account accountToUpdate; // account dung de quen mat khau
    public static String passwordToUpdate; // matkhau dung de doi
    public static String MethodVerify ;
    public static String identity ;
    private final String[] recoveryOptions = {"Email", "Số điện thoại"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo Recovery Method ChoiceBox
        recoveryMethodChoiceBox.setItems(FXCollections.observableArrayList(recoveryOptions));
        recoveryMethodChoiceBox.setValue(recoveryOptions[0]); // Default to Email

        // Khởi tạo Account Type ChoiceBox (dùng enum ACCOUNT_TYPE)
        String[] accountTypes = Arrays.stream(ACCOUNT_TYPE.values())
                .map(Enum::toString) // Lấy CHECKING, SAVING, LOAN
                .toArray(String[]::new);
        accountTypeChoiceBox.setItems(FXCollections.observableArrayList(accountTypes));
        accountTypeChoiceBox.setValue(accountTypes[0]); // Default to CHECKING

        // Xóa tất cả thông báo lỗi
        AccountTypeErrorLog.setText("");
        RecoveryMethodErrorLog.setText("");
        IdentityErrorLog.setText("");
        NewPasswordErrorLog.setText("");
        ConfirmNewPasswordErrorLog.setText("");
    }

    @FXML
    public void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "login_scene.fxml");
    }

    @FXML
    public void HoanThanh(ActionEvent event) {
        System.out.println("CHeck");
        // Reset logs
        AccountTypeErrorLog.setText("");
        RecoveryMethodErrorLog.setText("");
        IdentityErrorLog.setText("");
        NewPasswordErrorLog.setText("");
        ConfirmNewPasswordErrorLog.setText("");

        String selectedAccountType = accountTypeChoiceBox.getValue();
        String selectedMethod = recoveryMethodChoiceBox.getValue();
        identity = identityField.getText();
        String newPass = NewPasswordField.getText();
        String confirmNewPass = ConfirmNewPasswordField.getText();
        ACCOUNT_TYPE accountType=null ;
        boolean isValid = true;

        // 1. Check Account Type selection
        if (selectedAccountType == null || selectedAccountType.isEmpty()) {
            AccountTypeErrorLog.setText("Vui lòng chọn loại tài khoản.");
            isValid = false;
        }
        else{
            accountType = ACCOUNT_TYPE.fromLabel(selectedAccountType);
            System.out.println(accountType);
        }
        // 2. Kiểm tra phương thức khôi phục
        if (selectedMethod == null || selectedMethod.isEmpty()) {
            RecoveryMethodErrorLog.setText("Vui lòng chọn phương thức khôi phục.");
            isValid = false;
        }

        // 3. Tìm kiếm tài khoản và xác thực Identity
        if (isValid) {
            if ("Email".equals(selectedMethod)) {
                if (BankManager.checkSignUpEmail(identity) != BankManager.SignUpInformationState.RIGHT
                    && BankManager.checkSignUpEmail(identity) != BankManager.SignUpInformationState.EXISTED) {
                    IdentityErrorLog.setText("Email không hợp lệ.");
                    isValid = false;
                } else {
                    accountToUpdate = AccountManager.getInstance().findExactAccountFromEmail(identity,accountType);
                    MethodVerify = "Email" ;
                }
            } else if ("Số điện thoại".equals(selectedMethod)) {
                if (BankManager.checkSignUpPhoneNumber(identity) != BankManager.SignUpInformationState.RIGHT
                && BankManager.checkSignUpPhoneNumber(identity) != BankManager.SignUpInformationState.EXISTED) {
                    IdentityErrorLog.setText("Số điện thoại không hợp lệ.");
                    isValid = false;
                } else {
                    accountToUpdate = AccountManager.getInstance().findExactAccountFromPhoneNumber(identity,accountType);
                    MethodVerify = "PhoneNumber" ;
                }
            }
        }

        if (accountToUpdate == null && isValid) {
            IdentityErrorLog.setText("Không tìm thấy tài khoản với thông tin này.");
            isValid = false;
        }

        // 4. Xác thực độ mạnh của mật khẩu mới
        if (isValid) {
            BankManager.PasswordState passwordState = BankManager.checkNewPassword(newPass) ;
            BankManager.PasswordState AgainPasswordState = BankManager.checkPasswordAgain(newPass,confirmNewPass) ;
            NewPasswordErrorLog.setText(passwordState.getLabel());
            ConfirmNewPasswordErrorLog.setText(passwordState.getLabel());
            System.out.println(passwordState.getLabel());
            if(! (passwordState == BankManager.PasswordState.RIGHT) ||! (AgainPasswordState == BankManager.PasswordState.RIGHT) ){
                isValid = false;
            }
        }


        // 6. Cập nhật mật khẩu nếu hợp lệ
        if (isValid) {
            passwordToUpdate = newPass;
            SceneUtils.switchScene(mainStage, "verify_otp_scene.fxml");
        }
    }
}