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

//Scene gia hạn
public class ExtendDurationController implements Initializable {
    @FXML private TextField loanAccountID;
    @FXML private TextField amount;
    @FXML private TextField durationField;
    @FXML private TextArea description;
    @FXML private Text durationLog;

    private boolean isDurationValid = false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loanAccountID.setText(AccountManager.getInstance().getCurrentAccount().getAccountID());
        amount.setText(((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt() + "");
        loanAccountID.setEditable(false);
        amount.setEditable(false);
        description.setText(AccountManager.getInstance().getCurrentAccount().getAccountName().toUpperCase() + " gia han");
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
    public void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage, "loanScene/loan_info_and_history.fxml");
    }
    public void TiepTuc(ActionEvent event){
        if(durationField.getText().isEmpty()){
            durationLog.setText("Vui lòng nhập thời hạn vay tiền");
            durationLog.setFill(Color.rgb(255,0,0));
            isDurationValid = false;
        }
        if(isDurationValid){
            ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setDescription(description.getText());
            ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setDuration(-Long.parseLong(durationField.getText()));
            TransactionManager.getInstance().newTransaction(
                    TransactionType.LOAN,
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt(),
                    "VND",
                    AccountManager.getInstance().getCurrentAccount(),
                    AccountManager.getInstance().findCheckingAccount(AccountManager.getInstance().getCurrentAccount()),
                    description.getText()
            );
        }
        Pair<Parent, VerifyController> scene = SceneUtils.getRootAndController("verify/verify_scene.fxml");
        scene.getValue().displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());
        SceneUtils.switchScene(mainStage,scene.getKey());
    }
}
