package com.vnpt.vnptline.ui.view.hotel;
import com.vnpt.vnptline.domain.model.pojo.response.hotel.DanhSachNhaNghiResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 12/2/2018.
 */

public interface SearchHotelView extends View {
    void onSearchHotelSuccess(DanhSachNhaNghiResponse response);

    void onSearchHotelFailed(String message);

    void onSearchHotelError(Throwable e);
}
