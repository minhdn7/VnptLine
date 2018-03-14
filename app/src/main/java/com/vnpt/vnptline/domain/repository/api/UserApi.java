package com.vnpt.vnptline.domain.repository.api;

import com.vnpt.vnptline.domain.model.pojo.request.user.ChangePasswordRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.DanhSachThongBaoRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.DetailHistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.HistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.LoginAccountRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.LogoutRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.OTPRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.RegisterUserRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.VerifyOTPRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.DanhSachThongBaoResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.DetailHistoryResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.LoginAccountResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.RegisterUserResponse;
import com.vnpt.vnptline.domain.repository.ServiceUrl;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MinhDN on 5/2/2018.
 */

public interface UserApi {
    // user api
    @POST(ServiceUrl.LOGIN)
    public Observable<LoginAccountResponse> getAccountLogin(@Header("token") String token,
                                                            @Body LoginAccountRequest request);

    @GET(ServiceUrl.CHECK_USER)
    public Observable<CommonResponse> checkUser(@Header("token") String token,
                                                @Query("username") String username);

    @POST(ServiceUrl.CHANGE_PASSWORD)
    public Observable<CommonResponse> changePassword(@Header("token") String token,
                                                     @Body ChangePasswordRequest request);

    @GET(ServiceUrl.FORGOT_PASSWORD)
    public Observable<CommonResponse> forgotPassword(@Header("token") String token,
                                                     @Query("username") String username,
                                                     @Query("deviceId") String deviceId);

    @POST(ServiceUrl.REGISTER_USER)
    public Observable<RegisterUserResponse> registerUser(@Header("token") String token,
                                                         @Body RegisterUserRequest request);

    // end

    // otp
    @POST(ServiceUrl.GET_OTP)
    public Observable<CommonResponse> getOTP(@Header("token") String token,
                                             @Body OTPRequest request);

    @POST(ServiceUrl.VERIFY_OTP)
    public Observable<CommonResponse> verifyOTP(@Header("token") String token,
                                             @Body VerifyOTPRequest request);
    // end

    // thông báo
    @POST(ServiceUrl.DANH_SACH_THONG_BAO)
    public Observable<Response<DanhSachThongBaoResponse>> layThongBao(@Header("token") String token,
                                                                      @Body DanhSachThongBaoRequest request);
    // end

    // lịch sử
    @POST(ServiceUrl.LICH_SU)
    public Observable<Response<HistoryResponse>> layLichSu(@Header("token") String token,
                                                           @Body HistoryRequest request);

    @POST(ServiceUrl.CHI_TIET_LICH_SU)
    public Observable<Response<DetailHistoryResponse>> getDetailHistory(@Header("token") String token,
                                                                        @Body DetailHistoryRequest request);
    // end

    // logout
    @POST(ServiceUrl.LOGOUT_USER)
    public Observable<Response<CommonResponse>> logout(@Header("token") String token,
                                                                        @Body LogoutRequest request);
    // end
}
