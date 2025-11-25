package com.example.javafx_app.manager;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.config.Constant;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class BankManager {
    // Ham kiem tra mat khau
    private static String currentEmail ;
    private static String currentPhoneNumber;
    private static LocalDate currentDate ;
    public static String getCurrentEmail() {
        return currentEmail;
    }
    public static void setCurrentEmail(String currentEmail) {
        BankManager.currentEmail = currentEmail;
    }
    public static String getCurrentPhoneNumber() {
        return currentPhoneNumber;
    }
    public static void setCurrentPhoneNumber(String currentPhoneNumber) {
        BankManager.currentPhoneNumber = currentPhoneNumber;
    }
    public static LocalDate getCurrentDate() {
        return currentDate;
    }
    public static void setCurrentDate(LocalDate currentDate) {}
    public static Account VerifyPassword(String citizenID, String password, ACCOUNT_TYPE accountType) {
        List<Account> VerifyAccount = AccountManager.getInstance().findAccountFromCitizenID(citizenID);

        if(VerifyAccount==null) {
            return null;}
        System.out.println("Verify Password");
        for(Account account:VerifyAccount){
            if(account.getPassword().equals(password)&&accountType==account.getAccountType()){
                return account;
            }
        }
        return null;
    }
    /*Mấy cái hàm này cho phần đăng kí*/
    public enum SignUpInformationState {
        EMPTY,
        WRONG_FORM,
        WRONG_SIZE,
        EXISTED,
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
    public static void main(String args[]) throws IOException {
        ExampleUser.init();
        System.out.println(VerifyPassword("010203008386","NguyenVanA#1970"
                ,ACCOUNT_TYPE.CHECKING));
    }
    //Check email (Phức tạp vcl:))
    //Email: [ten_nguoi_dung]@[duong_dan] VD: NguyenVanA1970@gmail.com Binh.TT2412345@sis.hust.edu.vn
    public static SignUpInformationState checkSignUpEmail(String email){
        if(email.isEmpty()) return SignUpInformationState.EMPTY;
        if(AccountManager.getInstance().findAccountFromEmail(email) != null) return SignUpInformationState.EXISTED;
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
        if(AccountManager.getInstance().findAccountFromPhoneNumber(phoneNumber) != null)return SignUpInformationState.EXISTED;
        for(int i = 0; i < phoneNumber.length(); i++){
            if(!Character.isDigit(phoneNumber.charAt(i)))return SignUpInformationState.WRONG_FORM;
        }
        if(phoneNumber.length() != Constant.PHONE_NUMBER_LENGTH) return SignUpInformationState.WRONG_SIZE;
        return SignUpInformationState.RIGHT;
    }
    //Check số căn cước công dân
    public static SignUpInformationState checkSignUpCitizenID(String citizenID){
        if(citizenID.isEmpty()) return SignUpInformationState.EMPTY;
        if(AccountManager.getInstance().findAccountFromCitizenID(citizenID) != null)return SignUpInformationState.EXISTED;
        for(int i = 0; i < citizenID.length(); i++){
            if(!Character.isDigit(citizenID.charAt(i)))return SignUpInformationState.WRONG_FORM;
        }
        if(UserManager.getInstance().findUserByCitizenID(citizenID) != null)return SignUpInformationState.EXISTED;
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
    public enum PasswordState {
        EMPTY("Mật khẩu không được để trống."),
        WEAK("Mật khẩu yếu: cần ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt."),
        NOT_MATCHED("Mật khẩu nhập lại không khớp."),
        RIGHT("");

        private final String label;

        private PasswordState(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    /**
     * Phương thức phụ để kiểm tra độ mạnh của mật khẩu bằng Biểu thức chính quy (Regex).
     * @param password Mật khẩu cần kiểm tra.
     * @return true nếu mật khẩu mạnh, false nếu ngược lại.
     */
    private static boolean isPasswordStrong(String password) {
        // Định nghĩa mẫu regex cho mật khẩu mạnh
        // ^                 : Bắt đầu chuỗi
        // (?=.*[a-z])       : Phải chứa ít nhất một chữ thường
        // (?=.*[A-Z])       : Phải chứa ít nhất một chữ hoa
        // (?=.*\\d)         : Phải chứa ít nhất một chữ số
        // (?=.*[!@#$%^&*()]) : Phải chứa ít nhất một ký tự đặc biệt trong danh sách
        // . {8,}            : Phải có ít nhất 8 ký tự
        // $                 : Kết thúc chuỗi
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()]).{"+ Constant.MINIUM_PASSWORD_LENGTH + ",}$";

        // Kiểm tra xem mật khẩu có khớp với mẫu không
        return Pattern.matches(passwordPattern, password);
    }
    public static PasswordState checkNewPassword(String password){
        if (password.isEmpty()) return PasswordState.EMPTY;
        else if (!isPasswordStrong(password)) return PasswordState.WEAK;
        else return PasswordState.RIGHT;
    }
    public static PasswordState checkPasswordAgain(String password, String passwordAgain){
        if(passwordAgain.isEmpty())return PasswordState.EMPTY;
        else if(password.equals(passwordAgain))return PasswordState.NOT_MATCHED;
        else return PasswordState.RIGHT;
    }
    public enum PINState{
        // Sử dụng Constant.MINIUM_PIN_LENGTH (6) để tạo thông báo lỗi động
        EMPTY("Vui lòng nhập mã PIN"),
        WRONG_FORM("Mã PIN phải là " + Constant.MINIUM_PIN_LENGTH + " chữ số"),
        RIGHT(""); // Không có lỗi, trả về chuỗi rỗng

        private final String label;

        private PINState(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    public static PINState checkNewPIN(String PIN){
        if(PIN.isEmpty())return PINState.EMPTY;
        else if (!PIN.matches("\\d{" + Constant.MINIUM_PIN_LENGTH + "}"))return PINState.WRONG_FORM;
        else return PINState.RIGHT;
    }
}
