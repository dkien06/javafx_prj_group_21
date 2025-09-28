package com.example.javafx_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller2 {
    @FXML
    private Label nameLabel ;
    @FXML
    private Button btn_return ;
    public void ChangeNameLabel(String username) {
        nameLabel.setText("Hello"+username);
    }
    @FXML
    public void ReturnLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Hello-view1.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
