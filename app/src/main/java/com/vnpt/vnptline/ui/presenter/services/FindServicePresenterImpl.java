package com.vnpt.vnptline.ui.presenter.services;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.interactor.services.HotelService;
import com.vnpt.vnptline.domain.model.pojo.request.service.FindServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindServicesResponse;
import com.vnpt.vnptline.ui.presenter.hotel.FindRoomBookingPresenter;
import com.vnpt.vnptline.ui.view.hotel.FindBookingRoomView;
import com.vnpt.vnptline.ui.view.services.FindServicesView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 5/3/2018.
 */

public class FindServicePresenterImpl implements FindServicePresenter {
    private CompositeSubscription subscription;
    private FindServicesView findServicesView;
    @Inject
    HotelService service;

    @Override
    public void setView(FindServicesView view) {
        findServicesView = view;
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
    public void findService(String token, final FindServiceRequest request) {
        subscription.add(service.findService(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<FindServicesResponse>>() {
                    @Override
                    public void call(Response<FindServicesResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            findServicesView.onFindServiceSuccess(response.body());
                        } else {
                            findServicesView.onFindServiceFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        findServicesView.onFindServiceError(e);
                    }
                }));
    }
}
