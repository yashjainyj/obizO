package com.example.obizo.seller;

public class Shop_Detais_Modal {
    private String shop_Id;
    private String shop_Name;
    private String shop_Address;
    private String shop_rating;
    private String image_Url;
    private String min;
    private String contact_number;
    private String upi;
    private String userId;
    private String emailid;

    public Shop_Detais_Modal() {
    }
    public Shop_Detais_Modal(String shop_Id, String shop_Name, String shop_Address, String shop_rating, String min, String image_Url, String contact_number, String upi, String userId, String emailid) {
        this.shop_Id = shop_Id;
        this.shop_Name = shop_Name;
        this.shop_Address = shop_Address;
        this.shop_rating = shop_rating;
        this.image_Url = image_Url;
        this.min = min;
        this.contact_number = contact_number;
        this.upi = upi;
        this.userId = userId;
        this.emailid = emailid;
    }

    public String getShop_Id() {
        return shop_Id;
    }

    public void setShop_Id(String shop_Id) {
        this.shop_Id = shop_Id;
    }

    public String getShop_Name() {
        return shop_Name;
    }

    public void setShop_Name(String shop_Name) {
        this.shop_Name = shop_Name;
    }

    public String getShop_Address() {
        return shop_Address;
    }

    public void setShop_Address(String shop_Address) {
        this.shop_Address = shop_Address;
    }

    public String getShop_rating() {
        return shop_rating;
    }

    public void setShop_rating(String shop_rating) {
        this.shop_rating = shop_rating;
    }

    public String getImage_Url() {
        return image_Url;
    }

    public void setImage_Url(String image_Url) {
        this.image_Url = image_Url;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}

