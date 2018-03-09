package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.model.pojo.request.user.OTPRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.presenter.user.CheckUserPresenter;
import com.vnpt.vnptline.ui.presenter.user.OTPCodePresenter;
import com.vnpt.vnptline.ui.view.user.CheckUserView;
import com.vnpt.vnptline.ui.view.user.OTPCodeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseActivity implements CheckUserView, OTPCodeView {
    @BindView(R.id.txtPhone)
    EditText txtPhone;
    @Inject
    CheckUserPresenter checkUserPresenter;
    @Inject
    OTPCodePresenter otpCodePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_forgot_password));
//        forgotPasswordPresenter.setView(this);
//        forgotPasswordPresenter.onViewCreate();
        checkUserPresenter.setView(this);
        checkUserPresenter.onViewCreate();
        otpCodePresenter.setView(this);
        otpCodePresenter.onViewCreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @OnClick({R.id.btnForgotPassword})
    public void submit(View view) {
        switch (view.getId()){
            case R.id.btnForgotPassword:
                if(checkValidPhone(txtPhone)){
                    OTPRequest request = new OTPRequest(tinyDB.getString(AppDef.TOKEN_FIREBASE), AppDef.OTP_TYPE, txtPhone.getText().toString().trim());
                    otpCodePresenter.getOTPCode(AppDef.TOKEN_DEV, request);

                }
                break;
        }
    }



    @Override
    public void onOTPCodeSuccess(CommonResponse commonResponse) {
        try {
            if(commonResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS){
                Intent intent = new Intent(this, OTPActivity.class);
                intent.putExtra("EXTRA_PHONE", txtPhone.getText().toString());
                intent.putExtra("EXTRA_OTP", commonResponse.getResponseMessage());
                AppDef.OTP_CODE = 2;
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOTPCodeFailed(String message) {
        dilogThongBao("Thông báo", message, getString(R.string.dong));
    }

    @Override
    public void onOTPCoderError(Throwable e) {

    }

    @Override
    public void onCheckUserSuccess(CommonResponse commonResponse) {
        try {
            if(commonResponse.getResponseCode() == StatusCode.RESPONSE_USER_EXIST){
                Intent intent = new Intent(this, OTPActivity.class);
                intent.putExtra("EXTRA_PHONE", txtPhone.getText().toString());
                intent.putExtra("EXTRA_OTP", commonResponse.getResponseMessage());
                AppDef.OTP_CODE = 2;
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckUserFailed(String message) {
        dilogThongBao("Thông báo", message, getString(R.string.dong));
    }

    @Override
    public void onCheckUserError(Throwable e) {

    }
}
