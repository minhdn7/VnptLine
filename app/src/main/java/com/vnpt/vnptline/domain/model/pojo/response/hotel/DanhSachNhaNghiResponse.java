package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 9/2/2018.
 */

public class DanhSachNhaNghiResponse  {
    @SerializedName("hotelList")
    @Expose
    @Setter @Getter
    private List<HotelResponse> hotelList = null;

    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    private Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    private String responseMessage;
}
