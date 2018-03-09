package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;

import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 2/2/2018.
 */

public interface CheckUserView extends View {
    void onCheckUserSuccess(CommonResponse commonResponse);

    void onCheckUserFailed(String message);

    void onCheckUserError(Throwable e);
}
