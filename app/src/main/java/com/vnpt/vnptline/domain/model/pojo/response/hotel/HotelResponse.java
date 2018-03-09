package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 9/2/2018.
 */

public class HotelResponse implements Serializable {
    @SerializedName("address")
    @Expose
    @Getter @Setter
    public String address;

    @SerializedName("city")
    @Expose
    @Getter @Setter
    public String city;

    @SerializedName("description")
    @Expose
    @Getter @Setter
    public String description;

    @SerializedName("discountPriceDay")
    @Expose
    @Getter @Setter
    public Integer discountPriceDay;

    @SerializedName("discountPriceHour")
    @Expose
    @Getter @Setter
    public Integer discountPriceHour;

    @SerializedName("discountPriceNight")
    @Expose
    @Getter @Setter
    public Integer discountPriceNight;

    @SerializedName("distance")
    @Expose
    @Getter @Setter
    public double distance;

    @SerializedName("district")
    @Expose
    @Getter @Setter
    public String district;

    @SerializedName("hotelId")
    @Expose
    @Getter @Setter
    public Integer hotelId;

    @SerializedName("hotelName")
    @Expose
    @Getter @Setter
    public String hotelName;

    @SerializedName("hotelType")
    @Expose
    @Getter @Setter
    public String hotelType;

    @SerializedName("lat")
    @Expose
    @Getter @Setter
    public double lat;

    @SerializedName("lon")
    @Expose
    @Getter @Setter
    public double lon;

    @SerializedName("maxPerson")
    @Expose
    @Getter @Setter
    public Integer maxPerson;

    @SerializedName("pictures")
    @Expose
    @Getter @Setter
    public String pictures;

    @SerializedName("priceDay")
    @Expose
    @Getter @Setter
    public Integer priceDay;

    @SerializedName("priceHour")
    @Expose
    @Getter @Setter
    public Integer priceHour;

    @SerializedName("priceNight")
    @Getter @Setter
    public Integer priceNight;

    @SerializedName("prioritySearch")
    @Expose
    @Setter @Getter
    public String prioritySearch;

    @SerializedName("promotionPriceDay")
    @Expose
    @Getter @Setter
    public double promotionPriceDay;

    @SerializedName("promotionPriceHour")
    @Expose
    @Getter @Setter
    public double promotionPriceHour;

    @SerializedName("promotionPriceNight")
    @Expose
    @Getter @Setter
    public double promotionPriceNight;

    @SerializedName("rank")
    @Expose
    public Integer rank;

    @SerializedName("rating")
    @Expose
    public Integer rating;
}
