package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicBookOptionsActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.BookOptionAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookOptionsActivity extends BaseActivity implements View.OnClickListener, ViewBookDetailOptionActivity {
    private String query;
    private SOService soService;
    private GridView recyclerDanhSach;
    private BookOptionAdapter bookOptionAdapter;
    private ImageButton btnBack,btnDanhMuc;
    private TextView txtQuery,btnSearchGianHang, txtLoaiS,txtTbTk;
    private Spinner spLoaiSx;
    private ArrayList<String>arrListSpLoaiSx;
    private List<BiaSach>arrBiaSach;
    private MaterialSearchView searchV_Ds;
    private Handler handler;
    private Runnable runnable;
    private LinearLayout lnearGianHang;
    private Toolbar toolbarDs;
    private Dialog dialogTraoDoi;
    private PresenterLogicBookOptionsActivity presenterLogicBookOptionsActivity;
    private MyLoadAppProgessBar myLoadAppProgessBar;

    @Override
    protected void requestAgain() {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void addEvent() {
        btnSearchGianHang.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnDanhMuc.setOnClickListener(this);
    }

    protected void initView() {
        setContentView(R.layout.activity_seach_book);
        toolbarDs=findViewById(R.id.toolbarDs);
        setSupportActionBar(toolbarDs);
        arrBiaSach=new ArrayList<>();
        searchV_Ds=findViewById(R.id.searchV_Ds);
        btnSearchGianHang=findViewById(R.id.btnSearchGianHang);
        txtQuery=findViewById(R.id.txtQuery);
        lnearGianHang=findViewById(R.id.lnearGianHang);
        spLoaiSx=findViewById(R.id.spLoaiSx);
        btnBack=findViewById(R.id.btnBack);
        txtLoaiS=findViewById(R.id.txtLoaiS);
        txtTbTk=findViewById(R.id.txtTbTk);
        btnDanhMuc=findViewById(R.id.btnDanhMuc);
        soService= ApiUtils.getSOService();
        myLoadAppProgessBar=new MyLoadAppProgessBar(this);
        presenterLogicBookOptionsActivity=new PresenterLogicBookOptionsActivity(this,soService,myLoadAppProgessBar);
        initSpLoaiSx();
        initData();
        addEvent();
    }



    private void initSpLoaiSx()
    {
        arrListSpLoaiSx=new ArrayList<>();
        arrListSpLoaiSx.add(getString(R.string.tang_dan));
        arrListSpLoaiSx.add(getString(R.string.giam_dan));
        final ArrayAdapter<String> listLuaChon=new ArrayAdapter<>(this, R.layout.custom_text_spinner, arrListSpLoaiSx);
        spLoaiSx.setAdapter(listLuaChon);
        listLuaChon.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spLoaiSx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(arrBiaSach.size()!=0){
                     if (position==0){
                        Collections.sort(arrBiaSach, new Comparator<BiaSach>() {
                            @Override
                            public int compare(BiaSach o1, BiaSach o2) {
                                if (o1.getGiaS()<o2.getGiaS()){
                                    return -1;
                                }else {
                                    if (o1.getGiaS()==o2.getGiaS()){
                                        return 0;
                                    }else {
                                        return 1;
                                    }
                                }
                            }
                        });
                    }else if (position==1){
                        Collections.sort(arrBiaSach, new Comparator<BiaSach>() {
                            @Override
                            public int compare(BiaSach o1, BiaSach o2) {
                                if (o1.getGiaS()<o2.getGiaS()){
                                    return 1;
                                }else {
                                    if (o1.getGiaS()==o2.getGiaS()){
                                        return 0;
                                    }else {
                                        return -1;
                                    }
                                }
                            }
                        });
                    }
                    bookOptionAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main2,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.btnChonMua:
                Intent intent=new Intent(this, BookDetailActivity.class);
                intent.putExtra(Constants.CHECK,true);
                startActivity(intent);
                break;
            case R.id.btnUaThich:

                break;
            case R.id.btnXemChiTiet:
                Intent intent1=new Intent(this, BookDetailActivity.class);
                intent1.putExtra(Constants.CHECK,false);
                startActivity(intent1);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void getSeach(String query)
    {
        txtQuery.setText(query);
        presenterLogicBookOptionsActivity.getSearchBook(query);
    }

    private void initData() {
        recyclerDanhSach=findViewById(R.id.gvDanhSach);
        Intent intent=getIntent();
        query=intent.getStringExtra(Constants.SEARCH);
        txtQuery.setText(query);
        int idLS=intent.getIntExtra(Constants.ID,-1);
        if (query!=null){
            getSearchQuery(query);
        }
        if (idLS!=-1){
            txtLoaiS.setText(Util.loaiSach(idLS));
            getSachTheoLoai(idLS);
        }
    }



    private void getSachTheoLoai(int idLS)
    {
        presenterLogicBookOptionsActivity.getStyleBook(idLS);
    }



    private void getSearchQuery(final String query){
        getSeach(query);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnSearchGianHang:
                lnearGianHang.setVisibility(View.INVISIBLE);
                searchV_Ds.showSearch();
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnDanhMuc:
                initDialogDanhMuc();
                break;

        }
    }

    public void initDialogDanhMuc() {
        dialogTraoDoi = new Dialog(BookOptionsActivity.this,android.R.style.Theme_DeviceDefault_Dialog_MinWidth);
        dialogTraoDoi.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogTraoDoi.setContentView(R.layout.dilog_option_book);
        dialogTraoDoi.setCancelable(true);
        final ImageButton btnCancle=dialogTraoDoi.findViewById(R.id.btnCancle);
        final TextView btnSM=dialogTraoDoi.findViewById(R.id.btnSM);
        final TextView btnBSL=dialogTraoDoi.findViewById(R.id.btnBSL);
        final TextView btnKD=dialogTraoDoi.findViewById(R.id.btnKD);
        final TextView btnKT=dialogTraoDoi.findViewById(R.id.btnKT);
        final TextView btnTL=dialogTraoDoi.findViewById(R.id.btnTL);
        final TextView btnVH=dialogTraoDoi.findViewById(R.id.btnVH);
        final TextView btnTT=dialogTraoDoi.findViewById(R.id.btnTT);
        final TextView btnTTeen=dialogTraoDoi.findViewById(R.id.btnTTeen);
        final TextView btnNDT=dialogTraoDoi.findViewById(R.id.btnNDT);
        final TextView btnKH=dialogTraoDoi.findViewById(R.id.btnKH);
        final TextView btnNN=dialogTraoDoi.findViewById(R.id.btnNN);
        btnNN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(11,btnNN);
            }
        });
        btnKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(10,btnKH);
            }
        });
        btnNDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(9,btnNDT);
            }
        });
        btnTTeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(8,btnTTeen);
            }
        });
        btnTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(7,btnTT);
            }
        });
        btnVH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(6,btnVH);
            }
        });
        btnTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(5,btnTL);
            }
        });
        btnKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(4,btnKT);
            }
        });
        btnKD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(3,btnKD);
            }
        });
        btnBSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(2,btnBSL);
            }
        });
        btnSM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog(1,btnSM);
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.stAnimationButton(BookOptionsActivity.this,btnCancle);
                dialogTraoDoi.dismiss();
            }
        });
        dialogTraoDoi.show();
    }

    private void requestDialog(int idLS,TextView textView)
    {
        Utils.stAnimationButton(BookOptionsActivity.this,textView);
        dialogTraoDoi.dismiss();
        refeshText(idLS);
        getSachTheoLoai(idLS);
    }

    private void refeshText(int idLs){
        txtQuery.setText("");
        txtLoaiS.setText(Util.loaiSach(idLs));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_ds_sach,menu);
        MenuItem item=menu.findItem(R.id.btnSearchdsSach);
        searchV_Ds.setMenuItem(item);
        searchV_Ds.setVoiceSearch(true);
        searchV_Ds.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtLoaiS.setText(getString(R.string.tat_ca_sach));
                getSearchQuery(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                processCheck();
                return true;
            }
        });


        return true;

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
        if (searchV_Ds.isSearchOpen()){
            lnearGianHang.setVisibility(View.INVISIBLE);
        }else {
            lnearGianHang.setVisibility(View.VISIBLE);
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onSuccessGetSearch(List<BiaSach> arrListS) {
        txtTbTk.setVisibility(View.INVISIBLE);
        arrBiaSach=arrListS;
        if (arrBiaSach.size()==0){
            txtTbTk.setVisibility(View.VISIBLE);
        }else {
            txtTbTk.setVisibility(View.INVISIBLE);
        }
        Collections.sort(arrBiaSach, new Comparator<BiaSach>() {
            @Override
            public int compare(BiaSach o1, BiaSach o2) {
                if (o1.getGiaS()<o2.getGiaS()){
                    return -1;
                }else {
                    if (o1.getGiaS()==o2.getGiaS()){
                        return 0;
                    }else {
                        return 1;
                    }
                }
            }
        });
        bookOptionAdapter =new BookOptionAdapter(arrBiaSach,BookOptionsActivity.this);
        recyclerDanhSach.setAdapter(bookOptionAdapter);
    }

    @Override
    public void onSuccessGetStyleBook(List<BiaSach> arrListS) {
        arrBiaSach.clear();
        arrBiaSach=arrListS;
        if (arrBiaSach.size()==0){
            txtTbTk.setVisibility(View.VISIBLE);
        }else {
            txtTbTk.setVisibility(View.INVISIBLE);
        }
        Collections.sort(arrBiaSach, new Comparator<BiaSach>() {
            @Override
            public int compare(BiaSach o1, BiaSach o2) {
                if (o1.getGiaS()<o2.getGiaS()){
                    return -1;
                }else {
                    if (o1.getGiaS()==o2.getGiaS()){
                        return 0;
                    }else {
                        return 1;
                    }
                }
            }
        });
        bookOptionAdapter =new BookOptionAdapter(arrBiaSach,BookOptionsActivity.this);
        recyclerDanhSach.setAdapter(bookOptionAdapter);
    }

    @Override
    public void err() {
        txtTbTk.setVisibility(View.VISIBLE);
    }
}
