package com.vnpt.vnptline.ui.presenter.services;

import com.vnpt.vnptline.domain.model.pojo.request.service.OrderServicRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;

import com.vnpt.vnptline.ui.view.services.OrderServicesView;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface OrderServicePresenter extends Presenter<OrderServicesView> {
    void orderService(String token, OrderServicRequest request);
}
