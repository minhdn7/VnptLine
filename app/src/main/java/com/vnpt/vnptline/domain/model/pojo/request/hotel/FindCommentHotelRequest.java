package com.vnpt.vnptline.domain.model.pojo.request.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 15/3/2018.
 */

public class FindCommentHotelRequest {
    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    private Integer hotelId;

    @SerializedName("pageIndex")
    @Expose
    @Setter @Getter
    private Integer pageIndex;

    @SerializedName("pageSize")
    @Expose
    @Setter @Getter
    private Integer pageSize;

    public FindCommentHotelRequest(Integer hotelId, Integer pageIndex, Integer pageSize) {
        this.hotelId = hotelId;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
