package com.example.javafx_app.controller.saving;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.controller.verify.VerifyController;
import com.example.javafx_app.convert.NumberToVietnameseWord;
import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.object.Account.SavingType;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class SavingController implements Initializable {
    @FXML private TextField savingAccountID;
    @FXML private TextField savingMethod;
    @FXML private TextField amount;
    @FXML private Text amountLog;
    @FXML private Label extraInfoLabel;
    @FXML private Text extraInfoNoteText;
    @FXML private TextField extraInfoField;
    @FXML private Text extraInfoLog;
    @FXML private TextArea description;
    boolean isAmountValid = false;
    boolean isExtraInfoValid = false;
    private SavingAccount currentSavingAccount;
    public void loadSaving(Transaction transaction){
        amount.setText(Long.toString(transaction.getAmount()));
        switch (currentSavingAccount.getType()){
            case FLEXIBLE:
                break;
            case FIXED:
                extraInfoField.setText(Integer.toString(currentSavingAccount.getFixedDuration()));
                break;
            case ACCUMULATED:
                extraInfoField.setText(Long.toString(currentSavingAccount.getAccumulatedAmount()));
                break;
            default:
                throw new MysteriousException();
        }
        description.setText(transaction.getDescription());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentSavingAccount = (SavingAccount)AccountManager.getInstance().getCurrentAccount();
        savingAccountID.setText(currentSavingAccount.getAccountID());
        savingAccountID.setEditable(false);
        savingMethod.setEditable(false);
        description.setText(currentSavingAccount.getAccountName().toUpperCase() + " gui tien");
        switch (currentSavingAccount.getType()){
            case FLEXIBLE:
                savingMethod.setText("Tiền gửi linh hoạt - " + Constant.SAVING_FLEXIBLE_INTEREST_RATE_PER_YEAR + "%/năm");
                break;
            case FIXED:
                savingMethod.setText("Tiền gửi kì hạn - " + Constant.SAVING_FIXED_INTEREST_RATE_PER_YEAR + "%/năm");
                break;
            case ACCUMULATED:
                savingMethod.setText("Tiền gửi kì hạn - " + Constant.SAVING_ACCUMULATE_INTEREST_RATE_PER_YEAR + "%/năm");
                break;
            default:
                throw new MysteriousException();
        }
        amount.textProperty().addListener((observable, _, value) -> {
            amountLog.setText(NumberToVietnameseWord.displayError(value));
            if(!amountLog.getText().isEmpty()){
                amountLog.setFill(Color.rgb(255,0,0));
                isAmountValid = false;
            }
            else{
                amountLog.setText(NumberToVietnameseWord.numberToVietnameseWords(Long.parseLong(value)));
                amountLog.setFill(Color.rgb(255, 255, 255));
                isAmountValid = true;
            }
        });
        switch (currentSavingAccount.getType()){
            case FLEXIBLE:
                extraInfoLabel.setText("");
                extraInfoLabel.setVisible(false);
                extraInfoNoteText.setText("");
                extraInfoNoteText.setVisible(false);
                extraInfoField.setEditable(false);
                extraInfoField.setVisible(false);
                extraInfoLog.setText("");
                extraInfoLog.setVisible(false);
                isExtraInfoValid = true;
                break;
            case FIXED:
                extraInfoLabel.setText("Thời hạn (tháng)");
                extraInfoLabel.setVisible(true);
                extraInfoNoteText.setText("Nhập thời hạn bạn muốn gửi tiền");
                extraInfoNoteText.setVisible(true);
                extraInfoField.setEditable(true);
                extraInfoField.setVisible(true);
                extraInfoLog.setVisible(true);
                extraInfoField.textProperty().addListener(((observableValue, _, value) -> {
                    try {
                        if (!value.isEmpty() && value.matches("\\d+")) {
                            extraInfoLog.setText("");
                            extraInfoLog.setFill(Color.rgb(255,0,0));
                            isExtraInfoValid = true;
                        } else {
                            if(value.isEmpty()) extraInfoLog.setText("Vui lòng nhập thời hạn");
                            else extraInfoLog.setText("Thời hạn không hợp lệ");
                            extraInfoLog.setFill(Color.rgb(255,0,0));
                            isExtraInfoValid = false;
                        }
                    } catch (NumberFormatException e) {
                        extraInfoLog.setText("Thời hạn không hợp lệ");
                        extraInfoLog.setFill(Color.rgb(255,0,0));
                        isExtraInfoValid = false;
                    }
                }));
                break;
            case ACCUMULATED:
                extraInfoLabel.setText("Số tiền tích góp");
                extraInfoLabel.setVisible(true);
                extraInfoNoteText.setText("Nhâp số tiền bạn muốn tích góp mỗi tháng");
                extraInfoNoteText.setVisible(true);
                extraInfoField.setEditable(true);
                extraInfoField.setVisible(true);
                extraInfoLog.setVisible(true);
                extraInfoField.textProperty().addListener((observable, _, value) -> {
                    extraInfoLog.setText(NumberToVietnameseWord.displayError(value));
                    if(!extraInfoLog.getText().isEmpty()){
                        extraInfoLog.setFill(Color.rgb(255,0,0));
                        isExtraInfoValid = false;
                    }
                    else{
                        extraInfoLog.setText(NumberToVietnameseWord.numberToVietnameseWords(Long.parseLong(value)));
                        extraInfoLog.setFill(Color.rgb(255, 255, 255));
                        isExtraInfoValid = true;
                    }
                });
                break;
            default:
                throw new MysteriousException();
        }
    }

    @FXML
    void QuayLai(ActionEvent event){
        ((SavingAccount)AccountManager.getInstance().getCurrentAccount()).setType(SavingType.NONE);
        SceneUtils.switchScene(mainStage, "SavingScene/saving_choose_method_scene.fxml");
    }
    @FXML
    void TiepTuc(ActionEvent event){
        if(isAmountValid && isExtraInfoValid){
            TransactionManager.getInstance().newTransaction(
                    TransactionType.DEPOSIT,
                    Long.parseLong(amount.getText()),
                    "VND",
                    AccountManager.getInstance().findCheckingAccount(currentSavingAccount),
                    AccountManager.getInstance().getCurrentAccount(),
                    description.getText()
            );
            switch (currentSavingAccount.getType()){
                case FIXED:
                    ((SavingAccount)AccountManager.getInstance().getCurrentAccount()).setFixedDuration(Integer.parseInt(extraInfoField.getText()));
                    break;
                case ACCUMULATED:
                    ((SavingAccount)AccountManager.getInstance().getCurrentAccount()).setAccumulatedAmount(Long.parseLong(extraInfoField.getText()));
                    break;
                case FLEXIBLE:
                    break;
                default:
                    throw new MysteriousException();
            }
            Pair<Parent, VerifyController> scene = SceneUtils.getRootAndController("verify/verify_scene.fxml");
            scene.getValue().displayTransactionInformation(TransactionManager.getInstance().getCurrentTransaction());
            SceneUtils.switchScene(mainStage,scene.getKey());
        }
    }
}
