package com.vnpt.vnptline.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.domain.model.pojo.response.DanhSachBinhLuanResponse;
import com.vnpt.vnptline.ui.adapter.DanhSachBinhLuanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BinhLuanActivity extends BaseActivity {
    @BindView(R.id.lvDanhSachBinhLuan) ListView lvDanhSachBinhLuan;
    private DanhSachBinhLuanAdapter danhSachBinhLuanAdapter;
    private List<DanhSachBinhLuanResponse> danhSachBinhLuanResponses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_binhluan));
        addDanhSachBinhLuan();
    }

    private void addDanhSachBinhLuan() {
        danhSachBinhLuanResponses.add(new DanhSachBinhLuanResponse());
        danhSachBinhLuanResponses.add(new DanhSachBinhLuanResponse());
        danhSachBinhLuanResponses.add(new DanhSachBinhLuanResponse());
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
}
