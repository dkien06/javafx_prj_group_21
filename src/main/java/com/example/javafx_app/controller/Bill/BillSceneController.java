package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import static com.example.javafx_app.config.Constant.mainStage;

public class BillSceneController {
    @FXML
    Text ErrorLog ;
    CheckingAccount checkingAccount = (CheckingAccount) AccountManager.getInstance().getCurrentAccount() ;

    @FXML
    void QuayLai() {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/HomeScenes/checking_account_home_scene.fxml");
    }

    @FXML
    void XemCaiDat() {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/setting/setting.fxml");
    }

    @FXML
    void XemTaiKhoan() {
        SceneUtils.switchScene(mainStage, "/com/example/javafx_app/account_scene.fxml");

    }
    @FXML
    void GoToWaterScene() {
        if(checkingAccount.isWaterService()){
            ErrorLog.setText("Bạn đã có nhà cung cấp dịch vụ này");
            return;
        }
        SceneUtils.switchScene(mainStage, "BillScene/water_bill_first_scene.fxml");
    }
    @FXML
    void GoToElectricScene(){
        if(checkingAccount.isElectricService()){
            ErrorLog.setText("Bạn đã có nhà cung cấp dịch vụ này");
            return;
        }
        SceneUtils.switchScene(mainStage, "BillScene/electric_bill_first_scene.fxml");
    }
    @FXML
    void GoToInternetScene(){
        if(checkingAccount.isInternetService()){
            ErrorLog.setText("Bạn đã có nhà cung cấp dịch vụ này");
            return;
        }
        SceneUtils.switchScene(mainStage, "BillScene/internet_bill_first_scene.fxml");
    }
    @FXML
    void GoToTuitionScene(){
        if(checkingAccount.isSchoolService()){
            ErrorLog.setText("Bạn đã có nhà cung cấp dịch vụ này");
            return;
        }
        SceneUtils.switchScene(mainStage, "BillScene/school_fee_bill_first_scene.fxml");
    }
    @FXML
    void GoToBillList(){
        SceneUtils.switchScene(mainStage, "BillScene/list_bill_scene.fxml");
    }

}
