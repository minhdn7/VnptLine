package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class DetailHistoryResponse {
    @SerializedName("itemHistoryBookingDetailResponse")
    @Expose
    @Getter @Setter
    public ItemHistoryBookingDetailResponse itemHistoryBookingDetailResponse;

    @SerializedName("responseCode")
    @Expose
    @Getter @Setter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Getter @Setter
    public String responseMessage;

}
