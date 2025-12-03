package com.example.javafx_app.exception;

import java.util.Random;

public class NonExistedPath extends RuntimeException {
    public NonExistedPath(String message) {
        super(message);
    }
    private static final String[] randomMessage = {
            "Đường dẫn méo đúng kìa:)",
            "Sai đường dẫn kìa mày:)",
            "Check lại đường dẫn kìa bạn:)",
    };
    private static String generateMessage(){
        Random r = new Random();
        return randomMessage[r.nextInt(randomMessage.length)];
    }
    public static void throwException(){
        throw new NonExistedPath(generateMessage());
    }
}
