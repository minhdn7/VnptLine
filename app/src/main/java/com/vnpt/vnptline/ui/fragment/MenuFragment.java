package com.vnpt.vnptline.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseFragment;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.ui.activity.DanhSachPhongDatActivity;
import com.vnpt.vnptline.ui.activity.LichSuActivity;
import com.vnpt.vnptline.ui.activity.LoginActivity;
import com.vnpt.vnptline.ui.activity.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuFragment extends BaseFragment {
    @BindView(R.id.btnLichSu)
    LinearLayout btnLichSu;
    @BindView(R.id.viewLichSu)
    LinearLayout viewLichSu;
    @BindView(R.id.btnAnUong)
    LinearLayout btnAnUong;
    @BindView(R.id.viewDoAn)
    LinearLayout viewDoAn;
    @BindView(R.id.btnHoTro)
    LinearLayout btnHoTro;
    @BindView(R.id.viewSupport)
    LinearLayout viewSupport;
    @BindView(R.id.btnDangNhap)
    LinearLayout btnDangNhap;
    @BindView(R.id.viewlogin)
    LinearLayout viewlogin;
    @BindView(R.id.btnDangKy)
    LinearLayout btnDangKy;
    @BindView(R.id.viewRegister)
    LinearLayout viewRegister;
    @BindView(R.id.btnCaiDat)
    LinearLayout btnCaiDat;
    // TODO: Rename parameter arguments, choose names that match


    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        addControls();
        return view;
    }

    private void addControls() {
        if (tinyDB.getBoolean(AppDef.IS_LOGGIN)) {
            //  user
            btnLichSu.setVisibility(View.VISIBLE);
            viewLichSu.setVisibility(View.VISIBLE);
            btnAnUong.setVisibility(View.VISIBLE);
            viewDoAn.setVisibility(View.VISIBLE);
            btnHoTro.setVisibility(View.VISIBLE);
            viewSupport.setVisibility(View.VISIBLE);
            // non-user
            btnDangNhap.setVisibility(View.GONE);
            viewlogin.setVisibility(View.GONE);
            btnDangKy.setVisibility(View.GONE);
            viewRegister.setVisibility(View.GONE);
        } else {
            //  user
            btnLichSu.setVisibility(View.GONE);
            viewLichSu.setVisibility(View.GONE);
            btnAnUong.setVisibility(View.GONE);
            viewDoAn.setVisibility(View.GONE);
            btnHoTro.setVisibility(View.GONE);
            viewSupport.setVisibility(View.GONE);
            // non-user
            btnDangNhap.setVisibility(View.VISIBLE);
            viewlogin.setVisibility(View.VISIBLE);
            btnDangKy.setVisibility(View.VISIBLE);
            viewRegister.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.btnLichSu, R.id.btnAnUong, R.id.btnDangNhap, R.id.btnDangKy, R.id.btnCaiDat, R.id.btnHoTro})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.btnLichSu:
                startActivity(new Intent(getActivity(), LichSuActivity.class));
                break;
            case R.id.btnAnUong:
                AppDef.CHON_LOAI_DICH_VU = 1;
                startActivity(new Intent(getActivity(), DanhSachPhongDatActivity.class));
                break;
            case R.id.btnHoTro:
//                startActivity(new Intent(getActivity(), SupportActivity.class));
                AppDef.CHON_LOAI_DICH_VU = 2;
                startActivity(new Intent(getActivity(), DanhSachPhongDatActivity.class));
                break;
            case R.id.btnDangNhap:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.btnDangKy:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
            case R.id.btnCaiDat:

                break;
        }
    }


}
