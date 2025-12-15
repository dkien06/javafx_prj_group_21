package com.example.javafx_app.controller.Support;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class SupportHomeController {
    @FXML void GoToContactScene(){
        System.out.println("Contact");
        SceneUtils.switchScene(mainStage,"SupportScene/contact_information_scene.fxml");
    }
    @FXML void GoToQuestionScene(){
        SceneUtils.switchScene(mainStage,"SupportScene/frequently_asked_question_scene.fxml");
    }
    @FXML void XemTaiKhoan(){
        SceneUtils.switchScene(mainStage,"account_scene.fxml");
    }

    @FXML void XemCaiDat(){
        SceneUtils.switchScene(mainStage,"setting/setting.fxml");
    }
    @FXML void QuayLai(){
        SceneUtils.switchScene(mainStage,
                AccountManager.getInstance().chooseHomeScene(AccountManager.getInstance().getCurrentAccount()));
    }
}
