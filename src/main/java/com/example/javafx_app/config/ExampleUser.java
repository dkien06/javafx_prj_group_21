package com.example.javafx_app.config;

import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.manager.AccountManager;
import com.example.javafx_app.manager.UserManager;
import com.example.javafx_app.object.User.GENDER;
import com.example.javafx_app.object.User.User;

import java.time.LocalDate;

/**
 * Tài khoản ảo thôi:)
 */
public class ExampleUser {
    private static final Account accountA = new Account(
            "Nguyen Van A",
            "010203008386",
            "49538386",
            "NguyenVanA#1970",
            "VND",
            "010170");
    private static final User userA = new User(
            "Nguyễn Văn A",
            LocalDate.of(1970,1,1),
            GENDER.MALE,
            "0123456789",
            "NguyenVanA@gmail.com",
            "010203008386"
    );
    private static final Account accountB = new Account(
            "Tran Thi B",
            "010203004953",
            "83864953",
            "TranThiB@1975",
            "VND",
            "123456");
    private static final User userB = new User(
            "Trần Thị B",
            LocalDate.of(1975,4,30),
            GENDER.FEMALE,
            "0987654321",
            "TranThiB@vnu.edu.vn",
            "010203004953"
    );
    private static final Account accountC = new Account(
            "Ngo Duc C",
            "020406006769",
            "12345678",
            "TaoBiGay%2008",
            "VND",
            "112233"
    );
    private static final User userC = new User(
            "Ngô Đức C",
            LocalDate.of(2007,3,6),
            GENDER.OTHER,
            "0135792468",
            "C.ND@sis.hust.edu.vn",
            "020406006769"
    );
    private static final Account accountD = new Account(
            "Le Thi Thuy D",
            "020406006967",
            "87654321",
            "ThuyDXinhGai><2006",
            "VND",
            "112233"
    );
    private static final User userD = new User(
            "Lê Thị Thúy D",
            LocalDate.of(2006,6,7),
            GENDER.FEMALE,
            "0246813579",
            "DLeThiThuy@outlook.com",
            "020406006967"
    );
    public static void init(){
        AccountManager.getInstance().getAccountList().add(accountA);
        AccountManager.getInstance().getAccountList().add(accountB);
        AccountManager.getInstance().getAccountList().add(accountC);
        AccountManager.getInstance().getAccountList().add(accountD);

        AccountManager.getInstance().resisterCheckingAccount(accountA,2_000_000);
        AccountManager.getInstance().resisterCheckingAccount(accountB,1_000_000);

        UserManager.getInstance().addUser(userA);
        UserManager.getInstance().addUser(userB);
        UserManager.getInstance().addUser(userC);
        UserManager.getInstance().addUser(userD);
    }
}
