package com.vnpt.vnptline.ui.view.services;

import com.vnpt.vnptline.domain.model.pojo.response.service.FindServicesResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface FindServicesView extends View {
    void onFindServiceSuccess(FindServicesResponse response);

    void onFindServiceFailed(String message);

    void onFindServiceError(Throwable e);
}
