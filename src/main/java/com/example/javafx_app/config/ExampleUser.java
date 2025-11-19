package com.example.javafx_app.config;

import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.Account.CheckingAccount;
import com.example.javafx_app.object.Account.LoanAccount;
import com.example.javafx_app.object.Account.SavingAccount;
import com.example.javafx_app.object.User.Costumer;
import com.example.javafx_app.object.User.GENDER;

import java.time.LocalDate;

/**
 * Tài khoản ảo thôi:)
 */
public class ExampleUser {
    private static final Account accountA = new SavingAccount(
            "Nguyen Van A",
            "010203008386",     // citizenID
            "49538386",         // accountID
            "NguyenVanA#1970",  // password
            2_000_000,          // balance
            "VND",              // currency
            "010170"            // PIN
    );

    // Sử dụng Costumer thay vì User vì User là abstract
    private static final Costumer userA = new Costumer(
            "CUS001",           // customerID (mới thêm)
            "Nguyễn Văn A",
            LocalDate.of(1970,1,1),
            GENDER.MALE,
            "0123456789",
            "NguyenVanA@gmail.com",
            "010203008386"
    );

    // === USER B ===
    private static final Account accountB = new LoanAccount(
            "Tran Thi B",
            "010203004953",
            "83864953",
            "TranThiB@1975",
            1_000_000,          // balance (1.000.000 từ code cũ)
            "VND",
            "123456"
    );

    private static final Costumer userB = new Costumer(
            "CUS002",
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
            "12345678",
            "TaoBiGay%2008",
            0,                  // balance (mặc định 0)
            "VND",
            "112233"
    );

    private static final Costumer userC = new Costumer(
            "CUS003",
            "Ngô Đức C",
            LocalDate.of(2007,3,6),
            GENDER.OTHER,
            "0135792468",
            "C.ND@sis.hust.edu.vn",
            "020406006769"
    );

    // === USER D ===
    private static final Account accountD = new CheckingAccount(
            "Le Thi Thuy D",
            "020406006967",
            "87654321",
            "ThuyDXinhGai><2006",
            0,                  // balance (mặc định 0)
            "VND",
            "112233"
    );

    private static final Costumer userD = new Costumer(
            "CUS004",
            "Lê Thị Thúy D",
            LocalDate.of(2006,6,7),
            GENDER.FEMALE,
            "0246813579",
            "DLeThiThuy@outlook.com",
            "020406006967"
    );
    public static void init(){
        AccountManager.getInstance().getAccountList().put(accountA.getAccountID(),accountA);
        AccountManager.getInstance().getAccountList().put(accountB.getAccountID(),accountB);
        AccountManager.getInstance().getAccountList().put(accountC.getAccountID(),accountC);
        AccountManager.getInstance().getAccountList().put(accountD.getAccountID(),accountD);
        userA.addAccountID(accountA.getAccountID());
        userB.addAccountID(accountB.getAccountID());
        userC.addAccountID(accountC.getAccountID());
        userD.addAccountID(accountD.getAccountID());
        UserManager.getInstance().addUser(userA);
        UserManager.getInstance().addUser(userB);
        UserManager.getInstance().addUser(userC);
        UserManager.getInstance().addUser(userD);
    }
}
