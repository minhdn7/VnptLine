package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.OTPRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.user.OTPCodeView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 5/2/2018.
 */

public class OTPCodePresenterImpl implements  OTPCodePresenter{
    private CompositeSubscription subscription;
    private OTPCodeView otpCodeView;
    @Inject
    User user;

    @Override
    public void setView(OTPCodeView view) {
        otpCodeView = view;
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
    public void getOTPCode(String token, OTPRequest request) {
        subscription.add(user.getOTP(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonResponse>() {
                    @Override
                    public void call(CommonResponse commonResponse) {
                        if (commonResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            otpCodeView.onOTPCodeSuccess(commonResponse);
                        } else {
                            otpCodeView.onOTPCodeFailed(commonResponse.getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        otpCodeView.onOTPCoderError(e);
                    }
                }));
    }
}
