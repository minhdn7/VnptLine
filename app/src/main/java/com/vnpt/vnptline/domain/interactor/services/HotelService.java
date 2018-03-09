package com.vnpt.vnptline.domain.interactor.services;

import com.vnpt.vnptline.domain.model.pojo.request.service.FindServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.OrderServicRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.SupportServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindServicesResponse;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface HotelService {
    /**
     * @param token {@link String}
     * @param request {@link FindServiceRequest}
     */
    Observable<Response<FindServicesResponse>> findService(String token, FindServiceRequest request);

    /**
     * @param token {@link String}
     * @param request {@link FindServiceRequest}
     */
    Observable<Response<CommonResponse>> orderService(String token, OrderServicRequest request);

    /**
     * @param token {@link String}
     * @param request {@link FindServiceRequest}
     */
    Observable<Response<CommonResponse>> supportService(String token, SupportServiceRequest request);
}
