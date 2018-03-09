package com.vnpt.vnptline.domain.model.pojo.response.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class FindRoomBookingResponse {
    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    public String responseMessage;

    @SerializedName("rooms")
    @Expose
    @Setter @Getter
    public List<RoomBookingDetail> rooms = null;
}
