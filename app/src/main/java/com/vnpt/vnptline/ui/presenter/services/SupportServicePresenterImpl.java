package com.vnpt.vnptline.ui.presenter.services;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.services.HotelService;
import com.vnpt.vnptline.domain.model.pojo.request.service.SupportServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.services.OrderServicesView;
import com.vnpt.vnptline.ui.view.services.SupportServicesView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 6/3/2018.
 */

public class SupportServicePresenterImpl implements SupportServicePresenter{
    private CompositeSubscription subscription;
    private SupportServicesView supportServicesView;
    @Inject
    HotelService service;
    @Override
    public void setView(SupportServicesView view) {
        supportServicesView = view;
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
    public void supportService(String token, SupportServiceRequest request) {
        subscription.add(service.supportService(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonResponse>>() {
                    @Override
                    public void call(Response<CommonResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            supportServicesView.onSupportServicesSuccess(response.body());
                        } else {
                            supportServicesView.onSupportServicesFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        supportServicesView.onSupportServicesError(e);
                    }
                }));
    }
}
