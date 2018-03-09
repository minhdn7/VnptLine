package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 5/2/2018.
 */

public interface OTPCodeView extends View {
    void onOTPCodeSuccess(CommonResponse commonResponse);

    void onOTPCodeFailed(String message);

    void onOTPCoderError(Throwable e);
}
