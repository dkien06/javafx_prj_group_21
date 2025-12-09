package com.example.javafx_app.exception;

import com.example.javafx_app.util.DialogUtils;

/**
 * Lỗi mà các nhà khoa học éo giải thích được (Nói chung là mấy lỗi ngoài ý muốn), dùng thay cho return null
 */
public class MysteriousException extends RuntimeException {
    public MysteriousException(String message) {
        super(message);
    }
    public MysteriousException(){
        super("Có lỗi xảy ra!");
        DialogUtils.errorDialogButton(
                "Lỗi",
                "Có lỗi xảy ra",
                "Chắc do lỗi chỗ nào rồi nên mới báo thế này:))",
                "OK"
        );
    }
}
