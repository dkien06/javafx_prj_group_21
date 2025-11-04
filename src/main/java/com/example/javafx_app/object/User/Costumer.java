package com.example.javafx_app.object.User;

import java.time.LocalDate;

public class Costumer extends User {
        private String customerID;

        public Costumer(String customerID, String fullName, LocalDate dateOfBirth,
                        GENDER gender, String phoneNumber, String email, String citizenID) {
            super(fullName, dateOfBirth, gender, phoneNumber, email, citizenID);
            this.customerID = customerID;
        }

        public String getCustomerID() { return customerID; }
        public void setCustomerID(String customerID) { this.customerID = customerID; }
        @Override
        public String toString() {
            return "CustomerID: " + customerID + ", " + super.toString();
        }
        public USER_TYPE getUserType() { return  USER_TYPE.COSTUMER; }
}

