package com.example.nguyentanlinh_2122110398.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}


