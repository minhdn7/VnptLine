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

import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.domain.model.pojo.response.user.ThongBaoResponse;
import com.vnpt.vnptline.ui.activity.QRCodeActivity;

import java.util.List;

/**
 * Created by MinhDN on 30/1/2018.
 */

public class DanhSachThongBaoAdapter extends ArrayAdapter<ThongBaoResponse> {
    private Context context;
    private int resource;
    private List<ThongBaoResponse> objects;

    public DanhSachThongBaoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ThongBaoResponse> objects) {
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
        DanhSachThongBaoAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent,false);
            holder = new DanhSachThongBaoAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DanhSachThongBaoAdapter.ViewHolder) convertView.getTag();
        }

        try {
            holder.txtTitleThongBao.setText(getItem(position).getTitle());
            holder.txtNoiDungThongBao.setText(getItem(position).getMessage());
            holder.txtDateThongBao.setText(getItem(position).getNotificationDate());
            holder.viewThongBao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getItem(position).getNotificationType().equals("Booking")){
                        Intent intent = new Intent(getContext(), QRCodeActivity.class);
                        intent.putExtra("QR_CODE_DATA", getItem(position).getData());
                        getContext().startActivity(intent);
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder {
        TextView txtNoiDungThongBao, txtTitleThongBao, txtDateThongBao;
        LinearLayout viewThongBao;
        public ViewHolder(View view) {
            txtTitleThongBao = (TextView) view.findViewById(R.id.txtTitleThongBao);
            txtNoiDungThongBao = (TextView) view.findViewById(R.id.txtNoiDungThongBao);
            txtDateThongBao = (TextView) view.findViewById(R.id.txtDateThongBao);
            viewThongBao = (LinearLayout) view.findViewById(R.id.viewThongBao);
        }


    }
}
