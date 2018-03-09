package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ChiTietNhaNghiResponse;
import com.vnpt.vnptline.ui.view.hotel.DetailHotelView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 23/2/2018.
 */

public class DetailHotelPresenterImpl implements DetailHotelPresenter {
    private CompositeSubscription subscription;
    private DetailHotelView detailHotelView;
    @Inject
    Hotel hotel;

    @Override
    public void getHotel(String token, long idHotel) {
        subscription.add(hotel.detailHotel(token, idHotel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ChiTietNhaNghiResponse>() {
                    @Override
                    public void call(ChiTietNhaNghiResponse response) {
                        if (response.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            detailHotelView.onDetailHotelSuccess(response);
                        } else {
                            detailHotelView.onDetailHotelFailed(response.getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        detailHotelView.onDetailHotelError(e);
                    }
                }));
    }

    @Override
    public void setView(DetailHotelView view) {
        detailHotelView = view;
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

