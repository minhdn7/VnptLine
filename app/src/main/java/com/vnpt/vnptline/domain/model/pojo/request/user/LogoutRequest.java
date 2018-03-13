package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 13/3/2018.
 */

public class LogoutRequest {
    @SerializedName("tokenFirebase")
    @Expose
    @Getter @Setter
    private String tokenFirebase;

    @SerializedName("userId")
    @Expose
    @Getter @Setter
    private Integer userId;

    public LogoutRequest(String tokenFirebase, Integer userId) {
        this.tokenFirebase = tokenFirebase;
        this.userId = userId;
    }
}
