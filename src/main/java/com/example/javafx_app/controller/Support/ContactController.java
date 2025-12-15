package com.example.javafx_app.controller.Support;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class ContactController {
    @FXML void XemTaiKhoan(){
        SceneUtils.switchScene(mainStage,"account_scene.fxml");
    }

    @FXML void XemCaiDat(){
        SceneUtils.switchScene(mainStage,"setting/setting.fxml");
    }
    @FXML void QuayLai(){
        SceneUtils.switchScene(mainStage,
                "SupportScene/support_home_scene.fxml");
    }
}
