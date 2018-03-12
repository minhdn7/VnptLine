package com.vnpt.vnptline.domain.model.pojo.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 29/1/2018.
 */

public class DoAnResponse {
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String tenDoAn;

    @Getter @Setter
    private String giaDoAn;

    @Getter @Setter
    private String urlAnh;

    public DoAnResponse(String id, String tenDoAn, String giaDoAn, String urlAnh) {
        this.id = id;
        this.tenDoAn = tenDoAn;
        this.giaDoAn = giaDoAn;
        this.urlAnh = urlAnh;
    }
}
