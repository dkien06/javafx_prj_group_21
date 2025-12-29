package com.example.javafx_app.controller.loan;

import com.example.javafx_app.controller.verify.VerifyController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanController implements Initializable {
    @FXML private TextField loanAccountID;
    @FXML private TextField loanMethod;
    @FXML private TextField amount;
    @FXML private TextField durationField;
    @FXML private TextArea description;
    @FXML private Text amountLog;
    @FXML private Text durationLog;
    private int type = 0;
    private int index = 0;
    private long max = 0;
    private double interest = 0;
    private boolean isAmountValid = false;
    private boolean isDurationValid = false;
    public void loadInfo(int type, int index, long max, double interest){
        this.index = index;
        this.type = type;
        this.max = max;
        this.interest = interest;
        loanAccountID.setEditable(false);
        loanMethod.setEditable(false);
        loanAccountID.setText(AccountManager.getInstance().getCurrentAccount().getAccountID());
        loanMethod.setText(((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getType().toString() + " - " + interest + "%/tháng");
        amount.setPromptText("Hạn mức: " + max + AccountManager.getInstance().getCurrentAccount().getCurrency());
        switch (type){
            case 1:
                switch (index){
                    case 0:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tieu dung");
                        break;
                    case 1:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay gia dung");
                        break;
                    case 2:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tai san lon");
                        break;
                    default:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tien");
                        break;
                }
                break;
            case 2:
                switch (index){
                    case 0:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay kinh doanh nho");
                        break;
                    case 1:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay kinh doanh mo rong");
                        break;
                    case 2:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay kinh doanh quy mo lon");
                        break;
                    default:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tien");
                        break;
                }
                break;
            case 3:
                switch (index){
                    case 0:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay mua o to pho thong");
                        break;
                    case 1:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay mua o to cao cap");
                        break;
                    default:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tien");
                        break;
                }
                break;
            case 4:
                switch (index){
                    case 0:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay mua nha o");
                        break;
                    case 1:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay dau tu bat dong san");
                        break;
                    default:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tien");
                        break;
                }
                break;
            case 5:
                switch (index){
                    case 0:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay dau tu ca nhan");
                        break;
                    case 1:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay dau tu nang cao");
                        break;
                    default:
                        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tien");
                        break;
                }
                break;
            default:
                description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " vay tien");
        }
    }
    @FXML
    void QuayLai(ActionEvent event){
        Pair<Parent, LoanChooseMethodController> scene = SceneUtils.getRootAndController("loanScene/loan_choose_method.fxml");
        scene.getValue().loadInfo(this.type, this.index);
        SceneUtils.switchScene(mainStage, scene.getKey());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amount.textProperty().addListener((observable, _, value) -> {
            amountLog.setText(NumberToVietnameseWord.displayError(value));
            if(!amountLog.getText().isEmpty()){
                amountLog.setFill(Color.rgb(255,0,0));
                isAmountValid = false;
            }
            else{
                if(Long.parseLong(value) > max){
                    amountLog.setText("Số tiền vượt quá hạn mức!");
                    amountLog.setFill(Color.rgb(255, 0, 0));
                    isAmountValid = false;
                }
                else{
                    amountLog.setText(NumberToVietnameseWord.numberToVietnameseWords(Long.parseLong(value)));
                    amountLog.setFill(Color.rgb(0, 0, 0));
                    isAmountValid = true;
                }
            }
        });
        durationField.textProperty().addListener((observable, _, value) -> {
            durationLog.setText(NumberToVietnameseWord.displayError(value));
            if(!durationLog.getText().isEmpty()){
                if(durationField.getText().isEmpty()){
                    durationLog.setText("Vui lòng nhập thời hạn vay tiền");
                }
                else durationLog.setText("Thời hạn không hợp lệ!");
                durationLog.setFill(Color.rgb(255,0,0));
                isDurationValid = false;
            }
            else {
                durationLog.setText("");
                durationLog.setFill(Color.rgb(0, 0, 0));
                isDurationValid = true;
            }
        });
    }

    @FXML
    public void TiepTuc(ActionEvent event){
        if(amount.getText().isEmpty()){
            amountLog.setText("Vui lòng nhập số tiền!");
            amountLog.setFill(Color.rgb(255, 0, 0));
            isAmountValid = false;
        }
        if(durationField.getText().isEmpty()){
            durationLog.setText("Vui lòng nhập thời hạn vay tiền");
            durationLog.setFill(Color.rgb(255,0,0));
            isDurationValid = false;
        }
        if(isAmountValid && isDurationValid){
            ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setDescription(description.getText());
            ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setInterest(this.interest);
            ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setDuration(Long.parseLong(durationField.getText()));
            TransactionManager.getInstance().newTransaction(
                    TransactionType.LOAN,
                    Long.parseLong(amount.getText()),
                    "VND",
                    AccountManager.getInstance().getCurrentAccount(),
                    AccountManager.getInstance().findCheckingAccount(AccountManager.getInstance().getCurrentAccount()),
                    description.getText()
            );
        }
        Pair<Parent, VerifyController> scene = SceneUtils.getRootAndController("verify/verify_scene.fxml");
        scene.getValue().loadLoanInfo(this.type, this.index, this.max, this.interest);
        scene.getValue().displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());
        SceneUtils.switchScene(mainStage,scene.getKey());
    }
}
