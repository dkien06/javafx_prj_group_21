package com.example.javafx_app.controller.loan;

import com.example.javafx_app.controller.block.LoanHistoryBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.LoanStatus;
import com.example.javafx_app.object.Account.LoanType;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanInfoHistoryController implements Initializable {
    @FXML private Label accountID;
    @FXML private Label ki_han_tiep_theo;
    @FXML private Label lai_suat;
    @FXML private Label name;
    @FXML private Label phuong_thuc_vay;
    @FXML private Button return_btn;
    @FXML private ScrollPane rootPane;
    @FXML private Label so_du_no;
    @FXML private Label so_tien_can_thanh_toan;
    @FXML private VBox vbox_lich_su_giao_dich;
    @FXML private VBox vbox_thong_tin_vay;
    @FXML private VBox vbox_to;
    @FXML private Label trang_thai;
    @FXML private Button extra_info_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText("Họ và tên: " + AccountManager.getInstance().getCurrentAccount().getAccountName());
        so_du_no.setText("Số dư nợ: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt());
        accountID.setText("Số tài khoản: " + AccountManager.getInstance().getCurrentAccount().getAccountID());
        ki_han_tiep_theo.setText("Kì hạn tiếp theo: ");
        so_tien_can_thanh_toan.setText("Số tiền cần thanh toán: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt());
        if((((LoanAccount) AccountManager.getInstance().getCurrentAccount()).getInterest()) < 0){
            lai_suat.setText("Lãi suất: Không có");
        }
        else lai_suat.setText("Lãi suất: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getInterest());
        phuong_thuc_vay.setText("Phương thức vay: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getType().toString());
        ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getHistories().forEach((transaction, loanStatusStringPair) -> {
            Pair<Parent, LoanHistoryBlockController> scene = SceneUtils.getRootAndController("loanScene/loan_history_block.fxml");
            scene.getValue().loadData(transaction,loanStatusStringPair.getKey(),loanStatusStringPair.getValue());
            vbox_lich_su_giao_dich.getChildren().add(scene.getKey());
        });
        switch (((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getStatus()){
            case NONE, ACTIVE, EXTENDED:
                extra_info_button.setVisible(false);
                break;
            case REVIEW:
                extra_info_button.setVisible(true);
                extra_info_button.setText("Hủy vay");
                break;
            case OVERDUE:
                extra_info_button.setVisible(true);
                extra_info_button.setText("Gia hạn");
                break;
        }
    }
    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "HomeScenes/loan_account_home_scene.fxml");
    }

    @FXML
    void ButtonFunc(ActionEvent event){
        switch (((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getStatus()){
            case NONE, ACTIVE, EXTENDED:
                break;
            case REVIEW:
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Hủy khoản vay chưa duyệt");
                alert.setHeaderText("Bạn muốn hủy khoản vay chưa duyệt hiện tại của bạn?");
                alert.setContentText("Bạn không thể hoàn tác sau khi thực hiện việc này!");

                ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButton = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == okButton) {
                    Transaction transaction = new Transaction(
                            TransactionType.LOAN,
                            ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt(),
                            AccountManager.getInstance().getCurrentAccount().getCurrency(),
                            AccountManager.getInstance().getCurrentAccount(),
                            AccountManager.getInstance().findCheckingAccount(((LoanAccount)AccountManager.getInstance().getCurrentAccount())),
                            "Hủy vay"
                    );
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).addToHistories(transaction, LoanStatus.CANCELED);
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setDebt(0);
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setDuration(0);
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setInterest(-1.0);
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setType(LoanType.NONE);
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setStatus(LoanStatus.NONE);
                    SceneUtils.switchScene(mainStage, "loanScene/loan_choose_option.fxml");
                }
                else return;
                break;
            case OVERDUE:
                SceneUtils.switchScene(mainStage, "loanScene/extend_duration_scene.fxml");
                break;
        }
    }
}
