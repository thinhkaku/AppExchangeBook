package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.ListAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Singleton;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork.XuLySuKien;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.MainView;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 12/28/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainView{
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private List<BaiDang> arrListView=new ArrayList<>();
    private ListAdapter listAdapter;
    private Animation animationFabButton1,animationFabButton2,animationFabButton3,animationFabButton,animationFabButtonXoayNguoc,animationFabButton1XoayNguoc,animationFabButton2XoayNguoc,animationFabButton3XoayNguoc;
    private  boolean ktHienMenu=true;
    private User user;
    private boolean ktImageFabButton=true;
    private FloatingActionButton fab,fab1,fab2,fab3;
    private CircleImageView circleImageView;
    private TextView btnVeTrangChu,btnRate, btnGioiThieu, btnThongTinTaiTKhoan,txtName,btnDangXuat,txtGmail;
    private LinearLayout lnearRefesh,lnearDangBai;
    private MaterialSearchView materialSearchView;
    private Animation animationCoTinMoi;
    private Handler handler;
    private Runnable runnable;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBarLoadNews;
    private XuLySuKien xuLySuKien;
    private int page;
    private ImageView imgHinhDaiDienDangTin1;
    private Socket socket;


    {
        if (socket==null){
            try {
                socket = IO.socket(Constants.PORT);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            socket.connect();
            Singleton.Instance().setmSocket(socket);
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        page=1;
        xuLySuKien=new XuLySuKien(this,MainActivity.this);
        xuLySuKien.serverSendData(Constants.SERVER_SEND_REQUEST_BAIDANG);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        user= Util.getUser(this);
        initFloatingButton();
        initView();
        reCyClerView();
        addEvent();

    }

    private void processCheck(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                checkSearchView();
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
    private void checkSearchView(){
        if (materialSearchView.isSearchOpen()){
            toolbar.setVisibility(View.INVISIBLE);
        }else {
            toolbar.setVisibility(View.VISIBLE);
            handler.removeCallbacks(runnable);
        }
    }


    private void initFloatingButton() {
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab1=(FloatingActionButton)findViewById(R.id.fab1);
        fab2=(FloatingActionButton)findViewById(R.id.fab2);
        fab3=(FloatingActionButton)findViewById(R.id.fab3);
        animationFabButton1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbutton1_anim);
        animationFabButton2=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbutton2_anim);
        animationFabButton3=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbutton3_anim);
        animationFabButton=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbutton_anim);
        animationFabButtonXoayNguoc=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbuttonxoaynguoc_anim);
        animationFabButton1XoayNguoc=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbutton1_anim_xoaynguoc);
        animationFabButton2XoayNguoc=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbutton2_anim_xoaynguoc);
        animationFabButton3XoayNguoc=AnimationUtils.loadAnimation(MainActivity.this,R.anim.floatingbutton3_anim_xoaynguoc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktImageButton();
                hienMenu();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,NewsStorePersonalActivity.class);
                startActivity(intent);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdapter.refetchBaiDang();
            }
        });

    }

    private void ktImageButton(){
        if (ktImageFabButton){
            fab.startAnimation(animationFabButton);
            fab.setImageResource(R.drawable.ic_remove_circle_outline_black_24dp);
            ktImageFabButton=false;
        }else {
            fab.startAnimation(animationFabButtonXoayNguoc);
            fab.setImageResource(R.drawable.ic_control_point_black_24dp);
            ktImageFabButton=true;
        }
    }
    private void hienMenu(){
        if (ktHienMenu){
            fab1.show();
            fab2.show();
            fab3.show();
            fab1.startAnimation(animationFabButton1);
            fab2.startAnimation(animationFabButton2);
            fab3.startAnimation(animationFabButton3);
            ktHienMenu=false;
        }else {
            fab1.startAnimation(animationFabButton1XoayNguoc);
            fab2.startAnimation(animationFabButton2XoayNguoc);
            fab3.startAnimation(animationFabButton3XoayNguoc);
            fab1.hide();
            fab2.hide();
            fab3.hide();
            ktHienMenu=true;
        }

    }

    private void reCyClerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        listAdapter = new ListAdapter(arrListView, this,user,page,swipeRefreshLayout, progressBarLoadNews);
        recyclerView.setAdapter(listAdapter);
        listAdapter.getBaiDang();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_item,menu);
        MenuItem item=menu.findItem(R.id.btnSearch);
        materialSearchView.setMenuItem(item);

        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                processCheck();
//                listAdapter.upDateData(newText);
//                listAdapter.notifyDataSetChanged();
                return true;
            }
        });
        return true;

    }

    private void initView()
    {
        progressBarLoadNews = (ProgressBar) findViewById(R.id.progressBarLoadNews);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        animationCoTinMoi= AnimationUtils.loadAnimation(MainActivity.this,R.anim.co_tin_moi_animation);
        materialSearchView=findViewById(R.id.btnMaterSearchView);
        materialSearchView.setVoiceSearch(false);
        materialSearchView.setCursorDrawable(R.drawable.custom_cursor);
        circleImageView=(CircleImageView)findViewById(R.id.profile_image);
        Glide.with(MainActivity.this).load(Constants.PORT+user.getAnhDaiDien()).into(circleImageView);

        btnVeTrangChu=(TextView)findViewById(R.id.btnVeTrangChu);
        btnDangXuat=(TextView)findViewById(R.id.btnDangXuat);
        lnearRefesh=(LinearLayout) findViewById(R.id.lnearRefesh);
        lnearDangBai=(LinearLayout) findViewById(R.id.lnearDangBai);

        txtName=(TextView)findViewById(R.id.txtLogin);
        txtGmail=(TextView)findViewById(R.id.txtGmail);
        imgHinhDaiDienDangTin1=(ImageView) findViewById(R.id.imgHinhDaiDienDangTin1);
        txtGmail.setText(user.getEmail());
        btnThongTinTaiTKhoan=(TextView)findViewById(R.id.btnThongTinTaiKhoan);
        btnGioiThieu=(TextView)findViewById(R.id.btnGioiThieu);
        btnRate=(TextView)findViewById(R.id.btnRate);
//        Typeface typeface=Typeface.createFromAsset(getAssets(),"TrefayDemo.ttf");
//        btnDangTin.setTypeface(typeface);
//        btnGioiThieu.setTypeface(typeface);
//        btnRate.setTypeface(typeface);
//        btnDangXuat.setTypeface(typeface);
//        btnThongTinTaiTKhoan.setTypeface(typeface);
        txtName.setText(user.getTen());
        Glide.with(this).load(Constants.PORT+user.getAnhDaiDien()).into(imgHinhDaiDienDangTin1);
    }
    private void addEvent() {
        btnVeTrangChu.setOnClickListener(this);
        btnRate.setOnClickListener(this);
        btnGioiThieu.setOnClickListener(this);
        btnThongTinTaiTKhoan.setOnClickListener(this);
        btnDangXuat.setOnClickListener(this);
        lnearRefesh.setOnClickListener(this);
        lnearDangBai.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lnearDangBai:
                lnearDangBai.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                Intent intent=new Intent(MainActivity.this,NewsPostActivity.class);
                startActivity(intent);
                break;
            case R.id.lnearRefesh:
                lnearRefesh.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                //loadDilog.initDilogLoading(MainActivity.this);
                lnearRefesh.clearAnimation();
                lnearRefesh.setVisibility(View.INVISIBLE);
                listAdapter.refetchBaiDang();
                //xuLyEventGetBaiDang.getBaiDang();

                break;
            case R.id.btnVeTrangChu:
                btnVeTrangChu.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                drawerLayout.closeDrawers();
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case R.id.btnGioiThieu:
                btnGioiThieu.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                drawerLayout.closeDrawers();
                Intent intent1=new Intent(MainActivity.this,IntroduceActivity.class);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                break;
            case R.id.btnRate:
                btnRate.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                Toast.makeText(MainActivity.this,width+"+"+height,Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                break;
            case R.id.btnThongTinTaiKhoan:
                btnThongTinTaiTKhoan.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                drawerLayout.closeDrawers();
                Intent intent3=new Intent(MainActivity.this,ThongTinTaiKhoanActivity.class);
                startActivity(intent3);
                break;
            case R.id.btnDangXuat:
                btnDangXuat.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                drawerLayout.closeDrawers();
                Intent intent4=new Intent(MainActivity.this, Login.class);
                startActivity(intent4);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (materialSearchView.isSearchOpen()){
            materialSearchView.closeSearch();
        }else {
            finish();
        }
    }



    @Override
    public void traVeKetQua(Object b) {
        if (b.toString().equals("1")){
            lnearRefesh.setVisibility(View.VISIBLE);
            lnearRefesh.startAnimation(animationCoTinMoi);
        }
    }
}
