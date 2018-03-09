package com.vnpt.vnptline.ui.view.hotel;

import com.vnpt.vnptline.domain.model.pojo.response.hotel.ChiTietNhaNghiResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 23/2/2018.
 */

public interface DetailHotelView extends View {
    void onDetailHotelSuccess(ChiTietNhaNghiResponse response);

    void onDetailHotelFailed(String message);

    void onDetailHotelError(Throwable e);
}
