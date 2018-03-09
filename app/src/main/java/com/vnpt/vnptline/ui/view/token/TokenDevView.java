package com.vnpt.vnptline.ui.view.token;

import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 1/2/2018.
 */

public interface TokenDevView extends View {
    void onTokenDevSuccess(TokenDevResponse tokenDevResponse);

    void onTokenDevFailed(String message);

    void onTokenDevError(Throwable e);
}
