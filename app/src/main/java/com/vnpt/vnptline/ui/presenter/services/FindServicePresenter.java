package com.vnpt.vnptline.ui.presenter.services;

import com.vnpt.vnptline.domain.model.pojo.request.service.FindServiceRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.services.FindServicesView;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface FindServicePresenter extends Presenter<FindServicesView>{
    void findService(String token, FindServiceRequest request);
}
