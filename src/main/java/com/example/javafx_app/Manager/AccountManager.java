package com.example.javafx_app.Manager;

import com.example.javafx_app.Account.Account;
import com.example.javafx_app.Manager.BankManager.SignUpInformationState;
import com.example.javafx_app.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.javafx_app.Manager.BankManager.ACCOUNTS;
import static com.example.javafx_app.Manager.BankManager.currentAccount;

public class AccountManager {
    private static final AccountManager instance = new AccountManager();
    private AccountManager(){}



    public static AccountManager getInstance(){
        return instance;
    }
    public Account getCurrentAccount() {
        return BankManager.currentAccount;
    }
    public List<Account> getAccountList(){
        return ACCOUNTS;
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
            Account newAccount = new Account( signUpUser.getCitizenID(),Long.toString(1000000 + ACCOUNTS.size()),password,0.0,"VND",pin);
            UserManager.getInstance().getUserList().add(signUpUser);
            BankManager.ACCOUNTS.add(newAccount);
        }
    }
    //Đăng nhập
    public boolean logIn(String citizenID, String password){
        if(BankManager.VerifyPassword(citizenID,password)){
            currentAccount = findAccountFromCitizenID(citizenID);
            User currentUser = UserManager.getInstance().findUserFromAccount(currentAccount);
            if(currentUser == null)return false;
            UserManager.getInstance().setCurrentUser(currentUser);
        }
        else return false;
        return true;
    }
    //Đăng xuất
    public void logOut(){
        currentAccount = null;
        UserManager.getInstance().setCurrentUser(null);
    }
    //Tìm kiếm account
    public Account findAccount(String accountID) {
        for (Account a : BankManager.ACCOUNTS) {
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
    public Account findAccountFromUser(User user){
        for(Account a : ACCOUNTS){
            if(a.getCitizenID().equals(user.getCitizenID())){
                return a;
            }
        }
        return null;
    }
    public Account findAccountFromCitizenID(String citizenID){
        for (Account a : ACCOUNTS){
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
    public Account findAccountFromEmail(String email){
        return findAccountFromUser(UserManager.getInstance().findUserFromEmail(email));
    }
    public Account findAccountFromPhoneNumber(String phoneNumber){
        return findAccountFromUser(UserManager.getInstance().findUserFromPhoneNumber(phoneNumber));
    }
    //In log
    public void accountListLog(){
        int i = 0;
        for(Account a : ACCOUNTS){
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
