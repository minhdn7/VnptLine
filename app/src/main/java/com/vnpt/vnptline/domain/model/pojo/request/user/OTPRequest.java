package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 5/2/2018.
 */

public class OTPRequest {
    @Expose
    @SerializedName("deviceId")
    @Getter @Setter
    private String deviceId;

    @Expose
    @SerializedName("otpType")
    @Getter @Setter
    private String otpType;

    @Expose
    @SerializedName("username")
    @Getter @Setter
    private String username;

    public OTPRequest(String deviceId, String otpType, String username) {
        this.deviceId = deviceId;
        this.otpType = otpType;
        this.username = username;
    }
}
