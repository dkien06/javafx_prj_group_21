package com.example.javafx_app.controller.saving;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.object.Account.SavingType;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafx_app.config.Constant.mainStage;

public class SavingChooseMethodController implements Initializable {
    @FXML private Label flexibleInterestRateLabel;
    @FXML private Label fixedInterestRateLabel;
    @FXML private Label accumulatedInterestLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flexibleInterestRateLabel.setText(Constant.SAVING_FLEXIBLE_INTEREST_RATE_PER_MONTH*100 + "%/năm");
        fixedInterestRateLabel.setText(Constant.SAVING_FIXED_INTEREST_RATE_PER_MONTH*100 + "%/năm");
        accumulatedInterestLabel.setText(Constant.SAVING_ACCUMULATE_INTEREST_RATE_PER_MONTH*100 + "%/năm");
    }
    @FXML
    void MoNgay1(ActionEvent event){
        MoNgay(event, SavingType.FLEXIBLE);
    }
    @FXML
    void MoNgay2(ActionEvent event){
        MoNgay(event, SavingType.FIXED);
    }
    @FXML
    void MoNgay3(ActionEvent event){
        MoNgay(event, SavingType.ACCUMULATED);
    }
    void MoNgay(ActionEvent event, SavingType type){
        ((SavingAccount)AccountManager.getInstance().getCurrentAccount()).setType(type);
        Pair<Parent,SavingController> scene = SceneUtils.getRootAndController("SavingScene/saving_scene.fxml");
        SceneUtils.switchScene(mainStage,scene.getKey());
    }
    @FXML
    void QuayLai(ActionEvent event){
        SceneUtils.switchScene(mainStage,"HomeScenes/saving_account_home_scene.fxml");
    }
}
