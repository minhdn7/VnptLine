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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.domain.model.pojo.response.service.RoomBookingDetail;
import com.vnpt.vnptline.ui.activity.DatDoAnActivity;
import com.vnpt.vnptline.ui.activity.SupportActivity;

import java.util.List;

/**
 * Created by MinhDN on 30/1/2018.
 */

public class DanhSachPhongDatAdapter extends ArrayAdapter<RoomBookingDetail> {
    private Context context;
    private int resource;
    private List<RoomBookingDetail> objects;

    public DanhSachPhongDatAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RoomBookingDetail> objects) {
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
        DanhSachPhongDatAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent, false);
            holder = new DanhSachPhongDatAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DanhSachPhongDatAdapter.ViewHolder) convertView.getTag();
        }
        try {
            holder.txtTenNhaNghi.setText(getItem(position).getHotelName());
            holder.txtThoiGianNhanPhong.setText(getItem(position).getCheckInTime());
            String sTenPhong = context.getString(R.string.phong) + "\n" + getItem(position).getRoomNumber();
            holder.btnTenPhong.setText(sTenPhong);

            holder.viewDanhSachPhongChon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(AppDef.CHON_LOAI_DICH_VU == 1){
                        Intent intent = new Intent(context, DatDoAnActivity.class);
                        intent.putExtra("ID_HOTEL", getItem(position).getHotelId());
                        intent.putExtra("BOOKING_DETAIL_ID", getItem(position).getBookingDetailId());
                        context.startActivity(intent);
                    } else if(AppDef.CHON_LOAI_DICH_VU == 2){
                        context.startActivity(new Intent(context, SupportActivity.class));
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder {
        LinearLayout viewDanhSachPhongChon;
        TextView txtTenNhaNghi, txtThoiGianNhanPhong;
        Button btnTenPhong;

        public ViewHolder(View view) {
            txtTenNhaNghi = (TextView) view.findViewById(R.id.txtTenNhaNghi);
            txtThoiGianNhanPhong = (TextView) view.findViewById(R.id.txtThoiGianNhanPhong);
            btnTenPhong = (Button) view.findViewById(R.id.btnTenPhong);
            viewDanhSachPhongChon = (LinearLayout) view.findViewById(R.id.viewDanhSachPhongChon);

        }

    }

}

