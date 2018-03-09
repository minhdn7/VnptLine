package com.vnpt.vnptline.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.domain.interactor.hotel.HotelImpl;
import com.vnpt.vnptline.domain.repository.api.HotelApi;
import com.vnpt.vnptline.ui.presenter.hotel.BookingRoomPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.BookingRoomPresenterImpl;
import com.vnpt.vnptline.ui.presenter.hotel.DetailHotelPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.DetailHotelPresenterImpl;
import com.vnpt.vnptline.ui.presenter.hotel.FindRoomBookingPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.FindRoomBookingPresenterImpl;
import com.vnpt.vnptline.ui.presenter.hotel.RoomHotelPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.RoomHotelPresenterImpl;
import com.vnpt.vnptline.ui.presenter.hotel.SearchHotelPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.SearchHotelPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MinhDN on 12/2/2018.
 */
@Module(complete = false, library = true)
public class HotelModule {
    @Provides
    HotelApi provideIService(Retrofit.Builder retrofitBuilder) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit =
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson)).build();

        return retrofit.create(HotelApi.class);
    }


    @Provides
    SearchHotelPresenter provideSearchHotelPresenter(SearchHotelPresenterImpl searchHotelPresenter) {
        return searchHotelPresenter;
    }

    @Provides
    DetailHotelPresenter provideDetailHotelPresenter(DetailHotelPresenterImpl detailHotelPresenter) {
        return detailHotelPresenter;
    }

    @Provides
    RoomHotelPresenter provideRoomHotelPresenter(RoomHotelPresenterImpl roomHotelPresenter) {
        return roomHotelPresenter;
    }

    @Provides
    BookingRoomPresenter bookingRoomHotelPresenter(BookingRoomPresenterImpl bookingRoomPresenter) {
        return bookingRoomPresenter;
    }

    @Provides
    FindRoomBookingPresenter bookingRoomHotelPresenter(FindRoomBookingPresenterImpl presenter) {
        return presenter;
    }


    @Provides
    Hotel providesHotel(HotelImpl hotel) {
        return hotel;
    }
}
