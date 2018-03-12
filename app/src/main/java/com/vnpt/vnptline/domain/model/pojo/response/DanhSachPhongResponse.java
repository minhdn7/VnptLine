package com.vnpt.vnptline.domain.model.pojo.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 23/1/2018.
 */

public class DanhSachPhongResponse {
    @Getter @Setter
    private String idPhong;

    @Getter @Setter
    private String soKhachPhong;

    @Getter @Setter
    private String tenPhong;

    @Getter @Setter
    private String giaPhong;

    @Getter @Setter
    private String mieuTaPhong;

    @Getter @Setter
    private String linkAnh;

    public DanhSachPhongResponse(String idPhong, String soKhachPhong, String tenPhong, String giaPhong, String mieuTaPhong, String linkAnh) {
        this.idPhong = idPhong;
        this.soKhachPhong = soKhachPhong;
        this.tenPhong = tenPhong;
        this.giaPhong = giaPhong;
        this.mieuTaPhong = mieuTaPhong;
        this.linkAnh = linkAnh;
    }
}
