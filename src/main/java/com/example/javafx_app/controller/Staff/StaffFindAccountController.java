package com.example.javafx_app.controller.Staff;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static com.example.javafx_app.config.Constant.mainStage;

public class StaffFindAccountController {

    @FXML
    private TextField accountIdField;

    @FXML
    private Text errorLog;

    @FXML
    private VBox resultVBox;

    // Các Label hiển thị thông tin tài khoản
    @FXML private Label nameLabel;
    @FXML private Label typeLabel;
    @FXML private Label citizenIdLabel;
    @FXML private Label accountIdResultLabel;
    @FXML private Label startDateLabel;
    // Các Label hiển thị thông tin người dùng liên quan
    @FXML private Label phoneNumberLabel;
    @FXML private Label emailLabel;

    @FXML
    private Button searchButton;

    @FXML
    private Button returnButton;

    @FXML
    public void initialize() {
        // Ẩn khu vực kết quả khi khởi động
        resultVBox.setVisible(false);
        errorLog.setText("");
    }

    @FXML
    public void TimKiem(ActionEvent event) {
        String accountId = accountIdField.getText().trim();
        errorLog.setText("");
        resultVBox.setVisible(false); // Ẩn kết quả cũ

        if (accountId.isEmpty()) {
            errorLog.setText("Vui lòng nhập Số tài khoản.");
            return;
        }

        // 1. Tìm kiếm Account
        Account foundAccount = AccountManager.getInstance().findAccount(accountId); //

        if (foundAccount == null) {
            errorLog.setText("Không tìm thấy tài khoản với ID: " + accountId);
            return;
        }

        // 2. Hiển thị thông tin
        displayAccountInfo(foundAccount);
    }

    /**
     * Cập nhật các Label trên màn hình với thông tin tài khoản tìm được.
     */
    private void displayAccountInfo(Account account) {
        // 1. Lấy thông tin User từ CitizenID của Account
        User user = UserManager.getInstance().findUserByCitizenID(account.getCitizenID()); //

        // 2. Cập nhật thông tin Account
        nameLabel.setText("Tên chủ khoản: " + account.getAccountName()); //
        citizenIdLabel.setText("CCCD: " + account.getCitizenID()); //
        typeLabel.setText("Loại tài khoản: " + account.getAccountType().toString()); //
        accountIdResultLabel.setText("Số tài khoản: " + account.getAccountID()); //
        startDateLabel.setText("Ngày mở tài khoản: " + account.getStartDate().toString()); //

        // 3. Cập nhật thông tin User (Phone và Email)
        if (user != null) {
            phoneNumberLabel.setText("Số điện thoại: " + user.getPhoneNumber()); //
            emailLabel.setText("Email: " + user.getEmail()); //
        } else {
            phoneNumberLabel.setText("Số điện thoại: Không có dữ liệu");
            emailLabel.setText("Email: Không có dữ liệu");
        }

        resultVBox.setVisible(true);
    }

    @FXML
    public void QuayLai(ActionEvent event) {
        // Quay lại màn hình nhân viên chính
        SceneUtils.switchScene(mainStage, "HomeScenes/staff_home_scene.fxml"); //
    }
}