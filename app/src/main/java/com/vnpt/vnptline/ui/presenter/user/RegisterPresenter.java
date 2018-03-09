package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.RegisterUserRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.VerifyOTPRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.RegisterUserView;
import com.vnpt.vnptline.ui.view.user.VerifyOTPView;

/**
 * Created by MinhDN on 5/2/2018.
 */

public interface RegisterPresenter extends Presenter<RegisterUserView> {
    void registerUser(String token, RegisterUserRequest request);
}
