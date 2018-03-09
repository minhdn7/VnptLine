package com.vnpt.vnptline.ui.view.services;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 6/3/2018.
 */

public interface SupportServicesView extends View {
    void onSupportServicesSuccess(CommonResponse response);

    void onSupportServicesFailed(String message);

    void onSupportServicesError(Throwable e);
}
