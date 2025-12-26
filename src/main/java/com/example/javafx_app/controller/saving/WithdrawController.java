package com.example.javafx_app.controller.saving;

import com.example.javafx_app.controller.VerifyController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.*;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;

import static com.example.javafx_app.config.Constant.mainStage;

public class WithdrawController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label logo_label;


    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Label setting_icon;

    @FXML
    private VBox main_vbox;

    @FXML
    private TextField receiveAccountIDTextField;

    @FXML
    private Text receiveAccountIDLog;

    @FXML
    private TextField amountTextField;

    @FXML
    private Text amountLog;

    @FXML
    private TextField descriptionTextArea;

    @FXML
    private Rectangle warning;

    @FXML
    private Label WarningLabel;

    @FXML
    private Button QuayLai;

    @FXML
    private Button TiepTuc;
    boolean isAmountValid = false, isReceiveAccountValid = false;
    SavingAccount  savingAccount =(SavingAccount) AccountManager.getInstance().getCurrentAccount() ;
    Customer customer = (Customer) UserManager.getInstance().getCurrentUser();
    CheckingAccount  checkingAccount =(CheckingAccount) AccountManager.getInstance().
            findExactAccountFromCostumer(customer,ACCOUNT_TYPE.CHECKING) ;
    /**
     * Phương thức khởi tạo mặc định của JavaFX
     */
    @FXML
    public void initialize() {
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String error = NumberToVietnameseWord.displayErrorForSaving(newValue,savingAccount);
            if (!error.isEmpty()) {
                amountLog.setText(error);
                amountLog.setFill(Color.RED);
                isAmountValid = false;
            } else {
                try {
                    long amount = Long.parseLong(newValue);
                    amountLog.setText(NumberToVietnameseWord.numberToVietnameseWords(amount));
                    amountLog.setFill(Color.BLACK);
                    isAmountValid = true;
                } catch (NumberFormatException e) {
                    amountLog.setText("Số tiền không hợp lệ");
                    amountLog.setFill(Color.RED);
                    isAmountValid = false;
                }
            }
        });

        // 3. Logic kiểm tra số tài khoản đích (Viết thẳng vào initialize)
        receiveAccountIDTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Account receiveAccount = AccountManager.getInstance().findAccount(newValue);
            System.out.println("CHeck");
            if (receiveAccount == null) {
                receiveAccountIDLog.setText("Tài khoản không tồn tại");
                receiveAccountIDLog.setFill(Color.RED);
                isReceiveAccountValid = false;
            }
            // Không được chuyển tới tài khoản của người khác nếu không phải tài khoản CHECKING
            else if (receiveAccount.getAccountType() != ACCOUNT_TYPE.CHECKING &&
                    !receiveAccount.getCitizenID().equals(customer.getCitizenID())) {
                receiveAccountIDLog.setText("Bạn không thể thực hiện giao dịch tới tài khoản này");
                receiveAccountIDLog.setFill(Color.RED);
                isReceiveAccountValid = false;
            } else {
                receiveAccountIDLog.setText(receiveAccount.getAccountName().toUpperCase());
                receiveAccountIDLog.setFill(Color.BLACK);
                isReceiveAccountValid = true;
            }
        });
        receiveAccountIDTextField.setText(checkingAccount.getAccountID());
        receiveAccountIDTextField.setEditable(false);
        descriptionTextArea.setText(customer.getFullName().toUpperCase()+" rút tiền tiết kiệm");
        if(savingAccount.getType()== SavingType.FIXED){
            warning.setVisible(true);
            WarningLabel.setVisible(true);
            amountTextField.setText(savingAccount.getSaving()+"");
            amountTextField.setEditable(false);
        }

    }

    /**
     * Xử lý sự kiện khi nhấn nút Quay lại
     */
    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage,"HomeScenes/saving_account_home_scene.fxml");
    }

    /**
     * Xử lý sự kiện khi nhấn nút Tiếp tục
     */
    @FXML
    void TiepTuc(ActionEvent event) {
        if(isAmountValid&&isReceiveAccountValid){
            TransactionManager.getInstance().newTransaction(
                    TransactionType.WITHDRAW,
                    Long.parseLong(amountTextField.getText()),
                    "VND",
                    savingAccount,
                    checkingAccount,
                    descriptionTextArea.getText()
            );

            Pair<Parent, VerifyController> scene = SceneUtils.getRootAndController("verify/verify_scene.fxml");
            scene.getValue().displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());
            SceneUtils.switchScene(mainStage, scene.getKey());
        }
    }

}