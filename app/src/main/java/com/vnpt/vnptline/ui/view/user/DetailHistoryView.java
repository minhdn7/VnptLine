package com.vnpt.vnptline.ui.view.user;
import com.vnpt.vnptline.domain.model.pojo.response.user.DetailHistoryResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 1/3/2018.
 */

public interface DetailHistoryView extends View {
    void onDetailHistorySuccess(DetailHistoryResponse response);
    void onDetailHistoryFailed(String message);
    void onDetailHistoryError(Throwable e);
}
