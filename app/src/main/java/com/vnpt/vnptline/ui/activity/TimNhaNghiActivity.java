package com.vnpt.vnptline.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.jaygoo.widget.RangeSeekBar;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.app.utils.LocationUtils.PermissionUtils;
import com.vnpt.vnptline.domain.model.pojo.request.hotel.SearchHotelRequest;
import com.vnpt.vnptline.domain.model.pojo.response.GeoSearchResult;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelResponse;
import com.vnpt.vnptline.ui.adapter.GeoAutoCompleteAdapter;
import com.vnpt.vnptline.ui.presenter.hotel.SearchHotelPresenter;
import com.vnpt.vnptline.ui.view.hotel.SearchHotelView;
import com.vnpt.vnptline.ui.widget.DelayAutoCompleteTextView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimNhaNghiActivity extends BaseActivity implements SearchHotelView, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback,
        PermissionUtils.PermissionResultCallback {

    @BindView(R.id.txtLocationAddress)
    DelayAutoCompleteTextView txtLocationAddress;
    @BindView(R.id.img1Star)
    ImageView img1Star;
    @BindView(R.id.img2Star)
    ImageView img2Star;
    @BindView(R.id.img3Star)
    ImageView img3Star;
    @BindView(R.id.img4Star)
    ImageView img4Star;
    @BindView(R.id.img5Star)
    ImageView img5Star;
    @BindView(R.id.view1Star)
    LinearLayout view1Star;
    @BindView(R.id.view2Star)
    LinearLayout view2Star;
    @BindView(R.id.view3Star)
    LinearLayout view3Star;
    @BindView(R.id.view4Star)
    LinearLayout view4Star;
    @BindView(R.id.view5Star)
    LinearLayout view5Star;
    @BindView(R.id.ranglelideBar)
    RangeSeekBar ranglelideBar;
    @BindView(R.id.txtGiaMin)
    TextView txtGiaMin;
    @BindView(R.id.txtGiaMax)
    TextView txtGiaMax;
    @BindView(R.id.rd4Star)
    RadioButton rd4Star;
    @BindView(R.id.rd3Star)
    RadioButton rd3Star;
    @BindView(R.id.rd2Star)
    RadioButton rd2Star;
    @BindView(R.id.rd1Star)
    RadioButton rd1Star;
    @BindView(R.id.rdNoStar)
    RadioButton rdNoStar;
    @BindView(R.id.imgChangeLocation)
    ImageView imgChangeLocation;
    @BindView(R.id.txtKhoangCach)
    EditText txtKhoangCach;
    @BindView(R.id.radioGroup_character)
    RadioGroup radioGroupCharacter;

    private Integer rate = 0;
    private boolean[] bStatusStar = {false, false, false, false, false};
    private float minValues = 0;
    private float maxValues = 1000000;
    private LatLng searchLocationLatLong = null;
    private long dMinPrice = 0;
    private long dMaxPrice = 1000000;
    // page size, page index
    private Integer pageSize = 20;
    private Integer pageIndex = 0;
    // location
    // LogCat tag
    private static final String TAG = "MyLocationUsingHelper";

    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;

    private Location mLastLocation;

    // Google client to interact with Google API

    private GoogleApiClient mGoogleApiClient;

    double latitude = 0;
    double longitude = 0;

    // list of permissions

    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;

    boolean isPermissionGranted;
    // end

    @Inject
    SearchHotelPresenter searchHotelPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_nha_nghi);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_find_hotel));
        addControls();
        addEvents();
        addGooglePermission();
        findAddress();
    }

    private void addGooglePermission() {
        permissionUtils = new PermissionUtils(this);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionUtils.check_permission(permissions, "Need GPS permission for getting your location", 1);

        // check availability of play services
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
        }

    }

    private void addEvents() {
        ranglelideBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                ranglelideBar.setRules(minValues, maxValues, 0, 1);

                dMinPrice = ((long) min / 10000) * 10000;
                dMaxPrice = ((long) max / 10000) * 10000;
                DecimalFormat formatter = new DecimalFormat("#,###,###");
                String formatMin = formatter.format(dMinPrice);
                String formatMax = formatter.format(dMaxPrice);
                String giaMin = "VND " + formatMin;
                String giaMax = "VND " + formatMax;
                txtGiaMin.setText(giaMin);
                txtGiaMax.setText(giaMax);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        // end
    }

    private void addControls() {
        searchHotelPresenter.setView(this);
        searchHotelPresenter.onViewCreate();
        txtKhoangCach.getBackground().setColorFilter(getResources().getColor(R.color.md_blue_700), PorterDuff.Mode.SRC_IN);
        // google api
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();


        // end
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

    @OnClick({R.id.btnTimKiem, R.id.imgChangeLocation, R.id.rd4Star, R.id.rd3Star, R.id.rd2Star, R.id.rd1Star, R.id.rdNoStar})
    void submitButton(View view) {
        switch (view.getId()) {
            case R.id.btnTimKiem:
                if(txtKhoangCach.getText().toString().trim().equals("0")){
                    txtKhoangCach.setError("Khoảng cách không được để trống");
                }else {
                    showProgressBar();
                    SearchHotelRequest searchHotelRequest = new SearchHotelRequest();
                    searchHotelRequest.setCity("");
                    searchHotelRequest.setDistance(AppDef.DEFAULT_DISTANCE);
                    try {
                        Integer iDistance = Integer.parseInt(txtKhoangCach.getText().toString().trim());
                        searchHotelRequest.setDistance(iDistance);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
//                    searchHotelRequest.setLat(mLastLocation.getLatitude());
//                    searchHotelRequest.setLon(mLastLocation.getLongitude());
                    searchHotelRequest.setLat(latitude);
                    searchHotelRequest.setLon(longitude);
                    searchHotelRequest.setRating(rate);
                    searchHotelRequest.setRank(0);
                    searchHotelRequest.setPage(pageIndex);
                    searchHotelRequest.setSize(pageSize);
                    searchHotelRequest.setPriceFrom((int) dMinPrice);
                    searchHotelRequest.setPriceTo((int) dMaxPrice);
                    searchHotelPresenter.searchHotel(AppDef.TOKEN_DEV, searchHotelRequest);
                }

                break;
            case R.id.imgChangeLocation:
//                dialogTimDiaChi();
                txtLocationAddress.setText("");
                break;
            case R.id.rd4Star:
                if (rd4Star.isChecked()) {
                    rate = 4;
//                    Toast.makeText(this, "rate = " + rate, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rd3Star:
                if (rd3Star.isChecked()) {
                    rate = 3;
//                    Toast.makeText(this, "rate = " + rate, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rd2Star:
                if (rd2Star.isChecked()) {
                    rate = 2;
//                    Toast.makeText(this, "rate = " + rate, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rd1Star:
                if (rd1Star.isChecked()) {
                    rate = 1;
//                    Toast.makeText(this, "rate = " + rate, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rdNoStar:
                if (rdNoStar.isChecked()) {
                    rate = 0;

                }
                break;
        }
    }

    private void dialogTimDiaChi() {
        final Dialog dialog = new Dialog(this, R.style.full_screen_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tim_dia_chi);
        dialog.show();
        final DelayAutoCompleteTextView txtSearchLocation = (DelayAutoCompleteTextView) dialog.findViewById(R.id.txtSearchLocation);
        Button btnDong = (Button) dialog.findViewById(R.id.btnDong);
        txtSearchLocation.setSelected(false);
        txtSearchLocation.setThreshold(2);
        txtSearchLocation.setAdapter(new GeoAutoCompleteAdapter(this)); // 'this' is Activity instance

        txtSearchLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                searchLocationLatLong = new LatLng(result.getLocation().getLatitude(), result.getLocation().getLongitude());
                txtSearchLocation.setText(result.getAddress());
                txtLocationAddress.setText(result.getAddress());
                dialog.dismiss();

            }
        });

        txtSearchLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    txtLocationAddress.setText(txtSearchLocation.getText().toString().trim());
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    @Override
    public void onSearchHotelSuccess(DanhSachNhaNghiResponse response) {
        if(response.getHotelList() != null && response.getHotelList().size() > 0){
            Intent intent = new Intent(this, MapActivity.class);
            List<HotelResponse> danhSachNhaNghi = response.getHotelList();
            try {
                intent.putExtra("LATITUDE", latitude);
                intent.putExtra("LONGITUDE", longitude);
                intent.putExtra("DANH_SACH_HOTEL", (Serializable) danhSachNhaNghi);

            } catch (Exception e) {
                e.printStackTrace();
            }
            startActivity(intent);
        }else {
            dilogThongBao(getString(R.string.thongBao), getString(R.string.khongTimThayKetQua), getString(R.string.dong));
        }

        hideProgressBar();
    }

    @Override
    public void onSearchHotelFailed(String message) {
        hideProgressBar();
    }

    @Override
    public void onSearchHotelError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }


    /*********************************************
     * google permission
     * @param bundle
     ********************************************/
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void PermissionGranted(int request_code) {
        Log.i("PERMISSION", "GRANTED");
        isPermissionGranted = true;
    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
        Log.i("PERMISSION PARTIALLY", "GRANTED");
    }

    @Override
    public void PermissionDenied(int request_code) {
        Log.i("PERMISSION", "DENIED");
    }

    @Override
    public void NeverAskAgain(int request_code) {
        Log.i("PERMISSION", "NEVER ASK AGAIN");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        getLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        startActivity(new Intent(TimNhaNghiActivity.this, HomeActivity.class));
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    /**
     * Method to verify google play services on the device
     */

    private boolean checkPlayServices() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode,
                        PLAY_SERVICES_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Creating google api client object
     */

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location requests here
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(TimNhaNghiActivity.this, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });


    }


    private void getLocation() {

        if (isPermissionGranted) {
            try {
                mLastLocation = LocationServices.FusedLocationApi
                        .getLastLocation(mGoogleApiClient);
                latitude = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }

    }

    private void findAddress(){
        txtLocationAddress.setSelected(false);
        txtLocationAddress.setThreshold(2);
        txtLocationAddress.setAdapter(new GeoAutoCompleteAdapter(this)); // 'this' is Activity instance

        txtLocationAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                searchLocationLatLong = new LatLng(result.getLocation().getLatitude(), result.getLocation().getLongitude());
                latitude = result.getLocation().getLatitude();
                longitude = result.getLocation().getLongitude();
                txtLocationAddress.setText(result.getAddress());
            }
        });
    }
}
