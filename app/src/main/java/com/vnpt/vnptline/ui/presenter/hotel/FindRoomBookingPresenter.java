package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.hotel.FindBookingRoomView;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface FindRoomBookingPresenter extends Presenter<FindBookingRoomView>{
    void getRoom(String token, long userId);
}
