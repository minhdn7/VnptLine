package com.vnpt.vnptline.domain.interactor.services;

import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.model.pojo.request.service.FindServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.OrderServicRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.SupportServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindServicesResponse;
import com.vnpt.vnptline.domain.repository.api.HotelApi;
import com.vnpt.vnptline.domain.repository.api.ServiceApi;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 5/3/2018.
 */

public class HotelServiceImpl implements HotelService {
    @Inject
    ServiceApi serviceApi;

    @Override
    public Observable<Response<FindServicesResponse>> findService(String token, FindServiceRequest request) {
        return serviceApi.findService(token, request);
    }

    @Override
    public Observable<Response<CommonResponse>> orderService(String token, OrderServicRequest request) {
        return serviceApi.orderService(token, request);
    }

    @Override
    public Observable<Response<CommonResponse>> supportService(String token, SupportServiceRequest request) {
        return serviceApi.supportService(token, request);
    }
}
