package com.vnpt.vnptline.domain.model.pojo.response;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 23/1/2018.
 */

public class DanhSachNhaNghiResponse {
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String tenNhaNghi;

    @Getter @Setter
    private String khoangCach;

    @Getter @Setter
    private String giaPhong;

    @Getter @Setter
    private String mieuTa;

    @Getter @Setter
    private String linkAnh;

    public DanhSachNhaNghiResponse(String tenNhaNghi, String khoangCach, String giaPhong, String mieuTa, String linkAnh) {
        this.tenNhaNghi = tenNhaNghi;
        this.khoangCach = khoangCach;
        this.giaPhong = giaPhong;
        this.mieuTa = mieuTa;
        this.linkAnh = linkAnh;
    }

    public DanhSachNhaNghiResponse() {
    }


}
