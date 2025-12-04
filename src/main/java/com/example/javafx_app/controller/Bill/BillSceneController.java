package com.example.javafx_app.controller.Bill;

import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class BillSceneController {



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
        SceneUtils.switchScene(mainStage, "BillScene/water_bill_first_scene.fxml");
    }
    @FXML
    void GoToElectricScene(){
        SceneUtils.switchScene(mainStage, "BillScene/electric_bill_first_scene.fxml");
    }
    @FXML
    void GoToInternetScene(){
        SceneUtils.switchScene(mainStage, "BillScene/internet_bill_first_scene.fxml");
    }
    @FXML
    void GoToTuitionScene(){
        SceneUtils.switchScene(mainStage, "BillScene/school_fee_bill_first_scene.fxml");
    }
    @FXML
    void GoToBillList(){
        SceneUtils.switchScene(mainStage, "BillScene/list_bill_scene.fxml");
    }

}
