package com.example.javafx_app.controller.loan;

import com.example.javafx_app.controller.block.LoanHistoryBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.LoanStatus;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanInfoHistoryController implements Initializable {

    @FXML
    private Label accountID;

    @FXML
    private Label ki_han_tiep_theo;

    @FXML
    private Label lai_suat;

    @FXML
    private Label name;

    @FXML
    private Label phuong_thuc_vay;

    @FXML
    private Button return_btn;

    @FXML
    private ScrollPane rootPane;

    @FXML
    private Label so_du_no;

    @FXML
    private Label so_tien_can_thanh_toan;

    @FXML
    private VBox vbox_lich_su_giao_dich;

    @FXML
    private VBox vbox_thong_tin_vay;

    @FXML
    private VBox vbox_to;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText("Họ và tên: " + AccountManager.getInstance().getCurrentAccount().getAccountName());
        so_du_no.setText("Số dư nợ: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt());
        accountID.setText("Số tài khoản: " + AccountManager.getInstance().getCurrentAccount().getAccountID());
        ki_han_tiep_theo.setText("Kì hạn tiếp theo: ");
        so_tien_can_thanh_toan.setText("Số tiền cần thanh toán: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt());
        lai_suat.setText("Lãi suất: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getInterest());
        phuong_thuc_vay.setText("Phương thức vay: " + ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getType().toString());
        ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getHistories().forEach((transaction, loanStatusStringPair) -> {
            Pair<Parent, LoanHistoryBlockController> scene = SceneUtils.getRootAndController("loanScene/loan_history_block.fxml");
            scene.getValue().loadData(transaction,loanStatusStringPair.getKey(),loanStatusStringPair.getValue());
            vbox_lich_su_giao_dich.getChildren().add(scene.getKey());
        });
    }

    @FXML
    void QuayLai(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "HomeScenes/loan_account_home_scene.fxml");
    }
}
