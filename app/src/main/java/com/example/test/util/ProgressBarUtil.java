package com.example.test.util;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class ProgressBarUtil {
    private ProgressDialog progressBar;
    private Context context;

    public ProgressBarUtil(Context context){
        this.context = context;
        if(progressBar==null) {
            progressBar = new ProgressDialog(context);
        }
    }

    public void showProgress(){
        if(progressBar!=null) {
            progressBar.setCancelable(true);
            progressBar.setMessage("Please wait . . .");
            if(!((Activity) context).isFinishing()) {
                progressBar.show();
            }
        }
    }

    public void hideProgress(){
        if(progressBar!=null){
            progressBar.dismiss();
        }
    }
}
