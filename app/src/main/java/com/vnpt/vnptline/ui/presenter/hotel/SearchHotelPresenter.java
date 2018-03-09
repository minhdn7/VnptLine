package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.domain.model.pojo.request.hotel.SearchHotelRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.hotel.SearchHotelView;

/**
 * Created by MinhDN on 12/2/2018.
 */

public interface SearchHotelPresenter extends Presenter<SearchHotelView> {
    void searchHotel(String token, SearchHotelRequest request);
}
