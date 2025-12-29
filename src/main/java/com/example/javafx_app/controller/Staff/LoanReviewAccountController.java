package com.example.javafx_app.controller.Staff;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.LoanStatus;
import com.example.javafx_app.object.Account.LoanType;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Optional;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanReviewAccountController {
    @FXML private Label loanAccountNameLabel;
    @FXML private Label loanAccountCitizenIDLabel;
    @FXML private Label loanAccountIDLabel;
    @FXML private Label trang_thai;

    @FXML private Label loanAmountLabel;
    @FXML private Label loanTypeLabel;
    @FXML private Label commitDateLabel;
    @FXML private Label durationLabel;
    @FXML private Label descriptionLabel;

    @FXML private Label yeu_cau;
    @FXML private TextField interestField;
    @FXML private Text interestLog;
    public void loadInfo(String loanAccountID){
        LoanAccount loanAccount = (LoanAccount) AccountManager.getInstance().findAccount(loanAccountID);
        loanAccountNameLabel.setText("Họ và tên: " + loanAccount.getAccountName());
        loanAccountCitizenIDLabel.setText("Số CCCD: " + loanAccount.getCitizenID());
        loanAccountIDLabel.setText(loanAccount.getAccountID());
        trang_thai.setText("Trạng thái: " + loanAccount.getStatus().toString());

        loanAmountLabel.setText("Số tiền vay: " + loanAccount.getDebt() + loanAccount.getCurrency());
        loanTypeLabel.setText("Kiểu vay: " + loanAccount.getType().toString());
        commitDateLabel.setText("Ngày thực hiện: " + loanAccount.getStartLoanDate().toString());
        durationLabel.setText("Kỳ hạn: " + loanAccount.getDuration());
        descriptionLabel.setText(loanAccount.getDescription());
        interestLog.setText("");
        if(loanAccount.getDuration() < 0){
            yeu_cau.setText("Yêu cầu: Gia hạn");
        }
        else yeu_cau.setText("Yêu cầu: Duyệt vay");
        if(loanAccount.getInterest() > 0){
            interestField.setText("" + loanAccount.getInterest());
        }
    }

    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage, "StaffScene/review_scene.fxml");
    }

    @FXML
    void TuChoi(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Từ chối xét duyệt vay");
        alert.setHeaderText("Vui lòng nhập lý do từ chối");

        // 1. Tạo TextField để nhập liệu
        TextField reasonField = new TextField();
        reasonField.setPromptText("Nhập lý do tại đây...");

        // Đưa TextField vào một Layout để hiển thị đẹp hơn
        VBox content = new VBox();
        content.setSpacing(10);
        content.getChildren().add(reasonField);
        alert.getDialogPane().setContent(content);

        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            String reason = reasonField.getText().trim();

            if (reason.isEmpty()) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Lỗi nhập liệu");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Lý do từ chối không được để trống!");
                errorAlert.showAndWait();

                TuChoi(event);
            } else {
                LoanAccount account = (LoanAccount)AccountManager.getInstance().findAccount(loanAccountIDLabel.getText());
                if(account.getDuration() < 0){
                    Transaction transaction = new Transaction(
                            TransactionType.LOAN,
                            account.getDebt(),
                            account.getCurrency(),
                            account,
                            AccountManager.getInstance().findCheckingAccount(account),
                            "Từ chối gia hạn"
                    );
                    account.addToHistories(transaction, reason);
                    account.setDuration(0);
                    SceneUtils.switchScene(mainStage, "StaffScene/review_scene.fxml");
                }
                else{
                    Transaction transaction = new Transaction(
                            TransactionType.LOAN,
                            account.getDebt(),
                            account.getCurrency(),
                            account,
                            AccountManager.getInstance().findCheckingAccount(account),
                            "Từ chối duyệt vay"
                    );
                    account.addToHistories(transaction, reason);
                    account.setDebt(0);
                    account.setDuration(0);
                    account.setInterest(0.0);
                    account.setType(LoanType.NONE);
                    account.setStatus(LoanStatus.NONE);
                    SceneUtils.switchScene(mainStage, "StaffScene/review_scene.fxml");
                }
            }
        }
    }

    @FXML
    void XetDuyet(ActionEvent event) {
        String interestInput = interestField.getText().trim();

        if (interestInput.isEmpty()) {
            interestLog.setText("Vui lòng nhập số lãi");
            interestLog.setFill(Color.RED); // Làm chữ màu đỏ cho nổi bật
            return;
        }

        double interestValue;
        try {
            interestValue = Double.parseDouble(interestInput);
            if (interestValue < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            interestLog.setText("Số lãi không hợp lệ");
            interestLog.setFill(Color.RED);
            return;
        }
        interestLog.setText("");
        LoanAccount account = (LoanAccount)AccountManager.getInstance().findAccount(loanAccountIDLabel.getText());
        if(account.getDuration() < 0){
            account.extend(-(int)account.getDuration());
            account.setDuration(-account.getDuration());
        }
        else{
            Transaction transaction = new Transaction(
                    TransactionType.LOAN,
                    account.getDebt(),
                    account.getCurrency(),
                    account,
                    AccountManager.getInstance().findCheckingAccount(account),
                    descriptionLabel.getText()
            );
            TransactionManager.getInstance().addTransaction(transaction);
            account.loan(AccountManager.getInstance().findCheckingAccount(account), descriptionLabel.getText());
        }
        SceneUtils.switchScene(mainStage, "StaffScene/review_scene.fxml");

    }
}
