package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.NewsPostPersonalAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.LoginSystemInterface;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.loginsytem.BeginAsync;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;

import java.util.ArrayList;

public class ThongTinTaiKhoanActivity extends AppCompatActivity implements LoginSystemInterface {
    private ImageView imgHDDThongTinTaiKhoan,imgHDDangBai;
    private TextView txtTenUserTK,txtDiaChiTk,txtEmailTK,txtSDTTK;
    private LinearLayout lnearDangTin;
    private User user;
    private Toolbar toolbar;
    private RecyclerView recyclerBaiDangCaNhan;
    private BeginAsync beginAsync;
    private NewsPostPersonalAdapter newsPostPersonalAdapter;
    private ArrayList<BaiDang>arrListView=new ArrayList<>();
    private MyLoadAppProgessBar myLoadAppProgessBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_information_user);
        toolbar=findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myLoadAppProgessBar=new MyLoadAppProgessBar(getApplicationContext());
        user= Util.getUser(this);
        initView();
        addEvent();

    }

    private void addEvent() {
        lnearDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThongTinTaiKhoanActivity.this,NewsPostActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }else if (item.getItemId()==R.id.btnMessess){
           Intent intent=new Intent(ThongTinTaiKhoanActivity.this,MessageActivity.class);
           startActivity(intent);
           finish();
        }else if (item.getItemId()==R.id.btnGio){
           Intent intent=new Intent(ThongTinTaiKhoanActivity.this,NewsStorePersonalActivity.class);
           startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_messess,menu);
        MenuItem item=menu.findItem(R.id.btnMessess);
        MenuItem item1=menu.findItem(R.id.btnGio);
        item.getActionView();
        item1.getActionView();
        return super.onCreateOptionsMenu(menu);

    }

    public void getBaiDang(){
        myLoadAppProgessBar.show();
        beginAsync=new BeginAsync(this);
        beginAsync.execute(Constants.PORT+Constants.GETBAIDANGCANHAN+user.getId());
    }

    private void initView() {
        getBaiDang();

        imgHDDThongTinTaiKhoan=findViewById(R.id.imgHDDThongTinTaiKhoan);
        txtSDTTK=findViewById(R.id.txtSDTTK);
        lnearDangTin=findViewById(R.id.lnearDangTin);
        txtTenUserTK=findViewById(R.id.txtTenUserTK);
        txtDiaChiTk=findViewById(R.id.txtDiaChiTk);
        txtEmailTK=findViewById(R.id.txtEmailTK);
        imgHDDangBai=findViewById(R.id.imgHDDangBai);
        Glide.with(ThongTinTaiKhoanActivity.this).load(Constants.PORT+user.getAnhDaiDien()).into(imgHDDThongTinTaiKhoan);
        Glide.with(ThongTinTaiKhoanActivity.this).load(Constants.PORT+user.getAnhDaiDien()).into(imgHDDangBai);
        txtDiaChiTk.setText(user.getDiaChi());
        txtEmailTK.setText(user.getEmail());
        txtSDTTK.setText("0"+user.getSdt());
        txtTenUserTK.setText(user.getTen());
        toolbar.setTitle(user.getTen());
        toolbar.setBackgroundColor(Color.BLACK);
    }

    private void reCyClerView() {
        recyclerBaiDangCaNhan=findViewById(R.id.recyclerBaiDangCaNhan);
        recyclerBaiDangCaNhan.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThongTinTaiKhoanActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerBaiDangCaNhan.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerBaiDangCaNhan.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerBaiDangCaNhan.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        //newsPostPersonalAdapter =new NewsPostPersonalAdapter(arrListView,this);
        //listAdapter = new ListAdapter(arrListView, this,user,0,null,null);
        recyclerBaiDangCaNhan.setAdapter(newsPostPersonalAdapter);

    }

    @Override
    public void onEror() {

    }


    @Override
    public void onSucess(String result) {
//        arrListView.clear();
//        try {
//            JSONArray jsonArray=new JSONArray(result);
//        for (int i=0;i<jsonArray.length();i++){
//            try {
//                JSONObject jsonObject =jsonArray.getJSONObject(i);
//                int idBaiDang=jsonObject.getInt("idBaiDang");
//                int idKhachhang=jsonObject.getInt("idKhachHang");
//                int sdt=jsonObject.getInt("sdt");
//                String ten=jsonObject.getString("ten");
//                String anhDaiDien=jsonObject.getString("anhDaiDien");
//                String tenSach=jsonObject.getString("tenSach");
//                String thoiGian=jsonObject.getString("thoiGian");
//                String noiDung=jsonObject.getString("noiDung");
//                String hinhNoiDung=jsonObject.getString("hinh");
//                int gia=jsonObject.getInt("gia");
//                int luotLike=jsonObject.getInt("luotLike");
//                arrListView.add(new BaiDang(idBaiDang,idKhachhang,sdt,ten,anhDaiDien,tenSach,thoiGian,noiDung,hinhNoiDung,gia,luotLike,false));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        int a=10;
//        while (a>=0){
//            for (int i=0;i<arrListView.size();i++){
//                for (int j=i+1;j<arrListView.size();j++){
//                    if (arrListView.get(i).getIdBaiDang()==arrListView.get(j).getIdBaiDang()){
//                        arrListView.remove(j);
//                    }
//                }
//            }
//            a--;
//        }
//        reCyClerView();
//        newsPostPersonalAdapter.notifyDataSetChanged();
//           loadDilog.dimisDilog();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
}
