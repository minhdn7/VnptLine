package com.vnpt.vnptline.domain.repository.api;

import com.vnpt.vnptline.domain.model.pojo.request.service.OrderServicRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.FindServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.SupportServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindServicesResponse;
import com.vnpt.vnptline.domain.repository.ServiceUrl;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MinhDN on 5/3/2018.
 */

public interface ServiceApi {
    // hotel api
    @POST(ServiceUrl.TIM_DICH_VU)
    public Observable<Response<FindServicesResponse>> findService(@Header("token") String token,
                                                                  @Body FindServiceRequest request);

    @POST(ServiceUrl.ORDER_DICH_VU)
    public Observable<Response<CommonResponse>> orderService(@Header("token") String token,
                                                             @Body OrderServicRequest request);

    @POST(ServiceUrl.SUPPORT_DICH_VU)
    public Observable<Response<CommonResponse>> supportService(@Header("token") String token,
                                                             @Body SupportServiceRequest request);
}
