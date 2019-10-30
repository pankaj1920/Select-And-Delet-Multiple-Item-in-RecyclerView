package com.example.selectanddeletmultipeiteminrecyclerview;

public class Item_Model_Class {

    int profile_image;
    String name, email;

    public Item_Model_Class(int profile_image, String name, String email) {
        this.profile_image = profile_image;
        this.name = name;
        this.email = email;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
