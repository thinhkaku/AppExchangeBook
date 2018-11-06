package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.ListAdapter;

import java.util.List;

public interface PresenterImpExchangeFragment {
    void getNumberComment(int h, List<BaiDang>arrNews);
    void getNewsPost(int page, Context context);
    void getLike(int id, BaiDang baiDang);
    void saveStore(Context context, BaiDang baiDang);
}
