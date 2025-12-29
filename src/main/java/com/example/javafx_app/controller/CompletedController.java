package com.example.javafx_app.controller;

import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.util.SceneUtils;
import com.example.javafx_app.object.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import static com.example.javafx_app.config.Constant.mainStage;

public class CompletedController {
    @FXML private Label billHeadLabel;
    @FXML private Label amountLabel;
    @FXML private Text dateLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label toBankLabel;
    @FXML private Label toAccountFullNameLabel;
    @FXML private Label toAccountIDLabel;

    public void loadTransaction(){
        Transaction cur = TransactionManager.getInstance().getCurrentTransaction();
        switch (cur.getType()){
            case TRANSFER:
                billHeadLabel.setText("Chuyển khoản thành công");
                toAccountFullNameLabel.setVisible(true);
                toAccountFullNameLabel.setText("Người nhận: "+ cur.getToAccount().getAccountName());
                toAccountIDLabel.setVisible(true);
                toAccountIDLabel.setText("Số tài khoản: " + cur.getToAccount().getAccountID());
                descriptionLabel.setText(cur.getDescription());
                toBankLabel.setText("21stBank");
                break;
            case DEPOSIT:
                billHeadLabel.setText("Gửi tiền thành công");
                toAccountFullNameLabel.setVisible(false);
                toAccountIDLabel.setVisible(false);
                descriptionLabel.setText(cur.getDescription());
                toBankLabel.setText("21stBank");
                break;
            case WITHDRAW:
                billHeadLabel.setText("Rút tiền thành công");
                toAccountFullNameLabel.setVisible(false);
                toAccountIDLabel.setVisible(false);
                descriptionLabel.setText(cur.getDescription());
                toBankLabel.setText("21stBank");
                break;
            case REPAY:
                billHeadLabel.setText("Trả nợ thành công");
                toAccountFullNameLabel.setVisible(false);
                toAccountIDLabel.setVisible(false);
                descriptionLabel.setText(cur.getDescription());
                toBankLabel.setText("21stBank");
                break;
            case LOAN:
                if(((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDuration() < 0)
                    billHeadLabel.setText("Tạo hồ sơ vay thành công");
                else billHeadLabel.setText("Tạo hồ sơ gia hạn thành công");
                toAccountFullNameLabel.setVisible(false);
                toAccountIDLabel.setVisible(false);
                toBankLabel.setVisible(false);
                descriptionLabel.setText("Vui lòng chờ để chúng tôi xét duyệt cho bạn!");
                toBankLabel.setText("21stBank");
                break;
            default:
                break;
        }
        amountLabel.setText(TransactionManager.getInstance().
                formatCurrency(cur.getAmount(), cur.getCurrency()));
        dateLabel.setText(String.valueOf(cur.getDate()));
    }
    @FXML
    void TrangChu(ActionEvent event){
        switch (AccountManager.getInstance().getCurrentAccount()) {
            case CheckingAccount checkingAccount ->
                    SceneUtils.switchScene(mainStage, "HomeScenes/checking_account_home_scene.fxml");
            case SavingAccount savingAccount ->
                    SceneUtils.switchScene(mainStage, "HomeScenes/saving_account_home_scene.fxml");
            case LoanAccount loanAccount ->
                    SceneUtils.switchScene(mainStage, "HomeScenes/loan_account_home_scene.fxml");
            case null, default -> throw new MysteriousException();
        }
    }
    @FXML
    void ThucHienGiaoDichKhac(ActionEvent event){
        switch (AccountManager.getInstance().getCurrentAccount()) {
            case CheckingAccount checkingAccount ->
                    SceneUtils.switchScene(mainStage,"TransactionScene/transaction_options_scene.fxml");
            case SavingAccount savingAccount ->
                    SceneUtils.switchScene(mainStage,"SavingScene/saving_choose_method_scene.fxml");
            case LoanAccount loanAccount ->
                    SceneUtils.switchScene(mainStage,"loanScene/loan_choose_option.fxml");
            case null, default -> throw new MysteriousException();
        }
    }
}
