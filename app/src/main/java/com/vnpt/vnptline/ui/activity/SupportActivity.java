package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.service.SupportServiceRequest;
import com.vnpt.vnptline.domain.model.pojo.request.user.RegisterUserRequest;
import com.vnpt.vnptline.domain.model.pojo.response.CommonResponse;
import com.vnpt.vnptline.ui.presenter.services.FindServicePresenter;
import com.vnpt.vnptline.ui.presenter.services.SupportServicePresenter;
import com.vnpt.vnptline.ui.view.services.SupportServicesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupportActivity extends BaseActivity implements SupportServicesView{

    @BindView(R.id.spNguoiGui)
    Spinner spNguoiGui;

    @BindView(R.id.txtTieuDe)
    @NotEmpty(message = "Tiêu đề không được để trống")
    EditText txtTieuDe;

    @BindView(R.id.txtNoiDung)
    @NotEmpty(message = "Nội dung không được để trống")
    EditText txtNoiDung;
    private List<String> doiTuong = new ArrayList<>();

    private Integer bookingDetailId = 0;
    @Inject
    SupportServicePresenter supportServicePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        ButterKnife.bind(this);
        setTitle(getString(R.string.title_activity_support));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls();
    }

    private void addControls() {
        supportServicePresenter.setView(this);
        supportServicePresenter.onViewCreate();

        bookingDetailId = getIntent().getIntExtra("BOOKING_DETAIL_ID", 0);
        doiTuong.add(getString(R.string.chuNhaNghi));
        doiTuong.add(getString(R.string.leTan));
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, doiTuong);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spNguoiGui.setAdapter(spinnerArrayAdapter);
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


    @OnClick({R.id.btnGui})
    public void submit(View view) {
        if(view.getId() == R.id.btnGui){
            validator.validate();
            if(isPassedValidate){
                showProgressBar();
                SupportServiceRequest supportServiceRequest = new SupportServiceRequest();
                supportServiceRequest.setBookingDetailId(bookingDetailId);
                supportServiceRequest.setUserId(tinyDB.getInt(AppDef.USER_ID));
                supportServiceRequest.setType("");
                supportServiceRequest.setName(txtTieuDe.getText().toString().trim());
                supportServiceRequest.setDescription(txtNoiDung.getText().toString().trim());
                supportServicePresenter.supportService(tinyDB.getString(AppDef.TOKEN_USER), supportServiceRequest);
            }
        }
    }

    @Override
    public void onSupportServicesSuccess(CommonResponse response) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), response.getResponseMessage(), getString(R.string.tiepTuc));
    }

    @Override
    public void onSupportServicesFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onSupportServicesError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
