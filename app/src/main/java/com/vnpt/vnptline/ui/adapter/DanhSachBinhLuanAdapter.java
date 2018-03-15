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
import com.vnpt.vnptline.domain.model.pojo.response.DanhSachBinhLuanResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.FindCommentHotelResponse;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.FindCommentRating;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

/**
 * Created by MinhDN on 24/1/2018.
 */

public class DanhSachBinhLuanAdapter extends ArrayAdapter<FindCommentRating> {
    private Context context;
    private int resource;
    private List<FindCommentRating> objects;

    public DanhSachBinhLuanAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<FindCommentRating> objects) {
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
        DanhSachBinhLuanAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent,false);
            holder = new DanhSachBinhLuanAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DanhSachBinhLuanAdapter.ViewHolder) convertView.getTag();
        }
        try {
            holder.txtUser.setText(getItem(position).getName());
            holder.txtNoiDung.setText(getItem(position).getComment());
            holder.txtThoiGian.setText(getItem(position).getRatingDate());
            holder.ratingBar.setRating(getItem(position).getRate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder {
        TextView txtUser, txtTieuDe, txtNoiDung, txtThoiGian;
        ScaleRatingBar ratingBar;
        public ViewHolder(View view) {
            txtUser = (TextView) view.findViewById(R.id.txtUser);
            txtNoiDung = (TextView) view.findViewById(R.id.txtNoiDung);
            txtThoiGian = (TextView) view.findViewById(R.id.txtThoiGian);
            ratingBar = (ScaleRatingBar) view.findViewById(R.id.ratingBar);

        }


    }
}
