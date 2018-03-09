package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 5/2/2018.
 */

public class VerifyOTPRequest {
    @Expose
    @SerializedName("otpNumber")
    @Getter @Setter
    private String otpNumber;

    @Expose
    @SerializedName("otpType")
    @Getter @Setter
    private String otpType;

    @Expose
    @SerializedName("username")
    @Getter @Setter
    private String username;

    public VerifyOTPRequest(String otpNumber, String otpType, String username) {
        this.otpNumber = otpNumber;
        this.otpType = otpType;
        this.username = username;
    }
}
