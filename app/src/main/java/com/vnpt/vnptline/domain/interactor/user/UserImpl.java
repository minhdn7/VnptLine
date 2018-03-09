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
import com.vnpt.vnptline.domain.repository.api.UserApi;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 5/2/2018.
 */

public class UserImpl implements User {
    @Inject
    UserApi userApi;

    @Override
    public Observable<LoginAccountResponse> execute(String token, LoginAccountRequest request) {
        return userApi.getAccountLogin(token, request);
    }

    @Override
    public Observable<CommonResponse> checkUser(String token, String username) {
        return userApi.checkUser(token, username);
    }

    @Override
    public Observable<CommonResponse> getOTP(String token, OTPRequest otpRequest) {
        return userApi.getOTP(token, otpRequest);
    }

    @Override
    public Observable<CommonResponse> verifyOTP(String token, VerifyOTPRequest request) {
        return userApi.verifyOTP(token, request);
    }

    @Override
    public Observable<RegisterUserResponse> registerUser(String token, RegisterUserRequest request) {
        return userApi.registerUser(token, request);
    }

    @Override
    public Observable<CommonResponse> forgotPassword(String token, String username, String tokenFirebase) {
        return userApi.forgotPassword(token, username, tokenFirebase);
    }

    @Override
    public Observable<CommonResponse> changePassword(String token, ChangePasswordRequest request) {
        return userApi.changePassword(token, request);
    }

    @Override
    public Observable<Response<DanhSachThongBaoResponse>> layDanhSachThongBao(String token, DanhSachThongBaoRequest request) {
        return userApi.layThongBao(token, request);
    }

    @Override
    public Observable<Response<HistoryResponse>> layLichSu(String token, HistoryRequest request) {
        return userApi.layLichSu(token, request);
    }

    @Override
    public Observable<Response<DetailHistoryResponse>> getDetailHistory(String token, DetailHistoryRequest request) {
        return userApi.getDetailHistory(token, request);
    }
}
