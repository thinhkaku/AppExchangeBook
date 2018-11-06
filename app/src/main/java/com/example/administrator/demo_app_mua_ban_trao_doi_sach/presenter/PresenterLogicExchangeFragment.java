package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.SQLiteData;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.CheckLike;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NumberComment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NumberLike;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewExchangeFragment;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicExchangeFragment implements PresenterImpExchangeFragment{
    private ViewExchangeFragment viewExchangeFragment;
    private SOService soService;
    private int idUser;

    public PresenterLogicExchangeFragment(ViewExchangeFragment viewExchangeFragment, SOService soService, int idUser) {
        this.viewExchangeFragment = viewExchangeFragment;
        this.soService = soService;
        this.idUser=idUser;
    }


    @Override
    public void getNumberComment(int h, List<BaiDang>arrNews) {
        GetDataAsync getDataAsync=new GetDataAsync(h,arrNews);
        getDataAsync.execute();
    }

    private class GetDataAsync extends AsyncTask<Void,Void,Void>
    {
        private int h;
        private List<BaiDang>arrNews;

        public GetDataAsync(int h, List<BaiDang> arrNews) {
            this.h = h;
            this.arrNews = arrNews;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = h; i < arrNews.size(); i++) {
                final BaiDang baiDang = arrNews.get(i);
                soService.getSoBL(String.valueOf(baiDang.getIdBaiDang())).enqueue(new Callback<List<NumberComment>>() {
                    @Override
                    public void onResponse(Call<List<NumberComment>> call, retrofit2.Response<List<NumberComment>> response) {
                        baiDang.setSoBL(response.body().get(0).getSoBL());
                    }

                    @Override
                    public void onFailure(Call<List<NumberComment>> call, Throwable t) {
                        call.clone().enqueue(this);
                    }
                });
                getImage(baiDang);
                getLuotKike(baiDang);
                checkLike(baiDang);



            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewExchangeFragment.onSuccessGetComment();
        }
    }

    private void getImage(final BaiDang baiDang){
        soService.getHinhAnh(String.valueOf(baiDang.getIdBaiDang())).enqueue(new Callback<List<Hinh>>() {
            @Override
            public void onResponse(Call<List<Hinh>> call, Response<List<Hinh>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    baiDang.setArrListImage(response.body());
                    viewExchangeFragment.onSuccessLoadImage();
                }
            }

            @Override
            public void onFailure(Call<List<Hinh>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    private class GetDataNewsPost extends AsyncTask<Void,Void,Void>
    {
        private int page;
        private Context context;

        public GetDataNewsPost(int page, Context context) {
            this.page = page;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            soService.getBaiDang(String.valueOf(page)).enqueue(new retrofit2.Callback<List<BaiDang>>() {
                @Override
                public void onResponse(Call<List<BaiDang>> call, retrofit2.Response<List<BaiDang>> response) {
                    if (response.body().toString().equals("[]")){
                        viewExchangeFragment.onErrResult(context.getString(R.string.het_bai_dang));
                    }else {
                        viewExchangeFragment.onSuccessGetNewsPost(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<BaiDang>> call, Throwable t) {
                    call.clone().enqueue(this);
                    viewExchangeFragment.onErr();
                }

            });
            return null;
        }
    }



    @Override
    public void getNewsPost(int page, final Context context) {
        GetDataNewsPost getDataNewsPost=new GetDataNewsPost(page,context);
        getDataNewsPost.execute();
    }

    @Override
    public void getLike(int id, final BaiDang baiDang) {
        soService.getLike(String.valueOf(id),String.valueOf(baiDang.getIdBaiDang())).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().equals("1")){
                    viewExchangeFragment.onSuccessLike(baiDang);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    @Override
    public void saveStore(final Context context, final BaiDang baiDang) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.thong_bao));
        builder.setMessage(R.string.bai_dang_se_dc_luu);
        builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteData sqLiteData = new SQLiteData(context);
                long ckeckSave = sqLiteData.insert(baiDang);
                if (ckeckSave != -1) {
                    Toast.makeText(context, context.getString(R.string.luu_gb_success), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, context.getString(R.string.luu_gb_fails), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.cancle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    public void checkLike(final BaiDang baiDang) {
        soService.checkLike(idUser+"-"+baiDang.getIdBaiDang()).enqueue(new Callback<List<CheckLike>>() {
            @Override
            public void onResponse(Call<List<CheckLike>> call, Response<List<CheckLike>> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().get(0).getCheckLike()==1){
                    baiDang.setCheckLike(true);
                }else {
                    baiDang.setCheckLike(false);
                }
            }

            @Override
            public void onFailure(Call<List<CheckLike>> call, Throwable t) {

            }
        });
    }

    public void getLuotKike(final BaiDang baiDang){
        soService.getSoLuotLike(String.valueOf(baiDang.getIdBaiDang())).enqueue(new Callback<List<NumberLike>>() {
            @Override
            public void onResponse(Call<List<NumberLike>> call, retrofit2.Response<List<NumberLike>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    baiDang.setLuotLike(response.body().get(0).getSoLK());
                    viewExchangeFragment.onSuccessNumberLike();
                }
            }

            @Override
            public void onFailure(Call<List<NumberLike>> call, Throwable t) {

            }
        });
    }
}
