package com.vnpt.vnptline.domain.interactor.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.ChangePasswordRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.DanhSachThongBaoRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.DetailHistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.HistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.LoginAccountRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.OTPRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.RegisterUserRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.VerifyOTPRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.DanhSachThongBaoResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.DetailHistoryResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.LoginAccountResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.RegisterUserResponse;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 5/2/2018.
 */

public interface User {
    /**
     * @param token {@link String}
     * @param request {@link LoginAccountRequest}
     */
    Observable<LoginAccountResponse> execute(String token, LoginAccountRequest request);

    /**
     * @param token {@link String}
     * @param username {@link String}
     */
    Observable<CommonResponse> checkUser(String token, String username);

    /**
     * @param token {@link String}
     * @param otpRequest {@link OTPRequest}
     */
    Observable<CommonResponse> getOTP(String token, OTPRequest otpRequest);

    /**
     * @param token {@link String}
     * @param request {@link VerifyOTPRequest}
     */
    Observable<CommonResponse> verifyOTP(String token, VerifyOTPRequest request);

    /**
     * @param token {@link String}
     * @param request {@link RegisterUserRequest}
     */
    Observable<RegisterUserResponse> registerUser(String token, RegisterUserRequest request);

    /**
     * @param token {@link String}
     * @param username {@link String}
     */
    Observable<CommonResponse> forgotPassword(String token, String username, String tokenFirebase);


    /**
     * @param token {@link String}
     * @param request {@link ChangePasswordRequest}
     */
    Observable<CommonResponse> changePassword(String token, ChangePasswordRequest request);

    /**
     * @param token {@link String}
     * @param request {@link ChangePasswordRequest}
     */
    Observable<Response<DanhSachThongBaoResponse>> layDanhSachThongBao(String token, DanhSachThongBaoRequest request);

    /**
     * @param token {@link String}
     * @param request {@link HistoryRequest}
     */
    Observable<Response<HistoryResponse>> layLichSu(String token, HistoryRequest request);

    /**
     * @param token {@link String}
     * @param request {@link HistoryRequest}
     */
    Observable<Response<DetailHistoryResponse>> getDetailHistory(String token, DetailHistoryRequest request);
}
