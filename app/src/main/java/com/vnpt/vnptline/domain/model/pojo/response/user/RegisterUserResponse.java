package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/2/2018.
 */

public class RegisterUserResponse {
    @Getter @Setter
    @SerializedName("responseCode")
    private Integer responseCode;

    @Getter @Setter
    @SerializedName("responseMessage")
    private String responseMessage;

    @Getter @Setter
    @SerializedName("userId")
    private String userId;

    @Getter @Setter
    @SerializedName("username")
    private String username;
}
