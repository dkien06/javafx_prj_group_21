package com.example.javafx_app.controller.block;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class LoanCategoriesBlockController {
    @FXML private Label titleLabel;
    @FXML private Text contentLabel;
    @FXML private Label maxLabel;
    public void setData(String title, String content, long max){
        titleLabel.setText(title);
        contentLabel.setText(content);
        maxLabel.setText("Hạn mức: " + max + "VND");
    }
}
