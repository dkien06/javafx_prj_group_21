package com.example.javafx_app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static boolean VerifyPassword(String citizenID, String password) {
        Account VerifyAccount = BankManager.getAccount(citizenID);
        if(VerifyAccount==null) {return false;}
        return password.equals(VerifyAccount.getPassword());
    }
    public static enum InformationState {
        EMPTY,
        WRONG_FORM,
        WRONG_SIZE,
        RIGHT
    }
    public static Map<String, InformationState> CheckSignUpInfo(String fullName, LocalDate dateOfBirth, String gender, String email, String phoneNumber, String citizenID){
        //Basically #include <unorder_map> nhưng của java:))
        //Nó khá giống mảng nhưng index thay vì là int thi nó có thể là biến tùy ý. VD: infoState["fullName"]
        //Gán giá trị thay vì trực tiếp (A["blabla"] = 1) thì phải dùng hàm Map.put("blabla", 1)
        //Còn lấy giá trị thì dễ thôi: Map.set("blabla") (=1)
        Map<String, InformationState> infoStates = new HashMap<>();

        //Check họ tên
        if(fullName.isEmpty())
            infoStates.put("fullName",InformationState.EMPTY);
        else infoStates.put("fullName",InformationState.RIGHT);

        //Check ngày sinh
        if(dateOfBirth == null)
            infoStates.put("dateOfBirth",InformationState.EMPTY);
        else infoStates.put("dateOfBirth",InformationState.RIGHT);

        //Check giới tính
        if(gender == null)
            infoStates.put("gender",InformationState.EMPTY);
        else infoStates.put("gender",InformationState.RIGHT);

        //Check email (Phức tạp vcl:))
        //Email: [ten_nguoi_dung]@[duong_dan] VD: NguyenVanA1970@gmail.com Binh.TT2412345@sis.hust.edu.vn
        checkEmail: do{
            if(email.isEmpty()){
                infoStates.put("email",InformationState.EMPTY);
                break;
            }
            int viTriACong = email.indexOf('@');
            //Email thì phải có '@', hiển nhiên rồi còn gì:))
            if(viTriACong == -1){
                infoStates.put("email",InformationState.WRONG_FORM);
                break;
            }
            //Check [ten_nguoi_dung]
            //Kí tự đầu phải bằng chữ. Các kí tự còn lại có thể có chữ cái, chữ số và kí tự '.','_','+'
            if(!Character.isLetter(email.charAt(0))){
                infoStates.put("email",InformationState.WRONG_FORM);
                break;
            }
            for(int i = 1; i < viTriACong; i++){
                if(!(Character.isLetterOrDigit(email.charAt(i)) || email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '+')){
                    infoStates.put("email",InformationState.WRONG_FORM);
                    break checkEmail;
                }
            }
            //Check [duong_dan]
            //Tất cả các kí tự phải bằng chữ in thường, bắt buộc phải có kí tự '.'
            int dotCounter = 0;
            for(int i = viTriACong + 1; i<email.length(); i++){
                if(!(Character.isLowerCase(email.charAt(i)) || (i != viTriACong && email.charAt(i) == '.'))){
                    infoStates.put("email",InformationState.WRONG_FORM);
                    break checkEmail;
                }
                if(email.charAt(i) == '.')dotCounter++;
            }
            if(dotCounter == 0) {
                infoStates.put("email",InformationState.WRONG_FORM);
                break;
            }
            infoStates.put("email",InformationState.RIGHT);
        } while(false);

        //Check số điện thoại
        checkPhoneNumber: do{
            if(phoneNumber.isEmpty()) {
                infoStates.put("phoneNumber",InformationState.EMPTY);
            }
            for(int i = 0; i < phoneNumber.length(); i++){
                if(!Character.isDigit(phoneNumber.charAt(i))){
                    infoStates.put("phoneNumber",InformationState.WRONG_FORM);
                    break checkPhoneNumber;
                }
            }
            if(phoneNumber.length() != 10) {
                infoStates.put("phoneNumber",InformationState.WRONG_SIZE);
                break;
            }
            infoStates.put("phoneNumber",InformationState.RIGHT);
        } while(false);

        //Check số CCCD(như số điện thoại thôi)
        checkCitizenID: do{
            if(citizenID.isEmpty()) {
                infoStates.put("citizenID", InformationState.EMPTY);
                break;
            }
            for(int i = 0; i < citizenID.length(); i++){
                if(!Character.isDigit(citizenID.charAt(i))){
                    infoStates.put("citizenID",InformationState.WRONG_FORM);
                    break checkCitizenID;
                }
            }
            infoStates.put("citizenID",InformationState.RIGHT);
        } while(false);

        return infoStates;
    }
    //Class kiểm tra thông tin đăng kí(Đm dài vcl:)))
    public static class VerifySignUpInformation{
        //Check họ tên
        public static boolean isFullNameValid(String fullName){
            return !fullName.isEmpty();
        }

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
        //Email: [ten_nguoi_dung]@[duong_dan] VD: NguyenVanA1970@gmail.com Binh.TT2412345@sis.hust.edu.vn
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
        public static CitizenIDState isCitizenIDVaid(String citizenID){
            if(citizenID.isEmpty())return CitizenIDState.EMPTY;
            for(int i = 0; i < citizenID.length(); i++){
                if(!Character.isDigit(citizenID.charAt(i))){
                    return CitizenIDState.WRONG_FORM;
                }
            }
            return CitizenIDState.RIGHT;
        }
    }
}
