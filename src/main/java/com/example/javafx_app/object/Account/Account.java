package com.example.javafx_app.object.Account;

import com.example.javafx_app.object.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
enum ACCOUNT_TYPE{
    CHECKING,
    SAVING,
    LOAN
}

/**
 * Account chính (Dùng cho các hành vi cơ bản (Đăng nhập, đăng xuất, check mật khẩu, PIN,...))
 * Còn chuyển tiền thì vào CheckingAccount mà chuyển (Bình thường tạo tài khoản xong thì CheckingAccount tạo cùng luôn)
 * Đó là lí do tui trong class Account có mấy cái biến thuộc tính checkingAccount, savingAccount, loanAccount là thế đấy, vì nó có làm mấy thứ cơ bản đâu, với lại quản lí nó dễ hơn:)
 * Với lại phòng trường hợp 1 người có 2 account có function giống nhau nựa thì check null (VD: savingAccount == null) còn nhanh hơn là tìm kiếm nguyên cả list accounts dấy:)
 */
public class Account {
    protected String accountName;
    protected String citizenID;
    protected String accountID;
    protected String password;
    protected String currency;
    protected String PIN;
    private CheckingAccount checkingAccount;
    private SavingAccount savingAccount;
    private LoanAccount loanAccount;
    private List<Transaction> history;
    private LocalDate StartDate = null;
    protected boolean isVIP;

    // ✅ Constructor đầy đủ
    public Account(String fullName, String citizenID, String accountID, String password,
                   String currency, String PIN) {
        this.accountName = fullName;
        this.citizenID = citizenID;
        this.accountID = accountID;
        this.password = password;
        this.currency = currency;
        this.PIN = PIN;
        this.history = new ArrayList<>();
        this.StartDate = LocalDate.now();
        this.isVIP = false;
        this.checkingAccount = null;
        this.savingAccount = null;
        this.loanAccount = null;
    }

    // ✅ Constructor rỗng (cần cho JavaFX hoặc khởi tạo tạm)
    public Account() {
        this.history = new ArrayList<>();
    }

    // === Getter ===
    public String getAccountName() {
        return accountName;
    }
    public String getCitizenID() {
        return citizenID;
    }
    public String getAccountID() {
        return accountID;
    }
    public String getCurrency() {
        return currency;
    }
    public String getPIN() {
        return PIN;
    }
    public List<Transaction> getHistory() {
        return history;
    }
    public String getPassword() {
        return password;
    }
    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }
    public SavingAccount getSavingAccount() {
        return savingAccount;
    }
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
    public LocalDate getStartDate() {
        return StartDate;
    }

    // === Setter ===
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }
    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }
    public void setLoanAccount(LoanAccount loanAccount) {
        this.loanAccount = loanAccount;
    }

    // ✅ Thêm giao dịch
    public void addTransaction(Transaction t) {
        history.add(t);
    }
    //Đối chiếu PIN
    public boolean isPinMatched(String pin) {
        return this.PIN != null && this.PIN.equals(pin);
    }
    public boolean isPasswordMatched(String password){
        return this.password != null && this.password.equals(password);
    }
    // ✅ In ra thông tin tài khoản (dễ debug)
    public String accountToString() {
        return "Account{" +
                "citizenID='" + citizenID + '\'' +
                ", accountID='" + accountID + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}