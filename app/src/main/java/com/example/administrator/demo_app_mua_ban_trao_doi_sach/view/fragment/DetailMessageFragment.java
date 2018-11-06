package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicDetailMessageFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.MesageAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Message;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;


public class DetailMessageFragment extends BaseFragment implements View.OnClickListener, ViewDetailMessageFragment {
    private ListView lvNoiDungTinNhan;
    private MesageAdapter mesageAdapter;
    private ImageButton btnGuiTinNhan,btnCamera;
    private ImageButton btnXoaHinhTinNhan;
    private EmojiconEditText edtNoiDungTinNhan;
    private FrameLayout frameImage;
    private Activity activity;
    private int  idKH;
    private String idPhong;
    private User user;
    private Context context;
    private ArrayList<Message> arrMessageF;
    private int KET_QUA=1;
    private Bitmap bitmap;
    private ImageView imgImageTinNhan;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private Uri uri;
    private SOService soService;
    private PresenterLogicDetailMessageFragment presenterLogicDetailMessageFragment;
    private int CAMERA_PIC_REQUEST=2;
    private Fragment mFragment;



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_content_message,container,false);
        lvNoiDungTinNhan=view.findViewById(R.id.lvNoiDungTinNhan);
        frameImage=view.findViewById(R.id.frameImage);
        btnGuiTinNhan=view.findViewById(R.id.btnGuiTinNhan);
        edtNoiDungTinNhan=view.findViewById(R.id.edtNoiDungTinNhan);
        btnCamera=view.findViewById(R.id.btnCamera);
        imgImageTinNhan=view.findViewById(R.id.imgImageTinNhan);
        btnXoaHinhTinNhan=view.findViewById(R.id.btnXoaHinhTinNhan);
        myLoadAppProgessBar=new MyLoadAppProgessBar(context);
        soService= ApiUtils.getSOService();
        mFragment=this;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenterLogicDetailMessageFragment!=null){
            presenterLogicDetailMessageFragment.getData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        user= Util.getUser(context);
        arrMessageF =new ArrayList<>();
        mesageAdapter =new MesageAdapter(activity, arrMessageF,user);
        lvNoiDungTinNhan.setAdapter(mesageAdapter);
        addEvent();
    }

    private void addEvent() {
        btnGuiTinNhan.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnXoaHinhTinNhan.setOnClickListener(this);
    }

    public void getIdPhong(String id, int idKH){
        myLoadAppProgessBar.show();
        this.idPhong=id;
        this.idKH=idKH;
        arrMessageF.clear();
        presenterLogicDetailMessageFragment=new PresenterLogicDetailMessageFragment(context,idPhong,user,this,soService,myLoadAppProgessBar,idPhong);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }


    public void updateData(String newtext){
        mesageAdapter.upDateTinNhan(newtext);
        mesageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuiTinNhan:
                if (bitmap==null){
                    String noiDung=edtNoiDungTinNhan.getText().toString();
                    presenterLogicDetailMessageFragment.sendMessage(context, arrMessageF,noiDung,idKH);
                        edtNoiDungTinNhan.setText("");
                }else {
                    upLoadHinh();
                    bitmap=null;
                    frameImage.setVisibility(View.INVISIBLE);
                }

                break;
            case R.id.btnCamera:
                Utils.initDilogSelectCamera(context, mFragment,CAMERA_PIC_REQUEST,KET_QUA);
                break;
            case R.id.btnXoaHinhTinNhan:
                bitmap=null;
                frameImage.setVisibility(View.INVISIBLE);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==KET_QUA &&resultCode==activity.RESULT_OK&&data!=null){
            uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(activity.getContentResolver(),uri);
                frameImage.setVisibility(View.VISIBLE);
                imgImageTinNhan.setImageBitmap(bitmap);
                Utils.scaleImage(activity,imgImageTinNhan);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CAMERA_PIC_REQUEST &&resultCode==activity.RESULT_OK&&data!=null) {
                bitmap= (Bitmap) data.getExtras().get("data");
                uri=Utils.getImageUri(context,bitmap);
                frameImage.setVisibility(View.VISIBLE);
                imgImageTinNhan.setImageBitmap(bitmap);
                Utils.scaleImage(activity,imgImageTinNhan);
        }
    }




    private void upLoadHinh(){
        if (uri!=null){
            presenterLogicDetailMessageFragment.upLoadImage(uri,context);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSuccessUploadInage() {
        edtNoiDungTinNhan.setText("");
    }

    @Override
    public void onErr() {

    }

    @Override
    public void onErrSendMessage() {
        Toast.makeText(activity,context.getString(R.string.chua_nhap_noi_dung),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResultMessage(ArrayList<Message> arrMessage) {
        arrMessageF.clear();
        arrMessageF.addAll(arrMessage);
        lvNoiDungTinNhan.setSelection(arrMessage.size()-1);
        myLoadAppProgessBar.dismiss();
    }
}
