package com.example.javafx_app.object;

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

    /*
    Cảnh cáo:)) (Tùy theo số tiền vay mượn)
    - Mức 0: Chuyển tiền tiết kiệm bình thường
    - Mức 1: Éo cho tiết kiệm nữa
    - Mức 2: Bị giới hạn chuyển tiền (Ai nhủ éo trả nợ)
    - Mức 3:
     + Lần 1: Khóa chuyển tiền, éo cho chuyển nựa
     + Lần 2: Ban acc luôn:)) Muốn gỡ thì đến ngân hàng mà trả nợ hết
    Hạ mức cảnh cáo: Trả nợ đi:)
    Ý tưởng không lấy cảm hứng từ Bách khoa, nếu ko thích thì thôi:))
     */
    private int warning;

    // ✅ Constructor đầy đủ
    public Account(String fullName, String citizenID, String accountID, String password,
                   String currency, String PIN) {
        this.accountName = fullName;
        this.citizenID = citizenID;
        this.accountID = accountID;
        this.password = password;
        this.currency = currency;
        this.PIN = PIN;
        this.checkingAccount = null;
    }

    // ✅ Constructor rỗng (cần cho JavaFX hoặc khởi tạo tạm)
    public Account() {}

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
    public String getPassword() {
        return password;
    }
    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }
    public int getWarning() {
        return warning;
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
    public void setWarning(int warning) {
        this.warning = warning;
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