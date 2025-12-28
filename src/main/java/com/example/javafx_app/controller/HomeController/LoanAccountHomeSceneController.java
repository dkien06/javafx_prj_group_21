package com.example.javafx_app.controller.HomeController;

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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanAccountHomeSceneController implements Initializable {

    @FXML
    private Button account_btn;

    @FXML
    private Label account_icon;

    @FXML
    private VBox du_no_box;

    @FXML
    private GridPane gridpane1;

    @FXML
    private GridPane gridpane2;

    @FXML
    private Label logo_label;

    @FXML
    private Button noti_btn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label setting_icon;

    @FXML
    private Button settings_btn;

    @FXML
    private Label so_du_no;

    @FXML
    private Label so_du_no_label;

    @FXML
    private Label trang_thai;

    @FXML
    private Label kieu_vay;

    @FXML
    void XemCaiDat(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "setting/setting.fxml");
    }

    @FXML
    void XemTaiKhoan(ActionEvent event) {

    }

    @FXML
    void XemThongBao(ActionEvent event) {

    }

    @FXML
    void hoTro(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "SupportScene/support_home_scene.fxml");
    }

    @FXML
    void thanhToanKhoanVay(ActionEvent event) {

    }

    @FXML
    void thongTinVay(ActionEvent event) {
        SceneUtils.switchScene(mainStage, "loanScene/loan_info_and_history.fxml");
    }

    @FXML
    void vayMoi(ActionEvent event) {
        switch (((LoanAccount) AccountManager.getInstance().getCurrentAccount()).getStatus()){
            case NONE:
                SceneUtils.switchScene(mainStage, "loanScene/loan_choose_option.fxml");
                break;
            case REVIEW:
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thực hiện vay mới");
                alert.setHeaderText("Bạn muốn chắc chắn muốn tạo khoản vay mới?");
                alert.setContentText("Khoản vay chưa duyệt của bạn có thể bị hủy!");

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
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setInterest(0.0);
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setType(LoanType.NONE);
                    ((LoanAccount)AccountManager.getInstance().getCurrentAccount()).setStatus(LoanStatus.NONE);
                    SceneUtils.switchScene(mainStage, "loanScene/loan_choose_option.fxml");
                }
                else return;
                break;
            case ACTIVE,OVERDUE,EXTENDED:
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Không thể thực hiện khoản vay mới");
                alert1.setHeaderText("Bạn đã có khoản vay được duyệt rồi");
                alert1.setContentText("Vui lòng thanh toán khoản vay trước khi thực hiện khoản vay mới");

                ButtonType okButton1 = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButton1 = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert1.getButtonTypes().setAll(okButton1, cancelButton1);

                Optional<ButtonType> result1 = alert1.showAndWait();
                if (result1.isPresent()){
                    return;
                }
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        so_du_no.setText(((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getDebt() + AccountManager.getInstance().getCurrentAccount().getCurrency());
        trang_thai.setText(((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getStatus().toString());
        kieu_vay.setText(((LoanAccount)AccountManager.getInstance().getCurrentAccount()).getType().toString());
    }
}
