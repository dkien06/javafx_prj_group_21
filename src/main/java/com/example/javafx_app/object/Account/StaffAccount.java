package com.example.javafx_app.object.Account;

public class StaffAccount extends Account {
    public StaffAccount(String fullName, String citizenID, String accountID, String password,
                        String currency, String PIN) {
        super(fullName,citizenID,accountID,password,currency,PIN);
    }
    public boolean transfer(Account fromAccount, long amount,String PIN) {
        return true ;
    }
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.STAFF;
    }
}
