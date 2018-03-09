package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.ChangePasswordRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.ChangePasswordView;

/**
 * Created by MinhDN on 8/2/2018.
 */

public interface ChangePasswordPresenter extends Presenter<ChangePasswordView> {
    void changePass(String token, ChangePasswordRequest request);
}