package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.notifiresult.MessageResult;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewUpNewsPostFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicUpNewsPostFragment implements PresenterImpUpNewsPostFragment{
    private ViewUpNewsPostFragment viewUpNewsPostFragment;
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;

    public PresenterLogicUpNewsPostFragment(ViewUpNewsPostFragment viewUpNewsPostFragment, SOService soService, MyLoadAppProgessBar myLoadAppProgessBar) {
        this.viewUpNewsPostFragment = viewUpNewsPostFragment;
        this.soService = soService;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
    }


    @Override
    public void getUpNewPost(int idCustomer, String nameBook, String content, String price) {
        if(nameBook.isEmpty()||price.isEmpty()||content.isEmpty()){
             viewUpNewsPostFragment.errResult();
        }else {
            myLoadAppProgessBar.show();
            soService.getDangBai(String.valueOf(idCustomer),nameBook,content,price).enqueue(new Callback<MessageResult>() {
                @Override
                public void onResponse(Call<MessageResult> call, Response<MessageResult> response) {
                    if (response.isSuccessful()&&response.body()!=null){
                        viewUpNewsPostFragment.onSuccessPostNews(response.body());
                        myLoadAppProgessBar.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<MessageResult> call, Throwable t) {
                    call.clone().enqueue(this);
                    myLoadAppProgessBar.dismiss();
                    viewUpNewsPostFragment.err();
                }
            });
        }

    }
}
