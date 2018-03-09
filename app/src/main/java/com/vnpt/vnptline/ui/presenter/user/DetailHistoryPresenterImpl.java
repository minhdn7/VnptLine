package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.DetailHistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.HistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.DetailHistoryResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryResponse;
import com.vnpt.vnptline.ui.view.user.DetailHistoryView;
import com.vnpt.vnptline.ui.view.user.HistoryView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 2/3/2018.
 */

public class DetailHistoryPresenterImpl implements DetailHistoryPresenter {
    private CompositeSubscription subscription;
    private DetailHistoryView detailHistoryView;
    @Inject
    User user;


    @Override
    public void setView(DetailHistoryView view) {
        detailHistoryView = view;
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
    public void getDetailHistory(String token, DetailHistoryRequest request) {
        subscription.add(user.getDetailHistory(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<DetailHistoryResponse>>() {
                    @Override
                    public void call(Response<DetailHistoryResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            detailHistoryView.onDetailHistorySuccess(response.body());
                        } else {
                            detailHistoryView.onDetailHistoryFailed(response.body().getResponseMessage());
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        detailHistoryView.onDetailHistoryError(e);
                    }
                }));
    }

}

