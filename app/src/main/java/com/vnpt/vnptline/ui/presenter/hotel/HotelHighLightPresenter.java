package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.hotel.HotelHightLightView;

/**
 * Created by MinhDN on 13/3/2018.
 */

public interface HotelHighLightPresenter extends Presenter<HotelHightLightView> {
    void getHotel(String token);
}
