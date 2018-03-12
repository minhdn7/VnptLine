package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 26/2/2018.
 */

public class RoomAvailableResponse {
    @SerializedName("roomTypeId")
    @Expose
    @Setter @Getter
    public Integer roomTypeId;

    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    public Integer hotelId;

    @SerializedName("name")
    @Expose
    @Setter @Getter
    public String name;

    @SerializedName("description")
    @Expose
    @Setter @Getter
    public String description;

    @SerializedName("maxPerson")
    @Expose
    @Setter @Getter
    public Integer maxPerson;

    @SerializedName("pictures")
    @Expose
    @Setter @Getter
    public String pictures;

    @SerializedName("amenities")
    @Expose
    @Setter @Getter
    public String amenities;

    @SerializedName("priceDay")
    @Expose
    @Setter @Getter
    public double priceDay;

    @SerializedName("promotionPriceDay")
    @Expose
    @Setter @Getter
    public double promotionPriceDay;

    @SerializedName("discountPriceDay")
    @Expose
    @Setter @Getter
    public double discountPriceDay;

    @SerializedName("priceNight")
    @Expose
    @Setter @Getter
    public double priceNight;

    @SerializedName("promotionPriceNight")
    @Expose
    @Setter @Getter
    public double promotionPriceNight;

    @SerializedName("discountPriceNight")
    @Expose
    @Setter @Getter
    public double discountPriceNight;

    @SerializedName("priceHour")
    @Expose
    @Setter @Getter
    public Integer priceHour;

    @SerializedName("promotionPriceHour")
    @Expose
    @Setter @Getter
    public double promotionPriceHour;

    @SerializedName("discountPriceHour")
    @Expose
    @Setter @Getter
    public double discountPriceHour;

    @SerializedName("available")
    @Expose
    @Setter @Getter
    public Integer available;

    @SerializedName("availableDisplay")
    @Expose
    @Setter @Getter
    public Integer availableDisplay;
}
