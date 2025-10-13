package com.example.javafx_app;

import java.time.LocalDate;

public class UserInfo {
    private enum Gender {
        MALE, FEMALE, OTHER
    }
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String citizenID;

    // ✅ Constructor đầy đủ
    public UserInfo(String fullName, LocalDate dateOfBirth, Gender gender,
                    String phoneNumber, String email, String citizenID) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenID = citizenID;
    }

    // ✅ Constructor rỗng (cần thiết cho JavaFX hoặc khi khởi tạo tạm thời)
    public UserInfo() {}

    // ✅ Getter & Setter
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    // ✅ In ra thông tin người dùng (dễ debug)
    @Override
    public String toString() {
        return "UserInfo{" +
                "fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", citizenID='" + citizenID + '\'' +
                '}';
    }

    public static Gender stringToGender(String gender){
        return switch (gender) {
            case "MALE" -> Gender.MALE;
            case "FEMALE" -> Gender.FEMALE;
            default -> Gender.OTHER;
        };
    }
    public static String genderToString(Gender gender){
        return switch (gender){
            case MALE -> "MALE";
            case FEMALE -> "FEMALE";
            default -> "OTHER";
        };
    }
}
