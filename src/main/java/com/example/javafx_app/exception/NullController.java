package com.example.javafx_app.exception;

public class NullController extends NullPointerException {
    public NullController(String message) {
        super(message);
    }
    public NullController(){super("Scene này làm gì có controller kiểu này:))");}
    public static void throwException(){
        throw new NullController("Scene này làm gì có controller kiểu này:))");
    }
}
