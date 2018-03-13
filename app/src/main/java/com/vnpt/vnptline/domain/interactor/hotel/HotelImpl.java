package com.vnpt.vnptline.domain.interactor.hotel;

import com.vnpt.vnptline.domain.model.pojo.request.hotel.BookingRoomRequest;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.SearchHotelRequest;

import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLight;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.BookingRoomResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ChiTietNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLightResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ListRoomBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;
import com.vnpt.vnptline.domain.repository.api.HotelApi;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 12/2/2018.
 */

public class HotelImpl implements Hotel{
    @Inject
    HotelApi hotelApi;

    @Override
    public Observable<DanhSachNhaNghiResponse> searchHotel(String token, SearchHotelRequest request) {
        return hotelApi.searchHotel(token, request);
    }

    @Override
    public Observable<ChiTietNhaNghiResponse> detailHotel(String token, long id) {
        return hotelApi.detailHotel(token, id);
    }

    @Override
    public Observable<Response<ListRoomBookingResponse>> layDanhSachPhong(String token, long idHotel) {
        return hotelApi.getRoomHotel(token, idHotel);
    }

    @Override
    public Observable<Response<BookingRoomResponse>> bookingRoom(String token, BookingRoomRequest request) {
        return hotelApi.bookingRoom(token, request);
    }

    @Override
    public Observable<Response<FindRoomBookingResponse>> findBookingRoom(String token, long userId) {
        return hotelApi.findBookedRoom(token, userId);
    }

    @Override
    public Observable<Response<HotelHightLightResponse>> findHotelAdvance(String token) {
        return hotelApi.findHotelAdvence(token);
    }


}
