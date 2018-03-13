package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 13/3/2018.
 */

public interface LogoutView extends View {

    void onLogoutSuccess(CommonResponse response);


    void onLoginFailed(String message);

    void onLoginError(Throwable e);

}
