package com.vnpt.vnptline.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.ui.IconGenerator;
import com.jaygoo.widget.RangeSeekBar;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelResponse;
import com.vnpt.vnptline.domain.repository.MapDemoActivityPermissionsDispatcher;
import com.vnpt.vnptline.domain.model.pojo.response.GeoSearchResult;
import com.vnpt.vnptline.ui.adapter.DanhSachNhaNghiAdapter;
import com.vnpt.vnptline.ui.adapter.GeoAutoCompleteAdapter;
import com.vnpt.vnptline.ui.event.DanhSachNhaNghiEvent;
import com.vnpt.vnptline.ui.widget.DelayAutoCompleteTextView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

/**
 * Created by MinhDN on 15/1/2018.
 */

public class MapActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private Integer THRESHOLD = 2;
    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private LocationRequest mLocationRequest;
    private LatLng searchLocation;
    Location mCurrentLocation;
    private long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private long FASTEST_INTERVAL = 5000; /* 5 secs */
    private final static String KEY_LOCATION = "location";
    private int cameraZoomTo = 13;
    private float minValues = 0;
    private float maxValues = 1000000;
    protected LocationManager locationManager;
    private boolean isGPSEnabled = false;
    private boolean isWifi = false;
    private boolean isFood = false;
    private boolean isMasage = false;
    private boolean isAirCoditioning = false;
    private boolean isFridge = false;
    private boolean isDuobleBed = false;
    private HashMap<Marker, Integer> mHashMap = new HashMap<Marker, Integer>();
    private LatLng findLocation;
    private LatLng searchLocationLatLong = null;
    private DanhSachNhaNghiAdapter danhSachNhaNghiAdapter;
    private Integer idNhaNGhi = 0;
    private String sPriceHourHotel = "";


    // get tọa độ
    private double latitude = 0;
    private double longitude = 0;
    // end
    private List<HotelResponse> danhSachNhaNghiResponse = new ArrayList<>();
    /*
     * Define a request code to send to Google Play services This code is
     * returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private RangeSeekBar ranglelideBar;
    private TextView txtGiaMin;
    private TextView txtGiaMax;
    @BindView(R.id.btnLoc)
    LinearLayout btnLoc;
    @BindView(R.id.btnBanDo) LinearLayout btnBanDo;
    @BindView(R.id.btnDanhSach) LinearLayout btnDanhSach;
    @BindView(R.id.txtSearchLocation) TextView txtSearchLocation;
    @BindView(R.id.lvDanhSachNhaNghi) ListView lvDanhSachNhaNghi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.nhaNghiGanBan);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!isGPSEnabled){
            displayPromptForEnablingGPS(this);
        }

        addControls();
        addEvents();

    }



    private void addPointer() {
        if(danhSachNhaNghiResponse != null && danhSachNhaNghiResponse.size() > 0){
            for(int i = 0; i < danhSachNhaNghiResponse.size(); i ++){
                Locale.setDefault(Locale.US);
                String formattedNumber = String.format("%,d", danhSachNhaNghiResponse.get(i).getPriceHour()) + " đ";
                addMarker(formattedNumber,
                        "",
                        new LatLng(danhSachNhaNghiResponse.get(i).getLat(), danhSachNhaNghiResponse.get(i).getLon()),
                        i);
            }
        }


        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Locale.setDefault(Locale.US);
                IconGenerator iconFactory = new IconGenerator(MapActivity.this);
//                iconFactory.setTextAppearance(R.color.md_white);
                iconFactory.setStyle(R.style.iconFactoryClick);
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(marker.getTitle())));
                idNhaNGhi = danhSachNhaNghiResponse.get(mHashMap.get(marker)).getHotelId();
//                Integer iDistance = danhSachNhaNghiResponse.get(mHashMap.get(marker)).getDistance();
                String sDistance = String.valueOf((long) danhSachNhaNghiResponse.get(mHashMap.get(marker)).getDistance()) + "m" + " tính từ vị trí hiện tại";
                String sGiaPhong = String.format("%,d", danhSachNhaNghiResponse.get(mHashMap.get(marker)).getPriceHour()) + " VND" + "/Giờ";
                sPriceHourHotel = sGiaPhong;
                dialogThongTinNhaNghi(danhSachNhaNghiResponse.get(mHashMap.get(marker)).getHotelName(),
                                    sDistance, sGiaPhong,
                                    danhSachNhaNghiResponse.get(mHashMap.get(marker)).getDescription(),
                                            "");
                return false;
            }
        });
    }

    @OnClick({R.id.btnDanhSach, R.id.btnBanDo, R.id.btnLoc, R.id.txtSearchLocation})
    public void submit(View view) {
        switch (view.getId()){
            case R.id.btnDanhSach:
                btnBanDo.setVisibility(View.VISIBLE);
                btnDanhSach.setVisibility(View.GONE);
                lvDanhSachNhaNghi.setVisibility(View.VISIBLE);
                mapFragment.getView().setVisibility(View.GONE);
                break;
            case R.id.btnBanDo:
                btnBanDo.setVisibility(View.GONE);
                btnDanhSach.setVisibility(View.VISIBLE);
                lvDanhSachNhaNghi.setVisibility(View.GONE);
                mapFragment.getView().setVisibility(View.VISIBLE);
                break;
            case R.id.btnLoc:
                dialogLocKetQua();
                break;
            case R.id.txtSearchLocation:
                dialogTimDiaChi();
                break;
        }
    }

    private void addEvents() {

    }

    private void performSearchLocation(LatLng searchLocation) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(searchLocation);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(cameraZoomTo);
        map.moveCamera(center);
        map.animateCamera(zoom);
    }

    private void addControls() {
        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        latitude = getIntent().getDoubleExtra("LATITUDE" , 21.028511);
        longitude = getIntent().getDoubleExtra("LONGITUDE" , 105.804817);

        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }
            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }

//        danhSachNhaNghiResponse = (List<HotelResponse>) getIntent().getSerializableExtra("DANH_SACH_HOTEL");
        try {
            danhSachNhaNghiResponse = EventBus.getDefault().getStickyEvent(DanhSachNhaNghiEvent.class).getListHotelResponse();
            danhSachNhaNghiAdapter = new DanhSachNhaNghiAdapter(this, R.layout.item_danh_sach_nha_nghi, danhSachNhaNghiResponse);
            lvDanhSachNhaNghi.setAdapter(danhSachNhaNghiAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lvDanhSachNhaNghi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MapActivity.this, DetailActivity.class);
                intent.putExtra("ID_HOTEL", danhSachNhaNghiResponse.get(position).getHotelId());
                sPriceHourHotel = danhSachNhaNghiResponse.get(position).getPriceHour() + " VND" + "/Giờ";
                intent.putExtra("PRICE_HOUR_HOTEL", sPriceHourHotel);
                startActivity(intent);
            }
        });
    }


    protected void loadMap(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            // Map is ready
            Toast.makeText(this, "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();
            map = googleMap;
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            getMyLocation();

            MapDemoActivityPermissionsDispatcher.getMyLocationWithPermissionCheck(this);
            MapDemoActivityPermissionsDispatcher.startLocationUpdatesWithPermissionCheck(this);

            //Initialize Google Play Services
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //Location Permission already granted
                    map.setMyLocationEnabled(true);
                } else {
                    //Request Location Permission
                }
            } else {
                map.setMyLocationEnabled(true);
            }
            getMyLocation();
            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(21.028511, 105.804817));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(cameraZoomTo);
            // setting camera move
            if (mCurrentLocation != null) {
                LatLng locationUpdate = new LatLng(latitude, longitude);
                performSearchLocation(locationUpdate);
//                center = CameraUpdateFactory.newLatLng(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
//                zoom = CameraUpdateFactory.zoomTo(cameraZoomTo);
            }


//            map.moveCamera(center);
//            map.animateCamera(zoom);
            // end
        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MapDemoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void getMyLocation() {
        try{
            //noinspection MissingPermission
            map.setMyLocationEnabled(true);

            FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);
            //noinspection MissingPermission
            locationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                onLocationChanged(location);

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("MapDemoActivity", "Error trying to get last GPS location");
                            e.printStackTrace();
                        }
                    });
        }catch (Exception ex){
        }

    }


    private boolean isGooglePlayServicesAvailable() {
        // Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates", "Google Play services is available.");
            return true;
        } else {
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                MapActivity.ErrorDialogFragment errorFragment = new MapActivity.ErrorDialogFragment();
                errorFragment.setDialog(errorDialog);
                errorFragment.show(getSupportFragmentManager(), "Location Updates");
            }

            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Display the connection status
        try {
            if (mCurrentLocation != null) {
                Toast.makeText(this, "GPS location was found!", Toast.LENGTH_SHORT).show();
                LatLng latLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
                map.animateCamera(cameraUpdate);
            } else {
                Toast.makeText(this, "Current location was null, enable GPS on emulator!", Toast.LENGTH_SHORT).show();
            }
            MapDemoActivityPermissionsDispatcher.startLocationUpdatesWithPermissionCheck(this);
        } catch (Exception ex) {
            Log.d("Exception: ", ex.toString());
        }

    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        //noinspection MissingPermission
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    public void onLocationChanged(Location location) {
        // GPS may be turned off
        if (location == null) {
            return;
        }

        // Report to the UI that the location was updated

        mCurrentLocation = location;

//        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(cameraZoomTo);
        map.moveCamera(center);
        map.animateCamera(zoom);
        // hiển thị pointer
        map.addMarker(new MarkerOptions()
                .position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
                .title("vị trí của tôi"));
        addPointer();
        // end
//        hiển thị address
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
//            String sAddress = address + ", " + city + ", " + state + ", " + country + ", " + knownName;
            txtSearchLocation.setText(address);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(KEY_LOCATION, mCurrentLocation);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        addPointer();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {



        return false;
    }

    // Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends android.support.v4.app.DialogFragment {

        // Global field to contain the error dialog
        private Dialog mDialog;

        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch(item.getItemId()) {
            case R.id.nav_home:
                return true;
            case R.id.nav_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                return true;
            case R.id.nav_slideshow:
                Log.d("ActionItemClicked", "Share clicked");
                return true;
            case R.id.nav_setting:
                Log.d("ActionItemClicked", "Share clicked");
                return true;
            case R.id.nav_share:
                Log.d("ActionItemClicked", "Share clicked");
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onMapSearch(View view) {

        String location = txtSearchLocation.getText().toString();
        List<Address>addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void displayPromptForEnablingGPS(final Activity activity)
    {
        // Thong bao chua bat GPS
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle("Không xác định được vị trí");
        builder.setMessage("Xin vui lòng bật GPS!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, 0);
            }
        });
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    private void dialogLocKetQua() {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_loc_ket_qua);
        RelativeLayout view = (RelativeLayout) dialog.findViewById(R.id.viewDialogLoc);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());

        // chỉnh kích cỡ dialog show
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        height = (int) (height * 0.8);
        lp.width = width;
        lp.height = height;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        mBehavior.setPeekHeight(height);
        dialog.show();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = (FrameLayout) d.findViewById(android.support.design.R.id.design_bottom_sheet);
                // Right here!
                final BottomSheetBehavior behaviour = BottomSheetBehavior.from(bottomSheet);
                behaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            dialog.dismiss();
                        }

                        if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                            ((BottomSheetBehavior) behaviour).setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    }
                });
            }
        });


    }

    private void addMarker(String title, String desCription,  LatLng position, int id){
        IconGenerator iconFactory = new IconGenerator(this);
        iconFactory.setTextAppearance(R.style.iconGenText);
        Marker marker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(title)))
                .snippet(desCription));
        mHashMap.put(marker, id);

    }

    private void dialogThongTinNhaNghi(String title, String distance, String giaPhong, String shortDescription, String imgUrl) {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_nha_nghi);
        LinearLayout viewDialogNhaNghi = (LinearLayout) dialog.findViewById(R.id.viewDialogNhaNghi);
        TextView txtTenNhaNghi = (TextView) dialog.findViewById(R.id.txtTenNhaNghi);
        TextView txtDistance = (TextView) dialog.findViewById(R.id.txtDistance);
        TextView txtGiaPhong = (TextView) dialog.findViewById(R.id.txtGiaPhong);
        TextView txtMieuTa = (TextView) dialog.findViewById(R.id.txtMieuTa);
        ImageView imgNhaNghi = (ImageView) dialog.findViewById(R.id.imgNhaNghi);

        txtTenNhaNghi.setText(title);
        txtDistance.setText(distance);
        txtGiaPhong.setText(giaPhong);
        txtMieuTa.setText(shortDescription);

        // chỉnh kích cỡ dialog show
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        height = (int) (height * 0.4);
        lp.width = width;
        lp.height = height;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
//        mBehavior.setPeekHeight(height);
        dialog.show();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = (FrameLayout) d.findViewById(android.support.design.R.id.design_bottom_sheet);
                // Right here!
                final BottomSheetBehavior behaviour = BottomSheetBehavior.from(bottomSheet);
                behaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            dialog.dismiss();
                        }

                        if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                            ((BottomSheetBehavior) behaviour).setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    }
                });
            }
        });

        viewDialogNhaNghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, DetailActivity.class);
                intent.putExtra("ID_HOTEL", idNhaNGhi);
                intent.putExtra("PRICE_HOUR_HOTEL", sPriceHourHotel);
                startActivity(intent);
            }
        });

    }

    private void dialogTimDiaChi() {
        final Dialog dialog = new Dialog(this, R.style.full_screen_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tim_dia_chi);
        dialog.show();
        final DelayAutoCompleteTextView txtSearchAddressLocation = (DelayAutoCompleteTextView) dialog.findViewById(R.id.txtSearchLocation);
        Button btnDong = (Button) dialog.findViewById(R.id.btnDong);
        txtSearchAddressLocation.setSelected(false);
        txtSearchAddressLocation.setThreshold(2);
        txtSearchAddressLocation.setAdapter(new GeoAutoCompleteAdapter(this)); // 'this' is Activity instance

        txtSearchAddressLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                searchLocationLatLong = new LatLng(result.getLocation().getLatitude(), result.getLocation().getLongitude());
                txtSearchLocation.setText(result.getAddress());
                performSearchLocation(searchLocationLatLong);
                dialog.dismiss();

            }
        });

        txtSearchAddressLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    txtSearchLocation.setText(txtSearchLocation.getText().toString().trim());
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
}

