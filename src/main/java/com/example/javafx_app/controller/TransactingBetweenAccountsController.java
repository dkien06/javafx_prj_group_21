package com.example.javafx_app.controller;

import com.example.javafx_app.Account;
import com.example.javafx_app.AccountManager;
import com.example.javafx_app.SceneUtils;
import com.example.javafx_app.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TransactingBetweenAccountsController implements Initializable {
    @FXML
    private TextField sendingAccountIDTextField;
    @FXML
    private TextField currentBalanceTextField;
    @FXML
    private RadioButton localBank;
    @FXML
    private RadioButton otherBank;
    @FXML
    private ChoiceBox<String> bankChoiceBox;
    private final String[] banks = {"MB Bank","BIDV","Techcombank","etc..."}; //Tự add đi
    @FXML
    private Text bankChoiceErrorLog;
    @FXML
    private TextField receiveAccountIDTextField;
    @FXML
    private Text receiveAccountIDErrorLog;
    @FXML
    private TextField amountTextField;
    @FXML
    private Text amountInWordsText;
    @FXML
    private Text amountErrorLog;
    @FXML
    private TextArea descriptionTextArea;

    public static Transaction newTransaction;

    void displaySendingAccountIDAndMoney(Account account){
        sendingAccountIDTextField.setText(account.getAccountID());
        currentBalanceTextField.setText(account.getBalance() + " " + account.getCurrency());
        descriptionTextArea.setText(AccountManager.getInstance().getCurrentAccount().getAccountID() + " CHUYEN TIEN");
    }
    @FXML
    void allowChoosingBank(){
        bankChoiceBox.setDisable(!otherBank.isSelected());
    }
    @FXML
    void disallowChoosingBank(){
        bankChoiceBox.setValue("");
        bankChoiceBox.setDisable(localBank.isSelected());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bankChoiceBox.getItems().addAll(banks);
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Xử lý khi giá trị text thay đổi
            try {
                //newValue.matches("\\d+") -> Check xem biến newValue có viết dưới dạng số không)
                if (!newValue.isEmpty() && newValue.matches("\\d+")) {
                    long amount = Long.parseLong(newValue);
                    String amountInWords = numberToVietnameseWords(amount);
                    amountInWordsText.setText(amountInWords + " đồng");
                } else {
                    amountInWordsText.setText("");
                    amountErrorLog.setText("Số tiền không hợp lệ");
                }
            } catch (NumberFormatException e) {
                amountInWordsText.setText("");
                amountErrorLog.setText("Số tiền không hợp lệ");
            }
        });
    }
    private String numberToVietnameseWords(double amount){
        //Đổi sang dạng chữ, nhác làm vcl:))
        return "";
    }
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"transaction_choose_method_scene.fxml");
    }
    @FXML
    void TiepTuc(ActionEvent event){
        boolean isBankChoiceValid = false;
        if((bankChoiceBox.getValue() == null || bankChoiceBox.getValue().isEmpty()) && otherBank.isSelected())
            bankChoiceErrorLog.setText("Vui lòng chọn ngân hang");
        else {
            bankChoiceErrorLog.setText("");
            isBankChoiceValid = true;
        }

        Account receiveAccount = new Account();
        boolean isReceiveAccountIDValid = false;
        if(receiveAccountIDTextField.getText().isEmpty())
            receiveAccountIDErrorLog.setText("Vui lòng nhập tài khoản nhận");
        else{
            receiveAccountIDErrorLog.setText("");
            if(localBank.isSelected()){
                receiveAccount = AccountManager.getInstance().findAccount(receiveAccountIDTextField.getText());
                if(receiveAccount == null){
                    receiveAccountIDErrorLog.setText("Tài khoản nhận không tồn tại");
                }
                else isReceiveAccountIDValid = true;
            }
            else{
                //Méo biết
            }
        }

        boolean isAmountValid = !amountTextField.getText().isEmpty() || amountTextField.getText().matches("\\d+");
        if(!isAmountValid)amountErrorLog.setText("Vui lòng nhập số tiền hợp lệ");

        if(isAmountValid && isReceiveAccountIDValid && isBankChoiceValid){
            newTransaction = new Transaction(Transaction.TransactionType.TRANSFER,
                                             Double.parseDouble(amountTextField.getText()),
                                    "VND",
                                             AccountManager.getInstance().getCurrentAccount(),
                                             receiveAccount,
                                             descriptionTextArea.getText());
            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"home_scene.fxml");
        }
    }
}
