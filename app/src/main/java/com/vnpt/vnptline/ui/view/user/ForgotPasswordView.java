package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 6/2/2018.
 */

public interface ForgotPasswordView extends View {
    void onCheckForgotSuccess(CommonResponse commonResponse);

    void onCheckForgotFailed(String message);

    void onCheckForgotError(Throwable e);
}
