package com.example.javafx_app.exception;

public class IllegalAccountSignUpException extends RuntimeException {
    public IllegalAccountSignUpException(String message) {
        super(message);
    }
    public IllegalAccountSignUpException(){super("Đã tạo checkingAccount đâu mà đòi tạo Saving/LoanAccount???");}
}
