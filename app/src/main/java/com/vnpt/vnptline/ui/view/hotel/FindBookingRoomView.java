package com.vnpt.vnptline.ui.view.hotel;

import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface FindBookingRoomView extends View {
    void onFindBookingRoomSuccess(FindRoomBookingResponse response);

    void onFindBookingRoomFailed(String message);

    void onFindBookingRoomError(Throwable e);
}
