package com.vnpt.vnptline.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseFragment;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.ui.activity.ChangePasswordActivity;
import com.vnpt.vnptline.ui.activity.ChiaSeActivity;
import com.vnpt.vnptline.ui.activity.HomeActivity;
import com.vnpt.vnptline.ui.activity.LoginActivity;
import com.vnpt.vnptline.ui.activity.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    @BindView(R.id.viewAccount)
    LinearLayout viewAccount;
    @BindView(R.id.viewAccountNologin)
    RelativeLayout viewAccountNologin;
    @BindView(R.id.txtAcount)
    TextView txtAcount;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        try {
            if (tinyDB.getBoolean(AppDef.IS_LOGGIN)) {
                viewAccountNologin.setVisibility(View.GONE);
                viewAccount.setVisibility(View.VISIBLE);
                txtAcount.setText(tinyDB.getString(AppDef.USER_NAME));
            } else {
                viewAccountNologin.setVisibility(View.VISIBLE);
                viewAccount.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @OnClick({R.id.btnMoiBanBe, R.id.btnTroGiup, R.id.btnThoat, R.id.btnHuyTaiKhoan, R.id.btnDangKy, R.id.btnDangNhap, R.id.btnDoiMatKhau})
    public void submit(View v) {
        switch (v.getId()) {
            case R.id.btnMoiBanBe:
                startActivity(new Intent(getActivity(), ChiaSeActivity.class));
                break;

            case R.id.btnTroGiup:

                break;
            case R.id.btnThoat:
                dilogLogOut();
                break;
            case R.id.btnDoiMatKhau:
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                break;
            case R.id.btnHuyTaiKhoan:

                break;
            case R.id.btnDangKy:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
            case R.id.btnDangNhap:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    private void dilogLogOut() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_huy_yeu_cau, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        // set width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(b.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        width = (int) (width * 0.8);
        height = (int) (width * 0.4);
        lp.width = width;
        lp.height = height;
        lp.gravity = Gravity.CENTER;
        b.getWindow().setAttributes(lp);
        //
        TextView txtNoiDung = (TextView) b.findViewById(R.id.txtNoiDung);
        Button btnOk = (Button) b.findViewById(R.id.btnOk);
        Button btnHuy = (Button) b.findViewById(R.id.btnHuy);
        //
        txtNoiDung.setText(R.string.thongBaoLogout);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinyDB.putBoolean(AppDef.IS_LOGGIN, false);
                tinyDB.putInt(AppDef.USER_ID, 0);
                tinyDB.putString(AppDef.USER_NAME, "");
                tinyDB.putString(AppDef.TOKEN_USER, "");
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
