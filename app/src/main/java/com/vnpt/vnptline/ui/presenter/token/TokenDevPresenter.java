package com.vnpt.vnptline.ui.presenter.token;

import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.token.TokenDevView;

/**
 * Created by MinhDN on 2/2/2018.
 */

public interface TokenDevPresenter extends Presenter<TokenDevView> {
    void getTokenDev(String key);
}
