package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/24/2018.
 */

public class LoginAccountRequest {
    @Getter @Setter
    @SerializedName("tokenFirebase")
    private String tokenFirebase;

    @Getter @Setter
    @SerializedName("username")
    private String username;

    @Getter @Setter
    @SerializedName("password")
    private String password;



    public LoginAccountRequest(String tokenFirebase, String username, String password) {
        this.tokenFirebase = tokenFirebase;
        this.password = password;
        this.username = username;
    }
}
