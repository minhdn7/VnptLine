package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 1/3/2018.
 */

public class DetailHistoryRequest {
    @SerializedName("bookingId")
    @Getter @Setter
    @Expose
    private Integer bookingId;

    @SerializedName("userId")
    @Getter @Setter
    @Expose
    private Integer userId;

    public DetailHistoryRequest(Integer bookingId, Integer userId) {
        this.bookingId = bookingId;
        this.userId = userId;
    }
}
