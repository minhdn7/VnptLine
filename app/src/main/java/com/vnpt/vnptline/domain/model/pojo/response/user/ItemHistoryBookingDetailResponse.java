package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class ItemHistoryBookingDetailResponse {
    @SerializedName("amount")
    @Expose
    @Getter @Setter
    public String amount;

    @SerializedName("bookingDate")
    @Expose
    @Getter @Setter
    public String bookingDate;

    @SerializedName("bookingId")
    @Expose
    @Getter @Setter
    public Integer bookingId;

    @SerializedName("comment")
    @Expose
    @Getter @Setter
    public String comment;

    @SerializedName("hotelName")
    @Expose
    @Getter @Setter
    public String hotelName;

    @SerializedName("invoiceDate")
    @Expose
    @Getter @Setter
    public String invoiceDate;

    @SerializedName("invoiceNo")
    @Expose
    @Getter @Setter
    public String invoiceNo;

    @SerializedName("paymentType")
    @Expose
    @Getter @Setter
    public String paymentType;

    @SerializedName("pictures")
    @Setter @Getter
    @Expose
    public String pictures;

    @SerializedName("price")
    @Expose
    @Getter @Setter
    public Integer price;

    @SerializedName("qrNumber")
    @Expose
    @Getter @Setter
    public String qrNumber;

    @SerializedName("rate")
    @Expose
    @Getter @Setter
    public Integer rate;

    @SerializedName("ratingDate")
    @Expose
    @Getter @Setter
    public String ratingDate;

    @SerializedName("roomType")
    @Expose
    @Getter @Setter
    public String roomType;

    @SerializedName("status")
    @Expose
    @Getter @Setter
    public String status;

    @SerializedName("userId")
    @Expose
    @Getter @Setter
    public Integer userId;
}
