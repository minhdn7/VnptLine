package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.HistoryRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.HistoryView;


/**
 * Created by MinhDN on 1/3/2018.
 */

public interface HistoryPresenter extends Presenter<HistoryView> {
    void getHistory(String token, HistoryRequest request);
}
