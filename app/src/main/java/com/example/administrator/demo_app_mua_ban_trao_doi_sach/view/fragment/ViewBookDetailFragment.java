package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Sach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.UuDai;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.DanhGia;

import java.util.List;

public interface ViewBookDetailFragment {
    void onSuccessSachGoiy(List<BiaSach> arrSach);
    void onErr();
    void onSuccessPromotion(List<UuDai> arrPromotion);
    void onSuccessDetailBook(List<Sach>arrDetailB);
    void onSuccessGetRateBook(List<DanhGia>arrRateBook);
    void onSuccessSendRateBook();
    void onErrSendRateBook();
}
