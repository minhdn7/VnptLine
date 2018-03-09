package com.vnpt.vnptline.domain.model.pojo.response.hotel;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 8/3/2018.
 */

public class RoomHightLight {
    @SerializedName("url")
    @Expose
    @Getter @Setter
    public String url;

    @SerializedName("imageResoune")
    @Expose
    @Getter @Setter
    public int imageResoune;

    public RoomHightLight(String url) {
        this.url = url;
    }

    public RoomHightLight(int imageResoune) {
        this.imageResoune = imageResoune;
    }
}
