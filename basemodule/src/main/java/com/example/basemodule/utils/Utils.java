package com.example.basemodule.utils;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Utils {

    public static int getSoftButtonsBarHeight(Activity activity) {
        // getRealMetrics is only available with API 17 and +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    public static float getScreenDensity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            return metrics.density;
        } else
            return 0;
    }

    public static int convertDpToPx(Context context, int dps) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            return (int) (metrics.density * dps);
        } else
            return 0;
    }

    public static DisplayMetrics getScreenDisplayMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            return metrics;
        } else
            return null;
    }

    public static boolean isOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static int getOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }


    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetwork = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String convertStringIntoMD5(String password) {
        StringBuffer MD5Hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            MD5Hash = null;
        }
        if (MD5Hash != null)
            return MD5Hash.toString();
        else
            return null;
    }


}
