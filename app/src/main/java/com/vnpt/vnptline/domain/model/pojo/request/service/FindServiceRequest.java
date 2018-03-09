package com.vnpt.vnptline.domain.model.pojo.request.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class FindServiceRequest {
    @SerializedName("hotelId")
    @Expose
    @Getter @Setter
    public Integer hotelId;

    @SerializedName("type")
    @Expose
    @Getter @Setter
    public String type;

    public FindServiceRequest(Integer hotelId, String type) {
        this.hotelId = hotelId;
        this.type = type;
    }
}
