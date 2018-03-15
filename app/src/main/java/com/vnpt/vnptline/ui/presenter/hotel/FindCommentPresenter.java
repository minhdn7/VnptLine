package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.domain.model.pojo.request.hotel.FindCommentHotelRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.hotel.FindCommentHotelView;

/**
 * Created by MinhDN on 15/3/2018.
 */

public interface FindCommentPresenter extends Presenter<FindCommentHotelView> {
    void findCommentHotel(String token, FindCommentHotelRequest request);
}
