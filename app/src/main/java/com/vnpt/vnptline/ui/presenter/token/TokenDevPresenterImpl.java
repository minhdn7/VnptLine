package com.vnpt.vnptline.ui.presenter.token;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.token.TokenDev;
import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;
import com.vnpt.vnptline.ui.view.token.TokenDevView;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 2/2/2018.
 */

public class TokenDevPresenterImpl implements TokenDevPresenter {

    private CompositeSubscription subscription;
    private TokenDevView tokenView;
    @Inject
    TokenDev tokenDev;

    @Override
    public void setView(TokenDevView view) {
        tokenView = view;
    }

    @Override
    public void onViewCreate() {
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
    public void getTokenDev(String key) {
        subscription.add(tokenDev.getTokenDev(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TokenDevResponse>() {
                    @Override
                    public void call(TokenDevResponse tokenDevResponse) {
                        if (tokenDevResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            tokenView.onTokenDevSuccess(tokenDevResponse);
                        } else {
                            tokenView.onTokenDevFailed(tokenDevResponse.getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        tokenView.onTokenDevError(e);
                    }
                }));
    }
}
