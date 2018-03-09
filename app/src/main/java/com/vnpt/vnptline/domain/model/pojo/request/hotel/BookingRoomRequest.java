package com.vnpt.vnptline.domain.model.pojo.request.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 27/2/2018.
 */

public class BookingRoomRequest {
    @SerializedName("amount")
    @Expose
    @Setter @Getter
    private Integer amount;

    @SerializedName("bookingDate")
    @Expose
    @Setter @Getter
    private String bookingDate;

    @SerializedName("customerName")
    @Expose
    @Setter @Getter
    private String customerName;

    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    private Integer hotelId;

    @SerializedName("roomTypeId")
    @Expose
    @Setter @Getter
    private Integer roomTypeId;

    @SerializedName("userId")
    @Expose
    @Setter @Getter
    private Integer userId;
}
