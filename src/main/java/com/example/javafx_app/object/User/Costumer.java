package com.example.javafx_app.object.User;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Costumer extends User {
        private String customerID;
        private List<Pair<String,String>> accounts;


        public Costumer(String customerID, String fullName, LocalDate dateOfBirth,
                        GENDER gender, String phoneNumber, String email, String citizenID) {
            super(fullName, dateOfBirth, gender, phoneNumber, email, citizenID);
            this.customerID = customerID;
            this.accounts = new ArrayList<>();
        }

        public String getCustomerID() { return customerID; }
        public void setCustomerID(String customerID) { this.customerID = customerID; }
        public List<Pair<String,String>> getAccounts() { return accounts; }
        @Override
        public String toString() {
            return "CustomerID: " + customerID + ", " + super.toString();
        }
        public USER_TYPE getUserType() { return  USER_TYPE.COSTUMER; }
}

