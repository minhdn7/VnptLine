package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;
import com.vnpt.vnptline.ui.view.hotel.FindBookingRoomView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 5/3/2018.
 */

public class FindRoomBookingPresenterImpl implements FindRoomBookingPresenter{
    private CompositeSubscription subscription;
    private FindBookingRoomView findBookingRoomView;
    @Inject
    Hotel hotel;

    @Override
    public void setView(FindBookingRoomView view) {
        findBookingRoomView = view;
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
    public void getRoom(String token, long userId) {
        subscription.add(hotel.findBookingRoom(token, userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<FindRoomBookingResponse>>() {
                    @Override
                    public void call(Response<FindRoomBookingResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            findBookingRoomView.onFindBookingRoomSuccess(response.body());
                        } else {
                            findBookingRoomView.onFindBookingRoomFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        findBookingRoomView.onFindBookingRoomError(e);
                    }
                }));
    }
}
