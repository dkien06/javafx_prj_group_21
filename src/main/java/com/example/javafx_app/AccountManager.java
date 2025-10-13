package com.example.javafx_app;

import com.example.javafx_app.BankManager.SignUpInformationState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.javafx_app.BankManager.ACCOUNTS;


public class AccountManager {
    private static final AccountManager instance = new AccountManager();
    private AccountManager(){}

    private static List<Account> accounts = new ArrayList<>();
    //Vì mục đích test scene chuyển tiền (Có thể thay thành new Account() nếu éo thích)
    private static Account currentAccount = new Account("010203008386",
                                                       "49538386",
                                                       "NguyenVanA#1970",
                                                         2000000,
                                                        "VND",
                                                            "010170");

    public static AccountManager getInstance(){
        return instance;
    }
    public Account getCurrentAccount() {
        return currentAccount;
    }
    public List<Account> getAccountList(){
        return accounts;
    }

    //Đăng kí
    void resister(UserInfo signUpUserInfo, String password){
        Map<String, SignUpInformationState> checkSignUpUserInfo = BankManager.CheckAllSignUpInfo(
                signUpUserInfo.getFullName(),
                signUpUserInfo.getDateOfBirth(),
                UserInfo.genderToString(signUpUserInfo.getGender()),
                signUpUserInfo.getEmail(),
                signUpUserInfo.getPhoneNumber(),
                signUpUserInfo.getCitizenID()
        );
        if(checkSignUpUserInfo.get("fullName") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("dateOfBirth") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("gender") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("email") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("phoneNumber") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("citizenID") == SignUpInformationState.RIGHT
        && BankManager.checkNewPassword(password)){
            Account newAccount = new Account(signUpUserInfo.getCitizenID(),"",password,0.0,"VND","");
            accounts.add(newAccount);
        }
    }
    //Đăng nhập
    void logIn(String citizenID, String password){
        if(BankManager.VerifyPassword(citizenID,password)){
            currentAccount = findAccount(citizenID);
        }
    }
    //Đăng xuất
    void logOut(){
        currentAccount = null;
    }
    //Tìm kiếm account
    public Account findAccount(String accountID) {
        for (Account a : ACCOUNTS) {
            if (a.getCitizenID().equals(accountID)) {
                return a;
            }
        }
        List<Account> accountList = AccountManager.getInstance().getAccountList();
        for (Account a : accountList){
            boolean check = true;
            for(int i = 0; i < accountID.length(); i++){
                if(accountID.charAt(i) != a.getAccountID().charAt(i)){
                    check = false;
                    break;
                }
            }
            if(check){
                System.out.println("Matched!");
                return a;
            }
            else System.out.println("Not match");
        }
        return null;
    }
    //Check PIN
    public boolean checkNewPIN(Account account, String PIN){
        if(account == null)return false;
        if(!account.isPinValid(PIN))return false;
        if(PIN.equals(account.getPIN()))return false;
        return true;
    }
    //Thay PIN
    public boolean replacePIN(Account account, String PIN){
        if(checkNewPIN(account,PIN)){
            account.setPIN(PIN);
            return true;
        }
        else return false;
    }
    //In log
    public void accountListLog(){
        int i = 0;
        for(Account a : accounts){
            i++;
            System.out.println(i + "\n\tCitizenID: "+a.getCitizenID()
                                 + "\n\tAccountID: "+a.getAccountID()
                                 + "\n\tPassword: "+a.getPassword()
                                 + "\n\tBalance: "+a.getBalance()
                                 + "\n\tCurrency: "+a.getCurrency()
                                 + "\n\tPIN: "+a.getPIN() + "\n");
        }
    }
}
