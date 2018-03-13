package com.vnpt.vnptline.ui.view.hotel;

import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLight;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLightResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 13/3/2018.
 */

public interface HotelHightLightView extends View {
    void onHotelHightLightSuccess(HotelHightLightResponse response);

    void onHotelHightLightFailed(String message);

    void onHotelHightLightError(Throwable e);
}
