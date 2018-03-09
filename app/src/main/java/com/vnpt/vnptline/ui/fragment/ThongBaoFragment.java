package com.vnpt.vnptline.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseFragment;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.user.DanhSachThongBaoRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.DanhSachThongBaoResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.ThongBaoResponse;
import com.vnpt.vnptline.ui.adapter.DanhSachThongBaoAdapter;
import com.vnpt.vnptline.ui.presenter.LoginPresenter;
import com.vnpt.vnptline.ui.presenter.user.NotificationPresenter;
import com.vnpt.vnptline.ui.view.user.NotificationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThongBaoFragment extends BaseFragment implements NotificationView{
    @BindView(R.id.viewNotification) LinearLayout viewNotification;
    @BindView(R.id.lvDanhSachThongBao) ListView lvDanhSachThongBao;
    @BindView(R.id.txtTieuDeThongBao) TextView txtTieuDeThongBao;

    private List<ThongBaoResponse> danhSachThongBao = new ArrayList<>();
    private DanhSachThongBaoAdapter danhSachThongBaoAdapter = null;
    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    boolean loadingMore = false;
    boolean isFirstLoad = true;

    @Inject
    NotificationPresenter notificationPresenter;

    public ThongBaoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongBaoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongBaoFragment newInstance(String param1, String param2) {
        ThongBaoFragment fragment = new ThongBaoFragment();
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
        View view = inflater.inflate(R.layout.fragment_thong_bao, container, false);
        ButterKnife.bind(this, view);
        addControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        lvDanhSachThongBao.setOnScrollListener(new AbsListView.OnScrollListener() {
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
                    DanhSachThongBaoRequest request = new DanhSachThongBaoRequest(pageIndex, pageSize, tinyDB.getInt(AppDef.USER_ID));
                    notificationPresenter.getNotification(tinyDB.getString(AppDef.TOKEN_USER), request);
                }
            }
        });
    }

    private void addControls() {
        notificationPresenter.setView(this);
        notificationPresenter.onViewCreate();
        if(tinyDB.getBoolean(AppDef.IS_LOGGIN)){
            showProgressBar();
            pageIndex = 0;
            pageSize = 10;
            danhSachThongBao = new ArrayList<>();
            DanhSachThongBaoRequest request = new DanhSachThongBaoRequest(pageIndex, pageSize, tinyDB.getInt(AppDef.USER_ID));
            notificationPresenter.getNotification(tinyDB.getString(AppDef.TOKEN_USER), request);
        }else {
            viewNotification.setVisibility(View.VISIBLE);
            lvDanhSachThongBao.setVisibility(View.GONE);
            txtTieuDeThongBao.setText(getString(R.string.canDangNhap));
        }

    }


    @Override
    public void onNotificationSuccess(DanhSachThongBaoResponse response) {
        hideProgressBar();
        if(response.getNotifications() != null && response.getNotifications().size() > 0){
            viewNotification.setVisibility(View.GONE);
            lvDanhSachThongBao.setVisibility(View.VISIBLE);
            danhSachThongBao.addAll(response.getNotifications());
            if(danhSachThongBaoAdapter != null){
                danhSachThongBaoAdapter.notifyDataSetChanged();
                lvDanhSachThongBao.setAdapter(danhSachThongBaoAdapter);
            }else {
                danhSachThongBaoAdapter = new DanhSachThongBaoAdapter(getActivity(), R.layout.item_thong_bao, danhSachThongBao);
                lvDanhSachThongBao.setAdapter(danhSachThongBaoAdapter);
            }


            if(response.getNotifications().size() < pageSize){
                loadingMore = false;
            }else {
                loadingMore = true;
            }

        } else {
            if(isFirstLoad){
                viewNotification.setVisibility(View.VISIBLE);
                lvDanhSachThongBao.setVisibility(View.GONE);
                loadingMore = false;
            }


        }
        isFirstLoad = false;

    }

    @Override
    public void onNotificationFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onNotificationError(Throwable e) {
        hideProgressBar();
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadTokenDev(String tokenDev) {

    }
}
