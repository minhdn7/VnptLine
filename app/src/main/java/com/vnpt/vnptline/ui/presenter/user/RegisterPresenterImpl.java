package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.RegisterUserRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.RegisterUserResponse;
import com.vnpt.vnptline.ui.view.user.RegisterUserView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 5/2/2018.
 */

public class RegisterPresenterImpl implements  RegisterPresenter{
    private CompositeSubscription subscription;
    private RegisterUserView registerUserView;
    @Inject
    User user;

    @Override
    public void setView(RegisterUserView view) {
        this.registerUserView = view;
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
    public void registerUser(String token, RegisterUserRequest request) {
        subscription.add(user.registerUser(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RegisterUserResponse>() {
                    @Override
                    public void call(RegisterUserResponse response) {
                        if (response.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            registerUserView.onRegisterUserSuccess(response);
                        } else {
                            registerUserView.onRegisterUserFailed(response.getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        registerUserView.onRegisterUserError(e);
                    }
                }));
    }
}
