package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 26/1/2018.
 */

public class HotelHightLight {
    @SerializedName("hotelId")
    @Expose
    @Getter @Setter
    private Integer hotelId;

    @SerializedName("name")
    @Expose
    @Getter @Setter
    private String nameHotel;

    @SerializedName("picture")
    @Expose
    @Getter @Setter
    private String picture;

}
