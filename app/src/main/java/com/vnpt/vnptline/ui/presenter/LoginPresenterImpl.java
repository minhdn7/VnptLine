package com.vnpt.vnptline.ui.presenter;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.LoginAccountRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.LoginAccountResponse;
import com.vnpt.vnptline.ui.view.user.LoginView;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 1/24/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {

  private CompositeSubscription subscription;
  private LoginView loginView;
  @Inject
  User user;

  @Override public void setView(LoginView view) {
    loginView = view;
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

  @Override public void login(String token, LoginAccountRequest request) {
    subscription.add(user.execute(token, request)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<LoginAccountResponse>() {
          @Override
          public void call(LoginAccountResponse loginResult) {
            if (loginResult.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
              loginView.onLoginSuccess(loginResult);
            } else {
              loginView.onLoginFailed(loginResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable e) {
            loginView.onLoginError(e);
          }
        }));
  }
}
