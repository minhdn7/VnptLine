package com.vnpt.vnptline.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.utils.AppDef;
import com.vnpt.vnptline.app.utils.StatusCode;
import com.vnpt.vnptline.domain.model.pojo.response.TokenDevResponse;
import com.vnpt.vnptline.domain.repository.TinyDB;
import com.vnpt.vnptline.ui.presenter.LoginPresenter;
import com.vnpt.vnptline.ui.presenter.token.TokenDevPresenter;
import com.vnpt.vnptline.ui.view.token.TokenDevView;
import android.provider.Settings.Secure;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity implements Validator.ValidationListener,com.vnpt.vnptline.ui.view.View, TokenDevView {
    protected boolean isPassedValidate;
    public Validator validator;
    private KProgressHUD hud;
    private Tracker mTracker;
    public TinyDB tinyDB;

    @Inject
    TokenDevPresenter tokenDevPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT <= 20) {
            // only for gingerbread and newer versions
            MultiDex.install(this);
        }
        validator = new Validator(this);
        validator.setValidationListener(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDetailsLabel("Đang kết nối...")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
//        googleAnalyticEvent();
        injectDependencies();
        tokenDevPresenter.setView(this);
        tokenDevPresenter.onViewCreate();
        tinyDB = new TinyDB(this);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onValidationSucceeded() {
        isPassedValidate = true;
        Log.e("Valid: ", "Valid Success");
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isPassedValidate = false;
        Log.e("Valid: ", "" + isPassedValidate);
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void injectDependencies() {
        ((LineApplication) getApplication()).inject(this);
    }

    public boolean isConnectedNetwork() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(getApplication().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public Boolean isSpecialCharAvailable(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
//        Pattern p = Pattern.compile("[^A-Za-z0-9]");//replace this with your needs
        Pattern p = Pattern.compile("[^A-Za-z0-9]");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }

    public Boolean kiemTraKyTuSo(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9]");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }

    public Boolean validatePassWord(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;

    }
    public Boolean kiemTraKyTuChu(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z]");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }


    public void showProgressBar() {
        try{
            if (hud != null && !hud.isShowing())
                hud.show();
        }catch (Exception ex){

        }

    }

    public void hideProgressBar() {
        try{
            if (hud != null && hud.isShowing())
                hud.dismiss();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }catch (Exception ex){

        }

    }

    @Override public void onLoadTokenDev(String tokenDev) {

    }


    @Override
    public void onTokenDevSuccess(TokenDevResponse tokenDevResponse) {
        try {
            if(tokenDevResponse.getResponseCode() == StatusCode.RESPONSE_SUCCESS){
                AppDef.TOKEN_DEV = tokenDevResponse.getToken();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTokenDevFailed(String message) {

    }

    @Override
    public void onTokenDevError(Throwable e) {

    }

    public void getTokenDev(){
        tokenDevPresenter.getTokenDev(AppDef.KEY_TOKEN_DEV);
    }


    public void dilogThongBao(String title, String noiDung, String sButtton){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_thong_bao, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        // set width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(b.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        width = (int) (width * 0.8);
        lp.width = width;
        lp.gravity = Gravity.CENTER;
        b.getWindow().setAttributes(lp);
        //
        TextView txtHeaderDialog = (TextView) dialogView.findViewById(R.id.txtHeaderDialog);
        TextView txtNoiDung = (TextView) dialogView.findViewById(R.id.txtNoiDung);
        ImageView imgExit = (ImageView) dialogView.findViewById(R.id.imgExit);
        Button btnTiepTuc = (Button) dialogView.findViewById(R.id.btnTiepTuc);

        txtHeaderDialog.setText(title);
        txtNoiDung.setText(noiDung);
        btnTiepTuc.setText(sButtton);
        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

    }

    public boolean checkValidPhone(EditText editText){
        String phone = editText.getText().toString().trim();
        if(phone.equals("") || phone.length() < 10 || phone.length() > 12){
            editText.setError(getString(R.string.errorPhone));
            editText.requestFocus();
            return false;
        }else if(phone.charAt(0) != '0'){
            editText.setError(getString(R.string.errorPhone));
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void hideVirtualKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}