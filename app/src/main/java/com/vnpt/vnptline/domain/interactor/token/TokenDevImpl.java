package com.vnpt.vnptline.domain.interactor.token;

import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;
import com.vnpt.vnptline.domain.repository.api.TokenApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by MinhDN on 2/2/2018.
 */

public class TokenDevImpl implements TokenDev{
    @Inject
    TokenApi tokenApi;
    @Override
    public Observable<TokenDevResponse> getTokenDev(String key) {

        return tokenApi.getTokenDev(key);
    }
}
