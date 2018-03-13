package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;
import com.vnpt.vnptline.ui.presenter.token.TokenDevPresenter;
import com.vnpt.vnptline.ui.view.token.TokenDevView;

import javax.inject.Inject;

public class StartActivity extends BaseActivity implements TokenDevView {
    @Inject
    TokenDevPresenter tokenDevPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        showProgressBar();
        tokenDevPresenter.setView(this);
        tokenDevPresenter.onViewCreate();
        tokenDevPresenter.getTokenDev(AppDef.KEY_TOKEN_DEV);
    }

    @Override
    public void onTokenDevSuccess(TokenDevResponse tokenDevResponse) {
        try {
            if(tokenDevResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS){
                AppDef.TOKEN_DEV = tokenDevResponse.getToken();
                startActivity(new Intent(StartActivity.this, HomeActivity.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTokenDevFailed(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        startActivity(new Intent(StartActivity.this, HomeActivity.class));
    }

    @Override
    public void onTokenDevError(Throwable e) {
        hideProgressBar();
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(StartActivity.this, HomeActivity.class));
    }
}
