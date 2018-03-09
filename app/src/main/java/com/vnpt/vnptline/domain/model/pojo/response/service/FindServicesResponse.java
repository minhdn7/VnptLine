package com.vnpt.vnptline.domain.model.pojo.response.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class FindServicesResponse {
    @SerializedName("services")
    @Expose
    @Getter @Setter
    public List<ServiceDetailResponse> services = null;

    @SerializedName("responseCode")
    @Expose
    @Getter @Setter
    public Integer responseCode;

    @SerializedName("responseMessage")
    @Expose
    @Getter @Setter
    public String responseMessage;
}
