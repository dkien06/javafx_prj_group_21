package com.example.javafx_app.exception;

import com.example.javafx_app.util.DialogUtils;

import java.util.Random;

/**
 * Đường dẫn không đúng (Nhờ cái này đỡ debug mấy tiếng đồng hồ)
 */
public class NonExistedPathException extends RuntimeException {
    public NonExistedPathException(String message) {
        super(message);
    }
    private static final String[] randomMessage = {
            "Đường dẫn không đúng kìa:)",
            "Sai đường dẫn kìa bạn:)",
            "Check lại đường dẫn kìa bạn:)",
    };
    private static String generateMessage(){
        Random r = new Random();
        return randomMessage[r.nextInt(randomMessage.length)];
    }
    public static void throwException(){
        DialogUtils.errorDialogButton(
                "Lỗi đường dẫn",
                "Đường dẫn không tồn tại",
                generateMessage(),
                "OK"
        );
        throw new NonExistedPathException(generateMessage());
    }
}
