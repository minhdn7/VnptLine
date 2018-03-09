package com.vnpt.vnptline.ui.presenter.services;

import com.vnpt.vnptline.domain.model.pojo.request.service.OrderServicRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.SupportServiceRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.services.OrderServicesView;
import com.vnpt.vnptline.ui.view.services.SupportServicesView;

/**
 * Created by MinhDN on 6/3/2018.
 */

public interface SupportServicePresenter extends Presenter<SupportServicesView> {
    void supportService(String token, SupportServiceRequest request);
}
