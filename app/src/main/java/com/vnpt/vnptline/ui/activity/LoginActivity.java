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

import butterknife.BindView;
import butterknife.OnClick;

import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.user.LoginAccountRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.LoginAccountResponse;
import com.vnpt.vnptline.ui.presenter.LoginPresenter;
import com.vnpt.vnptline.ui.view.user.LoginView;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.txtDangKyNgay) TextView txtDangKyNgay;
    @BindView(R.id.txtQuenMatKhau) TextView txtQuenMatKhau;
    @BindView(R.id.txtPhone)

    @NotEmpty (message = "Trường bắt buôc không được để trống")
    EditText txtPhone;

    @BindView(R.id.txtPassword)
    @NotEmpty (message = "Trường bắt buôc không được để trống")
    EditText txtPassword;


    @Inject LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getString(R.string.title_activity_login));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginPresenter.setView(this);
        loginPresenter.onViewCreate();
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

    @OnClick({R.id.txtDangKyNgay, R.id.txtQuenMatKhau, R.id.btnDangNhap})
    public void submit(View view) {
        switch (view.getId()){
            case R.id.txtDangKyNgay:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.txtQuenMatKhau:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
            case R.id.btnDangNhap:
                showProgressBar();
                LoginAccountRequest request = new LoginAccountRequest(tinyDB.getString(AppDef.TOKEN_FIREBASE), txtPhone.getText().toString().trim(), txtPassword.getText().toString().trim());
                loginPresenter.login(AppDef.TOKEN_DEV, request);
                break;
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onViewDestroy();
    }

    @Override public void onLoginSuccess(LoginAccountResponse loginResult) {
        hideProgressBar();
        tinyDB.putBoolean(AppDef.IS_LOGGIN, true);
        tinyDB.putInt(AppDef.USER_ID, loginResult.getUserId());
        tinyDB.putString(AppDef.USER_NAME, loginResult.getUsername());
        tinyDB.putString(AppDef.NAME, loginResult.getName());
        tinyDB.putString(AppDef.TOKEN_USER, loginResult.getTokenId());
        Integer iBooking = getIntent().getIntExtra(AppDef.BOOKING_FLOW, 0);
        if(iBooking == 1){
            onBackPressed();
        }else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    }

    @Override public void onLoginFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override public void onLoginError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
