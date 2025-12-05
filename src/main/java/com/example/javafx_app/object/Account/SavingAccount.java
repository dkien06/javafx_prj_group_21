package com.example.javafx_app.object.Account;

public class SavingAccount extends Account {
    private long saving;
    public SavingAccount(String fullName, String citizenID, String accountID, String password, long saving,
                         String currency, String PIN) {
        super(fullName, citizenID, accountID, password, currency, PIN);
        this.saving = saving;
    }
    // ✅ Nạp tiền
    public boolean deposit(CheckingAccount account, long amount) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if (account.withdraw(amount)) {
            saving += amount;
            return true;
        }
        else return false;
    }
    // ✅ Rút tiền
    public boolean withdraw(CheckingAccount account, long amount) {
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if (amount <= saving) {
            if(account.deposit(amount)){
                saving -= amount;
                return true;
            }
            else return false;
        } else {
            return false;
        }
    }
    public boolean withdrawAll(CheckingAccount account){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(account.deposit(saving)){
            saving = 0;
            return true;
        }
        else return false;
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
