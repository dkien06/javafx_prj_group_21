package com.example.javafx_app.controller.Support;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class QuestionController {
    @FXML
    void XemTaiKhoan(){
        SceneUtils.switchScene(mainStage,"account_scene.fxml");
    }

    @FXML void XemCaiDat(){
        SceneUtils.switchScene(mainStage,"setting/setting.fxml");
    }
    @FXML void QuayLai(){
        SceneUtils.switchScene(mainStage,
                AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
    }
    @FXML void Faq1() {
        SceneUtils.switchScene(mainStage, "SupportScene/faq_1_remake.fxml");
    }

    @FXML void Faq2() {
        SceneUtils.switchScene(mainStage, "SupportScene/faq_2_remake.fxml");
    }

    @FXML void Faq3() {
        SceneUtils.switchScene(mainStage, "SupportScene/faq_3_remake.fxml");
    }

}
