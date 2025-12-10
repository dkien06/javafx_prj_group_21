package com.example.javafx_app.exception;

import com.example.javafx_app.util.DialogUtils;

/**
 * Lỗi không tồn tại controller trong scene. Mé tui hay bị kiểu này nhiều vl:)
 */
public class NullControllerException extends NullPointerException {
    public NullControllerException(String message) {
        super(message);
    }
    public static void throwException(){
        DialogUtils.errorDialogButton(
                "Lỗi controller",
                "Không tồn tại controller trong scene này",
                "Scene này làm gì có controller kiểu này:))",
                "OK"
        );
        throw new NullControllerException("Scene này làm gì có controller kiểu này:))");
    }
}
