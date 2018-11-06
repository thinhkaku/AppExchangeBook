package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.BookAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewBookHomePageFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicBookHomePageFragment implements PresenterImvBookHomePageFragment{
    private SOService soService;
    private ViewBookHomePageFragment viewBookHomePageFragment;
    public PresenterLogicBookHomePageFragment(SOService soService, ViewBookHomePageFragment viewBookHomePageFragment) {
        this.soService = soService;
        this.viewBookHomePageFragment = viewBookHomePageFragment;

    }

    @Override
    public void getBookNew(final ProgressBar progressBar) {
        soService.getSachMoiNhat(Constants.NEW).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookNew(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookPurchaseedMuch(final ProgressBar progressBar) {
        soService.getSach(Constants.PURCHASEMUCH).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookPurchaseedMuch(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookClassic(final ProgressBar progressBar) {
        soService.getSach(Constants.CLASSIC).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookClassic(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookEconomy(final ProgressBar progressBar) {
        soService.getSach(Constants.ECONOMY).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookEconomy(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookMentality(final ProgressBar progressBar) {
        soService.getSach(Constants.MENTALITY).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookMentality(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookLiterary(final ProgressBar progressBar) {
        soService.getSach(Constants.LETERARY).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookLiterary(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getComic(final ProgressBar progressBar) {
        soService.getSach(Constants.COMIC).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookComic(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookYouth(final ProgressBar progressBar) {
        soService.getSach(Constants.YOUTH).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookYouth(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookRaisingChildren(final ProgressBar progressBar) {
        soService.getSach(Constants.RAISING_CHILDREN).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookRaisingChildren(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getScience(final ProgressBar progressBar) {
        soService.getSach(Constants.SCIENS).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookScience(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getBookForeign(final ProgressBar progressBar) {
        soService.getSach(Constants.FOREIGN).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    List<BiaSach>a=response.body();
                    ChangedNameBook changedNameBook=new ChangedNameBook();
                    changedNameBook.execute(a);
                    viewBookHomePageFragment.onSuccessGetBookForeign(a);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                call.clone().enqueue(this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }


    private class ChangedNameBook extends AsyncTask<List<BiaSach>,Void,List<BiaSach>>{
        private List<BiaSach>listBook=new ArrayList<>();

        @Override
        protected List<BiaSach> doInBackground(List<BiaSach>... lists) {

            for (int i=0;i<lists[0].size();i++){
                BiaSach biaSach=lists[0].get(i);
                String nameBook="";
                String []listNameBook=biaSach.getTenS().split(" ");
                if (listNameBook.length>18){
                    for (int j=0;j<18;j++){
                        nameBook+=listNameBook[j]+" ";
                    }
                    biaSach.setTenS(nameBook+" ...");
                    listBook.add(biaSach);
                }

            }
            return listBook;
        }

        @Override
        protected void onPostExecute(List<BiaSach> biaSaches) {
            super.onPostExecute(biaSaches);
        }
    }
}
