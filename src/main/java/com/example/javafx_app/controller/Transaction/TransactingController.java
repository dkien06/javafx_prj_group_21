package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.BankApplication;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactingController implements Initializable {
    @FXML
    private ChoiceBox<String> bankChoiceBox;
    private final String[] banks = {"21stBank","MB Bank","BIDV","Techcombank","..."}; //Tự add đi
    @FXML
    private Text bankChoiceErrorLog;
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
    void loadTransaction(Account account, Transaction transaction){
        //Chờ scene sau đã
    }
    boolean isReceiveAccountValid = false;
    boolean isAmountValid = false;
    boolean isBankChosen = false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bankChoiceBox.getItems().addAll(banks);
        bankChoiceBox.setValue("Chọn ngân hàng");
        amountTextField.textProperty().addListener((observable, _, value) -> {
            try {
                if (!value.isEmpty() && value.matches("\\d+")) {
                    long amount = Long.parseLong(value);
                    if(amount > ((CheckingAccount)(AccountManager.getInstance().getCurrentAccount())).getBalance()){
                        amountLog.setText("Số tiền bạn nhập không đủ để chuyển");
                        amountLog.setFill(Color.rgb(255, 0, 0));
                        isAmountValid = false;
                    }
                    String amountInWords = NumberToVietnameseWord.numberToVietnameseWords(amount);
                    amountLog.setText(amountInWords);
                    amountLog.setFill(Color.rgb(0,0,0));
                    isAmountValid = true;
                } else {
                    amountLog.setText("Số tiền không hợp lệ");
                    amountLog.setFill(Color.rgb(255, 0, 0));
                    isAmountValid = false;
                }
            } catch (NumberFormatException e) {
                amountLog.setText("Số tiền không hợp lệ");
                amountLog.setFill(Color.rgb(255, 0, 0));
                isAmountValid = false;
            }
        });
        receiveAccountIDTextField.textProperty().addListener((observableValue, _, value) -> {
            CheckingAccount receiveAccount = (CheckingAccount) AccountManager.getInstance().findAccount(value);
            if(receiveAccount == null){
                receiveAccountIDLog.setText("Tài khoản không tồn tại");
                receiveAccountIDLog.setFill(Color.rgb(255,0,0));
                isReceiveAccountValid = false;
            }
            else{
                receiveAccountIDLog.setText(receiveAccount.getAccountName().toUpperCase());
                receiveAccountIDLog.setFill(Color.rgb(0,0,0));
                isReceiveAccountValid = true;
            }
        });
    }
    @FXML
    void QuayLai(ActionEvent event){
        TransactionManager.getInstance().removeNewTransaction();
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"HomeScenes/checking_account_home_scene.fxml");
    }
    @FXML
    void TiepTuc(ActionEvent event) throws IOException {
        if(receiveAccountIDTextField.getText().isEmpty()){
            receiveAccountIDLog.setText("Vui lòng nhập tên người gửi");
            receiveAccountIDLog.setFill(Color.rgb(255,0,0));
            isReceiveAccountValid = false;
        }
        if(amountTextField.getText().isEmpty()){
            amountLog.setText("Vui lòng nhập số tiền gửi");
        }
        if(bankChoiceBox.getValue().equals("Chọn ngân hàng")){
            bankChoiceErrorLog.setText("Vui lòng chọn ngân hàng");
            amountLog.setFill(Color.rgb(255,0,0));
            isBankChosen = false;
        }
        else{
            bankChoiceErrorLog.setText("");
            isBankChosen = true;
        }
        if(isReceiveAccountValid && isAmountValid && isBankChosen){
            System.out.println("Chuyển sang scene xác nhận chuyển khoản...");
        }
    }
}
