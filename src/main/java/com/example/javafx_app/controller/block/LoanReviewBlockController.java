package com.example.javafx_app.controller.block;

import com.example.javafx_app.controller.Staff.LoanReviewAccountController;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Pair;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanReviewBlockController {
    @FXML private Label loanAccountName;
    @FXML private Label loanAccountID;
    @FXML private Label description;
    @FXML private Label amount;
    @FXML private Label icon;
    @FXML private Button button;
    public void setData(LoanAccount account){
        loanAccountName.setText(account.getAccountName());
        loanAccountID.setText(account.getAccountID());
        description.setText(account.getDescription());
        amount.setText(account.getDebt() + account.getCurrency());
        icon.getStyleClass().addAll("icon_container","chuyen_vao");
        button.getStyleClass().addAll("nut_chua_lsgd", "nut_xanh");
    }
    @FXML
    public void ButtonFunc(ActionEvent event){
        Pair<Parent, LoanReviewAccountController> scene = SceneUtils.getRootAndController("StaffScene/loan_review_account_scene.fxml");
        scene.getValue().loadInfo(loanAccountID.getText());
        SceneUtils.switchScene(mainStage, scene.getKey());
    }
}
