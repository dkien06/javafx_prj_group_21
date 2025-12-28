package com.example.javafx_app.config;

import com.example.javafx_app.object.Account.*;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.GENDER;
import com.example.javafx_app.object.User.Staff;

import java.time.LocalDate;

/**
 * Tài khoản ảo thôi:)
 */
public class ExampleUser {
    public static final Account accountA = new CheckingAccount(
            "Phan Đức Kiên",
            "031206018246",     // citizenID
            "149538386",         // accountID
            "Kien8906@@",  // password
            10_000_000,          // balance
            "VND",              // currency
            "010170"            // PIN
    );
    // Sử dụng Customer thay vì User vì User là abstract
    public static final Customer userA = new Customer(// customerID (mới thêm)
            "Phan Đức Kiên",
            LocalDate.of(1970,1,1),
            GENDER.MALE,
            "0904004116",
            "Kien@gmail.com",
            "031206018246"
    );

    // === USER B ===
    private static final Account accountB = new CheckingAccount(
            "kkk",
            "010203004953",
            "183864953",
            "TranThiB@1975",
            5_000_000_000L,          // balance (1.000.000 từ code cũ)
            "VND",
            "123456"
    );
    private static final Account accountB1 = new LoanAccount(
            "kkk",
            "010203004953",
            "383864953",
            "TranThiB@1975",
            0,          // balance (1.000.000 từ code cũ)
            "VND",
            "123456"
    );

    private static final Customer userB = new Customer(
            "Trần Thị B",
            LocalDate.of(1975,4,30),
            GENDER.FEMALE,
            "0987654321",
            "TranThiB@vnu.edu.vn",
            "010203004953"
    );

    // === USER C ===
    private static final Account accountC = new CheckingAccount(
            "Ngo Duc C",
            "020406006769",
            "112345678",
            "CuongDuc%2008",
            2_000_000,                  // balance (mặc định 0)
            "VND",
            "112233"
    );
    private static final Account accountC1 = new SavingAccount(
            "Ngo Duc C",
            "020406006769",
            "212345678",
            "        ",
            1_000_000,                  // balance (mặc định 0)
            "VND",
            "112233"
    );
    private static final Customer userC = new Customer(
            "Ngô Đức C",
            LocalDate.of(2007,3,6),
            GENDER.OTHER,
            "0135792468",
            "C.ND@sis.hust.edu.vn",
            "020406006769"
    );

    // === USER D ===
    private static final Account accountD = new StaffAccount(
            "Le Thi Thuy D",
            "020406006967",
            "87654321",
            "ThuyDXinhGai><2000",
            "VND",
            "112233"
    );

    private static final Staff userD = new Staff(
            "CA_2022_1012",
            "87654321",
            "Lê Thị Thúy D",
            LocalDate.of(2000, 3,8),
            GENDER.FEMALE,
            "0173205081",
            "DLeThi2000@21stbank.com",
            "020406006967"
    );

    // === USER E ===
    private static final Account accountE = new CheckingAccount(
            "Hoang Thi E",
            "010102030508",
            "116180340",
            "EnHoang*1024",
            1_000_000,                  // balance (mặc định 0)
            "VND",
            "258013"
    );

    private static final Customer userE = new Customer(
            "Hoàng Thị E",
            LocalDate.of(2000,1,1),
            GENDER.FEMALE,
            "0141421356",
            "HoangVanThai1970@gmail.com",
            "010102030508"
    );
    // ================== BILL GIẢ LẬP ==================
    // ================== NHÀ CUNG CẤP DỊCH VỤ (CHECKING ACCOUNT) ==================
    public static final CheckingAccount ELECTRIC_PROVIDER = new CheckingAccount(
            "EVN HÀ NỘI",
            "999100000001",        // citizenID giả
            "90000001",            // accountID
            "EVN@123",             // password
            10_000_000,            // balance (dùng để nhận tiền thanh toán)
            "VND",
            "000001"               // PIN
    );

    public static final CheckingAccount WATER_PROVIDER = new CheckingAccount(
            "NƯỚC SẠCH HÀ NỘI",
            "999100000002",
            "90000002",
            "WATER@123",
            10_000_000,
            "VND",
            "000002"
    );

    public static final CheckingAccount INTERNET_PROVIDER = new CheckingAccount(
            "VIETTEL INTERNET",
            "999100000003",
            "90000003",
            "INTERNET@123",
            10_000_000,
            "VND",
            "000003"
    );

    public static final CheckingAccount SCHOOL_PROVIDER = new CheckingAccount(
            "ĐẠI HỌC BÁCH KHOA",
            "999100000004",
            "90000004",
            "EDU@123",
            10_000_000,
            "VND",
            "000004"
    );
    //========Noti Gia Lap ================
    public static void addExample(){
        CheckingAccount CheckingAccountC = (CheckingAccount) accountC;
        AccountManager.getInstance().getAccountList().put(accountA.getAccountID(),accountA);
        AccountManager.getInstance().getAccountList().put(accountB.getAccountID(),accountB);
        AccountManager.getInstance().getAccountList().put(accountB1.getAccountID(), accountB1);
        AccountManager.getInstance().getAccountList().put(accountC.getAccountID(),accountC);
        AccountManager.getInstance().getAccountList().put(accountC1.getAccountID(),accountC1);
        AccountManager.getInstance().getAccountList().put(accountD.getAccountID(),accountD);
        AccountManager.getInstance().getAccountList().put(accountE.getAccountID(),accountE);
        AccountManager.getInstance().getAccountList().put(ELECTRIC_PROVIDER.getAccountID(), ELECTRIC_PROVIDER);
        AccountManager.getInstance().getAccountList().put(WATER_PROVIDER.getAccountID(), WATER_PROVIDER);
        AccountManager.getInstance().getAccountList().put(INTERNET_PROVIDER.getAccountID(), INTERNET_PROVIDER);
        AccountManager.getInstance().getAccountList().put(SCHOOL_PROVIDER.getAccountID(), SCHOOL_PROVIDER);
        userA.addAccountID(accountA.getAccountID());
        userB.addAccountID(accountB.getAccountID());
        userB.addAccountID(accountB1.getAccountID());
        userC.addAccountID(accountC.getAccountID());
        userC.addAccountID(accountC1.getAccountID());
        userE.addAccountID(accountE.getAccountID());
        UserManager.getInstance().addUser(userA);
        UserManager.getInstance().addUser(userB);
        UserManager.getInstance().addUser(userC);
        UserManager.getInstance().addUser(userD);
        UserManager.getInstance().addUser(userE);
        // Gọi hàm thêm giao dịch mẫu
    }
    public static void init(){
        addExample();
    }
    public static void setCurrentAccount(){
        ((SavingAccount)accountC1).setType(SavingType.FLEXIBLE);
        AccountManager.getInstance().setCurrentAccount(accountC1);
    }
}
