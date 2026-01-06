package com.example.javafx_app.controller;

import com.example.javafx_app.DataPersistence;
import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoginController implements Initializable {

    @FXML
    private TextField CCCDField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Label WrongLoginLabel;
    @FXML
    private ChoiceBox<String> accountType;
    @FXML private CheckBox RememberPass ;

    @FXML private ChoiceBox<String> quick_log_in_for_example_account;
    @FXML private Button quick_login_button;
    @FXML private Label example_name_label;
    public void loadCheat(){
        if(ExampleUser.isCheat()){
            quick_log_in_for_example_account.setDisable(false);
            quick_log_in_for_example_account.setVisible(true);
            quick_login_button.setVisible(true);
            quick_login_button.setDisable(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quick_log_in_for_example_account.getItems().addAll(ExampleUser.getExampleAccountList().keySet());
        quick_log_in_for_example_account.getSelectionModel().selectedItemProperty().addListener((observable, _, value) -> {
            if(value != null)example_name_label.setText(
                    ExampleUser.getExampleAccountList().get(value).getAccountName()
                + " - " + ExampleUser.getExampleAccountList().get(value).getAccountType().toString());
            else example_name_label.setText("");
        });
        quick_log_in_for_example_account.setDisable(true);
        quick_log_in_for_example_account.setVisible(false);
        quick_login_button.setVisible(false);
        quick_login_button.setDisable(true);
        // Nạp các loại tài khoản vào ChoiceBox (hiển thị bằng tên tiếng Việt từ toString)
        if(!DataPersistence.savedAccountID.isEmpty()){
            CCCDField.setText(DataPersistence.savedAccountID);
            RememberPass.setSelected(true);
        }
        if(!DataPersistence.savedPassword.isEmpty()){
            PasswordField.setText(DataPersistence.savedPassword);
        }
        for (ACCOUNT_TYPE type : ACCOUNT_TYPE.values()) {
            accountType.getItems().add(type.toString());
        }
        if(!DataPersistence.accountType.isEmpty()){
            accountType.setValue(DataPersistence.accountType);
        }

        // Chọn mặc định là CHECKING
        else accountType.setValue(ACCOUNT_TYPE.CHECKING.toString());
    }

    @FXML
    void DangNhap(ActionEvent event) {
        String CCCD = CCCDField.getText();
        String password = PasswordField.getText();
        String selectedLabel = accountType.getValue(); // Lấy giá trị chuỗi người dùng chọn
        if(Objects.equals(password, "")) {
            WrongLoginLabel.setText("Bạn chưa nhập mật khẩu");
            return ;
        }
        // 1. Chuyển đổi từ String (Label) ngược lại thành Enum ACCOUNT_TYPE
        ACCOUNT_TYPE selectedType = ACCOUNT_TYPE.CHECKING; // Mặc định
        for (ACCOUNT_TYPE type : ACCOUNT_TYPE.values()) {
            if (type.toString().equals(selectedLabel)) {
                selectedType = type;
                break;
            }
        }
        if(AccountManager.getInstance().logIn(CCCD, password, selectedType)) {
            if(RememberPass.isSelected()) {
                DataPersistence.savedAccountID = CCCDField.getText();
                DataPersistence.savedPassword = PasswordField.getText();
                DataPersistence.accountType= selectedLabel ;
                System.out.println("Check");
            }
            else {
                DataPersistence.savedAccountID = "" ;
                DataPersistence.savedPassword = "" ;
                DataPersistence.accountType = "";
            }
            ExampleUser.setCheat(false);
            SceneUtils.switchScene(mainStage,
                    AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
        }
        else WrongLoginLabel.setText("Tài khoản hoặc mật khẩu bị sai");
    }

    @FXML
    void TaoTaiKhoanMoi(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "SignUpScene/signup_3option_scene.fxml");
    }
    @FXML
    public void QuenMatKhau(MouseEvent event) {
        SceneUtils.switchScene(mainStage,"forget_password.fxml");
    }
    @FXML
    public void DangNhapNhanh(ActionEvent event){
        if(quick_log_in_for_example_account.getValue() != null){
            Account account = AccountManager.getInstance().findAccount(quick_log_in_for_example_account.getValue());
            AccountManager.getInstance().setCurrentAccount(account);
            ExampleUser.setCheat(true);
            SceneUtils.switchScene(mainStage,
                    AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
        }
    }
}