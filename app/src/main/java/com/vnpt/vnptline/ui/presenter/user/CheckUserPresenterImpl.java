package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;

import com.vnpt.vnptline.ui.view.user.CheckUserView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 2/2/2018.
 */

public class CheckUserPresenterImpl implements  CheckUserPresenter{

    private CompositeSubscription subscription;
    private CheckUserView checkUserView;
    @Inject
    User user;

    @Override
    public void setView(CheckUserView view) {
        checkUserView = view;
    }


    @Override public void onViewCreate() {
        subscription = new CompositeSubscription();
    }

    @Override public void onViewStart() {

    }

    @Override public void onViewResume() {

    }

    @Override public void onViewPause() {

    }

    @Override public void onViewStop() {

    }

    @Override public void onViewDestroy() {
        subscription.unsubscribe();
    }

    @Override
    public void checkUser(String token, String username) {
        subscription.add(user.checkUser(token, username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonResponse>() {
                    @Override
                    public void call(CommonResponse commonResponse) {
                        checkUserView.onCheckUserSuccess(commonResponse);
//                        if (commonResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
//                            checkUserView.onCheckUserSuccess(commonResponse);
//                        } else {
//                            checkUserView.onCheckUserFailed(commonResponse.getResponseMessage());
//                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        checkUserView.onCheckUserError(e);
                    }
                }));
    }
}
