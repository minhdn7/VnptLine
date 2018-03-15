package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.FindCommentHotelRequest;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.FindCommentHotelResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;
import com.vnpt.vnptline.ui.view.hotel.FindBookingRoomView;
import com.vnpt.vnptline.ui.view.hotel.FindCommentHotelView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 15/3/2018.
 */

public class FindCommentPresenterImpl  implements FindCommentPresenter{
    private CompositeSubscription subscription;
    private FindCommentHotelView findCommentHotelView;
    @Inject
    Hotel hotel;

    @Override
    public void setView(FindCommentHotelView view) {
        findCommentHotelView = view;
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
    public void findCommentHotel(String token, FindCommentHotelRequest request) {
        subscription.add(hotel.findCommentHotel(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<FindCommentHotelResponse>>() {
                    @Override
                    public void call(Response<FindCommentHotelResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            findCommentHotelView.onFindCommentHotelSuccess(response.body());
                        } else {
                            findCommentHotelView.onFindCommentHotelFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        findCommentHotelView.onFindCommentHotelError(e);
                    }
                }));
    }
}
