package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.user.VerifyOTPRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.presenter.user.CheckUserPresenter;
import com.vnpt.vnptline.ui.presenter.user.ForgotPasswordPresenter;
import com.vnpt.vnptline.ui.presenter.user.OTPCodePresenter;
import com.vnpt.vnptline.ui.presenter.user.VerifyOTPPresenter;
import com.vnpt.vnptline.ui.view.user.ForgotPasswordView;
import com.vnpt.vnptline.ui.view.user.VerifyOTPView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OTPActivity extends BaseActivity implements VerifyOTPView, ForgotPasswordView{


    @BindView(R.id.txtCodeOTP)
    @NotEmpty(message = "OTP không được để trống")
    EditText txtCodeOTP;

    @BindView(R.id.txtTitle) TextView txtTitle;
    private String phone = "";
    @Inject
    ForgotPasswordPresenter forgotPasswordPresenter;

    @Inject
    VerifyOTPPresenter verifyOTPPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_otp));
        verifyOTPPresenter.setView(this);
        verifyOTPPresenter.onViewCreate();
        phone = getIntent().getStringExtra("EXTRA_PHONE");
        txtCodeOTP.setText(getIntent().getStringExtra("EXTRA_OTP"));
        String sTitle = getString(R.string.maXacThucGuiDen1) + " " + phone +  ". " + getString(R.string.maXacThucGuiDen2);
        txtTitle.setText(sTitle);
        forgotPasswordPresenter.setView(this);
        forgotPasswordPresenter.onViewCreate();
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

    @OnClick({R.id.btnGuiOTP})
    public void submit(View view) {
        if(view.getId() == R.id.btnGuiOTP){
            validator.validate();
            if(isPassedValidate) {
                VerifyOTPRequest request = new VerifyOTPRequest(txtCodeOTP.getText().toString().trim(), AppDef.OTP_TYPE, phone);
                verifyOTPPresenter.verifyOTPCode(AppDef.TOKEN_DEV, request);
            }
        }
    }

    @Override
    public void onVerifyOTPSuccess(CommonResponse commonResponse) {
        switch (AppDef.OTP_CODE){
            case 1:
                Intent intent = new Intent(this, SubmitRegisterActivity.class);
                intent.putExtra("EXTRA_PHONE", phone);
                startActivity(intent);
                break;
            case 2:
                forgotPasswordPresenter.forgotPassword(AppDef.TOKEN_DEV, phone, tinyDB.getString(AppDef.TOKEN_FIREBASE));
                break;
        }

    }

    @Override
    public void onVerifyOTPFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVerifyOTPError(Throwable e) {

    }

    @Override
    public void onCheckForgotSuccess(CommonResponse commonResponse) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
    }

    @Override
    public void onCheckForgotFailed(String message) {
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onCheckForgotError(Throwable e) {

    }
}
