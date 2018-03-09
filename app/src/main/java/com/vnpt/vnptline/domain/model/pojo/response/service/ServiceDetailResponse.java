package com.vnpt.vnptline.domain.model.pojo.response.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class ServiceDetailResponse {
    @SerializedName("serviceId")
    @Expose
    @Setter
    @Getter
    public Integer serviceId;

    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    public Integer hotelId;

    @SerializedName("type")
    @Expose
    @Setter @Getter
    public String type;

    @SerializedName("name")
    @Expose
    @Setter @Getter
    public String name;

    @SerializedName("price")
    @Expose
    @Setter @Getter
    public Integer price;

    @SerializedName("status")
    @Expose
    @Setter @Getter
    public String status;

    @SerializedName("amount")
    @Expose
    @Setter @Getter
    public Integer amount;
}
