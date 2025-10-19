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
                    "Name" + i,
                    "123456789" + i,     // citizenID
                    "AC" + i,            // accountID
                    "pwd" + i,           // password
                    Math.random() * 1_000_000, // balance
                    "VND",               // currency
                    "0000"               // PIN
            );
            ACCOUNTS.add(a);
        }
        ACCOUNTS.add(new Account("ADMIN","892006","AC","892006",1000,"VND","0000"));
    }

    /*Mấy hàm này cho phần đăng nhập*/
    // Ham kiem tra mat khau
    public static boolean VerifyPassword(String citizenID, String password) {
        Account VerifyAccount = AccountManager.getInstance().findAccountFromCitizenID(citizenID);
        if(VerifyAccount==null) {return false;}
        return password.equals(VerifyAccount.getPassword());
    }
    /*Mấy cái hàm này cho phần đăng kí*/
    public enum SignUpInformationState {
        EMPTY,
        WRONG_FORM,
        WRONG_SIZE,
        RIGHT
    }
    //Check họ tên
    public static SignUpInformationState checkSignUpFullName(String fullName){
        if(fullName.isEmpty())
            return SignUpInformationState.EMPTY;
        return SignUpInformationState.RIGHT;
    }
    //Check ngày sinh
    public static SignUpInformationState checkSignUpDateOfBirth(LocalDate dateOfBirth){
        if(dateOfBirth == null)
            return SignUpInformationState.EMPTY;
        return SignUpInformationState.RIGHT;
    }
    //Check giới tính
    public static SignUpInformationState checkSignUpGender(String gender){
        if(gender == null)
            return SignUpInformationState.EMPTY;
        return SignUpInformationState.RIGHT;
    }
    //Check email (Phức tạp vcl:))
    //Email: [ten_nguoi_dung]@[duong_dan] VD: NguyenVanA1970@gmail.com Binh.TT2412345@sis.hust.edu.vn
    public static SignUpInformationState checkSignUpEmail(String email){
        if(email.isEmpty()) return SignUpInformationState.EMPTY;
        int viTriACong = email.indexOf('@');
        //Email thì phải có '@', hiển nhiên rồi còn gì:))
        if(viTriACong == -1)return SignUpInformationState.WRONG_FORM;
        //Check [ten_nguoi_dung]
        //Kí tự đầu phải bằng chữ. Các kí tự còn lại có thể có chữ cái, chữ số và kí tự '.','_','+'
        if(!Character.isLetter(email.charAt(0)))return SignUpInformationState.WRONG_FORM;
        for(int i = 1; i < viTriACong; i++){
            if(!(Character.isLetterOrDigit(email.charAt(i)) || email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '+')){
                return SignUpInformationState.WRONG_FORM;
            }
        }
        //Check [duong_dan]
        //Tất cả các kí tự phải bằng chữ in thường, bắt buộc phải có kí tự '.'
        int dotCounter = 0;
        for(int i = viTriACong + 1; i<email.length(); i++){
            if(!(Character.isLowerCase(email.charAt(i)) || (i != viTriACong && email.charAt(i) == '.'))){
                return SignUpInformationState.WRONG_FORM;
            }
            if(email.charAt(i) == '.')dotCounter++;
        }
        if(dotCounter == 0) {
            return SignUpInformationState.WRONG_FORM;
        }
        return SignUpInformationState.RIGHT;
    }
    //Check số điện thoại
    public static SignUpInformationState checkSignUpPhoneNumber(String phoneNumber){
        if(phoneNumber.isEmpty()) return SignUpInformationState.EMPTY;
        for(int i = 0; i < phoneNumber.length(); i++){
            if(!Character.isDigit(phoneNumber.charAt(i)))return SignUpInformationState.WRONG_FORM;
        }
        if(phoneNumber.length() != 10) return SignUpInformationState.WRONG_SIZE;
        return SignUpInformationState.RIGHT;
    }
    //Check số căn cước công dân
    public static SignUpInformationState checkSignUpCitizenID(String citizenID){
        if(citizenID.isEmpty()) return SignUpInformationState.EMPTY;
        for(int i = 0; i < citizenID.length(); i++){
            if(!Character.isDigit(citizenID.charAt(i)))return SignUpInformationState.WRONG_FORM;
        }
        return SignUpInformationState.RIGHT;
    }
    public static Map<String, SignUpInformationState> CheckAllSignUpInfo(String fullName, LocalDate dateOfBirth, String gender, String email, String phoneNumber, String citizenID){
        //Basically #include <unorder_map> nhưng của java:))
        //Nó khá giống mảng nhưng index thay vì là int thi nó có thể là biến tùy ý. VD: infoState["fullName"]
        //Gán giá trị thay vì trực tiếp (A["blabla"] = 1) thì phải dùng hàm Map.put("blabla", 1)
        //Còn lấy giá trị thì dễ thôi: Map.set("blabla") (=1)
        Map<String, SignUpInformationState> infoStates = new HashMap<>();

        infoStates.put("fullName",checkSignUpFullName(fullName));
        infoStates.put("dateOfBirth",checkSignUpDateOfBirth(dateOfBirth));
        infoStates.put("gender",checkSignUpGender(gender));
        infoStates.put("email", checkSignUpEmail(email));
        infoStates.put("phoneNumber", checkSignUpPhoneNumber(phoneNumber));
        infoStates.put("citizenID", checkSignUpCitizenID(citizenID));

        return infoStates;
    }
    public static boolean checkNewPassword(String newPassword){
        //Tự viết đi, nhác vcl:))
        return true;
    }
}
