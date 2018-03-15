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
import com.vnpt.vnptline.domain.model.pojo.request.hotel.FindCommentHotelRequest;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.FindCommentHotelResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.FindCommentRating;
import com.vnpt.vnptline.ui.adapter.DanhSachBinhLuanAdapter;
import com.vnpt.vnptline.ui.presenter.hotel.FindCommentPresenter;
import com.vnpt.vnptline.ui.view.hotel.FindCommentHotelView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BinhLuanActivity extends BaseActivity implements FindCommentHotelView {
    @BindView(R.id.lvDanhSachBinhLuan)
    ListView lvDanhSachBinhLuan;
    @BindView(R.id.txtNoComment)
    LinearLayout txtNoComment;
    private DanhSachBinhLuanAdapter danhSachBinhLuanAdapter;
    private Integer idNhaNghi = 0;
    private List<FindCommentRating> danhSachBinhLuanResponses = new ArrayList<>();

    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    boolean loadingMore = false;
    boolean isFirstLoad = true;

    @Inject
    FindCommentPresenter findCommentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_binhluan));
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvDanhSachBinhLuan.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if ((lastInScreen == totalItemCount) && loadingMore) {
                    // load more
                    showProgressBar();
                    pageIndex += 1;
                    FindCommentHotelRequest findCommentRequest = new FindCommentHotelRequest(idNhaNghi, pageIndex, pageSize);
                    findCommentPresenter.findCommentHotel(AppDef.TOKEN_DEV, findCommentRequest);
                }
            }
        });
    }

    private void addControls() {
        findCommentPresenter.setView(this);
        findCommentPresenter.onViewCreate();
        idNhaNghi = getIntent().getIntExtra("ID_HOTEL", 0);
        showProgressBar();
        FindCommentHotelRequest findCommentRequest = new FindCommentHotelRequest(idNhaNghi, pageIndex, pageSize);
        findCommentPresenter.findCommentHotel(AppDef.TOKEN_DEV, findCommentRequest);
    }

    private void addDanhSachBinhLuan() {
//        danhSachBinhLuanResponses.add(new DanhSachBinhLuanResponse());
//        danhSachBinhLuanResponses.add(new DanhSachBinhLuanResponse());
//        danhSachBinhLuanResponses.add(new DanhSachBinhLuanResponse());
        danhSachBinhLuanAdapter = new DanhSachBinhLuanAdapter(this, R.layout.item_binh_luan, danhSachBinhLuanResponses);
        lvDanhSachBinhLuan.setAdapter(danhSachBinhLuanAdapter);
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
    public void onFindCommentHotelSuccess(FindCommentHotelResponse response) {
        hideProgressBar();
        if (response.getRatings() != null && response.getRatings().size() > 0) {
            txtNoComment.setVisibility(View.GONE);
            danhSachBinhLuanResponses.addAll(response.getRatings());
            if (danhSachBinhLuanAdapter != null) {
                danhSachBinhLuanAdapter.notifyDataSetChanged();
            } else {
                danhSachBinhLuanAdapter = new DanhSachBinhLuanAdapter(this, R.layout.item_binh_luan, danhSachBinhLuanResponses);
                lvDanhSachBinhLuan.setAdapter(danhSachBinhLuanAdapter);
            }

            if (response.getRatings().size() < pageSize) {
                loadingMore = false;
            } else {
                loadingMore = true;
            }
        } else {
            if(isFirstLoad){
                txtNoComment.setVisibility(View.VISIBLE);
                loadingMore = false;
            }
        }
        isFirstLoad = false;
    }

    @Override
    public void onFindCommentHotelFailed(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFindCommentHotelError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
