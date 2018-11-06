package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.ListDeleteBaiDang;

import java.util.List;

public interface ViewPersonalCustomerFragment {
    void onSuccessNewsPersonal(List<BaiDang>arrNews);
    void onErr();
    void onSuccessLoadImage();
    void onSuccessDeleteNews();
    void onErrDeleteNews();
}
