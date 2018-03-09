package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 8/2/2018.
 */

public interface ChangePasswordView extends View {
    void onChangePasswordSuccess(CommonResponse commonResponse);

    void onChangePasswordFailed(String message);

    void onChangePasswordError(Throwable e);
}
