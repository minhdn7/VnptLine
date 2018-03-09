package com.vnpt.vnptline.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.vnpt.vnptline.R;
import com.vnpt.vnptline.app.BaseActivity;
import com.vnpt.vnptline.domain.model.pojo.request.ShareAppModel;

import java.util.ArrayList;
import java.util.List;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import butterknife.OnClick;

public class ChiaSeActivity extends BaseActivity {
    private List<ShareAppModel> listAppShares = new ArrayList<>();
    private String shareBody = "VNPT Line, tìm nhà nghỉ, nhà trọ mơ ước trong tầm tay!";
    private String subject = "VNPT Line";
    private String shareType = "text/plain";
    private ShareDialog shareDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chia_se);
        setTitle(getString(R.string.title_activity_share));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls();
    }

    private void addControls() {
        shareDialog = new ShareDialog(this);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "test");
        sendIntent.setType("text/plain");
        List<ResolveInfo> resolveInfoList = getPackageManager()
                .queryIntentActivities(sendIntent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList) {
            try {
                Drawable icon = getPackageManager().getApplicationIcon(resolveInfo.activityInfo.packageName);
                listAppShares.add(new ShareAppModel(resolveInfo, icon));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({R.id.imgFacebook, R.id.imgMail, R.id.imgSMS, R.id.imgTwitter, R.id.imgOther})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgFacebook:
                shareFacebook();
                break;
            case R.id.imgMail:
                shareEmail(shareBody);
                break;
            case R.id.imgSMS:
                shareSMS(shareBody);
                break;
            case R.id.imgTwitter:
                Intent sharingTwitterIntent = shareTwitter(shareBody);
                startActivity(sharingTwitterIntent);
                break;
            case R.id.imgOther:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Chia sẻ với:"));
                break;
        }
    }

    private void shareSMS(String shareBody) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

            if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            startActivity(sendIntent);

        }
        else //For early versions, do what worked for you before.
        {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"));
            sendIntent.putExtra("sms_body", shareBody);
            startActivity(sendIntent);
        }
    }

    private void shareEmail(String shareBody) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));


    }

    private Intent shareTwitter(String shareBody) {
        Intent shareIntent;
        boolean hasAppTwitter = false;
        for(ShareAppModel item : listAppShares){
            if(item.getResolveInfo().activityInfo.packageName.equals("com.twitter.android")){
                hasAppTwitter = true;
            }
        }
        if(hasAppTwitter)
        {
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setClassName("com.twitter.android",
                    "com.twitter.android.PostActivity");
            shareIntent.setType(shareType);
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            return shareIntent;
        }
        else
        {
            String tweetUrl = "https://twitter.com/intent/tweet?text=" + shareBody;
            Uri uri = Uri.parse(tweetUrl);
            shareIntent = new Intent(Intent.ACTION_VIEW, uri);
            return shareIntent;
        }
    }

    private  void shareFacebook(){
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setQuote(shareBody)
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.vn.viplus"))
                .build();
        shareDialog.show(shareLinkContent);
    }
}
