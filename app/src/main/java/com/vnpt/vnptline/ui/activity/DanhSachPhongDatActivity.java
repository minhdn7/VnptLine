package com.vnpt.vnptline.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;

import com.vnpt.vnptline.domain.model.pojo.response.service.FindRoomBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.service.RoomBookingDetail;
import com.vnpt.vnptline.ui.adapter.DanhSachPhongDatAdapter;
import com.vnpt.vnptline.ui.presenter.hotel.FindRoomBookingPresenter;
import com.vnpt.vnptline.ui.view.hotel.FindBookingRoomView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DanhSachPhongDatActivity extends BaseActivity implements FindBookingRoomView {
    @BindView(R.id.lvDanhSachPhongDat)
    ListView lvDanhSachPhongDat;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    private DanhSachPhongDatAdapter danhSachPhongDatAdapter;
    private List<RoomBookingDetail> danhSachPhongDat = new ArrayList<>();
    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    boolean loadingMore = false;
    boolean isFirstLoad = true;

    @Inject
    FindRoomBookingPresenter findRoomBookingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phong_dat);
        ButterKnife.bind(this);
        setTitle(getString(R.string.title_activity_chon_phong));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvDanhSachPhongDat.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if ((lastInScreen == totalItemCount) && loadingMore) {
                    // load more
                    showProgressBar();
                    pageIndex += 1;
                }
            }
        });
    }

    private void addControls() {
        findRoomBookingPresenter.setView(this);
        findRoomBookingPresenter.onViewCreate();
        showProgressBar();
        findRoomBookingPresenter.getRoom(tinyDB.getString(AppDef.TOKEN_USER), tinyDB.getInt(AppDef.USER_ID));

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
    public void onFindBookingRoomSuccess(FindRoomBookingResponse response) {
        hideProgressBar();
        if (response.getRooms() != null && response.getRooms().size() > 0) {
            txtNoData.setVisibility(View.GONE);
            lvDanhSachPhongDat.setVisibility(View.VISIBLE);
            danhSachPhongDat.addAll(response.getRooms());
            if (danhSachPhongDatAdapter != null) {
                danhSachPhongDatAdapter.notifyDataSetChanged();
            } else {
                danhSachPhongDatAdapter = new DanhSachPhongDatAdapter(this, R.layout.item_danh_sach_chon_phong, danhSachPhongDat);
                lvDanhSachPhongDat.setAdapter(danhSachPhongDatAdapter);
            }

            if (response.getRooms().size() < pageSize) {
                loadingMore = false;
            } else {
                loadingMore = true;
            }

        } else {
            if (isFirstLoad) {
                txtNoData.setVisibility(View.VISIBLE);
                lvDanhSachPhongDat.setVisibility(View.GONE);
                loadingMore = false;
            }


        }
        isFirstLoad = false;
    }

    @Override
    public void onFindBookingRoomFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onFindBookingRoomError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
