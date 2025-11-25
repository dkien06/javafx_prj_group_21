package com.example.javafx_app.object.Account;

public class StaffAccount extends Account {
    public StaffAccount(String fullName, String citizenID, String accountID, String password,double balance,
                   String currency, String PIN) {
        super(fullName,citizenID,accountID,password,balance,currency,PIN);
    }
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.STAFF;
    }
}
