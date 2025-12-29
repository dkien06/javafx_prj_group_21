package com.example.javafx_app.controller.Staff;

import com.example.javafx_app.controller.block.ReviewBlockController;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.LoanStatus;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ReviewController implements Initializable {
    @FXML VBox vbox_xet_duyet_vay;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicInteger count = new AtomicInteger();
        AccountManager.getInstance().getAccountList().forEach((accountID, account) -> {
            if(account instanceof LoanAccount){
                if(((LoanAccount)account).getStatus() == LoanStatus.REVIEW) {
                    count.getAndIncrement();
                    Pair<Parent, ReviewBlockController> block = SceneUtils.getRootAndController("StaffScene/review_block.fxml");
                    block.getValue().setData((LoanAccount) account);
                    vbox_xet_duyet_vay.getChildren().add(block.getKey());
                }
                else if(((LoanAccount)account).getStatus() == LoanStatus.OVERDUE){
                    if(((LoanAccount)account).getDuration() < 0){
                        count.getAndIncrement();
                        Pair<Parent, ReviewBlockController> block = SceneUtils.getRootAndController("StaffScene/review_block.fxml");
                        block.getValue().setData((LoanAccount) account);
                        vbox_xet_duyet_vay.getChildren().add(block.getKey());
                    }
                }
            }
        });
        if(count.get() == 0){
            Label noReviewAccount = new Label("Không có yêu cầu xét duyệt");
            noReviewAccount.setAlignment(Pos.CENTER);
            vbox_xet_duyet_vay.getChildren().add(noReviewAccount);
        }
    }
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(SceneUtils.getStageFromEvent(event), "HomeScenes/staff_home_scene.fxml");
    }
}
