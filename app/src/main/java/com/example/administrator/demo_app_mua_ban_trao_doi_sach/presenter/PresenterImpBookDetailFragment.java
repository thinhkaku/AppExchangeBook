package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.widget.ProgressBar;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;

public interface PresenterImpBookDetailFragment {
    void getEvaluate(int idS, ProgressBar progressBarGoiYSach);
    void getPromotion();
    void getDetailBook(int idS, MyLoadAppProgessBar myLoadAppProgessBar);
    void getRateBook(int idS);
    void sendRate(int idS, String name, String content, int evaluate, MyLoadAppProgessBar myLoadAppProgessBar);
}
