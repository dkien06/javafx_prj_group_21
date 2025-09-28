package com.example.javafx_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_login;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private CheckBox chk_remember;
    @FXML
    private Label WrongPasswordLabel ;

    @FXML
    public void SwitchScene2(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();
        if(!chk_remember.isSelected()){
            if(!password.equals(userdata.getPassword())){
                WrongPasswordLabel.setText("Wrong Password");
                return;
            }
        }
        else{
            userdata.setPassword(password);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view2.fxml"));
        if(fxmlLoader==null){
            System.out.println("ERROR");
        }
        root = fxmlLoader.load(); // phải load ở đây!
        Controller2 controller = fxmlLoader.getController();
        controller.ChangeNameLabel(username);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
