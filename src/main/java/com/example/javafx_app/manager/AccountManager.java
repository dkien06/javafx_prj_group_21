package com.example.javafx_app.manager;

import com.example.javafx_app.object.Account;
import com.example.javafx_app.manager.BankManager.SignUpInformationState;
import com.example.javafx_app.object.CheckingAccount;
import com.example.javafx_app.object.User;
import com.example.javafx_app.config.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountManager {
    private static final AccountManager instance = new AccountManager();
    private AccountManager(){}

    private static List<Account> accounts = new ArrayList<>();
    public static final List<Account> ACCOUNTS = new ArrayList<>();
    static {
        for (int i = 1; i <= 100; i++) {
            Account a = new Account(
                    "Name" + i,
                    "123456789" + i,     // citizenID
                    "AC" + i,            // accountID
                    "pwd" + i,           // password
                    "VND",               // currency
                    "0000"               // PIN
            );
            ACCOUNTS.add(a);
        }
        ACCOUNTS.add(new Account("ADMIN", Constant.ADMIN_CITIZEN_ID,Constant.ADMIN_ACCOUNT_ID,Constant.ADMIN_PASSWORD,Constant.ADMIN_CURRENCY,Constant.ADMIN_PIN));
    }
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
            Account newAccount = new Account(signUpUser.getFullName(), signUpUser.getCitizenID(),Long.toString(1000000 + accounts.size()),password,"VND",pin);
            UserManager.getInstance().getUserList().add(signUpUser);
            resisterCheckingAccount(newAccount);
            accounts.add(newAccount);
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
    //Thêm tài khoản checkingAccount
    public void resisterCheckingAccount(Account account){
        CheckingAccount newCheckingAccount = new CheckingAccount(account);
        account.setCheckingAccount(newCheckingAccount);
    }
    public void resisterCheckingAccount(Account account, double balance){
        CheckingAccount newCheckingAccount = new CheckingAccount(account, balance);
        account.setCheckingAccount(newCheckingAccount);
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
    public Account findAccountFromUser(User user){
        for(Account a : accounts){
            if(a.getCitizenID().equals(user.getCitizenID())){
                return a;
            }
        }
        return null;
    }
    public Account findAccountFromCitizenID(String citizenID){
        for (Account a : accounts){
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
        for(Account a : accounts){
            i++;
            System.out.println(i + "\n\tCitizenID: "+a.getCitizenID()
                                 + "\n\tAccountID: "+a.getAccountID()
                                 + "\n\tPassword: "+a.getPassword()
                                 + "\n\tCurrency: "+a.getCurrency()
                                 + "\n\tPIN: "+a.getPIN() + "\n");
        }
    }
}
