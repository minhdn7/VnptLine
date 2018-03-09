package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 23/2/2018.
 */

public class ChiTietNhaNghiResponse {
    @SerializedName("hotelId")
    @Expose
    @Setter @Getter
    public Integer hotelId;

    @SerializedName("hotelType")
    @Expose
    @Setter @Getter
    public String hotelType;

    @SerializedName("hotelName")
    @Expose
    @Setter @Getter
    public String hotelName;

    @SerializedName("customerId")
    @Expose
    @Setter @Getter
    public Integer customerId;

    @SerializedName("status")
    @Expose
    @Setter @Getter
    public String status;

    @SerializedName("city")
    @Expose
    @Setter @Getter
    public String city;

    @SerializedName("rank")
    @Expose
    @Setter @Getter
    public Integer rank;

    @SerializedName("rating")
    @Expose
    @Setter @Getter
    public Integer rating;

    @SerializedName("description")
    @Expose
    @Setter @Getter
    public String description;

    @SerializedName("pictures")
    @Expose
    @Setter @Getter
    public String pictures;

    @SerializedName("createDate")
    @Expose
    @Setter @Getter
    public String createDate;

    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Setter @Getter
    @Expose
    public String responseMessage;
}
