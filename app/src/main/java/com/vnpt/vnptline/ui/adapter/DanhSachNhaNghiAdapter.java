package com.vnpt.vnptline.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelResponse;
import com.vnpt.vnptline.ui.activity.DatDoAnActivity;

import java.util.List;
import java.util.Locale;

/**
 * Created by MinhDN on 23/1/2018.
 */

public class DanhSachNhaNghiAdapter extends ArrayAdapter<HotelResponse> {
    private Context context;
    private int resource;
    private List<HotelResponse> objects;

    public DanhSachNhaNghiAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<HotelResponse> objects) {
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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            holder.txtTenNhaNghi.setText(getItem(position).getHotelName());
            holder.txtMieuTa.setText(getItem(position).getHotelType());
//            String sDistance = String.valueOf(getItem(position).getDistance().intValue()) + "m" + " tính từ vị trí hiện tại";
            String sDistance = String.valueOf((long) getItem(position).getDistance()) + "m" + " tính từ vị trí hiện tại";
            Locale.setDefault(Locale.US);
            String sGiaPhong = String.format("%,d",getItem(position).getPriceHour()) + " VND" + "/Giờ";
            holder.txtGiaPhong.setText(sGiaPhong);
            holder.txtDistance.setText(sDistance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder {
        LinearLayout viewDialogNhaNghi;
        ImageView imgNhaNghi;
        TextView txtTenNhaNghi, txtDistance, txtMieuTa, txtGiaPhong;

        public ViewHolder(View view) {
            txtTenNhaNghi = (TextView) view.findViewById(R.id.txtTenNhaNghi);
            txtDistance = (TextView) view.findViewById(R.id.txtDistance);
            txtMieuTa = (TextView) view.findViewById(R.id.txtMieuTa);
            txtGiaPhong = (TextView) view.findViewById(R.id.txtGiaPhong);
        }


    }
}
