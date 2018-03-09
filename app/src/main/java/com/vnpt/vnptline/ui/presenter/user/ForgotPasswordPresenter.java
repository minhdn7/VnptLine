package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.ForgotPasswordView;

/**
 * Created by MinhDN on 6/2/2018.
 */

public interface ForgotPasswordPresenter extends Presenter<ForgotPasswordView> {
    void forgotPassword(String token, String username, String tokenFirebase);
}
