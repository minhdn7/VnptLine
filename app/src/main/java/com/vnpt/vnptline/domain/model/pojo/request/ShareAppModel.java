package com.vnpt.vnptline.domain.model.pojo.request;

import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 9/2/2018.
 */

public class ShareAppModel {
    @SerializedName("resolveInfo")
    @Setter @Getter
    private ResolveInfo resolveInfo;

    @SerializedName("icon")
    @Setter @Getter
    private Drawable icon;

    public ShareAppModel(ResolveInfo resolveInfo, Drawable icon) {
        this.resolveInfo = resolveInfo;
        this.icon = icon;
    }
}
