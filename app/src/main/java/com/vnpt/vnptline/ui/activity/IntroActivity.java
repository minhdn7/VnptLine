package com.vnpt.vnptline.ui.activity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.app.utils.ConfigNotification;
import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;
import com.vnpt.vnptline.domain.repository.SharePrefDefine;
import com.vnpt.vnptline.domain.repository.TinyDB;
import com.vnpt.vnptline.ui.presenter.token.TokenDevPresenter;
import com.vnpt.vnptline.ui.view.token.TokenDevView;

import org.json.JSONObject;

import javax.inject.Inject;

public class IntroActivity extends AppIntro {

    String jsonData = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("testVNPTLine");
        TinyDB tinydb = new TinyDB(this);
        handleNotification();
        try{
            boolean isIntroDuce = tinydb.getBoolean(SharePrefDefine.IS_INTRODUCE);
            if(!isIntroDuce){
                SliderPage sliderPage1 = new SliderPage();
                sliderPage1.setTitle("Xin chào!");
                sliderPage1.setDescription("Chúng tôi là Vnpt Software");
                sliderPage1.setImageDrawable(R.drawable.ic_slide1);
                sliderPage1.setBgColor(Color.CYAN);
                addSlide(AppIntroFragment.newInstance(sliderPage1));

                SliderPage sliderPage2 = new SliderPage();
                sliderPage2.setTitle("Giới thiệu");
                sliderPage2.setDescription("Chúng tôi đến đây để mang lại cho các bạn 1 giải pháp về tìm kiếm nhà nghỉ, nhà trọ cho thuê với giá cả hợp lý nhất");
                sliderPage2.setImageDrawable(R.drawable.ic_slide2);
                sliderPage2.setBgColor(Color.CYAN);
                addSlide(AppIntroFragment.newInstance(sliderPage2));

                SliderPage sliderPage3 = new SliderPage();
                sliderPage3.setTitle("Giới thiệu");
                sliderPage3.setDescription("Chúng tôi gọi nó là Vnpt Line");
                sliderPage3.setImageDrawable(R.drawable.ic_slide3);
                sliderPage3.setBgColor(Color.CYAN);
                addSlide(AppIntroFragment.newInstance(sliderPage3));

                SliderPage sliderPage4 = new SliderPage();
                sliderPage4.setTitle("Hành trình");
                sliderPage4.setDescription("Nào, hãy cùng bắt đầu và trải nghiệm với chúng tôi!");
                sliderPage4.setImageDrawable(R.drawable.ic_slide4);
                sliderPage4.setBgColor(Color.CYAN);
                addSlide(AppIntroFragment.newInstance(sliderPage4));
                tinydb.putBoolean(SharePrefDefine.IS_INTRODUCE, true);
                return;
            }else if(jsonData != null && !jsonData.equals("")){
                JSONObject json = new JSONObject(jsonData);
                if(json.getString("type").equals("Booking")){
                    Intent intent = new Intent(getApplicationContext(), QRCodeActivity.class);
                    intent.putExtra(ConfigNotification.NOTIFICATION_APP, "false");
                    intent.putExtra("JSON_BOOKING_DATA", jsonData);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(this, StartActivity.class);
                    startActivity(intent);
                }

            } else {
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
            }
        }catch (Exception ex){

        }

    }

    private void handleNotification() {
        try {
            jsonData = getIntent().getStringExtra(ConfigNotification.NOTIFICATION_DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }





}
