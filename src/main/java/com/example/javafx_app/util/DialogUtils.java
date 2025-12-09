package com.example.javafx_app.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DialogUtils {
    public static int errorDialogButton(String title, String header, String content, String... buttons){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Tạo các nút
        List<ButtonType> buttonList = new ArrayList<>();
        for (String button : buttons) {
            ButtonType newButton = new ButtonType(button);
            buttonList.add(newButton);
        }
        alert.getButtonTypes().setAll(buttonList);

        // Hiển thị và lấy kết quả
        Optional<ButtonType> result = alert.showAndWait();
        return result.map(buttonList::indexOf).orElse(-1);
    }
    public static String dialogTextField(){
        return "";
    }
}
