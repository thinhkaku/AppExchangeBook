package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.ListDeleteBaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NumberComment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewPersonalCustomerFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicPersonalCustomerFragment implements PresenterImpPersonalCustomerFragment{
    private ViewPersonalCustomerFragment viewPersonalCustomerFragment;
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;


    public PresenterLogicPersonalCustomerFragment(ViewPersonalCustomerFragment viewPersonalCustomerFragment, SOService soService, MyLoadAppProgessBar myLoadAppProgessBar) {
        this.viewPersonalCustomerFragment = viewPersonalCustomerFragment;
        this.soService = soService;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
    }

    @Override
    public void getNewsPersonal(int id) {
        myLoadAppProgessBar.show();
        soService.getBaiDangCaNhan(String.valueOf(id)).enqueue(new Callback<List<BaiDang>>() {
            @Override
            public void onResponse(Call<List<BaiDang>> call, Response<List<BaiDang>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                   viewPersonalCustomerFragment.onSuccessNewsPersonal(response.body());
                    myLoadAppProgessBar.dismiss();
                }else {
                    viewPersonalCustomerFragment.onErr();
                }

            }

            @Override
            public void onFailure(Call<List<BaiDang>> call, Throwable t) {
                call.clone().enqueue(this);
                viewPersonalCustomerFragment.onErr();
            }
        });
    }

    @Override
    public void getImage(List<BaiDang> arrNews) {
        GetDataAsync getDataAsync=new GetDataAsync(arrNews);
        getDataAsync.execute();
    }

    @Override
    public void deleteNews(int idNews) {
        soService.deleteNews(String.valueOf(idNews)).enqueue(new Callback<ListDeleteBaiDang>() {
            @Override
            public void onResponse(Call<ListDeleteBaiDang> call, Response<ListDeleteBaiDang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getMessage().getDelete().equals("ok") && response.body().getMessage().getError().equals("null")) {
                        viewPersonalCustomerFragment.onSuccessDeleteNews();
                    } else {
                        viewPersonalCustomerFragment.onErrDeleteNews();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDeleteBaiDang> call, Throwable t) {
                call.clone().enqueue(this);
                viewPersonalCustomerFragment.onErrDeleteNews();
            }
        });
    }

    private class GetDataAsync extends AsyncTask<Void,Void,Void>
    {
        private List<BaiDang>arrNews;

        public GetDataAsync(List<BaiDang> arrNews) {
            this.arrNews = arrNews;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < arrNews.size(); i++) {
                final BaiDang baiDang = arrNews.get(i);
                soService.getSoBL(String.valueOf(baiDang.getIdBaiDang())).enqueue(new Callback<List<NumberComment>>() {
                    @Override
                    public void onResponse(Call<List<NumberComment>> call, retrofit2.Response<List<NumberComment>> response) {
                        baiDang.setSoBL(response.body().get(0).getSoBL());
                    }

                    @Override
                    public void onFailure(Call<List<NumberComment>> call, Throwable t) {

                    }
                });
                getImage(baiDang);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    private void getImage(final BaiDang baiDang){
        soService.getHinhAnh(String.valueOf(baiDang.getIdBaiDang())).enqueue(new Callback<List<Hinh>>() {
            @Override
            public void onResponse(Call<List<Hinh>> call, Response<List<Hinh>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    baiDang.setArrListImage(response.body());
                    viewPersonalCustomerFragment.onSuccessLoadImage();
                }
            }

            @Override
            public void onFailure(Call<List<Hinh>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
