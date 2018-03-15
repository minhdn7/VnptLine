package com.vnpt.vnptline.ui.view.hotel;

import com.vnpt.vnptline.domain.model.pojo.response.hotel.FindCommentHotelResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLightResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 15/3/2018.
 */

public interface FindCommentHotelView extends View {
    void onFindCommentHotelSuccess(FindCommentHotelResponse response);

    void onFindCommentHotelFailed(String message);

    void onFindCommentHotelError(Throwable e);
}
