package com.example.javafx_app.object.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
        private List<String> accountIDs ;
        public Customer(String fullName, LocalDate dateOfBirth,
                        GENDER gender, String phoneNumber, String email, String citizenID) {
            super(fullName, dateOfBirth, gender, phoneNumber, email, citizenID);
            accountIDs = new ArrayList<>();
        }

        @Override
        public String toString() {
            return  super.toString();
        }
        public USER_TYPE getUserType() { return  USER_TYPE.COSTUMER; }
        public List<String> getAccountIDs() { return accountIDs; }
        public void addAccountID(String newAccountID) { accountIDs.add(newAccountID); }
        @Override
        public USER_TYPE getType() { return USER_TYPE.COSTUMER; }
}

