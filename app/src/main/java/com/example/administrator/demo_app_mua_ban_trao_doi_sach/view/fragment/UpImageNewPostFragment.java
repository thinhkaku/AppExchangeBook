package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.LoginSystemInterface;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.loginsytem.BeginAsync;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork.XuLySuKien;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicUpImageNewPostFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.MainView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpImageNewPostFragment extends BaseFragment implements View.OnClickListener ,MainView,LoginSystemInterface, ViewUpImageNewPostFragment {
    private int soAnh=1;
    private ImageView image1,image2,image3,image4,image5;
    private int viTri;
    private int idUser;
    private  Activity activity;
    private XuLySuKien xuLySuKien;
    private String CLIENT_SEND_URL_IMAGE_BAI_DANG="CLIENT_SEND_URL_IMAGE_BAI_DANG";
    private String SERVER_SEND_RESULT_DANG_BAI="SERVER_SEND_RESULT_DANG_BAI";
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private SOService soService;
    private PresenterLogicUpImageNewPostFragment presenterLogicUpNewsPostFragment;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_upload_image,container,false);
        image1= view.findViewById(R.id.hinh1);
        image2= view.findViewById(R.id.hinh2);
        image3= view.findViewById(R.id.hinh3);
        image4= view.findViewById(R.id.hinh4);
        image5= view.findViewById(R.id.hinh5);
        myLoadAppProgessBar=new MyLoadAppProgessBar(activity);
        soService= ApiUtils.getSOService();
        presenterLogicUpNewsPostFragment=new PresenterLogicUpImageNewPostFragment(this,soService,activity,myLoadAppProgessBar);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        xuLySuKien=new XuLySuKien(this,activity);
        getSoAnh();
        addEvent();
    }

    private void addEvent() {
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
    }

    private  void getSoAnh(){
        if (soAnh==1){
            image1.setVisibility(View.VISIBLE);
        }else if (soAnh==2){
            image2.setVisibility(View.VISIBLE);
        }else if (soAnh==3){
            image3.setVisibility(View.VISIBLE);
        }else if (soAnh==4){
            image4.setVisibility(View.VISIBLE);
        }else if (soAnh==5){
            image5.setVisibility(View.VISIBLE);
        }
    }


    public void getData(int id) {
            idUser=id;
    }

    public void guiUrlToServer(){
        presenterLogicUpNewsPostFragment.upLoadImageNews(listUri);
    }

    public void getImageFromActivity(Uri uri){
        Bitmap bitmap= null;
        try {
            bitmap = Utils.getThumbnail(activity,uri,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (viTri==1){
            listUri.add(uri);
            image1.setImageBitmap(Utils.resize(bitmap,100,100));
            soAnh++;
            getSoAnh();
        }else if (viTri==2){
            listUri.add(uri);
            image2.setImageBitmap(Utils.resize(bitmap,100,100));
            soAnh++;
            getSoAnh();
        }else if (viTri==3){
            listUri.add(uri);
            image3.setImageBitmap(Utils.resize(bitmap,100,100));
            soAnh++;
            getSoAnh();
        }
        else if (viTri==4){
            listUri.add(uri);
            image4.setImageBitmap(Utils.resize(bitmap,100,100));
            soAnh++;
            getSoAnh();
        }
        else if (viTri==5){
            listUri.add(uri);
            image5.setImageBitmap(Utils.resize(bitmap,100,100));
            soAnh++;
            getSoAnh();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }




    @Override
    public void onClick(View v) {
        ButtonAnimation buttonAnimation=new ButtonAnimation((Context) activity);
        switch (v.getId()){
            case R.id.hinh1:
                image1.startAnimation(buttonAnimation.startAnimation());
                getImageFromAlbum();
                viTri=1;
                danhDauVitri();
                break;
            case R.id.hinh2:
                image2.startAnimation(buttonAnimation.startAnimation());
                getImageFromAlbum();
                viTri=2;
                danhDauVitri();
                break;
            case R.id.hinh3:
                image3.startAnimation(buttonAnimation.startAnimation());
                getImageFromAlbum();
                viTri=3;
                danhDauVitri();
                break;
            case R.id.hinh4:
                image4.startAnimation(buttonAnimation.startAnimation());
                getImageFromAlbum();
                viTri=4;
                danhDauVitri();
                break;
            case R.id.hinh5:
                image5.startAnimation(buttonAnimation.startAnimation());
                getImageFromAlbum();
                viTri=5;
                danhDauVitri();
                break;
        }
    }
    private void danhDauVitri(){
        if (viTri<soAnh){
            soAnh--;
        }else if (soAnh==6){
            soAnh--;
        }
    }
    private void getImageFromAlbum(){
        Utils.openAbum(activity,this,KET_QUA);
    }

    private Uri uri;
    private int KET_QUA=10;
    private List<Uri>listUri=new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==KET_QUA &&resultCode==activity.RESULT_OK&&data!=null){
            uri=data.getData();
            getImageFromActivity(uri);
        }
    }

    @Override
    public void traVeKetQua(Object b) {
        if (b.toString().equals("1")){
            myLoadAppProgessBar.dismiss();
            Toast.makeText(activity,"Đăng bài thành công",Toast.LENGTH_SHORT).show();
            activity.finish();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (SharePreseventConstant.getInstance(activity).getCheckDangBai().equals("1")){
            BeginAsync beginAsync=new BeginAsync(this);
            beginAsync.execute(Constants.PORT+"/xoabaidang/"+idUser);
        }
    }

    @Override
    public void onEror() {

    }

    @Override
    public void onSucess(String result) {
        Toast.makeText(activity, "Đăng bài thất bại"+result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String url) {
        xuLySuKien.sendToServer(CLIENT_SEND_URL_IMAGE_BAI_DANG,idUser+"-"+url);
        xuLySuKien.serverSendData(SERVER_SEND_RESULT_DANG_BAI);
        SharePreseventConstant.getInstance(activity).setCheckDangBai("0");
    }

    @Override
    public void err() {

    }

    @Override
    public void onResultErr() {
        Toast.makeText(activity, activity.getString(R.string.chua_chon_anh), Toast.LENGTH_SHORT).show();
    }
}
