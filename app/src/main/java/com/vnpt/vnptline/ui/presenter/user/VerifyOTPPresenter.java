package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.VerifyOTPRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.OTPCodeView;
import com.vnpt.vnptline.ui.view.user.VerifyOTPView;

/**
 * Created by MinhDN on 5/2/2018.
 */

public interface VerifyOTPPresenter  extends Presenter<VerifyOTPView> {
    void verifyOTPCode(String token, VerifyOTPRequest request);
}
