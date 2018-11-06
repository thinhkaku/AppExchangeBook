package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork.XuLySuKien;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.ResultUploadImage;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Message;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.MainView;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewDetailMessageFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class PresenterLogicDetailMessageFragment implements PresenterImpDetailMessageFragment, MainView {
    private ViewDetailMessageFragment viewDetailMessageFragment;
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private XuLySuKien xuLySuKien;
    private String idGroup;
    private User user;
    private boolean checkEvent;

    public PresenterLogicDetailMessageFragment(Context context,String idGroup,User user, ViewDetailMessageFragment viewDetailMessageFragment, SOService soService, MyLoadAppProgessBar myLoadAppProgessBar, String idPhong) {
        this.viewDetailMessageFragment = viewDetailMessageFragment;
        this.soService = soService;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
        this.idGroup=idGroup;
        this.user=user;
        xuLySuKien=new XuLySuKien(this,(Activity) context);
        xuLySuKien.sendToServer(Constants.CLIENT_SEND_REQUEST_TIN_NHAN,idPhong+"");
        xuLySuKien.serverSendData(Constants.SERVER_SEND_RESULT_TIN_NHAN);
        checkEvent=true;
    }

    public void getData(){
        if (checkEvent==true){
            xuLySuKien.sendToServer(Constants.CLIENT_SEND_REQUEST_TIN_NHAN,idGroup+"");
            xuLySuKien.serverSendData(Constants.SERVER_SEND_RESULT_TIN_NHAN);
        }
    }

    @Override
    public void upLoadImage(Uri uri, Context context) {
        myLoadAppProgessBar.show();
        soService.uploadImage(Utils.getBody(context,uri)).enqueue(new Callback<ResultUploadImage>() {
            @Override
            public void onResponse(Call<ResultUploadImage> call, retrofit2.Response<ResultUploadImage> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    xuLySuKien.sendToServer(Constants.CLIENT_SEND_REQUEST_GUI_TIN_NHAN,idGroup+"-"+user.getId()+"-"+response.body().getName());
                    viewDetailMessageFragment.onSuccessUploadInage();
                    myLoadAppProgessBar.dismiss();
                }else {
                    viewDetailMessageFragment.onErr();
                    myLoadAppProgessBar.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResultUploadImage> call, Throwable t) {
                call.clone().enqueue(this);
                viewDetailMessageFragment.onErr();
                myLoadAppProgessBar.dismiss();
            }
        });
    }

    @Override
    public void sendMessage(Context context, ArrayList<Message> arrMessage, String content, int idCustommer) {
        if (content.isEmpty()){
            viewDetailMessageFragment.onErrSendMessage();
        }else {
            if (arrMessage.size()==0){
                String api= Constants.PORT+Constants.INSERT_NHOM_CHAT+idCustommer+"/"+user.getId();
                StringRequest stringRequest=new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue= Volley.newRequestQueue(context);
                requestQueue.add(stringRequest) ;
            }
            xuLySuKien.sendToServer(Constants.CLIENT_SEND_REQUEST_GUI_TIN_NHAN,idGroup+"-"+user.getId()+"-"+content);
        }
    }


    @Override
    public void traVeKetQua(Object b) {
        ArrayList<Message>arrMessage=new ArrayList<>();
        JSONArray jsonArray = (JSONArray) b;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("idMessage");
                int idKhachHang = jsonObject.getInt("idKhachHang");
                String anhDaiDien = jsonObject.getString("anhDaiDien");
                String thoiGian = jsonObject.getString("time");
                String noiDung = jsonObject.getString("content");
                arrMessage.add(new Message(id, idKhachHang, anhDaiDien, thoiGian, noiDung));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        viewDetailMessageFragment.onResultMessage(arrMessage);
    }
}
