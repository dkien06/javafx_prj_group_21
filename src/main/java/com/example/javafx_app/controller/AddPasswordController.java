package com.example.javafx_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPasswordController {
    @FXML
    TextField CCCDLabel;
    void displayCCCD(String CCCD){
        CCCDLabel.setText(CCCD);
    }
}
