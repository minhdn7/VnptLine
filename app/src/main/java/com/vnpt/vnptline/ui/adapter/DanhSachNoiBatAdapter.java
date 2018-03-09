package com.vnpt.vnptline.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.domain.model.pojo.response.DanhSachNoiBatResponse;
import com.vnpt.vnptline.ui.activity.DetailActivity;
import com.vnpt.vnptline.ui.widget.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MinhDN on 26/1/2018.
 */

public class DanhSachNoiBatAdapter
        extends RecyclerView.Adapter<DanhSachNoiBatAdapter.MyView> {

    private List<DanhSachNoiBatResponse> danhSachNoiBatResponses;
    private Context context;
    public class MyView extends RecyclerView.ViewHolder {

        public TextView txtNhaNghi;
        public ImageView imgNhaNghi;

        public MyView(View view) {
            super(view);
            txtNhaNghi = (TextView) view.findViewById(R.id.txtNhaNghi);
            imgNhaNghi = (ImageView) view.findViewById(R.id.imgNhaNghi);

        }
    }


    public DanhSachNoiBatAdapter(@NonNull Context context, List<DanhSachNoiBatResponse> horizontalList) {
        this.danhSachNoiBatResponses = horizontalList;
        this.context = context;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uu_dai_noi_bat, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
            // set giá trị item ở đây
            holder.imgNhaNghi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 context.startActivity(new Intent(context, DetailActivity.class));
                }
            });
//        holder.textView.setText(danhSachNoiBatResponses.get(position).g);

    }

    @Override
    public int getItemCount() {
        return danhSachNoiBatResponses.size();
    }

}