package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/2/2018.
 */

public class RegisterUserRequest {
    @Expose
    @SerializedName("name")
    @Getter @Setter
    private String name;

    @Expose
    @SerializedName("password")
    @Getter @Setter
    private String password;

    @Expose
    @SerializedName("type")
    @Getter @Setter
    private String type;

    @Expose
    @SerializedName("username")
    @Getter @Setter
    private String username;

    @Expose
    @SerializedName("roles")
    @Getter @Setter
//    private String[] roles = null;
    private List<String> roles = null;


}
