package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.net.Uri;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.ResultUploadImage;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.Login;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.LoginFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewRegisterThreeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicRegisterThreeFragment implements PresenterImpRegisterThreeFragment {
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private ViewRegisterThreeFragment viewRegisterThreeFragment;

    public PresenterLogicRegisterThreeFragment(SOService soService, MyLoadAppProgessBar myLoadAppProgessBar, ViewRegisterThreeFragment viewRegisterThreeFragment) {
        this.soService = soService;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
        this.viewRegisterThreeFragment = viewRegisterThreeFragment;
    }

    @Override
    public void getRegisterThree(Uri uri, final Login login) {
        if (uri!=null){
            myLoadAppProgessBar.show();
            soService.uploadImage(Utils.getBody(login,uri)).enqueue(new Callback<ResultUploadImage>() {
                @Override
                public void onResponse(Call<ResultUploadImage> call, Response<ResultUploadImage> response) {
                    if (response.isSuccessful()&&response.body()!=null){
                        String ten= SharePreseventConstant.getInstance(login).getTen();
                        String email= SharePreseventConstant.getInstance(login).getEmail();
                        String sdt= SharePreseventConstant.getInstance(login).getSDT();
                        String diaChi= SharePreseventConstant.getInstance(login).getDiaChi();
                        String taiKhoan= SharePreseventConstant.getInstance(login).getTaiKhoan();
                        String matKhau= SharePreseventConstant.getInstance(login).getMatKhau();
                        String api=response.body().getName()+"','"+ten+"','"+email+"',"+sdt+",'"+diaChi+"','"+taiKhoan+"-"+matKhau+"','"+taiKhoan+";"+taiKhoan;
                        soService.insertKhachHang(api).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body()!=null && response.body().equals("1")){
                                    viewRegisterThreeFragment.onSusscess();
//                                    Toast.makeText(login, login.getString(R.string.dk_tk_success), Toast.LENGTH_SHORT).show();
//                                    loginFragment =(LoginFragment) login.getFramentDangNhap();
//                                    login.getFragmentDangNhap();
//                                    loginFragment.getResultDangKi(SharePreseventConstant.getInstance(login).getTaiKhoan(),SharePreseventConstant.getInstance(login).getMatKhau());
                                    myLoadAppProgessBar.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                call.clone().enqueue(this);
                                viewRegisterThreeFragment.onErrInternet();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<ResultUploadImage> call, Throwable t) {
                    call.clone().enqueue(this);
                    viewRegisterThreeFragment.onErrInternet();
                }
            });
            uri=null;
        }else {
            viewRegisterThreeFragment.onErr();
            Toast.makeText(login, login.getString(R.string.chua_chon_anh), Toast.LENGTH_SHORT).show();
        }
    }
}
