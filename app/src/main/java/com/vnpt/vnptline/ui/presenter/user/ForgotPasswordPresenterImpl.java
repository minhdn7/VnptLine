package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.user.CheckUserView;
import com.vnpt.vnptline.ui.view.user.ForgotPasswordView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 6/2/2018.
 */

public class ForgotPasswordPresenterImpl implements  ForgotPasswordPresenter {

    private CompositeSubscription subscription;
    private ForgotPasswordView forgotPasswordView;
    @Inject
    User user;

    @Override
    public void setView(ForgotPasswordView view) {
        forgotPasswordView = view;
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
        subscription.unsubscribe();
    }

    @Override
    public void forgotPassword(String token, String username, String tokenFirebase) {
        subscription.add(user.forgotPassword(token, username, tokenFirebase)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonResponse>() {
                    @Override
                    public void call(CommonResponse commonResponse) {
                        if (commonResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            forgotPasswordView.onCheckForgotSuccess(commonResponse);
                        } else {
                            forgotPasswordView.onCheckForgotFailed(commonResponse.getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        forgotPasswordView.onCheckForgotError(e);
                    }
                }));
    }
}
