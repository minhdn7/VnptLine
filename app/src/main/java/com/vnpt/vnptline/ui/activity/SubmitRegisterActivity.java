package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.user.RegisterUserRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.RegisterUserResponse;
import com.vnpt.vnptline.ui.presenter.user.RegisterPresenter;
import com.vnpt.vnptline.ui.view.user.RegisterUserView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SubmitRegisterActivity extends BaseActivity implements RegisterUserView{
    @BindView(R.id.btnDangKy) Button btnDangKy;

    @BindView(R.id.txtTenDangNhap)
    @NotEmpty(message = "Tên Đăng nhập không được để trống")
    EditText txtTenDangNhap;

    @BindView(R.id.txtMatKhau)
    @NotEmpty(message = "Tên Đăng nhập không được để trống")
    @Length(min = 8, max = 255, message = "Mật khẩu phải có độ dài từ 8 kí tự, phải bao gồm kí tự đặc biệt")
    EditText txtMatKhau;
    @Inject
    RegisterPresenter registerPresenter;
    private String phone = "";
    private List<String> roles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_submit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_submit_register));
        registerPresenter.setView(this);
        registerPresenter.onViewCreate();
        roles.add("CUSTOMER");
        phone = getIntent().getStringExtra("EXTRA_PHONE");
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



    @Override
    public void onRegisterUserSuccess(RegisterUserResponse commonResponse) {
        dilogDangKySuccess(getString(R.string.thongBao), commonResponse.getResponseMessage(), getString(R.string.tiepTuc));
    }

    @Override
    public void onRegisterUserFailed(String message) {
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onRegisterUserError(Throwable e) {

    }

    @OnClick({R.id.btnDangKy})
    public void submit(View view) {
        if(view.getId() == R.id.btnDangKy){
            validator.validate();
            if(isPassedValidate) {
                RegisterUserRequest request = new RegisterUserRequest();
                request.setName(txtTenDangNhap.getText().toString().trim());
                request.setPassword(txtMatKhau.getText().toString().trim());
                request.setRoles(roles);
                request.setType(AppDef.REGISTER_TYPE);
                request.setUsername(phone);

                registerPresenter.registerUser(AppDef.TOKEN_DEV, request);
            }
        }
    }

    public void dilogDangKySuccess(String title, String noiDung, String sButtton){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_thong_bao, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        // set width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(b.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        width = (int) (width * 0.7);
        lp.width = width;
        lp.gravity = Gravity.CENTER;
        b.getWindow().setAttributes(lp);
        //
        TextView txtHeaderDialog = (TextView) dialogView.findViewById(R.id.txtHeaderDialog);
        TextView txtNoiDung = (TextView) dialogView.findViewById(R.id.txtNoiDung);
        ImageView imgExit = (ImageView) dialogView.findViewById(R.id.imgExit);
        Button btnTiepTuc = (Button) dialogView.findViewById(R.id.btnTiepTuc);

        txtHeaderDialog.setText(title);
        txtNoiDung.setText(noiDung);
        btnTiepTuc.setText(sButtton);
        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitRegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
