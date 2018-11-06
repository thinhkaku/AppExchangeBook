package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.app.Activity;
import android.net.Uri;
import android.os.CountDownTimer;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.ResultUploadImage;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewUpImageNewPostFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicUpImageNewPostFragment implements PresenterImpUpImageNewPostFragment {
    private ViewUpImageNewPostFragment viewUpImageNewPostFragment;
    private SOService soService;
    private Activity activity;
    private MyLoadAppProgessBar myLoadAppProgessBar;

    public PresenterLogicUpImageNewPostFragment(ViewUpImageNewPostFragment viewUpImageNewPostFragment, SOService soService, Activity activity, MyLoadAppProgessBar myLoadAppProgessBar) {
        this.viewUpImageNewPostFragment = viewUpImageNewPostFragment;
        this.soService = soService;
        this.activity = activity;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
    }




    @Override
    public void upLoadImageNews(final List<Uri> listUri) {
        if (listUri.size()>0){
            myLoadAppProgessBar.show();
            for (int i = 0; i<listUri.size(); i++){
                final int finalI = i;
                CountDownTimer countDownTimer=new CountDownTimer(2000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        soService.uploadImage(Utils.getBody(activity,listUri.get(finalI))).enqueue(new Callback<ResultUploadImage>() {
                            @Override
                            public void onResponse(Call<ResultUploadImage> call, Response<ResultUploadImage> response) {
                                if (response.isSuccessful()&&response.body()!=null){
                                    viewUpImageNewPostFragment.onSuccess(response.body().getName());
                                    myLoadAppProgessBar.dismiss();
                                }else {
                                    viewUpImageNewPostFragment.err();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResultUploadImage> call, Throwable t) {
                                call.clone().enqueue(this);
                                viewUpImageNewPostFragment.err();
                                myLoadAppProgessBar.dismiss();
                            }
                        });
                    }
                }.start();
            }
        }else {
            viewUpImageNewPostFragment.onResultErr();
        }
    }
}
