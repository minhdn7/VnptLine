package com.vnpt.vnptline.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.HotelHightLight;
import com.vnpt.vnptline.ui.activity.DetailActivity;

import java.util.List;

/**
 * Created by MinhDN on 26/1/2018.
 */

public class DanhSachNoiBatAdapter
        extends RecyclerView.Adapter<DanhSachNoiBatAdapter.MyView> {

    private List<HotelHightLight> danhSachNoiBatResponses;
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


    public DanhSachNoiBatAdapter(@NonNull Context context, List<HotelHightLight> horizontalList) {
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
        holder.setIsRecyclable(false);
        try {
            holder.txtNhaNghi.setText(danhSachNoiBatResponses.get(position).getNameHotel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Picasso mPicasso = Picasso.with(context);
        mPicasso.setIndicatorsEnabled(true);
        mPicasso.load(danhSachNoiBatResponses.get(position).getPicture())
                .fit().centerInside()
                .placeholder( R.drawable.progress_animation)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .error(R.drawable.nha_nghi_11)
                .into(holder.imgNhaNghi);

//        Glide.with(context)
//                .load(danhSachNoiBatResponses.get(position).getPicture())
//                .error(R.drawable.nha_nghi_11)
//                .placeholder( R.drawable.progress_animation)
//                .into(holder.imgNhaNghi);

        holder.imgNhaNghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("ID_HOTEL", danhSachNoiBatResponses.get(position).getHotelId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachNoiBatResponses.size();
    }

}