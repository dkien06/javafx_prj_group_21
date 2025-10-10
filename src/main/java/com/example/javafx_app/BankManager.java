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
        //Check gmail (Phức tạp vcl:))
        public enum EmailState{
            EMPTY,
            WRONG_FORM,
            RIGHT
        }
        //Gmail: [ten_nguoi_dung]@[duong_dan] VD: NguyenVanA1970@gmail.com Binh.TT2412345@sis.hust.edu.vn
        public static EmailState isGmailValid(String email){
            if(email.isEmpty())return EmailState.EMPTY;
            int viTriACong = email.indexOf('@');
            //Gmail thì phải có '@' hiển nhiên vcl:))
            if(viTriACong == -1)return EmailState.WRONG_FORM;
            //Check [ten_nguoi_dung]
            //Kí tự đầu phải bằng chữ. Các kí tự còn lại có thể có chữ cái, chữ số và kí tự '.','_','+'
            if(!Character.isLetter(email.charAt(0)))return EmailState.WRONG_FORM;
            for(int i = 1; i < viTriACong; i++){
                if(!(Character.isLetterOrDigit(email.charAt(i)) || email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '+'))
                    return EmailState.WRONG_FORM;
            }
            //Check [duong_dan]
            //Tất cả các kí tự phải bằng chữ in thường, bắt buộc phải có kí tự '.'
            int dotCounter = 0;
            for(int i = viTriACong + 1; i<email.length(); i++){
                if(!(Character.isLowerCase(email.charAt(i)) || (i != viTriACong && email.charAt(i) == '.'))){
                    return EmailState.WRONG_FORM;
                }
                if(email.charAt(i) == '.')dotCounter++;
            }
            if(dotCounter == 0)return EmailState.WRONG_FORM;
            return EmailState.RIGHT;
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
                if(!Character.isDigit(phoneNumber.charAt(i))){
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
                if(!Character.isDigit(citizenNumber.charAt(i))){
                    return CitizenIDState.WRONG_FORM;
                }
            }
            return CitizenIDState.RIGHT;
        }
    }
}
