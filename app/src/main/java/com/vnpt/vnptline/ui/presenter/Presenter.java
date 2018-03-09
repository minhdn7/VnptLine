package com.vnpt.vnptline.ui.presenter;

import com.vnpt.vnptline.ui.view.View;

/**
 * Created by LiKaLi on 1/24/2018.
 */

public interface Presenter<T extends View> {

  void setView(T view);

  void onViewCreate();

  void onViewStart();

  void onViewResume();

  void onViewPause();

  void onViewStop();

  void onViewDestroy();

}
