package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.user.LoginAccountResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by LiKaLi on 1/24/2018.
 */

public interface LoginView extends View {

  void onLoginSuccess(LoginAccountResponse loginResult);

  /**
   * ログイン失敗の場合
   * @param message {@link String} サーバが返すメッセージ
   */
  void onLoginFailed(String message);

  /**
   * その他のエラー
   * @param e {@link Throwable}
   */
  void onLoginError(Throwable e);

}
