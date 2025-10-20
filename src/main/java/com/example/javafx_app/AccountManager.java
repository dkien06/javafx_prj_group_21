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
    private static Account currentAccount;

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
    public void resister(User signUpUser, String password, String pin){
        Map<String, SignUpInformationState> checkSignUpUserInfo = BankManager.CheckAllSignUpInfo(
                signUpUser.getFullName(),
                signUpUser.getDateOfBirth(),
                User.genderToString(signUpUser.getGender()),
                signUpUser.getEmail(),
                signUpUser.getPhoneNumber(),
                signUpUser.getCitizenID()
        );
        if(checkSignUpUserInfo.get("fullName") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("dateOfBirth") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("gender") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("email") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("phoneNumber") == SignUpInformationState.RIGHT
        && checkSignUpUserInfo.get("citizenID") == SignUpInformationState.RIGHT
        && BankManager.checkNewPassword(password) == BankManager.PasswordState.RIGHT
        && BankManager.checkNewPIN(pin) == BankManager.PINState.RIGHT){
            Account newAccount = new Account(signUpUser.getFullName(), signUpUser.getCitizenID(),Long.toString(1000000 + accounts.size()),password,0.0,"VND",pin);
            UserManager.getInstance().getUserList().add(signUpUser);
            accounts.add(newAccount);
        }
    }
    //Đăng nhập
    public void logIn(String citizenID, String password){
        if(BankManager.VerifyPassword(citizenID,password)){
            currentAccount = findAccountFromCitizenID(citizenID);
        }
    }
    //Đăng xuất
    public void logOut(){
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
                return a;
            }
        }
        return null;
    }
    public Account findAccountFromCitizenID(String citizenID){
        List<Account> accountList = AccountManager.getInstance().getAccountList();
        for (Account a : accountList){
            boolean check = true;
            for(int i = 0; i < citizenID.length(); i++){
                if(citizenID.charAt(i) != a.getCitizenID().charAt(i)){
                    check = false;
                    break;
                }
            }
            if(check){
                return a;
            }
        }
        return null;
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
