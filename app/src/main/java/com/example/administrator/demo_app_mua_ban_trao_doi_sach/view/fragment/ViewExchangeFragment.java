package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;

import java.util.List;

public interface ViewExchangeFragment {
    void onSuccessGetComment();
    void onErr();
    void onErrResult(String err);
    void onSuccessGetNewsPost(List<BaiDang>arrNewsPost);
    void onSuccessLike(BaiDang baiDang);
    void onSuccessNumberLike();
    void onSuccessLoadImage();


}
