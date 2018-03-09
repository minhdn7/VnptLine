package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.DanhSachThongBaoRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.NotificationView;

/**
 * Created by MinhDN on 28/2/2018.
 */

public interface NotificationPresenter extends Presenter<NotificationView>{
    void getNotification(String token, DanhSachThongBaoRequest request);
}
