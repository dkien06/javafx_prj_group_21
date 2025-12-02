package com.example.javafx_app.controller;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class AccountSceneController implements Initializable {

    // Khai báo các thành phần UI từ FXML
    @FXML
    private Label Name;
    @FXML
    private Label CitizenID;
    @FXML
    private Label PhoneNumber;
    @FXML
    private Label Email;
    @FXML
    private ChoiceBox<String> AccountType;
    @FXML
    private Button ChangeAccount;

    // Lấy instance của Manager
    private final UserManager userManager = UserManager.getInstance();
    private final AccountManager accountManager = AccountManager.getInstance();
    private final User currentUser = userManager.getCurrentUser();
    private final Account currentAccount = accountManager.getCurrentAccount();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Lấy thông tin người dùng và tài khoản hiện tại
        User currentUser = userManager.getCurrentUser();
        Account currentAccount = accountManager.getCurrentAccount();

        // 1. Hiển thị thông tin cá nhân (FullName, CCCD, Phone, Email)
        if (currentUser != null) {
            Name.setText(currentUser.getFullName());
            CitizenID.setText("CCCD: " + currentUser.getCitizenID());
            PhoneNumber.setText("Số điện thoại: " + currentUser.getPhoneNumber());
            Email.setText("Email: " + currentUser.getEmail());
        } else {
            // Trường hợp không có CurrentUser (chỉ có CurrentAccount)
            Name.setText(currentAccount.getAccountName());
            CitizenID.setText("CCCD: " + currentAccount.getCitizenID());
            PhoneNumber.setText("Số điện thoại: Không có dữ liệu");
            Email.setText("Email: Không có dữ liệu");
        }

        // 2. Khởi tạo danh sách loại tài khoản có sẵn
        List<ACCOUNT_TYPE> list = UserManager.getInstance().getAccountList(currentUser);

        for(ACCOUNT_TYPE type : list){
            AccountType.getItems().add(type.toString());
        }

        // Chọn loại tài khoản hiện tại (ví dụ: mặc định là Checking)
        if (!list.isEmpty()) {
            AccountType.setValue(currentAccount.getAccountType().toString());
        }

        // Đặt tên nút
        ChangeAccount.setText("Chuyển");
    }

    // Xử lý sự kiện khi nhấn nút "Quay lại"
    @FXML
    public void QuayLai(ActionEvent event) {
        System.out.println(currentAccount.getAccountType().toString());
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), AccountManager.getInstance().chooseHomeScene(currentAccount));
    }

    // Xử lý sự kiện khi nhấn nút "Chuyển" tài khoản
    @FXML
    public void ChangeAccount(ActionEvent event) {
        String selectedType = AccountType.getValue();
        ACCOUNT_TYPE Type = ACCOUNT_TYPE.fromLabel(selectedType);
        System.out.println(currentAccount.getAccountType().toString());
        if (Type != null) {
            if(Type==ACCOUNT_TYPE.STAFF){
                SceneUtils.switchScene(mainStage,AccountManager.getInstance().chooseHomeScene(currentAccount));

            }
            else{
                Account newAccount = AccountManager.getInstance().findExactAccountFromCostumer((Customer)currentUser,Type );
                AccountManager.getInstance().setCurrentAccount(newAccount);
                System.out.println(newAccount.getAccountType().toString());
                System.out.println(AccountManager.getInstance().chooseHomeScene(newAccount));
                SceneUtils.switchScene(mainStage,AccountManager.getInstance().chooseHomeScene(newAccount));
            }
        }
    }
}