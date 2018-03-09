package com.vnpt.vnptline.domain.model.pojo.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 24/1/2018.
 */

public class DanhSachBinhLuanResponse {
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String account;

    @Getter @Setter
    private String tieuDe;

    @Getter @Setter
    private String noiDung;

    @Getter @Setter
    private String rating;

    @Getter @Setter
    private String thoiGian;

}
