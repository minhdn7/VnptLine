package com.vnpt.vnptline.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vnpt.vnptline.domain.interactor.user.User;
import com.vnpt.vnptline.domain.interactor.user.UserImpl;
import com.vnpt.vnptline.domain.repository.api.UserApi;
import com.vnpt.vnptline.ui.presenter.LoginPresenter;
import com.vnpt.vnptline.ui.presenter.LoginPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.ChangePasswordPresenter;
import com.vnpt.vnptline.ui.presenter.user.ChangePasswordPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.CheckUserPresenter;
import com.vnpt.vnptline.ui.presenter.user.CheckUserPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.DetailHistoryPresenter;
import com.vnpt.vnptline.ui.presenter.user.DetailHistoryPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.ForgotPasswordPresenter;
import com.vnpt.vnptline.ui.presenter.user.ForgotPasswordPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.HistoryPresenter;
import com.vnpt.vnptline.ui.presenter.user.HistoryPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.LogoutPresenter;
import com.vnpt.vnptline.ui.presenter.user.LogoutPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.NotificationPresenter;
import com.vnpt.vnptline.ui.presenter.user.NotificationPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.OTPCodePresenter;
import com.vnpt.vnptline.ui.presenter.user.OTPCodePresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.RegisterPresenter;
import com.vnpt.vnptline.ui.presenter.user.RegisterPresenterImpl;
import com.vnpt.vnptline.ui.presenter.user.VerifyOTPPresenter;
import com.vnpt.vnptline.ui.presenter.user.VerifyOTPPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MinhDN on 5/2/2018.
 */
@Module(complete = false, library = true)
public class UserModule {
    @Provides
    UserApi provideIService(Retrofit.Builder retrofitBuilder) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit =
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson)).build();

        return retrofit.create(UserApi.class);
    }

    @Provides
    LoginPresenter provideMainPresenter(LoginPresenterImpl loginPresenter) {
        return loginPresenter;
    }

    @Provides
    CheckUserPresenter checkUserPresenter(CheckUserPresenterImpl checkUserPresenter) {
        return checkUserPresenter;
    }

    @Provides
    OTPCodePresenter getOTPPresenter(OTPCodePresenterImpl otpCodePresenter) {
        return otpCodePresenter;
    }

    @Provides
    VerifyOTPPresenter verifyOTPPresenter(VerifyOTPPresenterImpl verifyOTPPresenter) {
        return verifyOTPPresenter;
    }

    @Provides
    RegisterPresenter registerPresenter(RegisterPresenterImpl registerPresenter) {
        return registerPresenter;
    }

    @Provides
    ForgotPasswordPresenter forgotPasswordPresenter(ForgotPasswordPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    ChangePasswordPresenter changePasswordPresenter(ChangePasswordPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    NotificationPresenter getNotificationPresenter(NotificationPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    HistoryPresenter getHistoryPresenter(HistoryPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    DetailHistoryPresenter getDetailHistoryPresenter(DetailHistoryPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    LogoutPresenter logoutPresenter(LogoutPresenterImpl presenter) {
        return presenter;
    }


    @Provides
    User providesLoginUserLk(UserImpl loginUser) {
        return loginUser;
    }


}
