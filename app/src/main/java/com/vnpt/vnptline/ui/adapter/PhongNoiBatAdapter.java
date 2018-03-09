package com.vnpt.vnptline.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


import com.vnpt.vnptline.R;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.RoomHightLight;
import com.vnpt.vnptline.ui.activity.DetailActivity;

import java.util.List;


/**
 * Created by MinhDN on 8/3/2018.
 */

public class PhongNoiBatAdapter extends RecyclerView.Adapter<PhongNoiBatAdapter.MyView> {

    private List<RoomHightLight> danhSachNoiBatResponses;
    private Context context;
    private DetailActivity detailActivity;
    public class MyView extends RecyclerView.ViewHolder {

        public ImageView imgPhongNghi;

        public MyView(View view) {
            super(view);
            imgPhongNghi = (ImageView) view.findViewById(R.id.imgPhongNghi);

        }
    }


    public PhongNoiBatAdapter(@NonNull Context context, List<RoomHightLight> horizontalList) {
        this.danhSachNoiBatResponses = horizontalList;
        this.context = context;
        this.detailActivity = (DetailActivity) context;
    }

    @Override
    public PhongNoiBatAdapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong_nha_nghi, parent, false);

        return new PhongNoiBatAdapter.MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final PhongNoiBatAdapter.MyView holder, final int position) {
        // set giá trị item ở đây
        holder.imgPhongNghi.setImageResource(danhSachNoiBatResponses.get(position).getImageResoune());
        holder.imgPhongNghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailActivity.setTopImage(danhSachNoiBatResponses.get(position).getImageResoune());
            }
        });

    }

    @Override
    public int getItemCount() {
        return danhSachNoiBatResponses.size();
    }

}
