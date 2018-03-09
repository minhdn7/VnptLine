package com.vnpt.vnptline.domain.repository;

import android.support.v4.app.ActivityCompat;


import com.vnpt.vnptline.ui.activity.MapActivity;

import java.lang.String;

import permissions.dispatcher.PermissionUtils;

/**
 * Created by MinhDN on 25/12/2017.
 */

public final class MapDemoActivityPermissionsDispatcher {
    private static final int REQUEST_GETMYLOCATION = 0;

    private static final String[] PERMISSION_GETMYLOCATION = new String[] {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_COARSE_LOCATION"};

    private static final int REQUEST_STARTLOCATIONUPDATES = 1;

    private static final String[] PERMISSION_STARTLOCATIONUPDATES = new String[] {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_COARSE_LOCATION"};

    private MapDemoActivityPermissionsDispatcher() {
    }

    public static void getMyLocationWithPermissionCheck(MapActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_GETMYLOCATION)) {
//            target.getMyLocation();
        } else {
            ActivityCompat.requestPermissions(target, PERMISSION_GETMYLOCATION, REQUEST_GETMYLOCATION);
        }
    }

    public static void startLocationUpdatesWithPermissionCheck(MapActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_STARTLOCATIONUPDATES)) {
//            target.startLocationUpdates();
        } else {
            ActivityCompat.requestPermissions(target, PERMISSION_STARTLOCATIONUPDATES, REQUEST_STARTLOCATIONUPDATES);
        }
    }

    public static void onRequestPermissionsResult(MapActivity target, int requestCode,
                                                  int[] grantResults) {
        switch (requestCode) {
            case REQUEST_GETMYLOCATION:
                if (PermissionUtils.verifyPermissions(grantResults)) {
//                    target.getMyLocation();
                }
                break;
            case REQUEST_STARTLOCATIONUPDATES:
                if (PermissionUtils.verifyPermissions(grantResults)) {
//                    target.startLocationUpdates();
                }
                break;
            default:
                break;
        }
    }
}
