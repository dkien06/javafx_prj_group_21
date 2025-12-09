package com.example.javafx_app.exception;

/**
 * Lỗi mà các nhà khoa học éo giải thích được (Nói chung là mấy lỗi ngoài ý muốn), dùng thay cho return null
 */
public class MysteriousException extends RuntimeException {
    public MysteriousException(String message) {
        super(message);
    }
    public MysteriousException(){
        super("Có lỗi xảy ra!");
    }
}
