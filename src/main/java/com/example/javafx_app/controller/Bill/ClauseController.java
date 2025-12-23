package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Bill.BillType;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;

import java.lang.reflect.AccessFlag;

import static com.example.javafx_app.config.Constant.mainStage;

public class ClauseController {
    public static BillType billType;
    private CheckingAccount checkingAccount = (CheckingAccount) AccountManager.getInstance().getCurrentAccount() ;
    @FXML
    void HoanThanh(){
        if(billType == BillType.ELECTRIC){
            checkingAccount.setElectricService(true);
        }
        else if(billType == BillType.WATER){
            checkingAccount.setWaterService(true);
        }
        else if(billType == BillType.INTERNET){
            checkingAccount.setInternetService(true);
        }
        else checkingAccount.setSchoolService(true);
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }
    @FXML
    void QuayLai(){
        SceneUtils.switchScene(mainStage,"BillScene/bill_home_scene.fxml");
    }
}
