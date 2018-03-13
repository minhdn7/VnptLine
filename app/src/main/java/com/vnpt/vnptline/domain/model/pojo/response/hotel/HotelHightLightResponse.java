package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 13/3/2018.
 */

public class HotelHightLightResponse {
    @SerializedName("hotels")
    @Expose
    @Setter @Getter
    private List<HotelHightLight> hotelList = null;

    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    private Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    private String responseMessage;
}
