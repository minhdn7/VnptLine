package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.SearchHotelRequest;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.ui.view.hotel.SearchHotelView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 12/2/2018.
 */

public class SearchHotelPresenterImpl implements SearchHotelPresenter {
    private CompositeSubscription subscription;
    private SearchHotelView searchHotelView;
    @Inject
    Hotel hotel;

    @Override
    public void searchHotel(String token, SearchHotelRequest request) {
        subscription.add(hotel.searchHotel(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DanhSachNhaNghiResponse>() {
                    @Override
                    public void call(DanhSachNhaNghiResponse response) {
                        if (response.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            searchHotelView.onSearchHotelSuccess(response);
                        } else {
                            searchHotelView.onSearchHotelFailed(response.getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        searchHotelView.onSearchHotelError(e);
                    }
                }));
    }

    @Override
    public void setView(SearchHotelView view) {
        searchHotelView = view;
    }

    @Override
    public void onViewCreate() {
        subscription = new CompositeSubscription();
    }

    @Override
    public void onViewStart() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewStop() {

    }

    @Override
    public void onViewDestroy() {

    }
}
