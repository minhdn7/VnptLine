package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLightResponse;
import com.vnpt.vnptline.ui.view.hotel.HotelHightLightView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 13/3/2018.
 */

public class HotelHighLightPresenterImpl implements HotelHighLightPresenter {
    private CompositeSubscription subscription;
    private HotelHightLightView hotelHightLightView;
    @Inject
    Hotel hotel;

    @Override
    public void setView(HotelHightLightView view) {
        hotelHightLightView = view;
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

    @Override
    public void getHotel(String token) {
        subscription.add(hotel.findHotelAdvance(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<HotelHightLightResponse>>() {
                    @Override
                    public void call(Response<HotelHightLightResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            hotelHightLightView.onHotelHightLightSuccess(response.body());
                        } else {
                            hotelHightLightView.onHotelHightLightFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        hotelHightLightView.onHotelHightLightError(e);
                    }
                }));
    }
}
