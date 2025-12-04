package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.controller.Bill.BillButtonController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Bill.BillType;
import com.example.javafx_app.object.TransactionType;
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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.swing.plaf.ColorUIResource;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

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
    private CheckingAccount CurrentAccount= (CheckingAccount) AccountManager.getInstance().getCurrentAccount();
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
                    if(amount > AccountManager.getInstance().getCurrentAccount().getBalance()){
                        amountLog.setText("Số tiền bạn nhập không đủ để chuyển");
                        amountLog.setFill(Color.rgb(255, 0, 0));
                        isAmountValid = false;
                    }
                    else{
                        String amountInWords = NumberToVietnameseWord.numberToVietnameseWords(amount);
                        amountLog.setText(amountInWords);
                        amountLog.setFill(Color.rgb(0,0,0));
                        isAmountValid = true;
                    }
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
                receiveAccountIDLog.setFill(Color.rgb(0,0,0));
                isReceiveAccountValid = true;
            }
        });
        if(BillButtonController.isBillPayment==true){
            initializePayment();
        }
    }
    @FXML
    void QuayLai(ActionEvent event){
        TransactionManager.getInstance().removeNewTransaction();
        SceneUtils.switchScene(mainStage,"HomeScenes/checking_account_home_scene.fxml");
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
            // xoa bill
            CurrentAccount.removeBill(BillButtonController.bill);
            // tra lai bien cho bill
            BillButtonController.isBillPayment=false;
            BillButtonController.bill=null ;
            try{
                FXMLLoader nextSceneLoader = new FXMLLoader(BankApplication.class.getResource("TransactionScene/verify_transaction.scene.fxml"));
                Parent nextSceneRoot = nextSceneLoader.load();

                VerifyTransactionController controller = nextSceneLoader.getController();
                controller.displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());

                SceneUtils.switchScene(SceneUtils.getStageFromEvent(event),nextSceneRoot);
            }
            catch (IOException e){
                System.out.println("Có lỗi xảy ra!");
            }
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
