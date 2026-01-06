package com.example.javafx_app.controller.verify;

import com.example.javafx_app.BankApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class OTPMessage {
    public static void makeMessage(String message) {
        try {
            //Chấm hỏi ở đây là một số máy không run được file message trực tiếp được, tra ra mới biết do file bị nén, lạy ạ:)
            //Cho nên cả mấy code đằng dưới là đang giải nén file:)
            InputStream exeStream = BankApplication.class.getResourceAsStream("C++file/message.exe");
            if (exeStream == null) {
                throw new IOException("Không tìm thấy file message.exe trong resources");
            }
            File tempExe = File.createTempFile("message_temp", ".exe");
            Files.copy(exeStream, tempExe.toPath(), StandardCopyOption.REPLACE_EXISTING);

            tempExe.setExecutable(true); //Cho nó chạy được
            tempExe.deleteOnExit(); //File tạm đấy được xóa sau khi chạy

            //H mới bắt đầu chạy file exe này
            ProcessBuilder pb = new ProcessBuilder(tempExe.getAbsolutePath(), message);
            Process process = pb.start();

            int exitCode = process.waitFor();
            System.out.println("C++ program exited with code: " + exitCode);

            //Sau khi chạy nếu file đấy còn tồn tại thì xóa
            if (tempExe.exists()) {
                boolean deleted = tempExe.delete();
                if (!deleted) {
                    tempExe.deleteOnExit();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        OTPMessage.makeMessage("Blabla");
    }
}
