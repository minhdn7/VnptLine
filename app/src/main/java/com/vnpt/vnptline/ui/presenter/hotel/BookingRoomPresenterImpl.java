package com.vnpt.vnptline.ui.presenter.hotel;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.BookingRoomRequest;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.BookingRoomResponse;
import com.vnpt.vnptline.ui.view.hotel.BookingRoomView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 27/2/2018.
 */

public class BookingRoomPresenterImpl implements BookingRoomPresenter {
    private CompositeSubscription subscription;
    private BookingRoomView bookingRoomView;
    @Inject
    Hotel hotel;


    @Override
    public void setView(BookingRoomView view) {
        bookingRoomView = view;
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
    public void bookingHotel(String token, BookingRoomRequest request) {
        subscription.add(hotel.bookingRoom(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<BookingRoomResponse>>() {
                    @Override
                    public void call(Response<BookingRoomResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            bookingRoomView.onBookingRoomSuccess(response.body());
                        } else {
                            bookingRoomView.onBookingRoomFailed(response.body().getResponseMessage());
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        bookingRoomView.onBookingRoomError(e);
                    }
                }));
    }
}
