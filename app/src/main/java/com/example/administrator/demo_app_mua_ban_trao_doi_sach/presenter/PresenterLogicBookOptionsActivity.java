package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.ViewBookDetailOptionActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicBookOptionsActivity implements PresenterImpBookOptionsActivity {
    private ViewBookDetailOptionActivity viewBookDetailOptionActivity;
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;

    public PresenterLogicBookOptionsActivity(ViewBookDetailOptionActivity viewBookDetailOptionActivity, SOService soService, MyLoadAppProgessBar myLoadAppProgessBar) {
        this.viewBookDetailOptionActivity = viewBookDetailOptionActivity;
        this.soService = soService;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
    }

    @Override
    public void getSearchBook(String query) {
        myLoadAppProgessBar.show();
        soService.getSearchSach(query).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    viewBookDetailOptionActivity.onSuccessGetSearch(response.body());
                    myLoadAppProgessBar.dismiss();
                }else {
                    viewBookDetailOptionActivity.err();
                    myLoadAppProgessBar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                viewBookDetailOptionActivity.err();
                myLoadAppProgessBar.dismiss();
            }
        });
    }

    @Override
    public void getStyleBook(int idLS) {
        myLoadAppProgessBar.show();
        soService.getSach(idLS).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    viewBookDetailOptionActivity.onSuccessGetStyleBook(response.body());
                    myLoadAppProgessBar.dismiss();
                }else {
                    viewBookDetailOptionActivity.err();
                    myLoadAppProgessBar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                viewBookDetailOptionActivity.err();
                myLoadAppProgessBar.dismiss();
            }
        });
    }
}
