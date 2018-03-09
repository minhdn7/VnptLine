package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 30/1/2018.
 */

public class ThongBaoResponse {
    @SerializedName("message")
    @Getter @Setter
    @Expose
    private String message;

    @SerializedName("notificationDate")
    @Getter @Setter
    @Expose
    private String notificationDate;

    @SerializedName("notificationId")
    @Getter @Setter
    @Expose
    private String notificationId;

    @SerializedName("title")
    @Getter @Setter
    @Expose
    private String title;

    @SerializedName("notificationType")
    @Getter @Setter
    @Expose
    private String notificationType;

    @SerializedName("data")
    @Getter @Setter
    @Expose
    private String data;
}
