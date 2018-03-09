package com.vnpt.vnptline.domain.model.pojo.response.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class RoomBookingDetail {
    @SerializedName("bookingDetailId")
    @Expose
    @Getter @Setter
    public Integer bookingDetailId;

    @SerializedName("checkInTime")
    @Expose
    @Getter @Setter
    public String checkInTime;

    @SerializedName("hotelId")
    @Expose
    @Getter @Setter
    public Integer hotelId;

    @SerializedName("hotelName")
    @Expose
    @Getter @Setter
    public String hotelName;

    @SerializedName("roomNumber")
    @Expose
    @Getter @Setter
    public String roomNumber;

    @SerializedName("roomTypeId")
    @Expose
    @Getter @Setter
    public Integer roomTypeId;

    @SerializedName("roomTypeName")
    @Expose
    @Getter @Setter
    public String roomTypeName;
}
