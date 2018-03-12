package com.vnpt.vnptline.domain.model.pojo.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 30/1/2018.
 */

public class LichSuResponse {
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String tenNhaNghi;

    @Getter @Setter
    private String diaChi;

    @Getter @Setter
    private String time;

    @Getter @Setter
    private String trangThai;

    public LichSuResponse(String id, String tenNhaNghi, String diaChi, String time, String trangThai) {
        this.id = id;
        this.tenNhaNghi = tenNhaNghi;
        this.diaChi = diaChi;
        this.time = time;
        this.trangThai = trangThai;
    }
}
