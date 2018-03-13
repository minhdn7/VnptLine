package com.vnpt.vnptline.domain.model.pojo.request.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 9/2/2018.
 */

public class SearchHotelRequest {
    @SerializedName("city")
    @Expose
    @Setter @Getter
    private String city;

    @SerializedName("distance")
    @Expose
    @Setter @Getter
    private Integer distance;

    @SerializedName("lat")
    @Expose
    @Setter @Getter
    private Double lat;

    @SerializedName("lon")
    @Expose
    @Setter @Getter
    private Double lon;

    @SerializedName("page")
    @Expose
    @Setter @Getter
    private Integer page;

    @SerializedName("priceFrom")
    @Expose
    @Setter @Getter
    private Integer priceFrom;

    @SerializedName("priceTo")
    @Expose
    @Setter @Getter
    private Integer priceTo;

    @SerializedName("rank")
    @Expose
    @Setter @Getter
    private Integer rank;

    @SerializedName("rating")
    @Expose
    @Setter @Getter
    private Integer rating;

    @SerializedName("size")
    @Expose
    @Setter @Getter
    private Integer size;

    @SerializedName("priceType")
    @Expose
    @Setter @Getter
    private String priceType;

}
