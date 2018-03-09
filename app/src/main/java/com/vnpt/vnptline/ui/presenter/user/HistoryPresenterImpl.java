package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.HistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.DanhSachThongBaoResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryResponse;
import com.vnpt.vnptline.ui.view.user.HistoryView;
import com.vnpt.vnptline.ui.view.user.NotificationView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 1/3/2018.
 */

public class HistoryPresenterImpl implements HistoryPresenter {
    private CompositeSubscription subscription;
    private HistoryView historyView;
    @Inject
    User user;

    @Override
    public void setView(HistoryView view) {
        historyView = view;
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
    public void getHistory(String token, HistoryRequest request) {
        subscription.add(user.layLichSu(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<HistoryResponse>>() {
                    @Override
                    public void call(Response<HistoryResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            historyView.onHistorySuccess(response.body());
                        } else {
                            historyView.onHistoryFailed(response.body().getResponseMessage());
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        historyView.onHistoryError(e);
                    }
                }));
    }
}
