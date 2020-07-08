package com.example.test.util;
import android.content.Context;
import android.net.ConnectivityManager;
public class NetworkUtil {

    public static boolean checkInternetConnection(Context context) {
        boolean flag = false;

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
                flag = true;
            }
        } catch (Exception e) {

        }
        return flag;
    }
}
