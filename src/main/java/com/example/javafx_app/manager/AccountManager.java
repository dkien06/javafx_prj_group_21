package com.example.javafx_app.manager;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.exception.IllegalAccountSignUpException;
import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.object.Account.*;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.Staff;
import com.example.javafx_app.object.User.USER_TYPE;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.config.Constant;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountManager {
    private static final AccountManager instance = new AccountManager();
    private AccountManager(){}

    private static final Map<String, Account> accountMap = new HashMap<>();

    // 2. Khối static khởi tạo dữ liệu giả
    static {
        for (int i = 1; i <= 100; i++) {
            Account a = new CheckingAccount(
                    "Name" + i,
                    "123456789" + i,     // citizenID
                    getInstance().generateUniqueAccountID(),           // accountID
                    "pwd" + i,// password
                    Constant.DEFAULT_BALANCE,
                    "VND",               // currency
                    "0000"               // PIN
            );

            // QUAN TRỌNG: Dùng put(Key, Value) thay vì add(Value)
            // Ở đây ta map: "AC1" -> Object Account 1
            accountMap.put(a.getAccountID(), a);
        }
    }
    // ham tao accountID
    private String generateUniqueAccountID() {
        Random random = new Random();
        String newID;
        do {
            // Tạo số ngẫu nhiên trong khoảng 10.000.000 đến 99.999.999
            int number = Constant.START_ACCOUNT_ID + random.nextInt(90000000);
            newID = String.valueOf(number);

            // Vòng lặp sẽ chạy lại nếu newID đã tồn tại trong Map
            // Nhờ dùng Map, lệnh containsKey này chạy siêu nhanh (O(1))
        } while (accountMap.containsKey(newID));

        return newID;
    }
    private static Account currentAccount;

    public static AccountManager getInstance(){
        return instance;
    }
    public Account getCurrentAccount() {
        return currentAccount;
    }
    public Map<String, Account> getAccountList(){
        return accountMap;
    }

    //Đăng nhập trá hình
    public void setCurrentAccount(Account currentAccount) {
        AccountManager.currentAccount = currentAccount;
        UserManager.getInstance().setCurrentUser(UserManager.getInstance().findUserFromAccount(currentAccount));
    }

    // Đăng kí thêm một tài khoản
    // ID 1xxxxxxxx: CheckingAccount
    // ID 2xxxxxxxx: SavingAccount
    // ID 3xxxxxxxx: LoanAccount
    // Tài khoản cùng người dùng thì 8 số đầu bằng nhau hết, giả sử Checking là 112345678 thì khi tạo Saving thì ID sẽ là 212345678
    public void addAccountForCustomer(Customer customer, String accountType, String password, String pin){
        Account account ;
        if (accountType.equals(ACCOUNT_TYPE.CHECKING.toString())) {
            account = new CheckingAccount(customer.getFullName(), customer.getCitizenID(),"1" + generateUniqueAccountID(),password
                    ,Constant.DEFAULT_BALANCE,"VND",pin);
        }
        else if(accountType.equals(ACCOUNT_TYPE.SAVING.toString())){
            String savingAccountID = customer.getCheckingAccountID();
            if(savingAccountID == null)throw new IllegalAccountSignUpException();
            StringBuilder savingID = new StringBuilder(savingAccountID);
            savingID.setCharAt(0,'2');
            savingAccountID = savingID.toString();
            account = new SavingAccount(customer.getFullName(), customer.getCitizenID(),savingAccountID,
                     password,Constant.DEFAULT_BALANCE,"VND",pin);
        }
        else if(accountType.equals(ACCOUNT_TYPE.LOAN.toString())){
            String loanAccountID = customer.getCheckingAccountID();
            if(loanAccountID == null)throw new IllegalAccountSignUpException();
            StringBuilder savingID = new StringBuilder(loanAccountID);
            savingID.setCharAt(0,'3');
            loanAccountID = savingID.toString();
            account = new LoanAccount(customer.getFullName(), customer.getCitizenID(),loanAccountID,
                    password,Constant.DEFAULT_BALANCE,"VND",pin) ;
        }
        else throw new MysteriousException();
        accountMap.put(account.getAccountID(),account) ;
        customer.addAccountID(account.getAccountID());
    }
    public void addAccountForStaff(Staff staff, String password, String pin){
        Account account = new StaffAccount(staff.getFullName(),staff.getCitizenID(),generateUniqueAccountID(),password,
                "VND",pin) ;
        accountMap.put(account.getAccountID(),account) ;
        staff.setAccountID(account.getAccountID());

    }
    //Đăng nhập
    public boolean logIn(String citizenID, String password, ACCOUNT_TYPE accountType){
        Account account = BankManager.VerifyPassword(citizenID, password, accountType);
        if(account == null){
            return false ;
        }
        currentAccount = account;
        UserManager.getInstance().setCurrentUser(UserManager.getInstance().findUserByCitizenID(citizenID));
        return true;
    }
    //Đăng xuất
    public void logOut(){
        currentAccount = null;
        UserManager.getInstance().setCurrentUser(null);
    }
    // chon homescene co account
    public String chooseHomeScene(Account account){
        String filehome ;
        if(account.getAccountType()==ACCOUNT_TYPE.CHECKING) {
            if(account.isVIP()) filehome = "HomeScenes/checking_account_VIP_home_scene.fxml";
            else filehome = "HomeScenes/checking_account_home_scene.fxml";
        }
        else if(account.getAccountType()==ACCOUNT_TYPE.SAVING){
            if(account.isVIP()) filehome = "HomeScenes/saving_account_VIP_home_scene.fxml";
            else filehome = "HomeScenes/saving_account_home_scene.fxml";
        }
        else if(account.getAccountType()==ACCOUNT_TYPE.LOAN){
            if(account.isVIP()) filehome = "HomeScenes/loan_account_VIP_home_scene.fxml";
            else  filehome = "HomeScenes/loan_account_home_scene.fxml";
        }
        else filehome = "HomeScenes/staff_home_scene.fxml";
        return filehome;
    }
    //Tìm kiếm account
    public Account findAccount(String accountID) {
        return accountMap.get(accountID);
    }
    public CheckingAccount findCheckingAccount(SavingAccount savingAccount){
        StringBuilder checkingAccountID = new StringBuilder(savingAccount.getAccountID());
        checkingAccountID.setCharAt(0,'1');
        return (CheckingAccount) findAccount(checkingAccountID.toString());
    }
    public CheckingAccount findCheckingAccount(LoanAccount loanAccount){
        StringBuilder checkingAccountID = new StringBuilder(loanAccount.getAccountID());
        checkingAccountID.setCharAt(0,'1');
        return (CheckingAccount) findAccount(checkingAccountID.toString());
    }
    public List<Account> findAccountFromUser(User user){
        if(user == null) return null;
        List<Account> accounts = new ArrayList<>();
        if(user.getType()== USER_TYPE.Customer){
            Customer customer = (Customer)  user;
            List<String> AccountIDs = customer.getAccountIDs();
            for(String accountID : AccountIDs){
                accounts.add(accountMap.get(accountID)) ;
            }
            return accounts;
        }
        else if(user.getType()==USER_TYPE.STAFF){
            Staff staff = (Staff)  user;
            accounts.add(accountMap.get(staff.getAccountID()));
            return  accounts;
        }
        return null;
    }
    public List<Account> findAccountFromCitizenID(String citizenID){
         return findAccountFromUser(UserManager.getInstance().findUserByCitizenID(citizenID));
    }
    public List<Account> findAccountFromEmail(String email){
        return findAccountFromUser(UserManager.getInstance().findUserFromEmail(email));
    }
    public List<Account> findAccountFromPhoneNumber(String phoneNumber){
        return findAccountFromUser(UserManager.getInstance().findUserFromPhoneNumber(phoneNumber));
    }
    // tim chinh xac account
    public Account findExactAccountFromCostumer(Customer Customer,ACCOUNT_TYPE accountType){
        for(String accountID : Customer.getAccountIDs()){
            if(accountMap.get(accountID).getAccountType()==accountType){
                return accountMap.get(accountID) ;
            }
        }
        return null;
    }
    public Account findExactAccountFromEmail(String email,ACCOUNT_TYPE accountType){
        User user = UserManager.getInstance().findUserFromEmail(email);
        if(user == null) return null;
        if(user.getType()== USER_TYPE.Customer){
            Customer customer = (Customer)  user;
            return findExactAccountFromCostumer(customer,accountType);

        }
        else{
            return accountMap.get(((Staff)user).getAccountID());
        }
    }
    public Account findExactAccountFromPhoneNumber(String phoneNumber,ACCOUNT_TYPE accountType){
        User user = UserManager.getInstance().findUserFromPhoneNumber(phoneNumber);
        if(user == null) return null;
        if(user.getType()== USER_TYPE.Customer){
            Customer customer = (Customer)  user;
            return findExactAccountFromCostumer(customer,accountType);
        }
        else {
            return accountMap.get(((Staff)user).getAccountID());
        }
    }
    // ham tim bill
    public Bill findBillFromAccount(CheckingAccount account,String Amount,String Date,String Supplier){
        long amount = Long.parseLong(Amount);
        LocalDate date = LocalDate.parse(Date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for(Bill bill : account.getBills()){
            if(bill.getAmount()==amount&&
                bill.getDate().equals(date)&&
                bill.getSupplier().equals(Supplier)){
                return bill;
            }
        }
        return null;
    }
    //In log
    public void accountListLog(){
        int i = 0;
        for(Account a : accountMap.values()){
            i++;
            System.out.println(i + "\n\tCitizenID: "+a.getCitizenID()
                                 + "\n\tAccountID: "+a.getAccountID()
                                 + "\n\tPassword: "+a.getPassword()
                                 + "\n\tCurrency: "+a.getCurrency()
                                 + "\n\tPIN: "+a.getPIN() + "\n");
        }
    }
    public static void main(String args[]) throws IOException {
        ExampleUser.init();
        System.out.println(UserManager.getInstance().findUserByCitizenID("010203008386"));
        System.out.println(AccountManager.getInstance().findAccountFromUser(UserManager.getInstance().findUserByCitizenID("010203008386")));
    }
}
