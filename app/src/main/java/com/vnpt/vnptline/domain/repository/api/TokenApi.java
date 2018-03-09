package com.vnpt.vnptline.domain.repository.api;

import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;
import com.vnpt.vnptline.domain.repository.ServiceUrl;


import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by MinhDN on 1/2/2018.
 */

public interface TokenApi {

    @GET(ServiceUrl.GET_TOKEN_DEV)
    public Observable<TokenDevResponse> getTokenDev(@Header("key") String key);

}
