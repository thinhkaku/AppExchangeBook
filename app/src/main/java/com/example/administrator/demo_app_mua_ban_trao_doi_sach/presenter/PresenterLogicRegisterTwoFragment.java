package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.support.design.widget.Snackbar;
import android.widget.EditText;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.KiemTraKiTuDacBiet;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.Login;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewRegisterTwoFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicRegisterTwoFragment implements PresenterImpRegisterTwoFragment {
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private Login login;
    private ViewRegisterTwoFragment viewRegisterTwoFragment;

    public PresenterLogicRegisterTwoFragment(SOService soService, MyLoadAppProgessBar myLoadAppProgessBar, Login login, ViewRegisterTwoFragment viewRegisterTwoFragment) {
        this.soService = soService;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
        this.login = login;
        this.viewRegisterTwoFragment = viewRegisterTwoFragment;
    }

    @Override
    public void getRegisterTwo(EditText editText, final String address, final String user, final String password) {
        if(address.isEmpty()||user.isEmpty()||password.isEmpty()){
            Snackbar snackbar =Snackbar.make(editText, login.getString(R.string.chua_nhap_du_thong_tin),Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (password.length()<6){
            Snackbar snackbar =Snackbar.make(editText,login.getString(R.string.chua_nhap_du_ki_tu),Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if(user.length()<2){
            Snackbar snackbar =Snackbar.make(editText,login.getString(R.string.chua_nhap_du_ki_tu_tk),Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (!new KiemTraKiTuDacBiet().validate(user)) {
            Snackbar snackbar =Snackbar.make(editText,login.getString(R.string.chua_ki_tu_dac_biet),Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        else {
            myLoadAppProgessBar.show();
            soService= ApiUtils.getSOService();
            soService.kiemTraTaiKhoan(user).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    myLoadAppProgessBar.dismiss();
                    if (response.body()!=null &&response.body().equals("1")){
                        SharePreseventConstant.getInstance(login).setDangTin2(address, user,password);
                        viewRegisterTwoFragment.onSusscess();
//                        mainRegisterFragment = (MainRegisterFragment) login.getFragmentDangKi();
//                        mainRegisterFragment.getFragmentDangKi3();
                    }else {
                        viewRegisterTwoFragment.onErrExist();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    call.clone().enqueue(this);
                    myLoadAppProgessBar.dismiss();
                }
            });

        }
    }
}
