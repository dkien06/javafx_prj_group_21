package com.example.javafx_app.controller.Support;

import com.example.javafx_app.util.SceneUtils;
import javafx.fxml.FXML;

import static com.example.javafx_app.config.Constant.mainStage;

public class FaqController {
    @FXML void QuayLai(){
        SceneUtils.switchScene(mainStage,"SupportScene/frequently_asked_question_scene.fxml");
    }
}
