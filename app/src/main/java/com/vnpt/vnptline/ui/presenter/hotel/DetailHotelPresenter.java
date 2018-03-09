package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.domain.model.pojo.request.hotel.SearchHotelRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.hotel.DetailHotelView;
import com.vnpt.vnptline.ui.view.hotel.SearchHotelView;

/**
 * Created by MinhDN on 23/2/2018.
 */

public interface DetailHotelPresenter extends Presenter<DetailHotelView> {
    void getHotel(String token, long idHotel);
}
