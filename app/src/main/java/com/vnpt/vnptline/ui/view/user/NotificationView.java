package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.DanhSachThongBaoResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 28/2/2018.
 */

public interface NotificationView extends View {
    void onNotificationSuccess(DanhSachThongBaoResponse response);

    void onNotificationFailed(String message);

    void onNotificationError(Throwable e);
}
