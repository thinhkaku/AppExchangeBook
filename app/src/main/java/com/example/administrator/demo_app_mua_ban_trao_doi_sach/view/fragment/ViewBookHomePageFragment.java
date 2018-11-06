package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;

import java.util.List;

public interface ViewBookHomePageFragment {
    void onSuccessGetBookNew(List<BiaSach>arrListBook);
    void onSuccessGetBookPurchaseedMuch(List<BiaSach>arrListBook);
    void onSuccessGetBookClassic(List<BiaSach>arrListBook);
    void onSuccessGetBookEconomy(List<BiaSach>arrListBook);
    void onSuccessGetBookMentality(List<BiaSach>arrListBook);
    void onSuccessGetBookLiterary(List<BiaSach>arrListBook);
    void onSuccessGetBookComic(List<BiaSach>arrListBook);
    void onSuccessGetBookYouth(List<BiaSach>arrListBook);
    void onSuccessGetBookRaisingChildren(List<BiaSach>arrListBook);
    void onSuccessGetBookScience(List<BiaSach>arrListBook);
    void onSuccessGetBookForeign(List<BiaSach>arrListBook);

}
