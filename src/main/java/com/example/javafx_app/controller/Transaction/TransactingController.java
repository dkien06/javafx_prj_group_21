package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.controller.Bill.BillButtonController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Bill.BillType;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.controller.VerifyController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class TransactingController implements Initializable {
    @FXML
    private ChoiceBox<String> bankChoiceBox;
    private final String[] banks = {"21stBank"}; //Tự add đi
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
    private CheckingAccount CurrentAccount= (CheckingAccount) AccountManager.getInstance().getCurrentAccount();
    boolean isReceiveAccountValid = false;
    boolean isAmountValid = false;
    boolean isBankChosen = false;
    public void loadTransaction(Transaction transaction){
        amountTextField.setText(String.valueOf(transaction.getAmount()));
        receiveAccountIDTextField.setText(String.valueOf(transaction.getToAccount().getAccountID()));
        descriptionTextArea.setText(transaction.getDescription());
        bankChoiceBox.setValue("21stBank");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bankChoiceBox.getItems().addAll(banks);
        bankChoiceBox.setValue("Chọn ngân hàng");
        descriptionTextArea.setText(AccountManager.getInstance().getCurrentAccount().getAccountName() + " chuyen tien");
        amountTextField.textProperty().addListener((observable, _, value) -> {
            amountLog.setText(NumberToVietnameseWord.displayError(value));
            if(!amountLog.getText().isEmpty()){
                amountLog.setFill(Color.rgb(255,0,0));
                isAmountValid = false;
            }
            else{
                amountLog.setText(NumberToVietnameseWord.numberToVietnameseWords(Long.parseLong(value)));
                amountLog.setFill(Color.rgb(255,255,255));
                isAmountValid = true;
            }
        });
        receiveAccountIDTextField.textProperty().addListener((observableValue, _, value) -> {
            Account receiveAccount = AccountManager.getInstance().findAccount(value);
            // TH không tìm thấy account
            if(receiveAccount == null){
                receiveAccountIDLog.setText("Tài khoản không tồn tại");
                receiveAccountIDLog.setFill(Color.rgb(255,0,0));
                isReceiveAccountValid = false;
            }
            // Không thể thực hiện giao dịch đến tài khoản của người khác nếu như không phải là tài khoản thanh toán
            else if(receiveAccount.getAccountType()!= ACCOUNT_TYPE.CHECKING&&
                    receiveAccount.getCitizenID()!= CurrentAccount.getCitizenID()){
                receiveAccountIDLog.setText("Bạn không thể thực hiện giao dịch tới tài khoản này");
                receiveAccountIDLog.setFill(Color.rgb(255,0,0));
                isReceiveAccountValid = false;
            }
            else{
                receiveAccountIDLog.setText(receiveAccount.getAccountName().toUpperCase());
                receiveAccountIDLog.setFill(Color.rgb(255, 255, 255));
                isReceiveAccountValid = true;
            }
        });
        if(BillButtonController.isBillPayment){
            initializePayment();
        }
    }
    @FXML
    void QuayLai(ActionEvent event){
        TransactionManager.getInstance().removeNewTransaction();
        SceneUtils.switchScene(mainStage, "TransactionScene/transaction_options_scene.fxml");
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
            TransactionManager.getInstance().newTransaction(
                    TransactionType.TRANSFER,
                    Long.parseLong(amountTextField.getText()),
                    "VND", //Tạm thời thế này đi
                    AccountManager.getInstance().getCurrentAccount(),
                     AccountManager.getInstance().findAccount(receiveAccountIDTextField.getText()),
                    descriptionTextArea.getText()
            );
            Pair<Parent, VerifyController> scene = SceneUtils.getRootAndController("verify/verify_scene.fxml");
            scene.getValue().displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());
            SceneUtils.switchScene(mainStage,scene.getKey());
        }
    }
    private void initializePayment(){
        Bill billNeedToPay = BillButtonController.bill ;
        amountTextField.setText(String.valueOf(billNeedToPay.getAmount()));
        if(billNeedToPay.getBillType()== BillType.ELECTRIC){
            receiveAccountIDTextField.setText(ExampleUser.ELECTRIC_PROVIDER.getAccountID());
        }
        else if(billNeedToPay.getBillType() == BillType.INTERNET){
            receiveAccountIDTextField.setText(ExampleUser.INTERNET_PROVIDER.getAccountID());
        }
        else if(billNeedToPay.getBillType() == BillType.TUITION){
            receiveAccountIDTextField.setText(ExampleUser.SCHOOL_PROVIDER.getAccountID());
        }
        else {
            receiveAccountIDTextField.setText(ExampleUser.WATER_PROVIDER.getAccountID());
        }
        bankChoiceBox.setValue("21stBank");
        amountTextField.setEditable(false);
        receiveAccountIDTextField.setEditable(false) ;
    }
}
