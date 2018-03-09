package com.vnpt.vnptline.ui.view.hotel;


import com.vnpt.vnptline.domain.model.pojo.response.hotel.BookingRoomResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 27/2/2018.
 */

public interface BookingRoomView extends View {
    void onBookingRoomSuccess(BookingRoomResponse response);

    void onBookingRoomFailed(String message);

    void onBookingRoomError(Throwable e);
}
