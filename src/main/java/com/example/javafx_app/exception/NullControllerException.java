package com.example.javafx_app.exception;

/**
 * Lỗi không tồn tại controller trong scene. Mé tui hay bị kiểu này nhiều vl:)
 */
public class NullControllerException extends NullPointerException {
    public NullControllerException(String message) {
        super(message);
    }
    public static void throwException(){
        throw new NullControllerException("Scene này làm gì có controller kiểu này:))");
    }
}
