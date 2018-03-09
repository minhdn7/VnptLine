package com.vnpt.vnptline.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.user.DetailHistoryRequest;
import com.vnpt.vnptline.domain.model.pojo.response.user.DetailHistoryResponse;
import com.vnpt.vnptline.domain.model.pojo.response.user.ItemHistoryBookingDetailResponse;
import com.vnpt.vnptline.ui.presenter.user.DetailHistoryPresenter;
import com.vnpt.vnptline.ui.view.user.DetailHistoryView;
import com.willy.ratingbar.ScaleRatingBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailHistoryActivity extends BaseActivity implements DetailHistoryView {


    @Inject
    DetailHistoryPresenter detailHistoryPresenter;
    @BindView(R.id.imgAnhDaiDien)
    ImageView imgAnhDaiDien;
    @BindView(R.id.txtTenNhaNghi)
    TextView txtTenNhaNghi;
    @BindView(R.id.txtDiaChi)
    TextView txtDiaChi;
    @BindView(R.id.txtRoomType)
    TextView txtRoomType;
    @BindView(R.id.txtCodeBooking)
    TextView txtCodeBooking;
    @BindView(R.id.txtNumberBooking)
    TextView txtNumberBooking;
    @BindView(R.id.txtDateBooking)
    TextView txtDateBooking;
    @BindView(R.id.txtMaHoaDon)
    TextView txtMaHoaDon;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.txtTongTien)
    TextView txtTongTien;
    @BindView(R.id.txtNgayThanhToan)
    TextView txtNgayThanhToan;
    @BindView(R.id.txtHinhThucThanhToan)
    TextView txtHinhThucThanhToan;
    @BindView(R.id.viewDanhSachPhong)
    LinearLayout viewDanhSachPhong;
    @BindView(R.id.simpleRatingBar)
    ScaleRatingBar simpleRatingBar;
    @BindView(R.id.txtNgayBinhLuan)
    TextView txtNgayBinhLuan;
    @BindView(R.id.txtTieuDeBinhLuan)
    TextView txtTieuDeBinhLuan;
    @BindView(R.id.txtNoiDungBinhLuan)
    TextView txtNoiDungBinhLuan;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.txtTittleNhaNghi)
    TextView txtTittleNhaNghi;
    @BindView(R.id.icBack)
    ImageView icBack;

    private Integer idBooking = 0;
    private String hotelAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        ButterKnife.bind(this);
        setTitle(getString(R.string.title_activity_history));
        addControls();
        addEvents();
    }

    private void addEvents() {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (scrollView != null) {
                    if (scrollView.getChildAt(0).getTop() >= scrollView.getScrollY()) {
                        txtTittleNhaNghi.setVisibility(View.GONE);
                        icBack.setVisibility(View.GONE);

                        //scroll view is at top
                    } else {
                        //scroll view is not at top
                        txtTittleNhaNghi.setVisibility(View.VISIBLE);
                        icBack.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void addControls() {
        detailHistoryPresenter.setView(this);
        detailHistoryPresenter.onViewCreate();
        idBooking = getIntent().getIntExtra("BOOKING_ID", 0);
        hotelAddress = getIntent().getStringExtra("HOTEL_ADDRESS");
        showProgressBar();
        DetailHistoryRequest detailHistoryRequest = new DetailHistoryRequest(idBooking, tinyDB.getInt(AppDef.USER_ID));
        detailHistoryPresenter.getDetailHistory(tinyDB.getString(AppDef.TOKEN_USER), detailHistoryRequest);
    }


    @OnClick({R.id.icBack})
    void submitButton(View view) {
        if (view.getId() == R.id.icBack) {
            onBackPressed();
        }
    }

    @Override
    public void onDetailHistorySuccess(DetailHistoryResponse response) {
        hideProgressBar();

        // fill data

        // set image
        try {
            if(response.getItemHistoryBookingDetailResponse().getPictures() != null){
                Picasso.with(this)
                        .load(response.getItemHistoryBookingDetailResponse().getPictures())
                        .fit()
                        .error(R.drawable.nha_nghi_1)
                        .into(imgAnhDaiDien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // end
        txtTenNhaNghi.setText(response.getItemHistoryBookingDetailResponse().getHotelName());
        txtDiaChi.setText(hotelAddress);
        txtRoomType.setText(response.getItemHistoryBookingDetailResponse().getRoomType());
        // thông tin đặt phòng

        try {
            txtCodeBooking.setText(String.valueOf(response.getItemHistoryBookingDetailResponse().getBookingId()));
            txtNumberBooking.setText(response.getItemHistoryBookingDetailResponse().getAmount());
            txtDateBooking.setText(response.getItemHistoryBookingDetailResponse().getBookingDate());
            txtStatus.setText(response.getItemHistoryBookingDetailResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // end

        // thông tin thanh toán
        try {
            txtMaHoaDon.setText(response.getItemHistoryBookingDetailResponse().getInvoiceNo());
            txtTongTien.setText(String.valueOf(response.getItemHistoryBookingDetailResponse().getPrice()));
            txtNgayThanhToan.setText(response.getItemHistoryBookingDetailResponse().getInvoiceDate());
            txtHinhThucThanhToan.setText(response.getItemHistoryBookingDetailResponse().getPaymentType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // end
        // đánh giá

        try {
            txtNoiDungBinhLuan.setText(response.getItemHistoryBookingDetailResponse().getComment());
            txtNgayBinhLuan.setText(response.getItemHistoryBookingDetailResponse().getRatingDate());
            simpleRatingBar.setRating(response.getItemHistoryBookingDetailResponse().getRate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // end
        // end
    }

    @Override
    public void onDetailHistoryFailed(String message) {
        hideProgressBar();
        dilogThongBao(getString(R.string.thongBao), message, getString(R.string.tiepTuc));
    }

    @Override
    public void onDetailHistoryError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
