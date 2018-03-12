package com.vnpt.vnptline.ui.event;

import com.google.gson.annotations.Expose;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 9/3/2018.
 */

public class DanhSachNhaNghiEvent {
    @Getter @Setter
    @Expose
    private List<HotelResponse> listHotelResponse;

    public DanhSachNhaNghiEvent(List<HotelResponse> listHotelResponse) {
        this.listHotelResponse = listHotelResponse;
    }
}
