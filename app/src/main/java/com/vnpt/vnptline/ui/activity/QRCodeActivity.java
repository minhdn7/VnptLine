package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.domain.model.pojo.response.user.QRCodeDataResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRCodeActivity extends BaseActivity {

    @BindView(R.id.imgMaQR) ImageView imgMaQR;
    @BindView(R.id.txtMaQR) TextView txtMaQR;
    @BindView(R.id.txtHotelName) TextView txtHotelName;
    @BindView(R.id.txtRoomType) TextView txtRoomType;
    @BindView(R.id.txtBookingDate) TextView txtBookingDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        setTitle(getString(R.string.maQR));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControl();
    }

    private void addControl() {

        try {
            Gson gson = new Gson();
            String sData = getIntent().getStringExtra("QR_CODE_DATA");

            QRCodeDataResponse qrCodeDataResponse = gson.fromJson(sData, QRCodeDataResponse.class);
            txtMaQR.setText(qrCodeDataResponse.getQrNumber());
            byte[] decodedString = Base64.decode(qrCodeDataResponse.getQrCode(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgMaQR.setImageBitmap(decodedByte);
            try {
                txtHotelName.setText(qrCodeDataResponse.getHotelName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                txtRoomType.setText(qrCodeDataResponse.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                txtBookingDate.setText(qrCodeDataResponse.getBookingDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // convert
        } catch (Throwable tx) {
            Toast.makeText(this, tx.toString(), Toast.LENGTH_LONG).show();
        }

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
}
