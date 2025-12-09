package com.example.javafx_app.controller.Transaction;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class TransactionOptionsController {

    @FXML
    public void goToTransferScene(ActionEvent event) {
        // Chuyển đến màn hình Chuyển tiền cũ
        SceneUtils.switchScene(mainStage, "TransactionScene/transaction_scene.fxml");
    }

    @FXML
    public void goToWithdrawScene(ActionEvent event) {
        // Chuyển đến màn hình Rút tiền (withdraw_scene.fxml)
        SceneUtils.switchScene(mainStage, "TransactionScene/withdraw_scene.fxml");
    }

    @FXML
    public void goToDepositScene(ActionEvent event) {
        // Chuyển đến màn hình Nạp tiền (deposit_scene.fxml)
        SceneUtils.switchScene(mainStage, "TransactionScene/deposit_scene.fxml");
    }

    @FXML
    public void turnBack(ActionEvent event) {
        // Quay lại màn hình chính của tài khoản hiện tại
        SceneUtils.switchScene(mainStage,
                AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
    }
}