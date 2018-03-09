package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.CheckUserView;

/**
 * Created by MinhDN on 2/2/2018.
 */

public interface CheckUserPresenter extends Presenter<CheckUserView> {
    void checkUser(String token, String username);
}
