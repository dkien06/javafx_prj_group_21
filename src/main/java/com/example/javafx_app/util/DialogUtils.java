package com.example.javafx_app.util;

import java.io.IOException;

public class DialogUtils {
    public static void optionDialog(String path) throws IOException {
        Runtime.getRuntime().exec("target\\classes\\com\\example\\javafx_app\\c_file\\" + path);
    }
    public static void optionDialog(String path, String input) throws IOException {
        Runtime.getRuntime().exec("target\\classes\\com\\example\\javafx_app\\c_file\\" + path + " " + input);
    }
}
