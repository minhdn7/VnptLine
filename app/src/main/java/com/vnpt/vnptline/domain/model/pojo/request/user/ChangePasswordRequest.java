package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/2/2018.
 */

public class ChangePasswordRequest {
    @Getter @Setter
    @SerializedName("username")
    private String username;

    @Getter @Setter
    @SerializedName("password")
    private String password;

    public ChangePasswordRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
