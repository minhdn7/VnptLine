package com.vnpt.vnptline.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;

import com.vnpt.vnptline.domain.model.pojo.request.user.HistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryResponse;
import com.vnpt.vnptline.ui.adapter.DanhSachLichSuAdapter;

import com.vnpt.vnptline.ui.presenter.user.HistoryPresenter;
import com.vnpt.vnptline.ui.view.user.HistoryView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class LichSuActivity extends BaseActivity implements HistoryView{

    @BindView(R.id.viewNoHistory) LinearLayout viewNoHistory;
    @BindView(R.id.lvDanhSachLichSu) ListView lvDanhSachLichSu;
    private List<HistoryBookingResponse> danhSachLichSu = new ArrayList<>();
    private DanhSachLichSuAdapter danhSachLichSuAdapter;
    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    boolean loadingMore = false;
    boolean isFirstLoad = true;

    @Inject HistoryPresenter historyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);
        setTitle(getString(R.string.title_activity_history));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvDanhSachLichSu.setOnScrollListener(new AbsListView.OnScrollListener() {
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
                    HistoryRequest request = new HistoryRequest(pageIndex, pageSize, tinyDB.getInt(AppDef.USER_ID));
                    historyPresenter.getHistory(tinyDB.getString(AppDef.TOKEN_USER), request);
                }
            }
        });
    }

    private void addControls() {
        historyPresenter.setView(this);
        historyPresenter.onViewCreate();
        showProgressBar();
        HistoryRequest request = new HistoryRequest(pageIndex, pageSize, tinyDB.getInt(AppDef.USER_ID));
        historyPresenter.getHistory(tinyDB.getString(AppDef.TOKEN_USER), request);
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
    public void onHistorySuccess(HistoryResponse response) {
        hideProgressBar();
        if(response.getBookings() != null && response.getBookings().size() > 0){
            viewNoHistory.setVisibility(View.GONE);
            lvDanhSachLichSu.setVisibility(View.VISIBLE);
            danhSachLichSu.addAll(response.getBookings());
            if(danhSachLichSuAdapter != null){
                danhSachLichSuAdapter.notifyDataSetChanged();
            }else {
                danhSachLichSuAdapter = new DanhSachLichSuAdapter(this, R.layout.item_lich_su, danhSachLichSu);
                lvDanhSachLichSu.setAdapter(danhSachLichSuAdapter);
            }

            if(response.getBookings().size() < pageSize){
                loadingMore = false;
            }else {
                loadingMore = true;
            }

        } else {
            if(isFirstLoad){
                viewNoHistory.setVisibility(View.VISIBLE);
                lvDanhSachLichSu.setVisibility(View.GONE);
                loadingMore = false;
            }


        }
        isFirstLoad = false;
    }

    @Override
    public void onHistoryFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onHistoryError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
