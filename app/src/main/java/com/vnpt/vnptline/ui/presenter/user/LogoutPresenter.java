package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.DanhSachThongBaoRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.LogoutRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.LogoutView;
import com.vnpt.vnptline.ui.view.user.NotificationView;

/**
 * Created by MinhDN on 14/3/2018.
 */

public interface LogoutPresenter extends Presenter<LogoutView> {
    void logoutPresenter(String token, LogoutRequest logoutRequest);
}
