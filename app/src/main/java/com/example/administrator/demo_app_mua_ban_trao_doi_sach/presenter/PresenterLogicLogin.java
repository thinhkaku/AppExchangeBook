package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.KiemTraKiTuDacBiet;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.TrasosaConstans;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewLogin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicLogin implements PresenterImpLogin{
    public ViewLogin viewLogin;

    public PresenterLogicLogin(ViewLogin viewLogin) {
        this.viewLogin = viewLogin;
    }

    @Override
    public void login(String userName, String password, final Activity activity, CheckBox checkBox, final MyLoadAppProgessBar myLoadAppProgessBar) {
        SharedPreferences.Editor editor;
        SOService soService;
        if (userName.isEmpty() && password.isEmpty()) {
            viewLogin.err(activity.getString(R.string.chua_nhap_du_thong_tin));
        } else if (userName.isEmpty()) {
            viewLogin.err(activity.getString(R.string.chua_nhap_du_ten_tk));
        } else if (password.isEmpty()) {
            viewLogin.err(activity.getString(R.string.chua_nhap_du_mk));
        } else if (password.length() < 6) {
            viewLogin.err(activity.getString(R.string.chua_nhap_du_ki_tu));
        } else if (userName.length() < 2) {
            viewLogin.err(activity.getString(R.string.chua_nhap_du_ki_tu_tk));
        } else if (!new KiemTraKiTuDacBiet().validate(userName)) {
            viewLogin.err(activity.getString(R.string.chua_ki_tu_dac_biet));
        } else {
            if (checkBox.isChecked() == true) {
                editor = activity.getSharedPreferences(TrasosaConstans.TAIKHOAN_MK, activity.MODE_PRIVATE).edit();
                editor.putString(TrasosaConstans.TAI_KHOAN, userName);
                editor.putString(TrasosaConstans.MAT_KHAU, password);
                editor.apply();
            } else {
                editor = activity.getSharedPreferences(TrasosaConstans.TAIKHOAN_MK, activity.MODE_PRIVATE).edit();
                editor.putString(TrasosaConstans.TAI_KHOAN, userName);
                editor.putString(TrasosaConstans.MAT_KHAU, "");
                editor.apply();
            }
            myLoadAppProgessBar.show();
            soService = ApiUtils.getSOService();
            String userPass = userName + "-" + password;
            if (Utils.checkInternet(activity) == true) {
                soService.checkLogin(userPass).enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.body() != null) {
                            List<User> listUser = new ArrayList<>();
                            listUser = response.body();
                            if (listUser.size() == 0) {
                                viewLogin.err(activity.getString(R.string.tk_mk_false));
                            } else {
                                SharedPreferences sharedPreferences = activity.getSharedPreferences(TrasosaConstans.CHECK_BEGIN, activity.MODE_PRIVATE);
                                String kt = sharedPreferences.getString(TrasosaConstans.CHECK_BEGIN, "");
                                if (kt.equals(TrasosaConstans.OK)) {
                                    viewLogin.success(listUser.get(0), "0");
                                } else {
                                    viewLogin.success(listUser.get(0), "1");
                                }
                            }
                            myLoadAppProgessBar.dismiss();
                        } else {
                            viewLogin.err(activity.getString(R.string.loi));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        call.clone().enqueue(this);
                        viewLogin.err(activity.getString(R.string.loi));
                    }
                });
            } else {
                viewLogin.err(activity.getString(R.string.loi));
            }

        }
    }
}
