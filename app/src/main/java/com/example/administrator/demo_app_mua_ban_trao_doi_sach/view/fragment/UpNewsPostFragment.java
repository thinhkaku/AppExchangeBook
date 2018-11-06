package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicUpNewsPostFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.NewsPostActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.notifiresult.MessageResult;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import de.hdodenhof.circleimageview.CircleImageView;


public class UpNewsPostFragment extends BaseFragment implements View.OnClickListener, ViewUpNewsPostFragment{
    private CircleImageView circleImageView;
    private User user;
    private Activity activity;
    private TextView txtUser;
    private Button btnHuyDangTin,btnNextDangTin;
    private EditText edtTenSachDang,edtGiaSachDang,edtNoiDungDang;
    private SOService soService;
    private ButtonAnimation buttonAnimation;
    private PresenterLogicUpNewsPostFragment presenterLogicUpNewsPostFragment;
    private MyLoadAppProgessBar myLoadAppProgessBar;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_post_news,container,false);
        circleImageView=view.findViewById(R.id.imgHinhDaiDienDangTin);
        btnHuyDangTin=view.findViewById(R.id.btnHuyDangTin);
        btnNextDangTin=view.findViewById(R.id.btnNextDangTin);
        edtTenSachDang=view.findViewById(R.id.edtTenSachDang);
        edtGiaSachDang=view.findViewById(R.id.edtGiaSachDang);
        edtNoiDungDang=view.findViewById(R.id.edtNoiDungDang);
        txtUser=view.findViewById(R.id.txtTenUser);
        myLoadAppProgessBar=new MyLoadAppProgessBar(activity);
        soService= ApiUtils.getSOService();
        presenterLogicUpNewsPostFragment=new PresenterLogicUpNewsPostFragment(this,soService, myLoadAppProgessBar);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        user= Util.getUser(activity);
         buttonAnimation=new ButtonAnimation((Context) activity);
        Glide.with(activity).load(Constants.PORTIMAGE+user.getAnhDaiDien()).into(circleImageView);
        txtUser.setText(user.getTen());
        addEvent();
    }

    private void addEvent() {
        btnNextDangTin.setOnClickListener(this);
        btnHuyDangTin.setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        this.activity=activity;
        super.onAttach(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNextDangTin:
                buttonAnimation=new ButtonAnimation((Context) activity);
                btnNextDangTin.startAnimation(buttonAnimation.startAnimation());
                int idCutomer=user.getId();
                String nameBook=edtTenSachDang.getText().toString();
                String price=edtGiaSachDang.getText().toString();
                String content=edtNoiDungDang.getText().toString();
                presenterLogicUpNewsPostFragment.getUpNewPost(idCutomer,nameBook,content,price);
                break;
            case R.id.btnHuyDangTin:
                btnHuyDangTin.startAnimation(buttonAnimation.startAnimation());
                activity.finish();
                break;
        }
    }


    @Override
    public void onSuccessPostNews(MessageResult messageResult) {
        int idBaiDang=messageResult.getMessage().getInsertId();
        NewsPostActivity newsPostActivity = (NewsPostActivity) activity;
        UpImageNewPostFragment upImageNewPostFragment = newsPostActivity.getUpImageNewPostFragment();
        SharePreseventConstant.getInstance(activity).setCheckDangBai("1");
        upImageNewPostFragment.getData(idBaiDang);
        newsPostActivity.switchFragment(upImageNewPostFragment);
    }

    @Override
    public void err() {

    }

    @Override
    public void errResult() {
        Toast.makeText(activity, activity.getString(R.string.chua_nhap_noi_dung), Toast.LENGTH_SHORT).show();
    }
}
