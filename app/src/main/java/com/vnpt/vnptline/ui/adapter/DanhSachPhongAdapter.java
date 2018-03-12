package com.vnpt.vnptline.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import com.vnpt.vnptline.R;

import com.vnpt.vnptline.domain.model.pojo.response.hotel.RoomAvailableResponse;
import com.vnpt.vnptline.ui.activity.DatDoAnActivity;
import com.vnpt.vnptline.ui.activity.DetailActivity;

import java.util.List;
import java.util.Locale;


/**
 * Created by MinhDN on 23/1/2018.
 */

public class DanhSachPhongAdapter extends ArrayAdapter<RoomAvailableResponse> {
    private Context context;
    private int resource;
    private List<RoomAvailableResponse> objects;
    private DetailActivity detailActivity;
    public DanhSachPhongAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RoomAvailableResponse> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.detailActivity = (DetailActivity) context;
    }

    @Override
    public int getCount() {
        return objects != null ? objects.size() : 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DanhSachPhongAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent,false);
            holder = new DanhSachPhongAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DanhSachPhongAdapter.ViewHolder) convertView.getTag();
        }

        try {
            holder.txtTenPhongNghi.setText(getItem(position).getName());
            String sSoKhach = getItem(position).getMaxPerson() + " " + getContext().getString(R.string.maxKhachPhong);
            holder.txtSoKhachPhong.setText(sSoKhach);
            Locale.setDefault(Locale.US);
            String formattedNumber = String.format("%,d", getItem(position).getPriceHour()) + context.getString(R.string.donViTien) + "/Giờ";
            holder.txtGiaPhong.setText(formattedNumber);
            holder.txtMieuTaPhong.setText(getItem(position).getDescription());



        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.btnChonPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailActivity.bookingRoom(getItem(position).getRoomTypeId(), getItem(position).getHotelId());
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView imgNhaNghi;
        Button btnChonPhong;
        TextView txtTenPhongNghi, txtSoKhachPhong, txtMieuTaPhong, txtGiaPhong;

        public ViewHolder(View view) {
            txtTenPhongNghi = (TextView) view.findViewById(R.id.txtTenPhongNghi);
            txtSoKhachPhong = (TextView) view.findViewById(R.id.txtSoKhachPhong);
            txtMieuTaPhong = (TextView) view.findViewById(R.id.txtMieuTaPhong);
            txtGiaPhong = (TextView) view.findViewById(R.id.txtGiaPhong);
            imgNhaNghi = (ImageView) view.findViewById(R.id.imgNhaNghi);
            btnChonPhong = (Button) view.findViewById(R.id.btnChonPhong);
        }



    }
}
