package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ListRoomBookingResponse;
import com.vnpt.vnptline.ui.view.hotel.RoomHotelView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 26/2/2018.
 */

public class RoomHotelPresenterImpl implements RoomHotelPresenter {
    private CompositeSubscription subscription;
    private RoomHotelView roomHotelView;
    @Inject
    Hotel hotel;

    @Override
    public void setView(RoomHotelView view) {
        roomHotelView = view;
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
    public void getRoom(String token, long idHotel) {
        subscription.add(hotel.layDanhSachPhong(token, idHotel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ListRoomBookingResponse>>() {
                    @Override
                    public void call(Response<ListRoomBookingResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            roomHotelView.onRoomHotelSuccess(response.body());
                        } else {
                            roomHotelView.onRoomHotelFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        roomHotelView.onRoomHotelError(e);
                    }
                }));
    }
}
