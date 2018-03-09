package com.vnpt.vnptline.ui.view.services;

import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindServicesResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface OrderServicesView extends View {
    void onOrderServicesSuccess(CommonResponse response);

    void onOrderServicesFailed(String message);

    void onOrderServicesError(Throwable e);
}
