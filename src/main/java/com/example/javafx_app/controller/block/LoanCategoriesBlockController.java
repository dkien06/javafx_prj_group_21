package com.example.javafx_app.controller.block;

import com.example.javafx_app.config.Constant;
import com.example.javafx_app.controller.loan.LoanChooseMethodController;
import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import static com.example.javafx_app.config.Constant.mainStage;

public class LoanCategoriesBlockController {
    @FXML private Button button;
    @FXML private Label titleLabel;
    @FXML private Text contentLabel;
    @FXML private Label maxLabel;
    private int type = 0;
    public void setData(String title, String content, int index, int type){
        this.type = type;
        button.setText("" + index);
        button.setTextFill(Color.color(1.0,1.0,1.0,0.0));
        titleLabel.setText(title);
        contentLabel.setText(content);
        switch (type){
            case 1:
                maxLabel.setText("Hạn mức: " + Constant.max1[index] + "VND");
                break;
            case 2:
                maxLabel.setText("Hạn mức: " + Constant.max2[index] + "VND");
                break;
            case 3:
                maxLabel.setText("Hạn mức: " + Constant.max3[index] + "VND");
                break;
            case 4:
                maxLabel.setText("Hạn mức: " + Constant.max4[index] + "VND");
                break;
            case 5:
                maxLabel.setText("Hạn mức: " + Constant.max5[index] + "VND");
                break;
            default:
                maxLabel.setText("");
                break;
        }
    }
    @FXML
    void ButtonFunc(ActionEvent event){
        Button clickedButton = (Button) event.getSource();
        Pair<Parent, LoanChooseMethodController> scene = SceneUtils.getRootAndController("loanScene/loan_choose_method.fxml");
        if(clickedButton.getText().equals("0")){
            scene.getValue().loadInfo(type, 0);
        }
        else if(clickedButton.getText().equals("1")){
            scene.getValue().loadInfo(type, 1);
        }
        else if(clickedButton.getText().equals("2")){
            scene.getValue().loadInfo(type, 2);
        }
        else throw new MysteriousException();
        SceneUtils.switchScene(mainStage, scene.getKey());
    }
}
