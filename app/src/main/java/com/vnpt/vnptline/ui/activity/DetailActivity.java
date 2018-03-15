package com.vnpt.vnptline.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.BookingRoomRequest;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.BookingRoomResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ChiTietNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.ListRoomBookingResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.RoomHightLight;
import com.vnpt.vnptline.ui.adapter.DanhSachPhongAdapter;
import com.vnpt.vnptline.ui.adapter.PhongNoiBatAdapter;
import com.vnpt.vnptline.ui.presenter.hotel.BookingRoomPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.DetailHotelPresenter;
import com.vnpt.vnptline.ui.presenter.hotel.RoomHotelPresenter;
import com.vnpt.vnptline.ui.view.hotel.BookingRoomView;
import com.vnpt.vnptline.ui.view.hotel.DetailHotelView;
import com.vnpt.vnptline.ui.view.hotel.RoomHotelView;
import com.willy.ratingbar.ScaleRatingBar;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements DetailHotelView, RoomHotelView, BookingRoomView {
    @BindView(R.id.txtTenNhaNghi)
    TextView txtTenNhaNghi;
    @BindView(R.id.btnLoaiHinh)
    Button btnLoaiHinh;
    @BindView(R.id.txtDiaChiNhaNghi)
    TextView txtDiaChiNhaNghi;
    @BindView(R.id.txtXemChiTiet)
    TextView txtXemChiTiet;

    @BindView(R.id.txtMieuTaChiTiet)
    TextView txtMieuTaChiTiet;
    @BindView(R.id.imgXemChiTiet)
    ImageView imgXemChiTiet;
    @BindView(R.id.simpleRatingBar)
    ScaleRatingBar simpleRatingBar;

    @BindView(R.id.viewDanhSachPhong)
    LinearLayout viewDanhSachPhong;
    @BindView(R.id.lvDanhSachPhong)
    ListView lvDanhSachPhong;
    @BindView(R.id.btnChon)
    Button btnChon;
    @BindView(R.id.txtTittleNhaNghi)
    TextView txtTittleNhaNghi;
    @BindView(R.id.txtGiaPhongMoiDem)
    TextView txtGiaPhongMoiDem;
    @BindView(R.id.icBack)
    ImageView icBack;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.imgAnhDaiDien)
    ImageView imgAnhDaiDien;
    @BindView(R.id.viewBinhLuan)
    LinearLayout viewBinhLuan;
    @BindView(R.id.viewXemChiTiet)
    LinearLayout viewXemChiTiet;
    @BindView(R.id.viewChonDanhSachPhong)
    LinearLayout viewChonDanhSachPhong;
    @BindView(R.id.lvDanhSachPhongNoiBat)
    RecyclerView lvDanhSachPhongNoiBat;
    @BindView(R.id.imgGoogleStaticMap)
    ImageView imgGoogleStaticMap;
    @BindView(R.id.btnXemBanDo)
    Button btnXemBanDo;

    private DanhSachPhongAdapter danhSachPhongAdapter;
    private List<ListRoomBookingResponse> danhSachPhongResponses = new ArrayList<>();
    boolean isXemChiTietClicked = false;
    private Integer idNhaNghi = 0;
    private String sPriceHourHotel = "";
    private AlertDialog dialogBooking;
    private List<RoomHightLight> danhSachPhongNoiBat = new ArrayList<>();
    LinearLayoutManager HorizontalLayout;
    private PhongNoiBatAdapter phongNoiBatAdapter;
    // get tọa độ
    private double latitude = 0;
    private double longitude = 0;
    // set up map

    // end
    @Inject
    DetailHotelPresenter detailHotelPresenter;
    @Inject
    RoomHotelPresenter roomHotelPresenter;
    @Inject
    BookingRoomPresenter bookingRoomPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        addControls();
        addEvents();
        loadMapImage(this, 21.028511, 105.804817);
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


    private void addEvents() {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (scrollView != null) {
                    if (scrollView.getChildAt(0).getTop() >= scrollView.getScrollY()) {
                        icBack.setVisibility(View.VISIBLE);
                        getSupportActionBar().hide();
                        //scroll view is at top
                    } else {
                        //scroll view is not at top
                        getSupportActionBar().show();
                        icBack.setVisibility(View.GONE);
                    }

                    // We take the last son in the scrollview
                    View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
                    int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                    // if diff is zero, then the bottom has been reached
                    if (diff == 0) {
                        // do stuff
                        viewChonDanhSachPhong.setVisibility(View.GONE);
                    } else {
                        viewChonDanhSachPhong.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }

    private void addControls() {
        detailHotelPresenter.setView(this);
        detailHotelPresenter.onViewCreate();
        roomHotelPresenter.setView(this);
        roomHotelPresenter.onViewCreate();
        bookingRoomPresenter.setView(this);
        bookingRoomPresenter.onViewCreate();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idNhaNghi = extras.getInt("ID_HOTEL");
            sPriceHourHotel = extras.getString("PRICE_HOUR_HOTEL");
            detailHotelPresenter.getHotel(AppDef.TOKEN_DEV, idNhaNghi);
            roomHotelPresenter.getRoom(AppDef.TOKEN_DEV, idNhaNghi);
            showProgressBar();
        }

        danhSachPhongNoiBat.add(new RoomHightLight(R.drawable.nha_nghi_1));
        danhSachPhongNoiBat.add(new RoomHightLight(R.drawable.nha_nghi_2));
        danhSachPhongNoiBat.add(new RoomHightLight(R.drawable.nha_nghi_3));
        danhSachPhongNoiBat.add(new RoomHightLight(R.drawable.nha_nghi_4));
        danhSachPhongNoiBat.add(new RoomHightLight(R.drawable.nha_nghi_5));
        danhSachPhongNoiBat.add(new RoomHightLight(R.drawable.nha_nghi_6));
        phongNoiBatAdapter = new PhongNoiBatAdapter(this, danhSachPhongNoiBat);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lvDanhSachPhongNoiBat.setLayoutManager(HorizontalLayout);
        lvDanhSachPhongNoiBat.setAdapter(phongNoiBatAdapter);
    }

    @OnClick({R.id.btnChon, R.id.viewBinhLuan, R.id.viewXemChiTiet, R.id.icBack, R.id.btnXemBanDo})
    void submitButton(View view) {
        switch (view.getId()) {
            case R.id.btnXemBanDo:

                break;
            case R.id.btnChon:
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.viewBinhLuan:
                Intent intent = new Intent(this, BinhLuanActivity.class);
                intent.putExtra("ID_HOTEL", idNhaNghi);
                startActivity(intent);
                break;
            case R.id.viewXemChiTiet:
                if (isXemChiTietClicked) {
                    //This will shrink textview to 2 lines if it is expanded.
                    txtMieuTaChiTiet.setMaxLines(3);
                    imgXemChiTiet.setRotation(0);
                    txtXemChiTiet.setText(R.string.xemChiTiet);
                    isXemChiTietClicked = false;
                } else {
                    //This will expand the textview if it is of 2 lines
                    txtMieuTaChiTiet.setMaxLines(Integer.MAX_VALUE);
                    imgXemChiTiet.setRotation(180);
                    txtXemChiTiet.setText(R.string.thuGon);
                    isXemChiTietClicked = true;
                }
                break;

            case R.id.icBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onDetailHotelSuccess(final ChiTietNhaNghiResponse response) {
        try {
            hideProgressBar();
            txtTenNhaNghi.setText(response.getHotelName());
            DetailActivity.this.setTitle(response.getHotelName());
            btnLoaiHinh.setText(response.getHotelType());
            txtTittleNhaNghi.setText(response.getHotelName());
            txtDiaChiNhaNghi.setText(response.getCity());
            txtMieuTaChiTiet.setText(response.getDescription());
            simpleRatingBar.setRating(response.getRank());
            txtGiaPhongMoiDem.setText(sPriceHourHotel);
            latitude = response.getLat();
            longitude = response.getLon();
            loadMapImage(this, latitude, longitude);
            btnXemBanDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailActivity.this, MapDetailActivity.class);
                    intent.putExtra("LAT_POSITION", latitude);
                    intent.putExtra("LONG_POSITION", longitude);
                    intent.putExtra("NAME_HOTEL", txtTittleNhaNghi.getText().toString().trim());
                    startActivity(intent);
                }
            });
            imgGoogleStaticMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailActivity.this, MapDetailActivity.class);
                    intent.putExtra("LAT_POSITION", latitude);
                    intent.putExtra("LONG_POSITION", longitude);
                    intent.putExtra("NAME_HOTEL", txtTittleNhaNghi.getText().toString().trim());
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetailHotelFailed(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetailHotelError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRoomHotelSuccess(ListRoomBookingResponse response) {
        if (response.getAvailableRoomTypeList() != null && response.getAvailableRoomTypeList().size() > 0) {
            danhSachPhongAdapter = new DanhSachPhongAdapter(this, R.layout.item_danh_sach_phong, response.getAvailableRoomTypeList());
            lvDanhSachPhong.setAdapter(danhSachPhongAdapter);
            setListViewHeightBasedOnChildren(lvDanhSachPhong);
        }

    }

    @Override
    public void onRoomHotelFailed(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRoomHotelError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    public void bookingRoom(Integer roomTypeId, Integer hotelId) {
        if (tinyDB.getBoolean(AppDef.IS_LOGGIN)) {
            dilogDatPhong(getString(R.string.datPhong), getString(R.string.datPhong), roomTypeId, hotelId);
        } else {
            Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
            intent.putExtra(AppDef.BOOKING_FLOW, 1);
            startActivity(intent);
        }
    }


    public void dilogDatPhong(String title, String sButtton, final Integer roomTypeId, final Integer hotelId) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_dat_phong, null);
        dialogBuilder.setView(dialogView);

        dialogBooking = dialogBuilder.create();
        dialogBooking.setCanceledOnTouchOutside(false);
        dialogBooking.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBooking.show();
        // set width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogBooking.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        width = (int) (width * 0.8);
        lp.width = width;
        lp.gravity = Gravity.CENTER;
        dialogBooking.getWindow().setAttributes(lp);
        //
        TextView txtHeaderDialog = (TextView) dialogView.findViewById(R.id.txtHeaderDialog);
        final TextView txtNgayDat = (TextView) dialogView.findViewById(R.id.txtNgayDat);
        ImageView imgExit = (ImageView) dialogView.findViewById(R.id.imgExit);

        Button btnTiepTuc = (Button) dialogView.findViewById(R.id.btnTiepTuc);
        final EditText txtSoPhong = (EditText) dialogView.findViewById(R.id.txtSoPhong);

        txtHeaderDialog.setText(title);
        btnTiepTuc.setText(sButtton);
        // get current date
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        final String sDate = sdf.format(currentTime);
        txtNgayDat.setText(sDate);
        txtNgayDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                android.app.FragmentManager fragmentManager = getFragmentManager();
//                DateTimePickerFragment dateTimePickerFragment = new DateTimePickerFragment(txtNgayDat);
//                dateTimePickerFragment.show(fragmentManager, "dateTimePickerFragment");
            }
        });
        // end
        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBooking.dismiss();
            }
        });

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                BookingRoomRequest request = new BookingRoomRequest();
                if (!txtSoPhong.getText().toString().trim().equals("")) {
                    Integer iSoPhong = Integer.parseInt(txtSoPhong.getText().toString().trim());
                    request.setAmount(iSoPhong);
                    request.setBookingDate(txtNgayDat.getText().toString().trim());
                    request.setHotelId(hotelId);
                    request.setRoomTypeId(roomTypeId);
                    request.setUserId(tinyDB.getInt(AppDef.USER_ID));
                    request.setCustomerName(tinyDB.getString(AppDef.USER_NAME));
                    bookingRoomPresenter.bookingHotel(tinyDB.getString(AppDef.TOKEN_USER), request);
                    dialogBooking.dismiss();
                } else {
                    txtSoPhong.setError(getString(R.string.errorSoPhong));
                }


            }
        });

    }

    @Override
    public void onBookingRoomSuccess(BookingRoomResponse response) {
        hideProgressBar();
        dilogThongBao("Đặt phòng thành công", "Thông tin đặt phòng đã được gửi đến quản lí nhà nghỉ, bộ phận lễ tân sẽ liên lạc với bạn sớm nhất theo SDT: \n"
                + "- " + response.getCustomerName() + "\n Trân trọng cảm ơn quý khách đã sửu dụng dịch vụ!", getString(R.string.dong));
    }

    @Override
    public void onBookingRoomFailed(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookingRoomError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    public void setTopImage(Integer imageResource) {
        imgAnhDaiDien.setImageResource(imageResource);
    }

    private void loadMapImage(Context context, double latitude, double longitude) {
        StringBuffer sb = new StringBuffer();
        sb.append("https://maps.googleapis.com/maps/api/staticmap?");
        sb.append("&markers=color:%3agreen|");
        sb.append(latitude);
        sb.append(",");
        sb.append(longitude);
        sb.append("&zoom=12");
        sb.append("&size=600x300");
        sb.append("&key=AIzaSyAoDF0slrRXQUIuaI5GXG8E2xX1_xjAOgc&img.jpg");

        Log.i("LOG", "Picasso loading: " + sb.toString());
        Picasso.with(context)
                .load(sb.toString())
                .into(imgGoogleStaticMap);
    }
}
