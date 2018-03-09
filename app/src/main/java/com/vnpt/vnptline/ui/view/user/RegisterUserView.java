package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.user.RegisterUserResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 5/2/2018.
 */

public interface RegisterUserView extends View {
    void onRegisterUserSuccess(RegisterUserResponse commonResponse);

    void onRegisterUserFailed(String message);

    void onRegisterUserError(Throwable e);
}
