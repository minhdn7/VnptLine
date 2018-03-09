package com.vnpt.vnptline.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vnpt.vnptline.domain.interactor.token.TokenDev;
import com.vnpt.vnptline.domain.interactor.token.TokenDevImpl;
import com.vnpt.vnptline.domain.repository.api.TokenApi;
import com.vnpt.vnptline.ui.presenter.token.TokenDevPresenter;
import com.vnpt.vnptline.ui.presenter.token.TokenDevPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MinhDN on 2/2/2018.
 */
@Module(complete = false, library = true)
public class TokenModule {
    @Provides
    TokenApi provideIService(Retrofit.Builder retrofitBuilder) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit =
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(TokenApi.class);
    }

    @Provides
    TokenDevPresenter provideMainPresenter(TokenDevPresenterImpl tokenDevPresenter) {
        return tokenDevPresenter;
    }

    @Provides
    TokenDev providesLoginUserLk(TokenDevImpl tokenDev) {
        return tokenDev;
    }
}
