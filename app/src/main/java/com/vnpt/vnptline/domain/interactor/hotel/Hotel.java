package com.vnpt.vnptline.domain.interactor.hotel;

import com.vnpt.vnptline.domain.model.pojo.request.hotel.BookingRoomRequest;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.FindCommentHotelRequest;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.SearchHotelRequest;

import retrofit2.Response;
import rx.Observable;

import com.vnpt.vnptline.domain.model.pojo.response.hotel.FindCommentHotelResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLight;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.BookingRoomResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ChiTietNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLightResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ListRoomBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;

/**
 * Created by MinhDN on 12/2/2018.
 */

public interface Hotel {
    /**
     * @param token {@link String}
     * @param request {@link SearchHotelRequest}
     */
    Observable<DanhSachNhaNghiResponse> searchHotel(String token, SearchHotelRequest request);

    /**
     * @param token {@link String}
     * @param id {@link long}
     */
    Observable<ChiTietNhaNghiResponse> detailHotel(String token, long id);

    /**
     * @param token {@link String}
     * @param idHotel {@link long}
     */
    Observable<Response<ListRoomBookingResponse>> layDanhSachPhong(String token, long idHotel);


    /**
     * @param token {@link String}
     * @param request {@link BookingRoomRequest}
     */
    Observable<Response<BookingRoomResponse>> bookingRoom(String token, BookingRoomRequest request);

    /**
     * @param token {@link String}
     * @param userId {@link Long}
     */
    Observable<Response<FindRoomBookingResponse>> findBookingRoom(String token, long userId);

    /**
     * @param token {@link String}
     *
     */
    Observable<Response<HotelHightLightResponse>> findHotelAdvance(String token);

    /**
     * @param token {@link String}
     * @param request {@link FindCommentHotelRequest}
     */
    Observable<Response<FindCommentHotelResponse>> findCommentHotel(String token, FindCommentHotelRequest request);

}
