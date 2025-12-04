package com.example.javafx_app.util;

import com.example.javafx_app.BankApplication;
import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.exception.NonExistedPathException;
import com.example.javafx_app.exception.NullControllerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

public class SceneUtils {
    public static <T> Pair<Parent, T> getRootAndController(String fxmlFile){
        try{
            FXMLLoader loader = new FXMLLoader(BankApplication.class.getResource(fxmlFile));
            Parent root = loader.load();
            T controller = loader.getController();
            return new Pair<>(root, controller);
        } catch (IllegalStateException e){
            NonExistedPathException.throwException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            NullControllerException.throwException();
        }
        throw new MysteriousException();
    }
    public static void switchScene(Stage stage, Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void switchScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(BankApplication.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IllegalStateException e) {
            NonExistedPathException.throwException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stage getStageFromEvent(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
    public static Stage getStageFromEvent(MouseEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}

