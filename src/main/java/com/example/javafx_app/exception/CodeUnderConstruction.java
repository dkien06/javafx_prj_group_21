package com.example.javafx_app.exception;

public class CodeUnderConstruction extends RuntimeException {
    public CodeUnderConstruction(String message) {
        super(message);
    }
    public CodeUnderConstruction(){super("Code chưa viết hoặc đang bảo trì, sorry:))");}
    public static void throwException(){
        throw new CodeUnderConstruction();
    }
}
