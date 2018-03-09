package com.vnpt.vnptline.app.di;

import android.content.Context;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.BaseFragment;
import com.vnpt.vnptline.app.LineApplication;
import com.vnpt.vnptline.domain.interactor.hotel.Hotel;
import com.vnpt.vnptline.ui.activity.BinhLuanActivity;
import com.vnpt.vnptline.ui.activity.ChangePasswordActivity;
import com.vnpt.vnptline.ui.activity.ChiaSeActivity;
import com.vnpt.vnptline.ui.activity.DanhSachPhongDatActivity;
import com.vnpt.vnptline.ui.activity.DatDoAnActivity;
import com.vnpt.vnptline.ui.activity.DetailActivity;
import com.vnpt.vnptline.ui.activity.DetailHistoryActivity;
import com.vnpt.vnptline.ui.activity.ForgotPasswordActivity;
import com.vnpt.vnptline.ui.activity.HomeActivity;
import com.vnpt.vnptline.ui.activity.IntroActivity;
import com.vnpt.vnptline.ui.activity.LichSuActivity;
import com.vnpt.vnptline.ui.activity.LoginActivity;
import com.vnpt.vnptline.ui.activity.MainActivity;
import com.vnpt.vnptline.ui.activity.MapActivity;
import com.vnpt.vnptline.ui.activity.OTPActivity;
import com.vnpt.vnptline.ui.activity.QRCodeActivity;
import com.vnpt.vnptline.ui.activity.RegisterActivity;
import com.vnpt.vnptline.ui.activity.SubmitRegisterActivity;
import com.vnpt.vnptline.ui.activity.SupportActivity;
import com.vnpt.vnptline.ui.activity.TimNhaNghiActivity;
import com.vnpt.vnptline.ui.activity.UuDaiActivity;
import com.vnpt.vnptline.ui.fragment.AccountFragment;
import com.vnpt.vnptline.ui.fragment.FirstIntroFragment;
import com.vnpt.vnptline.ui.fragment.HomeFragment;
import com.vnpt.vnptline.ui.fragment.MenuFragment;
import com.vnpt.vnptline.ui.fragment.SettingFragment;
import com.vnpt.vnptline.ui.fragment.ThongBaoFragment;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/**
 * Created by LiKaLi on 1/22/2018.
 */
@Module(
    includes = {
//        LoginModule.class,
        HotelModule.class,
        UserModule.class,
        TokenModule.class,
        ServiceModule.class,
        NetModule.class},
    injects = {
        //App
        LineApplication.class,

        // - view
        BaseActivity.class,
        BaseFragment.class,
        //Activity
        DetailActivity.class,
        ForgotPasswordActivity.class,
        HomeActivity.class,
        IntroActivity.class,
        LoginActivity.class,
        MainActivity.class,
        MapActivity.class,
        MainActivity.class,
        OTPActivity.class,
        RegisterActivity.class,
        TimNhaNghiActivity.class,
        UuDaiActivity.class,
        BinhLuanActivity.class,
        SupportActivity.class,
        DatDoAnActivity.class,
        LichSuActivity.class,
        DanhSachPhongDatActivity.class,
        SubmitRegisterActivity.class,
        DetailHistoryActivity.class,
        ChangePasswordActivity.class,
        ChiaSeActivity.class,
        QRCodeActivity.class,
        //Fragment
        AccountFragment.class,
        FirstIntroFragment.class,
        HomeFragment.class,
        SettingFragment.class,
        MenuFragment.class,
        ThongBaoFragment.class
                }, library = true)
public class AppModule {

  private Context context;

  public AppModule(Context context) {
    this.context = context;
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return this.context;
  }
}
