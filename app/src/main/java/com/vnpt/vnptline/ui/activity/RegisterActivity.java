package com.vnpt.vnptline.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.model.pojo.request.user.OTPRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.domain.repository.TinyDB;
import com.vnpt.vnptline.ui.presenter.token.TokenDevPresenter;
import com.vnpt.vnptline.ui.presenter.user.CheckUserPresenter;
import com.vnpt.vnptline.ui.presenter.user.OTPCodePresenter;
import com.vnpt.vnptline.ui.view.user.CheckUserView;
import com.vnpt.vnptline.ui.view.user.OTPCodeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements CheckUserView, OTPCodeView{
    @BindView(R.id.btnDangKy) Button btnDangKy;
    @BindView(R.id.txtPhone) EditText txtPhone;
    @Inject
    CheckUserPresenter checkUserPresenter;
    @Inject
    OTPCodePresenter otpCodePresenter;

    private TinyDB tinydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_register));
        checkUserPresenter.setView(this);
        checkUserPresenter.onViewCreate();
        otpCodePresenter.setView(this);
        otpCodePresenter.onViewCreate();
        tinydb = new TinyDB(this);
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

    @OnClick({R.id.btnDangKy})
    public void submit(View view) {
        switch (view.getId()){
            case R.id.btnDangKy:
                if(checkValidPhone()){
                    showProgressBar();
                    checkUserPresenter.checkUser(AppDef.TOKEN_DEV, txtPhone.getText().toString());

                }
                break;
        }
    }

    private boolean checkValidPhone(){
        String phone = txtPhone.getText().toString().trim();
        if(phone.equals("") || phone.length() < 10 || phone.length() > 12){
            txtPhone.setError(getString(R.string.errorPhone));
            txtPhone.requestFocus();
            return false;
        }else if(phone.charAt(0) != '0'){
            txtPhone.setError(getString(R.string.errorPhone));
            txtPhone.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onCheckUserSuccess(CommonResponse commonResponse) {
        try {
            if(commonResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS){
                OTPRequest request = new OTPRequest(tinydb.getString(AppDef.TOKEN_FIREBASE), AppDef.OTP_TYPE, txtPhone.getText().toString().trim());
                otpCodePresenter.getOTPCode(AppDef.TOKEN_DEV, request);
            }else {
                dilogThongBao("Thông báo", commonResponse.getResponseMessage(), getString(R.string.dong));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckUserFailed(String message) {
        hideProgressBar();
        dilogThongBao("Thông báo", message, getString(R.string.dong));
    }

    @Override
    public void onCheckUserError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOTPCodeSuccess(CommonResponse commonResponse) {
        hideProgressBar();
        try {
            if(commonResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS){
                Intent intent = new Intent(this, OTPActivity.class);
                intent.putExtra("EXTRA_PHONE", txtPhone.getText().toString());
                intent.putExtra("EXTRA_OTP", commonResponse.getResponseMessage());
                AppDef.OTP_CODE = 1;
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOTPCodeFailed(String message) {
        hideProgressBar();
        dilogThongBao("Thông báo", message, getString(R.string.dong));
    }

    @Override
    public void onOTPCoderError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
