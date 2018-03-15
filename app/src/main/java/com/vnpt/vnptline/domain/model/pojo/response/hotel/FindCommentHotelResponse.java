package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 15/3/2018.
 */

public class FindCommentHotelResponse {
    @SerializedName("ratings")
    @Expose
    @Setter @Getter
    public List<FindCommentRating> ratings = null;

    @SerializedName("rate")
    @Expose
    @Setter @Getter
    public Integer rate;

    @SerializedName("total")
    @Expose
    @Setter @Getter
    public Integer total;

    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    public String responseMessage;
}
