package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.OTPRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.OTPCodeView;

/**
 * Created by MinhDN on 5/2/2018.
 */

public interface OTPCodePresenter extends Presenter<OTPCodeView> {
    void getOTPCode(String token, OTPRequest request);
}
