package com.example.javafx_app.controller.Transaction;

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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class TransactingController implements Initializable {
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
    void loadTransaction(Account account, Transaction transaction) {
        if (transaction != null) {
            receiveAccountIDTextField.setText(transaction.getToAccount().getAccountID());
            amountTextField.setText(Integer.toString((int) transaction.getAmount()));
            descriptionTextArea.setText(transaction.getDescription());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //bankChoiceBox.getItems().addAll(banks);
        /*amountTextField.textProperty().addListener((observable, _, value) -> {
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
        });*/
    }
    @FXML
    void QuayLai(ActionEvent event){
        TransactionManager.getInstance().removeNewTransaction();
        SceneUtils.switchScene(mainStage,"HomeScenes/checking_account_home_scene.fxml");
    }
    @FXML
    void TiepTuc(ActionEvent event) throws IOException {

    }
}
