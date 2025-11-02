package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account;
import com.example.javafx_app.object.CheckingAccount;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
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
    private static final String[] soDonVi = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] hangChuc = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
    private static final String[] hangTram = {"không trăm", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm"};
    void displaySendingAccountIDAndMoney(Account account){
        sendingAccountIDTextField.setText(account.getAccountID());
        currentBalanceTextField.setText(account.getCheckingAccount().getBalance() + " " + account.getCurrency());
        descriptionTextArea.setText(AccountManager.getInstance().getCurrentAccount().getAccountName() + " CHUYEN TIEN");
    }
    void loadTransaction(Account account, Transaction transaction){
        if(transaction != null){
            sendingAccountIDTextField.setText(account.getAccountID());
            currentBalanceTextField.setText(Double.toString(account.getCheckingAccount().getBalance()));
            receiveAccountIDTextField.setText(transaction.getToAccount().getAccountID());
            amountTextField.setText(Integer.toString((int)transaction.getAmount()));
            descriptionTextArea.setText(transaction.getDescription());
        }
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
        amountTextField.textProperty().addListener((observable, _, value) -> {
            // Xử lý khi giá trị text thay đổi
            try {
                //newValue.matches("\\d+") -> Check xem biến newValue có viết dưới dạng số không)
                if (!value.isEmpty() && value.matches("\\d+")) {
                    long amount = Long.parseLong(value);
                    if(amount > AccountManager.getInstance().getCurrentAccount().getCheckingAccount().getBalance()){
                        amountErrorLog.setText("Số tiền bạn nhập không đủ để chuyển");
                    }
                    else amountErrorLog.setText("");
                    String amountInWords = TransactionManager.getInstance().numberToVietnameseWords(amount);
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
    @FXML
    void QuayLai(ActionEvent event){
        TransactionManager.getInstance().removeNewTransaction();
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),"transaction_choose_method_scene.fxml");
    }
    @FXML
    void TiepTuc(ActionEvent event) throws IOException {
        boolean isBankChoiceValid = false;
        if((bankChoiceBox.getValue() == null || bankChoiceBox.getValue().isEmpty()) && otherBank.isSelected())
            bankChoiceErrorLog.setText("Vui lòng chọn ngân hang");
        else {
            bankChoiceErrorLog.setText("");
            isBankChoiceValid = true;
        }

        CheckingAccount receiveAccount = null;
        boolean isReceiveAccountIDValid = false;
        if(receiveAccountIDTextField.getText().isEmpty())
            receiveAccountIDErrorLog.setText("Vui lòng nhập tài khoản nhận");
        else{
            receiveAccountIDErrorLog.setText("");
            if(localBank.isSelected()){
                receiveAccount = AccountManager.getInstance().findAccount(receiveAccountIDTextField.getText()).getCheckingAccount();
                if(receiveAccount == null){
                    receiveAccountIDErrorLog.setText("Tài khoản nhận không tồn tại");
                }
                else isReceiveAccountIDValid = true;
            }
            else{
                //Méo biết
            }
        }

        boolean isAmountValid = !amountTextField.getText().isEmpty() && amountTextField.getText().matches("\\d+");
        if(!isAmountValid)amountErrorLog.setText("Vui lòng nhập số tiền hợp lệ");

        if(isAmountValid && isReceiveAccountIDValid && isBankChoiceValid){
            TransactionManager.getInstance().newTransaction(
                    Transaction.TransactionType.TRANSFER,
                    Double.parseDouble(amountTextField.getText()),
                    "VND",
                    AccountManager.getInstance().getCurrentAccount(),
                    AccountManager.getInstance().findAccount(receiveAccountIDTextField.getText()),
                    descriptionTextArea.getText());
            FXMLLoader nextSceneLoader = new FXMLLoader(BankApplication.class.getResource("verify_transaction.scene.fxml"));
            Parent nextSceneRoot = nextSceneLoader.load();

            VerifyTransactionController controller = nextSceneLoader.getController();
            controller.displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());

            SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
        }
    }
}
