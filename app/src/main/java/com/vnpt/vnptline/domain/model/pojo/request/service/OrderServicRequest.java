package com.vnpt.vnptline.domain.model.pojo.request.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 5/3/2018.
 */

public class OrderServicRequest {
    @SerializedName("bookingDetailId")
    @Expose
    @Getter @Setter
    public Integer bookingDetailId;

    @SerializedName("count")
    @Expose
    @Getter @Setter
    public Integer count;

    @SerializedName("serviceId")
    @Expose
    @Getter @Setter
    public Integer serviceId;

    @SerializedName("userId")
    @Expose
    @Getter @Setter
    public Integer userId;
}
