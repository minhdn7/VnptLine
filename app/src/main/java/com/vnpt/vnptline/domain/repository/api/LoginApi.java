package com.vnpt.vnptline.domain.repository.api;

import com.vnpt.vnptline.domain.model.pojo.response.LoginAccountResponse;
import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MinhDN on 28/12/2017.
 */

public interface LoginApi {
  @POST("/api/APIForAppMobile/Api_HoiVienDangNhapTaiKhoan?")
  public Observable<LoginAccountResponse> getAccountLogin(@Field("login_id") String loginId,
  @Field("password") String password);

  @POST("/api/APIForAppMobile/Api_HoiVienDangNhapTaiKhoan?")
  public Observable<LoginAccountResponse> getAccounàdasdftLogin(@Field("login_id") String loginId,
      @Field("password") String password);

}
