package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicBookHomePageFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.BookOptionsActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.BookDetailActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.AdvertisementAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.BookAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class BookHomePageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, ViewBookHomePageFragment{
    private ArrayList<String>arrUrlHinh;
    private AdvertisementAdapter advertisementAdapter;
    private ViewPager viewPagerAdvertisement;
    private RecyclerView recyclerSachMoiNhat,recyclerSachMuaNhieu,recyclerSachKinhDien,recyclerSachTamLy,recyclerSachKinhTe,recyclerSachVanHoc,recyclerSachTruyenTranh,recyclerSachTuoiTeen,recyclerSachNuoiDayTre,recyclerSachKhoaHoc,recyclerSachNuocNgoai;
    private Activity activity;
    private ProgressBar progressBarSachMoi,progressBarKinhDien,progressBarKinhTe,progressBarVanHoc,progressBarTamLy,progressBarTruyenTranh,progressBarTuoiTeen,progressBarNuoiDayTre,progressBarKhoaHoc,progressBarNuocNgoai;
    private ProgressBar progressBarSachMuaNhieu;
    private SwipeRefreshLayout swipeRefreshLayoutTrangChu;
    private Handler handler;
    private TextView btnXemThemMn;
    private ImageView imgAdvertisement;
    private Runnable runnable;
    private SOService soService;
    private BookAdapter bookAdapter;
    private PresenterLogicBookHomePageFragment presenterLogicBookHomePageFragment;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public void onPause() {
        super.onPause();
        removeRunable();
    }

    private void removeRunable(){
        handler.removeCallbacks(runnable);
    }

    public void initData() {
        presenterLogicBookHomePageFragment.getBookNew(progressBarSachMoi);
        presenterLogicBookHomePageFragment.getBookPurchaseedMuch(progressBarSachMuaNhieu);
        presenterLogicBookHomePageFragment.getBookClassic(progressBarKinhDien);
        presenterLogicBookHomePageFragment.getBookEconomy(progressBarKinhTe);
        presenterLogicBookHomePageFragment.getBookMentality(progressBarTamLy);
        presenterLogicBookHomePageFragment.getBookLiterary(progressBarVanHoc);
        presenterLogicBookHomePageFragment.getComic(progressBarTruyenTranh);
        presenterLogicBookHomePageFragment.getBookYouth(progressBarTuoiTeen);
        presenterLogicBookHomePageFragment.getBookRaisingChildren(progressBarNuoiDayTre);
        presenterLogicBookHomePageFragment.getScience(progressBarKhoaHoc);
        presenterLogicBookHomePageFragment.getBookForeign(progressBarNuocNgoai);
    }

    @Override
    public void onRefresh() {
        initData();
        swipeRefreshLayoutTrangChu.setRefreshing(false);
    }



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_home_page,container,false);
        viewPagerAdvertisement=view.findViewById(R.id.viewPagerAdvertisement);
        recyclerSachMoiNhat=view.findViewById(R.id.recycleSachMoi);
        swipeRefreshLayoutTrangChu=view.findViewById(R.id.swipeRefreshTrangChu);
        recyclerSachMuaNhieu=view.findViewById(R.id.recycleBanChay);
        recyclerSachKinhDien=view.findViewById(R.id.recycleSachKinhDien);
        recyclerSachKinhTe=view.findViewById(R.id.recycleSachKinhTe);
        recyclerSachTamLy=view.findViewById(R.id.recycleSachTamLy);
        recyclerSachVanHoc=view.findViewById(R.id.recycleSachVanHoc);
        recyclerSachTruyenTranh=view.findViewById(R.id.recycleSachTruyenTranh);
        recyclerSachTuoiTeen=view.findViewById(R.id.recycleSachTuoiTeen);
        recyclerSachNuoiDayTre=view.findViewById(R.id.recycleSachNuoiDayTre);
        recyclerSachKhoaHoc=view.findViewById(R.id.recycleSachKhoaHoc);
        recyclerSachNuocNgoai=view.findViewById(R.id.recycleSachNuocNgoai);
        progressBarSachMoi=view.findViewById(R.id.progressBarSachMoi);
        progressBarSachMuaNhieu=view.findViewById(R.id.progressBarSachMuaNhieu);
        progressBarKinhDien=view.findViewById(R.id.progressBarSachKinhDien);
        progressBarKinhTe=view.findViewById(R.id.progressBarSachKinhTe);
        progressBarTamLy=view.findViewById(R.id.progressBarSachTamLy);
        progressBarVanHoc=view.findViewById(R.id.progressBarSachVanHoc);
        progressBarTruyenTranh=view.findViewById(R.id.progressBarSachTruyenTranh);
        progressBarTuoiTeen=view.findViewById(R.id.progressBarSachTuoiTeen);
        progressBarNuoiDayTre=view.findViewById(R.id.progressBarSachNuoiDayTre);
        progressBarKhoaHoc=view.findViewById(R.id.progressBarSachKhoaHoc);
        progressBarNuocNgoai=view.findViewById(R.id.progressBarSachNuocNgoai);
        imgAdvertisement=view.findViewById(R.id.imgAdvertisement);
        initView(view);
        Utils.initRecycleBookHomePage(recyclerSachMoiNhat,activity);
        Utils.initRecycleBookHomePage(recyclerSachMuaNhieu,activity);
        Utils.initRecycleBookHomePage(recyclerSachKinhDien,activity);
        Utils.initRecycleBookHomePage(recyclerSachKinhTe,activity);
        Utils.initRecycleBookHomePage(recyclerSachTamLy,activity);
        Utils.initRecycleBookHomePage(recyclerSachVanHoc,activity);
        Utils.initRecycleBookHomePage(recyclerSachTruyenTranh,activity);
        Utils.initRecycleBookHomePage(recyclerSachTuoiTeen,activity);
        Utils.initRecycleBookHomePage(recyclerSachNuoiDayTre,activity);
        Utils.initRecycleBookHomePage(recyclerSachKhoaHoc,activity);
        Utils.initRecycleBookHomePage(recyclerSachNuocNgoai,activity);
        initAction();
        soService= ApiUtils.getSOService();
        presenterLogicBookHomePageFragment=new PresenterLogicBookHomePageFragment(soService,this);
        initData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initAdvertisement();
        actionViewPagerAdvertisement();
        addEvent();
    }

    private void addEvent() {
        btnXemThemMn.setOnClickListener(this);
        btnNDT.setOnClickListener(this);
        btnBSL.setOnClickListener(this);
        btnKD.setOnClickListener(this);
        btnKH.setOnClickListener(this);
        btnKT.setOnClickListener(this);
        btnNN.setOnClickListener(this);
        btnTL.setOnClickListener(this);
        btnTT.setOnClickListener(this);
        btnTTeen.setOnClickListener(this);
        btnVH.setOnClickListener(this);
    }

    private void actionViewPagerAdvertisement() {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                actionAnimationImage();
                handler.postDelayed(this,10000);
            }
        };
        handler.post(runnable);
    }

    private void actionAnimationImage() {
        int viTri=viewPagerAdvertisement.getCurrentItem();
        viTri++;
        if (viTri==arrUrlHinh.size()){
            viTri=0;
        }
        viewPagerAdvertisement.setCurrentItem(viTri);
    }

    private void initAdvertisement() {
       arrUrlHinh=new ArrayList<>();
       arrUrlHinh.add("https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/h/a/hanh_trinh_ve_phuong_dong_2.jpg");
       arrUrlHinh.add("https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/k/h/kheo-an-noi-se-co-duoc-thien-ha.jpg");
       arrUrlHinh.add("https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/n/o/noi-the-nao-de-duoc-chao-don.jpg");
       arrUrlHinh.add("https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/n/x/nxbtre_full_21052016_100557-u547-d20161223-t153654-559899.u3059.d20170616.t102445.279969.jpg");
       advertisementAdapter =new AdvertisementAdapter(activity,arrUrlHinh);
       viewPagerAdvertisement.setAdapter(advertisementAdapter);
        Picasso.with(activity).load("https://cdn.pixabay.com/photo/2015/01/10/22/46/bookmarks-595776_960_720.jpg").into(imgAdvertisement);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.btnChonMua:
                Intent intent=new Intent(activity, BookDetailActivity.class);
                intent.putExtra(Constants.CHECK,true);
                startActivity(intent);
                break;
            case R.id.btnUaThich:

                break;
            case R.id.btnXemChiTiet:
                Intent intent1=new Intent(activity, BookDetailActivity.class);
                intent1.putExtra(Constants.CHECK,false);
                startActivity(intent1);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initAction() {
        swipeRefreshLayoutTrangChu.setOnRefreshListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private TextView btnBSL,btnKD,btnKT,btnTL,btnVH,btnTT,btnTTeen,btnNDT,btnKH,btnNN;

    private void initView(View view)
    {
        btnXemThemMn=view.findViewById(R.id.btnXemThemMn);
        btnBSL=view.findViewById(R.id.btnBSL);
        btnKD=view.findViewById(R.id.btnKD);
        btnKT=view.findViewById(R.id.btnKT);
        btnTL=view.findViewById(R.id.btnTL);
        btnVH=view.findViewById(R.id.btnVH);
        btnTT=view.findViewById(R.id.btnTT);
        btnTTeen=view.findViewById(R.id.btnTTeen);
        btnNDT=view.findViewById(R.id.btnNDT);
        btnKH=view.findViewById(R.id.btnKH);
        btnNN=view.findViewById(R.id.btnNN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnNN:
                nextToDS(11,btnNN);
                break;
            case R.id.btnKH:
                nextToDS(10,btnKH);
                break;
            case R.id.btnNDT:
                nextToDS(9,btnNDT);
                break;
            case R.id.btnTTeen:
                nextToDS(8,btnTTeen);
                break;
            case R.id.btnTT:
                nextToDS(7,btnTT);
                break;
            case R.id.btnVH:
                nextToDS(6,btnVH);
                break;
            case R.id.btnTL:
                nextToDS(5,btnTL);
                break;
            case R.id.btnKT:
                nextToDS(4,btnKT);
                break;
            case R.id.btnXemThemMn:
                nextToDS(1,btnXemThemMn);
                break;
            case R.id.btnBSL:
                nextToDS(2,btnBSL);
                break;
            case R.id.btnKD:
                nextToDS(3,btnKD);
                break;
        }
    }
    private void nextToDS(int idLS,TextView textView)
    {
        Utils.stAnimationButton(activity,textView);
        Intent intent=new Intent(activity, BookOptionsActivity.class);
        intent.putExtra(Constants.ID,idLS);
        startActivity(intent);
    }

    @Override
    public void onSuccessGetBookNew(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachMoiNhat.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookPurchaseedMuch(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachMuaNhieu.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookClassic(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachKinhDien.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookEconomy(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachKinhTe.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookMentality(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachTamLy.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookLiterary(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachVanHoc.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookComic(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachTruyenTranh.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookYouth(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachTuoiTeen.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookRaisingChildren(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachNuoiDayTre.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookScience(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachKhoaHoc.setAdapter(bookAdapter);
    }

    @Override
    public void onSuccessGetBookForeign(List<BiaSach> arrListBook) {
        bookAdapter =new BookAdapter(activity,arrListBook);
        recyclerSachNuocNgoai.setAdapter(bookAdapter);
    }
}
