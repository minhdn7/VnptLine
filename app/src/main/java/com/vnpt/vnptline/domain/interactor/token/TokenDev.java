package com.vnpt.vnptline.domain.interactor.token;

import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;

import rx.Observable;

/**
 * Created by MinhDN on 2/2/2018.
 */

public interface TokenDev {
    Observable<TokenDevResponse> getTokenDev(String key);
}
