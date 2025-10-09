package com.example.javafx_app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankManager {
    public static final List<Account> ACCOUNTS = new ArrayList<>();
    static {
        for (int i = 1; i <= 100; i++) {
            Account a = new Account(
                    "123456789" + i,     // citizenID
                    "AC" + i,            // accountID
                    "pwd" + i,           // password
                    Math.random() * 1_000_000, // balance
                    "VND",               // currency
                    "0000"               // PIN
            );
            ACCOUNTS.add(a);
        }
        ACCOUNTS.add(new Account("892006","AC","892006",1000,"VND","0000"));
    }
    public static Account getAccount(String accountID) {
        for (Account a : ACCOUNTS) {
            if (a.getCitizenID().equals(accountID)) {
                return a;
            }
        }
        return null;
    }
    // Ham kiem tra mat khau
    public  static boolean VerifyPassword(String citizenID, String password) {
        Account VerifyAccount = BankManager.getAccount(citizenID);
        if(VerifyAccount==null) {return false;}
        return password.equals(VerifyAccount.getPassword());
    }
    //Class kiểm tra thông tin đăng kí(Đm dài vcl:)))
    public static class VerifySignUpInformation{
        //Check họ tên
        public static boolean isFullNameValid(String fullName){
            return !fullName.isEmpty();
        }
        //Check ngày sinh
        public static boolean isDateOfBirthValid(LocalDate dateOfBirth){
            return dateOfBirth != null;
        }
        //Check giới tính
        public static boolean isGenderValid(String gender){
            return gender!=null;
        }
        //Check số điện thoại
        public enum PhoneNumberState{
            EMPTY,
            WRONG_SIZE,
            WRONG_FORM,
            RIGHT,
        }
        public static PhoneNumberState isPhoneNumberVaid(String phoneNumber){
            if(phoneNumber.isEmpty())return PhoneNumberState.EMPTY;
            for(int i = 0; i < phoneNumber.length(); i++){
                if(phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9'){
                    return PhoneNumberState.WRONG_FORM;
                }
            }
            if(phoneNumber.length() != 10)return PhoneNumberState.WRONG_SIZE;
            return PhoneNumberState.RIGHT;
        }
        //Check số CCCD(như số điện thoại thôi)
        public enum CitizenIDState{
            EMPTY,
            WRONG_FORM,
            RIGHT,
        }
        public static CitizenIDState isCitizenIDVaid(String citizenNumber){
            if(citizenNumber.isEmpty())return CitizenIDState.EMPTY;
            for(int i = 0; i < citizenNumber.length(); i++){
                if(citizenNumber.charAt(i) < '0' || citizenNumber.charAt(i) > '9'){
                    return CitizenIDState.WRONG_FORM;
                }
            }
            return CitizenIDState.RIGHT;
        }

    }
}
