package com.example.javafx_app.controller.verify;
import java.io.IOException;

public class OTPMessage {
    public static void makeMessage(String message) {
        try {
            // 1. Phải là file .exe đã biên dịch, không phải file .cpp
            String exePath = "src\\main\\resources\\com\\example\\javafx_app\\C++file\\message.exe";

            // 2. Truyền exePath và message như 2 đối số riêng biệt
            ProcessBuilder pb = new ProcessBuilder(exePath, message);
            Process process = pb.start();
            int exitCode = process.waitFor();
            System.out.println("C++ program exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
