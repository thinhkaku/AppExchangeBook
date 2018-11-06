package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;

public class ApiUtils {
    public static SOService getSOService() {
        return RetrofitClient.getClient(Constants.PORT).create(SOService.class);
    }
}
