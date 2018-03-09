package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.domain.model.pojo.request.hotel.BookingRoomRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.hotel.BookingRoomView;
import com.vnpt.vnptline.ui.view.hotel.DetailHotelView;

/**
 * Created by MinhDN on 27/2/2018.
 */

public interface BookingRoomPresenter extends Presenter<BookingRoomView> {
    void bookingHotel(String token, BookingRoomRequest request);
}
