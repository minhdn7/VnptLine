package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vnpt.vnptline.domain.model.pojo.response.user.ThongBaoResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 28/2/2018.
 */

public class DanhSachThongBaoResponse {
    @SerializedName("notifications")
    @Expose
    @Setter @Getter
    private List<ThongBaoResponse> notifications = null;

    @SerializedName("responseCode")
    @Expose
    @Setter @Getter
    private Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Setter @Getter
    private String responseMessage;
}
