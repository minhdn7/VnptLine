package com.vnpt.vnptline.ui.view.user;

import com.vnpt.vnptline.domain.model.pojo.response.user.HistoryResponse;
import com.vnpt.vnptline.ui.view.View;

/**
 * Created by MinhDN on 1/3/2018.
 */

public interface HistoryView extends View {
    void onHistorySuccess(HistoryResponse response);
    void onHistoryFailed(String message);
    void onHistoryError(Throwable e);
}
