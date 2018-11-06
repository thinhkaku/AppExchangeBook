package com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;

public class MyLoadAppProgessBar extends AlertDialog{
    public MyLoadAppProgessBar(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.dilog_load_app);
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }
}
