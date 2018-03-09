package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.user.ChangePasswordRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.OTPRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.presenter.user.ChangePasswordPresenter;
import com.vnpt.vnptline.ui.presenter.user.VerifyOTPPresenter;
import com.vnpt.vnptline.ui.view.user.ChangePasswordView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordView{
//    @BindView(R.id.txtOTP)
//    @NotEmpty(message = "Trường bắt buộc không được để trống")
//    TextView txtOTP;

    @BindView(R.id.txtMatKhauMoi)
    @NotEmpty(message = "Trường bắt buộc không được để trống")
    @Length(min =  8, message = "Độ dài tối thiểu 8 kí tự")
    EditText txtMatKhauMoi;

    @BindView(R.id.txtNhapLaiMatKhau)
    @NotEmpty(message = "Trường bắt buộc không được để trống")
    @Length(min =  8, message = "Độ dài tối thiểu 8 kí tự")
    EditText txtNhapLaiMatKhau;

    @Inject
    ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_change_password));
        changePasswordPresenter.setView(this);
        changePasswordPresenter.onViewCreate();
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

    @OnClick({R.id.btnDoiMatKhau})
    public void submit(View view) {
        if(view.getId() == R.id.btnDoiMatKhau){
            validator.validate();
            if(isPassedValidate){
                if(!txtMatKhauMoi.getText().toString().trim().equals(txtNhapLaiMatKhau.getText().toString().trim())){
                    txtNhapLaiMatKhau.setError("Mật khẩu nhập lại không chính xác");
                    return;
                }
                ChangePasswordRequest request = new ChangePasswordRequest(tinyDB.getString(AppDef.USER_NAME), txtMatKhauMoi.getText().toString().trim());
                changePasswordPresenter.changePass(AppDef.TOKEN_DEV, request);
            }
        }

    }

    @Override
    public void onChangePasswordSuccess(CommonResponse commonResponse) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onChangePasswordFailed(String message) {
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onChangePasswordError(Throwable e) {

    }
}
