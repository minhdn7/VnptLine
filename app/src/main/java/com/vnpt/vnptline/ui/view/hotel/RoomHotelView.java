package com.vnpt.vnptline.ui.view.hotel;



import com.vnpt.vnptline.domain.model.pojo.response.hotel.ListRoomBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.RoomAvailableResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 23/2/2018.
 */

public interface RoomHotelView extends View {
    void onRoomHotelSuccess(ListRoomBookingResponse response);

    void onRoomHotelFailed(String message);

    void onRoomHotelError(Throwable e);
}
