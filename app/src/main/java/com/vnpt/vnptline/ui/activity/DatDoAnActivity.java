package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.service.FindServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.request.service.OrderServicRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;

import com.vnpt.vnptline.domain.model.pojo.response.service.FindServicesResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.ServiceDetailResponse;
import com.vnpt.vnptline.ui.adapter.DanhSachDoAnAdapter;
import com.vnpt.vnptline.ui.presenter.services.FindServicePresenter;
import com.vnpt.vnptline.ui.presenter.services.OrderServicePresenter;

import com.vnpt.vnptline.ui.view.services.FindServicesView;
import com.vnpt.vnptline.ui.view.services.OrderServicesView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

public class DatDoAnActivity extends BaseActivity implements FindServicesView, OrderServicesView{
    @BindView(R.id.lvDanhSachDoAnUong) ListView lvDanhSachDoAnUong;
    @BindView(R.id.spLoaiDichVu) Spinner spLoaiDichVu;
    private DanhSachDoAnAdapter danhSachDoAnAdapter;
    private List<ServiceDetailResponse> danhSachDoAn = new ArrayList<>();
    private List<String> loaiDoAn = new ArrayList<>();
    private Integer iTongTien = 0;
    private Integer bookingDetailId = 0;
    @Inject
    FindServicePresenter findServicePresenter;
    @Inject
    OrderServicePresenter orderServicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_do_an);
        setTitle(getString(R.string.title_activity_food));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    private void addControls() {
        findServicePresenter.setView(this);
        findServicePresenter.onViewCreate();
        orderServicePresenter.setView(this);
        orderServicePresenter.onViewCreate();
        //
        loaiDoAn.add("Tất cả");
//        loaiDoAn.add("Đồ uống");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, loaiDoAn);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spLoaiDichVu.setAdapter(spinnerArrayAdapter);
        //
        showProgressBar();
        FindServiceRequest findServiceRequest = new FindServiceRequest(getIntent().getIntExtra("ID_HOTEL", 0), "");
        bookingDetailId = getIntent().getIntExtra("BOOKING_DETAIL_ID", 0);
        findServicePresenter.findService(tinyDB.getString(AppDef.TOKEN_USER), findServiceRequest);
    }

    public void dilogDatDoAn(String title, String donGia, final Integer iDonGia, final Integer serviceId){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_dat_do_an, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        TextView txtTitle = (TextView) b.findViewById(R.id.txtTitle);
        final TextView txtGia = (TextView) b.findViewById(R.id.txtGia);
        final TextView txtTongTien = (TextView) b.findViewById(R.id.txtTongTien);
        final EditText txtSoLuong = (EditText) b.findViewById(R.id.txtSoLuong);
        Button btnDat = (Button) b.findViewById(R.id.btnDat);
        Button btnHuy = (Button) b.findViewById(R.id.btnHuy);

        txtTitle.setText(title);
        txtGia.setText(donGia);
        txtTongTien.setText(donGia);
        txtSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!txtSoLuong.getText().toString().trim().equals("")){
                    Integer iSoLuong = Integer.parseInt(txtSoLuong.getText().toString().trim());
                    iTongTien = iSoLuong * iDonGia;
                    Locale.setDefault(Locale.US);
                    String formattedNumber = String.format("%,d", iTongTien) + DatDoAnActivity.this.getString(R.string.donViTien);
                    txtTongTien.setText(formattedNumber);
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSoLuong.getText().toString().trim().equals("")){
                    txtSoLuong.setError(getString(R.string.khongDeTrong));
                }else {
                    showProgressBar();
                    OrderServicRequest orderServicRequest = new OrderServicRequest();
                    orderServicRequest.setUserId(tinyDB.getInt(AppDef.USER_ID));
                    orderServicRequest.setBookingDetailId(bookingDetailId);
                    orderServicRequest.setServiceId(serviceId);
                    orderServicRequest.setCount(Integer.parseInt(txtSoLuong.getText().toString().trim()));
                    orderServicePresenter.orderService(tinyDB.getString(AppDef.TOKEN_USER), orderServicRequest);
                    b.dismiss();
                }

            }
        });
    }


    @Override
    public void onFindServiceSuccess(FindServicesResponse response) {
        try {
            hideProgressBar();
            if(response.getServices() != null && response.getServices().size() > 0){
                danhSachDoAn.addAll(response.getServices());
                danhSachDoAnAdapter = new DanhSachDoAnAdapter(this, R.layout.item_do_an, danhSachDoAn, this);
                lvDanhSachDoAnUong.setAdapter(danhSachDoAnAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFindServiceFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onFindServiceError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOrderServicesSuccess(CommonResponse response) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), response.getResponseMessage(), getString(R.string.tiepTuc));
    }

    @Override
    public void onOrderServicesFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onOrderServicesError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
