package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 15/3/2018.
 */

public class FindCommentRating {
    @SerializedName("rate")
    @Expose
    @Setter @Getter
    public Integer rate;

    @SerializedName("comment")
    @Expose
    @Setter @Getter
    public String comment;

    @SerializedName("name")
    @Expose
    @Setter @Getter
    public String name;

    @SerializedName("ratingDate")
    @Expose
    @Setter @Getter
    public String ratingDate;
}
