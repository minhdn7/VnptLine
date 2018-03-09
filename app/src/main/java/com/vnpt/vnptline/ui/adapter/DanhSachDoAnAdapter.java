package com.vnpt.vnptline.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.domain.model.pojo.response.service.ServiceDetailResponse;
import com.vnpt.vnptline.ui.activity.DatDoAnActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by MinhDN on 29/1/2018.
 */

public class DanhSachDoAnAdapter extends ArrayAdapter<ServiceDetailResponse> {
    private Context context;
    private int resource;
    private List<ServiceDetailResponse> objects;
    private DatDoAnActivity datDoAnActivity;

    public DanhSachDoAnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ServiceDetailResponse> objects, DatDoAnActivity datDoAnActivity) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.datDoAnActivity = datDoAnActivity;
    }

    @Override
    public int getCount() {
        return objects != null ? objects.size() : 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DanhSachDoAnAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent, false);
            holder = new DanhSachDoAnAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DanhSachDoAnAdapter.ViewHolder) convertView.getTag();
        }
        try {
            holder.txtTenMon.setText(getItem(position).getName());
            Locale.setDefault(Locale.US);
            String formattedNumber = String.format("%,d", getItem(position).getPrice());
            final String sDonGia = formattedNumber + " " + context.getString(R.string.donViTien);
            holder.txtGiaDo.setText(sDonGia);
            holder.btnChonDoAn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datDoAnActivity.dilogDatDoAn(getItem(position).getName(), sDonGia, getItem(position).getPrice(), getItem(position).getServiceId());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imgDoAn;
        TextView txtTenMon, txtGiaDo;
        Button btnChonDoAn;

        public ViewHolder(View view) {
            txtTenMon = (TextView) view.findViewById(R.id.txtTenMon);
            txtGiaDo = (TextView) view.findViewById(R.id.txtGiaDo);
            imgDoAn = (ImageView) view.findViewById(R.id.imgDoAn);
            btnChonDoAn = (Button) view.findViewById(R.id.btnChonDoAn);

        }

    }

}
