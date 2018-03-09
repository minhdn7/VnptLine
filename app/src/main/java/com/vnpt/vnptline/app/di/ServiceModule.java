package com.vnpt.vnptline.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.interactor.hotel.HotelImpl;
import com.vnpt.vnptline.domain.interactor.services.HotelService;
import com.vnpt.vnptline.domain.interactor.services.HotelServiceImpl;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.interactor.user.UserImpl;
import com.vnpt.vnptline.domain.repository.api.ServiceApi;
import com.vnpt.vnptline.domain.repository.api.UserApi;
import com.vnpt.vnptline.ui.presenter.services.FindServicePresenter;
import com.vnpt.vnptline.ui.presenter.services.FindServicePresenterImpl;
import com.vnpt.vnptline.ui.presenter.services.OrderServicePresenter;
import com.vnpt.vnptline.ui.presenter.services.OrderServicePresenterImpl;
import com.vnpt.vnptline.ui.presenter.services.SupportServicePresenter;
import com.vnpt.vnptline.ui.presenter.services.SupportServicePresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MinhDN on 5/3/2018.
 */

@Module(complete = false, library = true)
public class ServiceModule {
    @Provides
    ServiceApi provideIService(Retrofit.Builder retrofitBuilder) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit =
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson)).build();

        return retrofit.create(ServiceApi.class);
    }

    @Provides
    FindServicePresenter findServicePresenter(FindServicePresenterImpl presenter) {
        return presenter;
    }

    @Provides
    OrderServicePresenter orderServicePresenter(OrderServicePresenterImpl presenter) {
        return presenter;
    }

    @Provides
    SupportServicePresenter supportServicePresenter(SupportServicePresenterImpl presenter) {
        return presenter;
    }

    @Provides
    HotelService providesService(HotelServiceImpl hotelService) {
        return hotelService;
    }
}
