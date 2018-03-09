package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 26/2/2018.
 */

public class ListRoomBookingResponse {
    @SerializedName("availableRoomTypeList")
    @Getter @Setter
    @Expose
    private List<RoomAvailableResponse> availableRoomTypeList = null;

    @SerializedName("responseCode")
    @Getter @Setter
    @Expose
    private Integer responseCode;

    @SerializedName("responseMessage")
    @Getter @Setter
    @Expose
    private String responseMessage;
}
