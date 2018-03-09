package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.hotel.RoomHotelView;

/**
 * Created by MinhDN on 26/2/2018.
 */

public interface RoomHotelPresenter extends Presenter<RoomHotelView>{
    void getRoom(String token, long idHotel);
}
