package com.vnpt.vnptline.ui.presenter.services;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.services.HotelService;
import com.vnpt.vnptline.domain.model.pojo.request.service.OrderServicRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.services.OrderServicesView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 5/3/2018.
 */

public class OrderServicePresenterImpl implements OrderServicePresenter{
    private CompositeSubscription subscription;
    private OrderServicesView orderServicesView;
    @Inject
    HotelService service;
    @Override
    public void setView(OrderServicesView view) {
        orderServicesView = view;
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
    public void orderService(String token, OrderServicRequest request) {
        subscription.add(service.orderService(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonResponse>>() {
                    @Override
                    public void call(Response<CommonResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            orderServicesView.onOrderServicesSuccess(response.body());
                        } else {
                            orderServicesView.onOrderServicesFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        orderServicesView.onOrderServicesError(e);
                    }
                }));
    }
}
