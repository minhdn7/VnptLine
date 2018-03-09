package com.vnpt.vnptline.domain.model.pojo.request.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 1/3/2018.
 */

public class HistoryRequest {
    @SerializedName("pageIndex")
    @Getter @Setter
    @Expose
    private Integer pageIndex;

    @SerializedName("pageSize")
    @Getter @Setter
    @Expose
    private Integer pageSize;

    @SerializedName("userId")
    @Getter @Setter
    @Expose
    private Integer userId;

    public HistoryRequest(Integer pageIndex, Integer pageSize, Integer userId) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.userId = userId;
    }
}
