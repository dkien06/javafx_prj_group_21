package com.example.javafx_app.object.Account;

public class SavingAccount extends Account {
    private long saving;
    public SavingAccount(String fullName, String citizenID, String accountID, String password, long saving,
                         String currency, String PIN) {
        super(fullName, citizenID, accountID, password, currency, PIN);
        this.saving = saving;
    }

    public long getSaving() {
        return saving;
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
        return withdraw(account, saving);
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.SAVING ;}
}
