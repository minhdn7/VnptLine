package com.vnpt.vnptline.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseFragment;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLight;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLightResponse;
import com.vnpt.vnptline.ui.activity.TimNhaNghiActivity;
import com.vnpt.vnptline.ui.activity.UuDaiActivity;
import com.vnpt.vnptline.ui.adapter.DanhSachNoiBatAdapter;
import com.vnpt.vnptline.ui.presenter.hotel.DetailHotelPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.HotelHighLightPresenter;
import com.vnpt.vnptline.ui.view.hotel.HotelHightLightView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by MinhDN on 17/1/2018.
 */

public class HomeFragment extends BaseFragment implements HotelHightLightView{
    @BindView(R.id.banner_slider) BannerSlider banner_slider;
    @BindView(R.id.btnTimNhaNghi) LinearLayout btnTimNhaNghi;
    @BindView(R.id.btnTimNhaTro) LinearLayout btnTimNhaTro;
    @BindView(R.id.btnTimQuanKaraoke) LinearLayout btnTimQuanKaraoke;
//    @BindView(R.id.horizontalScrollView) HorizontalScrollView horizontalScrollView;
    @BindView(R.id.lvDanhSachNoiBat) RecyclerView lvDanhSachNoiBat;
    @BindView(R.id.viewDangNhap) LinearLayout viewDangNhap;

    LinearLayoutManager HorizontalLayout;
    private DanhSachNoiBatAdapter danhSachNoiBatAdapter;
    private List<HotelHightLight> danhSachNoiBat = new ArrayList<>();

    List<Banner> banners;
    @Inject
    HotelHighLightPresenter hotelHighLightPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        ButterKnife.bind(this, view);
        addControls();
        addEvents();

        return view;
    }

    private void addEvents() {
        try {
            if(tinyDB.getBoolean(AppDef.IS_LOGGIN)){
                viewDangNhap.setVisibility(View.GONE);
            }else {
                viewDangNhap.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addControls() {
        hotelHighLightPresenter.setView(this);
        hotelHighLightPresenter.onViewCreate();
        banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.nha_nghi_11));
        banners.add(new DrawableBanner(R.drawable.nha_nghi_12));
        banners.add(new DrawableBanner(R.drawable.nha_nghi_11));
        banners.add(new DrawableBanner(R.drawable.nha_nghi_12));
        banners.add(new DrawableBanner(R.drawable.nha_nghi_11));
        banner_slider.setBanners(banners);

        // set danh sách nhà nghỉ nổi bật
        showProgressBar();
        hotelHighLightPresenter.getHotel(AppDef.TOKEN_DEV);
//        danhSachNoiBat.add(new HotelHightLight());
//        danhSachNoiBat.add(new HotelHightLight());
//        danhSachNoiBat.add(new HotelHightLight());
//        danhSachNoiBat.add(new HotelHightLight());
//        danhSachNoiBat.add(new HotelHightLight());
//        danhSachNoiBatAdapter = new DanhSachNoiBatAdapter(getActivity(), danhSachNoiBat);
//        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        lvDanhSachNoiBat.setLayoutManager(HorizontalLayout);
//        lvDanhSachNoiBat.setAdapter(danhSachNoiBatAdapter);



    }


    @OnClick({R.id.btnTimNhaNghi, R.id.btnTimNhaTro, R.id.btnTimQuanKaraoke, R.id.viewDangNhap})
    void submitButton(View view) {
        switch (view.getId()){
            case R.id.btnTimNhaNghi:
                Intent intent = new Intent(getActivity(), TimNhaNghiActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTimNhaTro:
                dilogThongBao("Thông báo", "Chức năng này đang được xây dựng!", "Đóng");
                break;
            case R.id.btnTimQuanKaraoke:
                dilogThongBao("Thông báo", "Chức năng này đang được xây dựng!", "Đóng");
                break;
            case R.id.viewDangNhap:
                startActivity(new Intent(getActivity(), UuDaiActivity.class));
                break;
        }
    }

    @Override
    public void onLoadTokenDev(String tokenDev) {

    }

    @Override
    public void onHotelHightLightSuccess(HotelHightLightResponse response) {
        hideProgressBar();
        if(response.getHotelList()!= null && response.getHotelList().size() > 0){
            danhSachNoiBat.addAll(response.getHotelList());
            danhSachNoiBatAdapter = new DanhSachNoiBatAdapter(getActivity(), danhSachNoiBat);
            HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            lvDanhSachNoiBat.setLayoutManager(HorizontalLayout);
            lvDanhSachNoiBat.setAdapter(danhSachNoiBatAdapter);
        }

    }

    @Override
    public void onHotelHightLightFailed(String message) {
        hideProgressBar();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHotelHightLightError(Throwable e) {
        hideProgressBar();
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }
}
