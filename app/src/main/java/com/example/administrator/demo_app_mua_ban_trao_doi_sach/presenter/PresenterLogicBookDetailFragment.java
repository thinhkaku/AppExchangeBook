package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.view.View;
import android.widget.ProgressBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Sach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.UuDai;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.DanhGia;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.notifiresult.ResultDangBai;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewBookDetailFragment;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicBookDetailFragment implements PresenterImpBookDetailFragment {
    private ViewBookDetailFragment viewBookDetailFragment;
    private SOService soService;

    public PresenterLogicBookDetailFragment(ViewBookDetailFragment viewBookDetailFragment, SOService soService) {
        this.viewBookDetailFragment = viewBookDetailFragment;
        this.soService = soService;
    }


    @Override
    public void getEvaluate(int idS, final ProgressBar progressBarGoiYSach) {
        soService.getSachGoiY(idS).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    viewBookDetailFragment.onSuccessSachGoiy(response.body());
                    progressBarGoiYSach.setVisibility(View.INVISIBLE);
                }else {
                    viewBookDetailFragment.onErr();
                    progressBarGoiYSach.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                viewBookDetailFragment.onErr();
                progressBarGoiYSach.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getPromotion() {
        soService.getUuDai().enqueue(new Callback<List<UuDai>>() {
            @Override
            public void onResponse(Call<List<UuDai>> call, Response<List<UuDai>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    if (response.body().toString().equals("1")){
                        viewBookDetailFragment.onErr();
                    }else {
                        viewBookDetailFragment.onSuccessPromotion(response.body());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<UuDai>> call, Throwable t) {
                call.clone().enqueue(this);
                viewBookDetailFragment.onErr();
            }
        });
    }

    @Override
    public void getDetailBook(int idS, final MyLoadAppProgessBar myLoadAppProgessBar) {
        myLoadAppProgessBar.show();
        soService.getChiTietSach(idS).enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                myLoadAppProgessBar.dismiss();
                if (response.isSuccessful()&&response.body()!=null){
                    if (response.body().toString().equals("1")){
                        viewBookDetailFragment.onErr();
                    }else {
                        viewBookDetailFragment.onSuccessDetailBook(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                call.clone().enqueue(this);
                viewBookDetailFragment.onErr();
            }
        });
    }

    @Override
    public void getRateBook(int idS) {
        soService.getDanhGia(String.valueOf(idS)).enqueue(new Callback<List<DanhGia>>() {
            @Override
            public void onResponse(Call<List<DanhGia>> call, Response<List<DanhGia>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    viewBookDetailFragment.onSuccessGetRateBook(response.body());
                }else {
                    viewBookDetailFragment.onErr();
                }
            }

            @Override
            public void onFailure(Call<List<DanhGia>> call, Throwable t) {
                call.clone().enqueue(this);
                viewBookDetailFragment.onErr();
            }
        });
    }

    @Override
    public void sendRate(int idS, String name, String content, int evaluate, final MyLoadAppProgessBar myLoadAppProgessBar) {
        if (name.isEmpty()||content.isEmpty()){
            viewBookDetailFragment.onErrSendRateBook();
        }else {
            myLoadAppProgessBar.show();
            soService.guiDanhGia(String.valueOf(idS),content, String.valueOf(evaluate),name).enqueue(new Callback<ResultDangBai>() {
                @Override
                public void onResponse(Call<ResultDangBai> call, Response<ResultDangBai> response) {
                    if (response.isSuccessful()&&response.body()!=null&&response.body().getAffectedRows().toString().trim().equals("1")){
                        viewBookDetailFragment.onSuccessSendRateBook();
                        myLoadAppProgessBar.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResultDangBai> call, Throwable t) {
                    call.clone().enqueue(this);
                    myLoadAppProgessBar.dismiss();
                    viewBookDetailFragment.onErr();
                }
            });
        }
    }
}
