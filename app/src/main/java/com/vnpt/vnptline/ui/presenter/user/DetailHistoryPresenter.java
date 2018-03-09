package com.vnpt.vnptline.ui.presenter.user;

import com.vnpt.vnptline.domain.model.pojo.request.user.DetailHistoryRequest;
import com.vnpt.vnptline.ui.presenter.Presenter;
import com.vnpt.vnptline.ui.view.user.DetailHistoryView;

/**
 * Created by MinhDN on 2/3/2018.
 */

public interface DetailHistoryPresenter extends Presenter<DetailHistoryView>{
    void getDetailHistory(String token, DetailHistoryRequest request);
}
