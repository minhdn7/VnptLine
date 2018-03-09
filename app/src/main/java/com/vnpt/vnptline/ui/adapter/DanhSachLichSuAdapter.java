package com.vnpt.vnptline.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryBookingResponse;

import com.vnpt.vnptline.ui.activity.DetailHistoryActivity;

import java.util.List;

/**
 * Created by MinhDN on 30/1/2018.
 */

public class DanhSachLichSuAdapter extends ArrayAdapter<HistoryBookingResponse> {
    private Context context;
    private int resource;
    private List<HistoryBookingResponse> objects;


    public DanhSachLichSuAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<HistoryBookingResponse> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects != null ? objects.size() : 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DanhSachLichSuAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent, false);
            holder = new DanhSachLichSuAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DanhSachLichSuAdapter.ViewHolder) convertView.getTag();
        }
        try {
            try {
                holder.txtTenNhaNghi.setText(getItem(position).getHotelName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                holder.txtDiaChi.setText(getItem(position).getHotelAddress());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                holder.txtTime.setText(getItem(position).getBookingDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                holder.txtTrangThai.setText(getItem(position).getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.viewLichSu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailHistoryActivity.class);
                    intent.putExtra("BOOKING_ID", getItem(position).getBookingId());
                    intent.putExtra("HOTEL_ADDRESS", getItem(position).getHotelAddress());
                    context.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView imgTrangThai;
        LinearLayout viewLichSu;
        TextView txtTenNhaNghi, txtDiaChi, txtTime, txtTrangThai;


        public ViewHolder(View view) {
            txtTenNhaNghi = (TextView) view.findViewById(R.id.txtTenNhaNghi);
            txtDiaChi = (TextView) view.findViewById(R.id.txtDiaChi);
            txtTime = (TextView) view.findViewById(R.id.txtTime);
            txtTrangThai = (TextView) view.findViewById(R.id.txtTrangThai);
            imgTrangThai = (ImageView) view.findViewById(R.id.imgTrangThai);
            viewLichSu = (LinearLayout) view.findViewById(R.id.viewLichSu);

        }
    }
}
