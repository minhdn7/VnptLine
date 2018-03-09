package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.model.pojo.request.user.DanhSachThongBaoRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.DanhSachThongBaoResponse;
import com.vnpt.vnptline.ui.view.user.NotificationView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 28/2/2018.
 */

public class NotificationPresenterImpl implements NotificationPresenter{
    private CompositeSubscription subscription;
    private NotificationView notificationView;
    @Inject
    User user;

    @Override
    public void setView(NotificationView view) {
        notificationView = view;
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
    public void getNotification(String token, DanhSachThongBaoRequest request) {
        subscription.add(user.layDanhSachThongBao(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<DanhSachThongBaoResponse>>() {
                    @Override
                    public void call(Response<DanhSachThongBaoResponse> response) {
                        response.raw().request().url();
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            notificationView.onNotificationSuccess(response.body());
                        } else {
                            notificationView.onNotificationFailed(response.body().getResponseMessage());
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        notificationView.onNotificationError(e);
                    }
                }));
    }
}
