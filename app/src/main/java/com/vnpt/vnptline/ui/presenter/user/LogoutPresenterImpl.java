package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.LogoutRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryResponse;
import com.vnpt.vnptline.ui.view.user.ChangePasswordView;
import com.vnpt.vnptline.ui.view.user.LogoutView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 14/3/2018.
 */

public class LogoutPresenterImpl implements LogoutPresenter {
    private CompositeSubscription subscription;
    private LogoutView logoutView;
    @Inject
    User user;
    @Override
    public void setView(LogoutView view) {
        logoutView = view;
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
    public void logoutPresenter(String token, LogoutRequest logoutRequest) {
        subscription.add(user.logoutUser(token, logoutRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonResponse>>() {
                    @Override
                    public void call(Response<CommonResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            logoutView.onLogoutSuccess(response.body());
                        } else {
                            logoutView.onLogoutFailed(response.body().getResponseMessage());
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        logoutView.onLogoutError(e);
                    }
                }));
    }
}
