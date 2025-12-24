package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.NotiManager;
import com.example.javafx_app.manager.TransactionManager;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tài khoản chuển tiền:)
 */
public class CheckingAccount extends Account implements Serializable {
    private long balance;
    private List<Bill> bills;
    private boolean ElectricService, InternetService, WaterService, SchoolService ;

    public CheckingAccount(String fullName, String citizenID, String accountID, String password, long balance, String currency, String PIN){
        super(fullName, citizenID, accountID, password, currency,  PIN);
        bills = new ArrayList<>() ;
        this.balance = balance;
        this.ElectricService = false;
        this.InternetService = false;
        this.WaterService = false;
        this.SchoolService = false;
    }


    public long getBalance() {
        return balance;
    }
    public boolean isElectricService() {
        return ElectricService;
    }
    public boolean isInternetService() {
        return InternetService;
    }
    public boolean isWaterService() {
        return WaterService;
    }
    public boolean isSchoolService() {
        return SchoolService;
    }
    public void setElectricService(boolean ElectricService) {
        this.ElectricService = ElectricService;
    }
    public void setInternetService(boolean InternetService) {
        this.InternetService = InternetService;
    }
    public void setWaterService(boolean WaterService) {
        this.WaterService = WaterService;
    }
    public void setSchoolService(boolean SchoolService) {
        this.SchoolService = SchoolService;
    }
    // ✅ Chuyển tiền
    public boolean transfer(CheckingAccount toAccount, long amount, String description) {
        if (toAccount == null || amount <= 0 || amount > balance) {
            return false;
        }
        // rút tiền bên gửi
        this.balance -= amount;
        // nạp tiền bên nhận
        toAccount.balance += amount;

        // thêm lịch sử giao dịch cho cả 2
        Transaction newTransfer = new Transaction(TransactionType.TRANSFER, amount, "VND", this, toAccount, description);
        this.addTransaction(newTransfer);
        TransactionManager.getInstance().addTransaction(newTransfer);
        addNotification(NotiManager.getNotifromTransaction(newTransfer));
        Transaction newReceive = new Transaction(TransactionType.TRANSFER, -amount, "VND", this, toAccount, description);
        toAccount.addTransaction(newReceive);
        toAccount.addNotification(NotiManager.getNotifromTransaction(newReceive));
        return true;
    }
    public boolean withdraw(long amount,String description) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            Transaction newWithdraw = new Transaction(TransactionType.WITHDRAW,amount,"VND",this,this,description) ;
            this.addTransaction(newWithdraw);
            TransactionManager.getInstance().addTransaction(newWithdraw);
            addNotification(NotiManager.getNotifromTransaction(newWithdraw));
            return true;
        }
        else return false;
    }
    // ✅ Nạp tiền từ tiền tiết kiệm hoặc vay mượn
    public boolean deposit(long amount, String description) {
        if (amount > 0) {
            balance += amount;
            Transaction newDeposit = new Transaction(TransactionType.DEPOSIT,amount,"VND",this,this,description) ;
            this.addTransaction(newDeposit);
            TransactionManager.getInstance().addTransaction(newDeposit);
            addNotification(NotiManager.getNotifromTransaction(newDeposit));

            return true;
        } else return false;
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.CHECKING ;}
    public void addBill(Bill bill){
        bills.add(bill);
        addNotification(NotiManager.getNotifromBill(bill));
    }
    public List<Bill> getBills(){
        return bills;
    }
    public boolean removeBill(Bill bill) {
        // List.remove(Object o) sẽ sử dụng phương thức Bill.equals()
        return bills.remove(bill);
    }
}
