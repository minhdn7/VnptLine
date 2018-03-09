package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.ChangePasswordRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.user.ChangePasswordView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 8/2/2018.
 */

public class ChangePasswordPresenterImpl implements  ChangePasswordPresenter{
    private CompositeSubscription subscription;
    private ChangePasswordView changePasswordView;
    @Inject
    User user;

    @Override
    public void setView(ChangePasswordView view) {
        changePasswordView = view;
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
    public void changePass(String token, ChangePasswordRequest request) {
        subscription.add(user.changePassword(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonResponse>() {
                    @Override
                    public void call(CommonResponse response) {
                        if (response.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            changePasswordView.onChangePasswordSuccess(response);
                        } else {
                            changePasswordView.onChangePasswordFailed(response.getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        changePasswordView.onChangePasswordError(e);
                    }
                }));
    }
}
