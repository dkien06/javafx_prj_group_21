package com.example.javafx_app.exception;

import com.example.javafx_app.util.DialogUtils;

public class CodeUnderConstruction extends RuntimeException {
    public CodeUnderConstruction(String message) {
        super(message);
    }
    public CodeUnderConstruction(){super("Code chưa viết hoặc đang bảo trì, sorry:))");}
    public static void throwException(){
        DialogUtils.errorDialogButton(
                "Chưa code xong",
                "Hiện tại đang bảo trì",
                "Code chưa viết hoặc đang bảo trì, sorry:))",
                "OK"
        );
        throw new CodeUnderConstruction();
    }
}
