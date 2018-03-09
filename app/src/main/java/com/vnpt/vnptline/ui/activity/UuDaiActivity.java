package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UuDaiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uu_dai);
        setTitle(R.string.uuDaiDanhRieng);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @OnClick({R.id.btnDangKy, R.id.btnDangNhap})
    public void submit(View view) {
        switch (view.getId()){
            case R.id.btnDangKy:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btnDangNhap:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
