package com.vnpt.vnptline.ui.activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.ui.fragment.AccountFragment;
import com.vnpt.vnptline.ui.fragment.HomeFragment;
import com.vnpt.vnptline.ui.fragment.MenuFragment;
import com.vnpt.vnptline.ui.fragment.ThongBaoFragment;

import butterknife.BindView;
import android.provider.Settings.Secure;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.bottomBar) BottomBar bottomBar;
    @BindView(R.id.contentContainer) FrameLayout contentContainer;
    private HomeFragment homeFragment;
    ThongBaoFragment thongBaoFragment;
    MenuFragment menuFragment;
    AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        addControls();
        addEvents();
        getTokenDev();
    }

    private void addEvents() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_home:
                        commitFragment(homeFragment);
                        break;
                    case R.id.tab_notification:
                        commitFragment(thongBaoFragment);
                        break;
                    case R.id.tab_account:
                        commitFragment(accountFragment);
                        break;
                    case R.id.tab_menu:
                        commitFragment(menuFragment);
                        break;
                }
            }

        });

    }

    private void addControls() {
        AppDef.DEVICE_ID = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);
        createFragments();
    }

    private void createFragments() {
        homeFragment = new HomeFragment();
        thongBaoFragment = new ThongBaoFragment();
        menuFragment = new MenuFragment();
        accountFragment = new AccountFragment();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    private void commitFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer, fragment);
        fragmentTransaction.commit();
    }
}
