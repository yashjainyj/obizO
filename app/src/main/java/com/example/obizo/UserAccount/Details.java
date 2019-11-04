package com.example.obizo.UserAccount;

public class Details {

    private String picUrl;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String number;
    private String address;
    private String bio;
    private String occupation;
    private String BusinessName;
    private String BusinessDescription;
    private String BusinessAcquiredYear;
    private String BusinessType;
    private String Localty;

    public Details(String picUrl, String userName, String firstName, String lastName, String email, String number, String address, String bio, String occupation, String businessName, String businessDescription, String businessAcquiredYear, String businessType, String localty) {
        this.picUrl = picUrl;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.address = address;
        this.bio = bio;
        this.occupation = occupation;
        BusinessName = businessName;
        BusinessDescription = businessDescription;
        BusinessAcquiredYear = businessAcquiredYear;
        BusinessType = businessType;
        Localty = localty;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getBusinessDescription() {
        return BusinessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        BusinessDescription = businessDescription;
    }

    public String getBusinessAcquiredYear() {
        return BusinessAcquiredYear;
    }

    public void setBusinessAcquiredYear(String businessAcquiredYear) {
        BusinessAcquiredYear = businessAcquiredYear;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(String businessType) {
        BusinessType = businessType;
    }

    public String getLocalty() {
        return Localty;
    }

    public void setLocalty(String localty) {
        Localty = localty;
    }
}
