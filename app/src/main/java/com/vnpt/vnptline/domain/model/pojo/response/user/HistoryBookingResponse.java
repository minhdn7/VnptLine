package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by MinhDN on 1/3/2018.
 */

public class HistoryBookingResponse {
    @SerializedName("bookingId")
    @Expose
    @Getter @Setter
    @NonNull
    public Integer bookingId;

    @SerializedName("bookingDate")
    @Expose
    @Getter @Setter
    @NonNull
    public String bookingDate;

    @SerializedName("hotelName")
    @Expose
    @Getter @Setter
    @NonNull
    public String hotelName;

    @SerializedName("hotelAddress")
    @Expose
    @Getter @Setter
    @NonNull
    public String hotelAddress;

    @SerializedName("status")
    @Getter @Setter
    @Expose
    @NonNull
    public String status;
}
