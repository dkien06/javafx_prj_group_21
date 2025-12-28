package com.example.javafx_app.object.Account;

import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.object.Transaction;
import com.example.javafx_app.object.TransactionType;
import javafx.util.Pair;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LoanAccount extends Account implements Serializable {
    //Lịch sử vay (Có cả trạng thái nữa)
    private LoanType type; //Kiểu vay (Vay trả góp, vay kỳ hạn)
    private LoanStatus status; //Trạng thái (Chờ duyệt, đang hoạt động, quá hạn)
    //Thông tin vay
    private long debt; //Số tiền nợ
    private LocalDate startLoanDate; //Ngày bắt đầu nợ
    private double interest; //Phần trăm lãi
    private long duration; //Kỳ hạn
    private String description; //Mô tả
    private Map<Transaction, Pair<LoanStatus, String>> histories;
    public LoanAccount(String fullName, String citizenID, String accountID, String password, long debt,
                       String currency, String PIN){
        super(fullName, citizenID, accountID, password, currency, PIN);
        this.type = LoanType.NONE;
        this.status = LoanStatus.NONE;
        this.debt = debt;
        this.startLoanDate = LocalDate.now();
        this.interest = -1.0;
        this.duration = 0;
        this.description = "";
        this.histories = new HashMap<>();
    }
    public void setType(LoanType type) {
        this.type = type;
    }
    public LoanType getType() {
        return type;
    }
    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
    public long getDebt() {
        return debt;
    }
    public void setDebt(long debt) {
        this.debt = debt;
    }
    public LocalDate getStartLoanDate() {
        return startLoanDate;
    }
    public double getInterest() {
        return interest;
    }
    public void setInterest(double interest) {
        this.interest = interest;
    }
    public long getDuration() {
        return duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    //Vay tiền
    public boolean makeLoan(CheckingAccount account, long amount, String description){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(amount > 0){
            this.debt += amount;
            this.status = LoanStatus.REVIEW;
            this.startLoanDate = LocalDate.now();
            Transaction newTransaction = new Transaction(
                    TransactionType.LOAN,
                    amount,
                    "VND",
                    this,
                    AccountManager.getInstance().findCheckingAccount(this),
                    description
            );
            addToHistories(newTransaction);
            return true;
        }
        else return false;
    }
    public boolean loan(CheckingAccount account, String description){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(account.deposit(debt, description)){
            this.startLoanDate = LocalDate.now();
            Transaction newTransaction = new Transaction(
                    TransactionType.LOAN,
                    debt,
                    "VND",
                    this,
                    AccountManager.getInstance().findCheckingAccount(this),
                    description
            );
            this.status = LoanStatus.ACTIVE;
            this.startLoanDate = LocalDate.now();
            addTransaction(newTransaction);
            addToHistories(newTransaction, LoanStatus.ACTIVE);
            return true;
        }
        else return false;
    }
    //Trả nợ
    public boolean repay(CheckingAccount account, long amount, String description){
        if (!this.citizenID.equals(account.getCitizenID())) {
            System.out.println("Không thể nạp tiền từ tài khoản khác chủ.");
            return false;
        }
        if(account.withdraw(amount,"")){
            debt -= amount;
            if(debt == 0)this.type = LoanType.NONE;
            Transaction newWithdraw =
                    new Transaction(TransactionType.REPAY,amount,getCurrency(),account,this,description);
            this.addTransaction(newWithdraw);
            return true;
        }
        else return false;
    }
    public boolean repayAll(CheckingAccount account, String description){
        return repay(account, debt, description);
    }
    @Override
    public ACCOUNT_TYPE getAccountType(){ return ACCOUNT_TYPE.LOAN ;}


    public Map<Transaction, Pair<LoanStatus, String>> getHistories() {
        return histories;
    }
    public void addToHistories(Transaction transaction){
        histories.put(transaction, new Pair<>(LoanStatus.REVIEW, ""));
    }
    public void addToHistories(Transaction transaction, LoanStatus status){
        histories.put(transaction, new Pair<>(status, ""));
    }
    public void addToHistories(Transaction transaction, String reason){
        histories.put(transaction, new Pair<>(LoanStatus.REJECTED, ""));
    }
}
