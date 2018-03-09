package com.vnpt.vnptline.ui.presenter;

import com.vnpt.vnptline.domain.model.pojo.request.user.LoginAccountRequest;
import com.vnpt.vnptline.ui.view.user.LoginView;

/**
 * Created by LiKaLi on 1/24/2018.
 */

public interface LoginPresenter extends Presenter<LoginView>{
  void login(String token, LoginAccountRequest request);
}
