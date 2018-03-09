package com.vnpt.vnptline.domain.repository.api;

import com.vnpt.vnptline.domain.model.pojo.request.hotel.BookingRoomRequest;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.SearchHotelRequest;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.BookingRoomResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ChiTietNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ListRoomBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;
import com.vnpt.vnptline.domain.repository.ServiceUrl;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MinhDN on 12/2/2018.
 */

public interface HotelApi {
    // hotel api
    @POST(ServiceUrl.SEARCH_HOTEL)
    public Observable<DanhSachNhaNghiResponse> searchHotel(@Header("token") String token,
                                                           @Body SearchHotelRequest request);

    @GET(ServiceUrl.LAY_CHI_TIET_HOTEL)
    public Observable<ChiTietNhaNghiResponse> detailHotel(@Header("token") String token,
                                                          @Query("hotelId") long hotelId);
    // end

    // room hotel api
    @GET(ServiceUrl.LAY_DANH_SACH_PHONG)
    public Observable<Response<ListRoomBookingResponse>> getRoomHotel(@Header("token") String token,
                                                            @Query("hotelId") long hotelId);

    @POST(ServiceUrl.DAT_PHONG)
    public Observable<Response<BookingRoomResponse>> bookingRoom(@Header("token") String token,
                                                                 @Body BookingRoomRequest request);
    // end

    // service hotel api
    @GET(ServiceUrl.TIM_PHONG_DANG_SU_DUNG)
    public Observable<Response<FindRoomBookingResponse>> findBookedRoom(@Header("token") String token,
                                                                        @Query("userId") long userId);
    // end
}
