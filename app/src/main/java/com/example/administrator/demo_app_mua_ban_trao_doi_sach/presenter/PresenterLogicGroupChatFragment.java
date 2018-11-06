package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NhomChat;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewGroupChatFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicGroupChatFragment implements PresenterImpGroupChatFragment {
    private ViewGroupChatFragment viewGroupChatFragment;
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;

    public PresenterLogicGroupChatFragment(ViewGroupChatFragment viewGroupChatFragment, SOService soService, MyLoadAppProgessBar myLoadAppProgessBar) {
        this.viewGroupChatFragment = viewGroupChatFragment;
        this.soService = soService;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
    }

    @Override
    public void getGroupChat(int idCustomer) {
        myLoadAppProgessBar.show();
        soService.getNhomChat(String.valueOf(idCustomer)).enqueue(new Callback<List<NhomChat>>() {
            @Override
            public void onResponse(Call<List<NhomChat>> call, Response<List<NhomChat>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    viewGroupChatFragment.onSuccessGroupChat(response.body());
                    myLoadAppProgessBar.dismiss();
                }else {
                    myLoadAppProgessBar.dismiss();
                    viewGroupChatFragment.err();
                }
            }

            @Override
            public void onFailure(Call<List<NhomChat>> call, Throwable t) {
                call.clone().enqueue(this);
                viewGroupChatFragment.err();
                myLoadAppProgessBar.dismiss();
            }
        });
    }
}
