package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicRegisterThreeFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.Login;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;

import java.io.IOException;

public class RegisterThreeFragment extends BaseFragment implements View.OnClickListener, ViewRegisterThreeFragment{
    private ImageView imgHinhDaiDien;
    private Button btnHuy, btnHoanTat;
    private Login login;
    private MainRegisterFragment mainRegisterFragment;
    private LoginFragment loginFragment;
    private ImageView imgCheck;
    private int KET_QUA=5;
    private Bitmap bitmap;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private SOService soService;
    private Context context;
    private Uri uri;
    private PresenterLogicRegisterThreeFragment presenterLogicRegisterThreeFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_register_three,container,false);
        imgHinhDaiDien=view.findViewById(R.id.imgHinhDaiDienDangKi);
        btnHoanTat=view.findViewById(R.id.btnHoanTat);
        btnHuy=view.findViewById(R.id.btnTroLaiDangKi3);
        imgCheck=view.findViewById(R.id.imgCheck);
        soService= ApiUtils.getSOService();
        myLoadAppProgessBar=new MyLoadAppProgessBar(context);
        presenterLogicRegisterThreeFragment=new PresenterLogicRegisterThreeFragment(soService, myLoadAppProgessBar, this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        login= (Login) activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        addEvent();
    }

    private void addEvent() {
        btnHuy.setOnClickListener(this);
        btnHoanTat.setOnClickListener(this);
        imgHinhDaiDien.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTroLaiDangKi3:
                mainRegisterFragment = (MainRegisterFragment) login.getFragmentDangKi();
                mainRegisterFragment.getFragmentDangKi2Back();
                break;
            case R.id.btnHoanTat:
                presenterLogicRegisterThreeFragment.getRegisterThree(uri, login);
                break;
            case R.id.imgHinhDaiDienDangKi:
                Utils.openAbum(login,this,KET_QUA);
                break;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==KET_QUA &&resultCode==login.RESULT_OK&&data!=null){
            uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(login.getContentResolver(),uri);
                imgHinhDaiDien.setImageBitmap(bitmap);
                Utils.scaleImage(login,imgHinhDaiDien);
                imgCheck.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onSusscess() {
        Snackbar snackbar = Snackbar.make(imgHinhDaiDien, login.getString(R.string.dk_tk_success), Snackbar.LENGTH_SHORT);
        snackbar.show();
        loginFragment = (LoginFragment) login.getFramentDangNhap();
        login.getFragmentDangNhap();
        loginFragment.getResultDangKi(SharePreseventConstant.getInstance(login).getTaiKhoan(), SharePreseventConstant.getInstance(login).getMatKhau());
    }

    @Override
    public void onErr() {
        Snackbar snackbar =Snackbar.make(imgHinhDaiDien,login.getString(R.string.chua_chon_anh), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onErrInternet() {
        Snackbar snackbar =Snackbar.make(imgHinhDaiDien,login.getString(R.string.loi), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
