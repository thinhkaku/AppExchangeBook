package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.content.Context;
import android.widget.EditText;

public interface PresenterImpRegisterOneFragment {
    void registerOne(EditText editText, String name, String email, String phone, Context context);
}
