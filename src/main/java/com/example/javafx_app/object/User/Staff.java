package com.example.javafx_app.object.User;

import java.time.LocalDate;

public class Staff extends User {
    private String staffID;
    private String accountID;

    public Staff(String staffID, String accountID, String fullName, LocalDate dateOfBirth,
                 GENDER gender, String phoneNumber, String email, String citizenID) {
        super(fullName, dateOfBirth, gender, phoneNumber, email, citizenID);
        this.staffID = staffID;
        this.accountID = accountID;
    }

    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }

    public String getAccountID() { return accountID; }
    public void setAccountID(String accountID) { this.accountID = accountID; }

    @Override
    public String toString() {
        return "StaffID: " + staffID + ", AccountID: " + accountID + ", " + super.toString();
    }
    @Override
    public USER_TYPE getType() { return  USER_TYPE.STAFF; }
}
