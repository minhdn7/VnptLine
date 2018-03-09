package com.vnpt.vnptline.domain.model.pojo.request.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 6/3/2018.
 */

public class SupportServiceRequest {
    @SerializedName("bookingDetailId")
    @Expose
    @Getter @Setter
    public Integer bookingDetailId;

    @SerializedName("description")
    @Expose
    @Getter @Setter
    public String description;

    @SerializedName("name")
    @Expose
    @Getter @Setter
    public String name;

    @SerializedName("type")
    @Expose
    @Getter @Setter
    public String type;


    @SerializedName("userId")
    @Expose
    @Getter @Setter
    public Integer userId;
}
