package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 1/3/2018.
 */

public class QRCodeDataResponse {
    @SerializedName("type")
    @Expose
    @Getter @Setter
    private String type;

    @SerializedName("qrNumber")
    @Expose
    @Getter @Setter
    private String qrNumber;

    @SerializedName("qrCode")
    @Expose
    @Getter @Setter
    private String qrCode;

    @SerializedName("bookingDate")
    @Expose
    @Getter @Setter
    private String bookingDate;

    @SerializedName("hotelName")
    @Expose
    @Getter @Setter
    private String hotelName;

    @SerializedName("roomTypeName")
    @Expose
    @Getter @Setter
    private String roomTypeName;
}
