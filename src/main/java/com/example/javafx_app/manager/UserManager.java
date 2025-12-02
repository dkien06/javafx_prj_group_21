package com.example.javafx_app.manager;

import com.example.javafx_app.config.ExampleUser;
import com.example.javafx_app.object.Account.ACCOUNT_TYPE;
import com.example.javafx_app.object.Account.Account;
import com.example.javafx_app.object.User.Customer;
import com.example.javafx_app.object.User.GENDER;
import com.example.javafx_app.object.User.User;
import com.example.javafx_app.config.ExampleUser;

import java.util.*;

public class UserManager {
    private final static UserManager instance = new UserManager();
    private UserManager(){}

    private static User currentUser,signUpUser;
    private static final Map<String, User> userMap = new HashMap<>();

    public static UserManager getInstance() {
        return instance;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public User getSignUpUser() { return signUpUser; }
    public Map<String,User> getUserList() {
        return userMap;
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }
    public void setSignUpUser(User user){ signUpUser = user; }
    public void addUser(User user){
        userMap.put(user.getCitizenID(),user);
    }
    public User findUserByCitizenID(String citizenID){
        return userMap.get(citizenID);
    }
    public User findUserFromAccount(Account account){
        if(account == null) return null;
        return userMap.get(account.getAccountID()) ;
    }
    // Hàm tìm kiếm khi userMap đang lưu theo CCCD
    public User findUserFromEmail(String email){
        // Phải duyệt qua toàn bộ các giá trị (Values) trong Map
        for(User u : userMap.values()){
            if(u.getEmail() != null && u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    public User findUserFromPhoneNumber(String phoneNumber){
        for(User u : userMap.values()){
            if(u.getPhoneNumber() != null && u.getPhoneNumber().equals(phoneNumber)){
                return u;
            }
        }
        return null;
    }
    public static GENDER stringToGender(String gender){
        return switch (gender) {
            case "MALE" -> GENDER.MALE;
            case "FEMALE" -> GENDER.FEMALE;
            default -> GENDER.OTHER;
        };
    }
    public static String genderToString(GENDER gender) {
        return switch (gender) {
            case MALE -> "MALE";
            case FEMALE -> "FEMALE";
            default -> "OTHER";
        };
    }
    public static void main(String[] args) {
        ExampleUser.init();
        System.out.println(getInstance().findUserByCitizenID("010102030508"));
    }

}
