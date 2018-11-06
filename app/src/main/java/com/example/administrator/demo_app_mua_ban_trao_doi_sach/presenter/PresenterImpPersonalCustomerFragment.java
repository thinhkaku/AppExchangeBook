package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;

import java.util.List;

public interface PresenterImpPersonalCustomerFragment {
    void getNewsPersonal(int id);
    void getImage(List<BaiDang> arrNews);
    void deleteNews(int idNews);
}
