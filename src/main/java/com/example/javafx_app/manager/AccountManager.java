package com.example.javafx_app.manager;

import com.example.javafx_app.DataPersistence;
import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.exception.MysteriousException;
import com.example.javafx_app.object.Account.*;
import com.example.javafx_app.object.Bill.Bill;
import com.example.javafx_app.object.Bill.BillType;
import com.example.javafx_app.object.Noti.Notification;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.Staff;
import com.example.javafx_app.object.User.USER_TYPE;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.config.Constant;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AccountManager {
    private static final AccountManager instance = new AccountManager();
    private AccountManager(){}
    private static final Map<String, Account> accountMap = new HashMap<>();
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

        return "1"+newID;
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
    // Thêm setter để lớp Persistence có thể ghi đè dữ liệu
    public void setAccountMap(Map<String, Account> newAccountMap) {
        if (newAccountMap != null) {
            accountMap.clear();
            accountMap.putAll(newAccountMap);
        }
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
            StringBuilder savingID = new StringBuilder(savingAccountID);
            savingID.setCharAt(0,'2');
            savingAccountID = savingID.toString();
            account = new SavingAccount(customer.getFullName(), customer.getCitizenID(),savingAccountID,
                     password,Constant.DEFAULT_BALANCE,"VND",pin);
        }
        else if(accountType.equals(ACCOUNT_TYPE.LOAN.toString())){
            String loanAccountID = customer.getCheckingAccountID();
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
             filehome = "HomeScenes/checking_account_home_scene.fxml";
        }
        else if(account.getAccountType()==ACCOUNT_TYPE.SAVING){
             filehome = "HomeScenes/saving_account_home_scene.fxml";
        }
        else if(account.getAccountType()==ACCOUNT_TYPE.LOAN){
              filehome = "HomeScenes/loan_account_home_scene.fxml";
        }
        else filehome = "HomeScenes/staff_home_scene.fxml";
        return filehome;
    }
    //Tìm kiếm account
    public Account findAccount(String accountID) {
        System.out.println(accountMap.get(accountID));
        return accountMap.get(accountID);
    }
    public CheckingAccount findCheckingAccount(Account account){
        return switch (account) {
            case CheckingAccount checkingAccount -> checkingAccount;
            case SavingAccount savingAccount -> findCheckingAccount(savingAccount);
            case LoanAccount loanAccount -> findCheckingAccount(loanAccount);
            case null, default -> throw new MysteriousException();
        };
    }
    public CheckingAccount findCheckingAccount(CheckingAccount checkingAccount){
        return checkingAccount;
    }
    public CheckingAccount findCheckingAccount(SavingAccount savingAccount){
        StringBuilder checkingAccountID = new StringBuilder(savingAccount.getAccountID());
        checkingAccountID.setCharAt(0,'1');
        return (CheckingAccount) findAccount(checkingAccountID.toString());
    }
    public CheckingAccount findCheckingAccount(LoanAccount loanAccount){
        StringBuilder checkingAccountID = new StringBuilder(loanAccount.getAccountID());
        checkingAccountID.setCharAt(0,'1');
        System.out.println(checkingAccountID);
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
    public void addMonthlyBills(CheckingAccount account, LocalDate previousDate, LocalDate startDate) {
        // Đảm bảo previousDate không lớn hơn hoặc bằng startDate
        if (previousDate.isAfter(startDate) || previousDate.isEqual(startDate)) {
            return;
        }

        // Bắt đầu từ tháng tiếp theo sau previousDate
        YearMonth currentMonth = YearMonth.from(previousDate).plusMonths(1);
        // Lặp cho đến tháng chứa startDate
        YearMonth endMonth = YearMonth.from(startDate);

        Random random = new Random();

        // Lặp qua từng tháng
        while (!currentMonth.isAfter(endMonth)) {
            // Ngày 10 của tháng hiện tại
            LocalDate billDate;
            try {
                billDate = currentMonth.atDay(10);
            } catch (Exception e) {
                // Xử lý nếu tháng không có ngày 10 (thực tế không xảy ra)
                currentMonth = currentMonth.plusMonths(1);
                continue;
            }

            // Chỉ thêm hóa đơn nếu ngày 10 nằm trong khoảng (previousDate, startDate]
            // Tức là lớn hơn ngày trước đó và nhỏ hơn hoặc bằng ngày hiện tại
            if (billDate.isAfter(previousDate) && (billDate.isBefore(startDate) || billDate.isEqual(startDate))) {

                // Thêm hóa đơn cho các dịch vụ đã đăng ký (giả định có các getter trong CheckingAccount)
                // Các giá trị amount và supplier là giả định/placeholder

                if (account.isElectricService()) {
                    long electricAmount = 500000 + random.nextInt(200000); // Số tiền ngẫu nhiên
                    Bill electricBill = new Bill(electricAmount, billDate, BillType.ELECTRIC,
                            ExampleUser.ELECTRIC_PROVIDER.getAccountName()); //
                    account.addBill(electricBill); //
                }

                if (account.isWaterService()) {
                    long waterAmount = 200000 + random.nextInt(100000); // Số tiền ngẫu nhiên
                    Bill waterBill = new Bill(waterAmount, billDate, BillType.WATER,
                            ExampleUser.WATER_PROVIDER.getAccountName()); //
                    account.addBill(waterBill); //
                }

                if (account.isInternetService()) {
                    long internetAmount = 150000 + random.nextInt(50000); // Số tiền ngẫu nhiên
                    Bill internetBill = new Bill(internetAmount, billDate, BillType.INTERNET,
                            ExampleUser.INTERNET_PROVIDER.getAccountName()); //
                    account.addBill(internetBill); //
                }

                if (account.isSchoolService()) {
                    long tuitionAmount = 3000000 + random.nextInt(1000000); // Số tiền ngẫu nhiên
                    Bill tuitionBill = new Bill(tuitionAmount, billDate, BillType.TUITION,
                            ExampleUser.SCHOOL_PROVIDER.getAccountName()); //
                    account.addBill(tuitionBill); //
                }
            }

            // Chuyển sang tháng tiếp theo
            currentMonth = currentMonth.plusMonths(1);
        }
    }
    public void checkAndCancelAllServices(CheckingAccount account) {
        List<Bill> bills = account.getBills();
        if (bills == null || bills.isEmpty()) return;

        LocalDate today = BankManager.getCurrentDate();
        LocalDate limitDate = today.minusMonths(3);

        // 1. Tìm ngày xa nhất cho từng loại
        Map<BillType, LocalDate> oldestDates = new HashMap<>();
        for (Bill bill : bills) {
            BillType type = bill.getBillType();
            LocalDate date = bill.getDate();
            if (!oldestDates.containsKey(type) || date.isBefore(oldestDates.get(type))) {
                oldestDates.put(type, date);
            }
        }

        // 2. Kiểm tra quá hạn và gửi Notification
        for (Map.Entry<BillType, LocalDate> entry : oldestDates.entrySet()) {
            BillType type = entry.getKey();
            LocalDate oldestDate = entry.getValue();

            if (oldestDate.isBefore(limitDate)) {
                String serviceName = "";
                switch (type) {
                    case ELECTRIC:
                        account.setElectricService(false);
                        serviceName = "Điện";
                        break;
                    case WATER:
                        account.setWaterService(false);
                        serviceName = "Nước";
                        break;
                    case INTERNET:
                        account.setInternetService(false);
                        serviceName = "Internet";
                        break;
                    case TUITION:
                        account.setSchoolService(false);
                        serviceName = "Học phí";
                        break;
                }

                // Tạo thông báo và thêm vào tài khoản thay vì chỉ sout
                if (!serviceName.isEmpty()) {
                    bills.removeIf(b -> b.getBillType() == type);
                    Notification cancelNoti = NotiManager.getNotiForServiceCancellation(serviceName, oldestDate);
                    account.addNotification(cancelNoti); //
                }
            }
        }
    }
    public  void updateSavingBalance(SavingAccount savingAccount,LocalDate oldDate,LocalDate newDate) {
        // 1. Tính tổng số tháng trọn vẹn từ lúc gửi đến hôm nay
        LocalDate startDate = savingAccount.getStartSavingDate();
        if(startDate==null) System.out.println("startDate==null");
        if(oldDate==null) System.out.println("oldDate==null");
        if(newDate==null) System.out.println("newDate==null");
        long totalMonthsToToday = ChronoUnit.MONTHS.between(startDate, newDate);

        // 2. Tính tổng số tháng trọn vẹn từ lúc gửi đến lần đăng nhập cuối
        long totalMonthsToLastLogin = ChronoUnit.MONTHS.between(startDate, oldDate);

        // 3. Số tháng chênh lệch cần phải trả lãi thêm
        long totalMonths = totalMonthsToToday - totalMonthsToLastLogin;
        if(totalMonths <= 0){
            return;
        }
        switch (savingAccount.getType()) {
            case SavingType.FLEXIBLE :
                for (int i = 1; i <= totalMonths; i++) {
                    savingAccount.applyFlexibleInterest();
                }
                savingAccount.addNotification(NotiManager.getNotiForSavingUpdate(savingAccount.getAccountID(),
                        savingAccount.getSaving(),totalMonths));
                break;
            case SavingType.ACCUMULATED:
                for (int i = 1; i <= totalMonths; i++) {
                    savingAccount.applyAccumulatedInterest();
                }
                savingAccount.addNotification(NotiManager.getNotiForSavingUpdate(savingAccount.getAccountID(),
                        savingAccount.getSaving(),totalMonths));
                break;
            case SavingType.FIXED:
                if(savingAccount.isOverdue()){
                    savingAccount.addNotification(NotiManager.getNotiForFixedSavingMaturity(
                            savingAccount.getAccountID(), savingAccount.applyFixedInterest(),
                            findCheckingAccount(savingAccount).getAccountID() ));
                }
                break;
        }
    }
    public void updateLoanBalance(LoanAccount loanAccount, LocalDate oldDate, LocalDate newDate){
        LocalDate startDate = loanAccount.getStartLoanDate();
        long totalMonthsToToday = ChronoUnit.MONTHS.between(startDate, newDate);

        long totalMonthsToLastLogin = ChronoUnit.MONTHS.between(startDate, oldDate);

        long totalMonths = totalMonthsToToday - totalMonthsToLastLogin;
        if(totalMonths <= 0){
            return;
        }
        switch (loanAccount.getType()){
            case FIXED:
                for (int i = 1; i <= totalMonths; i++) {
                    loanAccount.applyFixedInterest();
                }
                loanAccount.addNotification(NotiManager.getNotiForLoanUpdate(loanAccount.getAccountID(),
                        loanAccount.getDebt(),totalMonths));
                break;
            case ACCUMULATED:
                for (int i = 1; i <= totalMonths; i++) {
                    loanAccount.applyAccumulatedInterest();
                }
                loanAccount.addNotification(NotiManager.getNotiForLoanUpdate(loanAccount.getAccountID(),
                        loanAccount.getDebt(),totalMonths));
                break;
        }
        if(loanAccount.isOverdue()){
            loanAccount.addNotification(NotiManager.getNotiForLoanMaturity(
                    loanAccount.getAccountID(), loanAccount.getDebt(),
                    findCheckingAccount(loanAccount).getAccountID()));
        }
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
                                 + "\n\tPIN: "+a.getPIN()
                                    + "\n\tPIN: "+a.getStartDate()
                    + "\n");
        }
    }
    public static void main(String[] args) {
        DataPersistence.loadAllData();
        AccountManager.getInstance().accountListLog();
    }

    // Hàm của bạn cần test (đưa vào static để gọi trong main)

}
