package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.MainBookPagerAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Singleton;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.GetDataFromSqlite;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.PersonalCustomerFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.BookStoreFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.NotificationFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.BookHomePageFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ExchangeFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.net.URISyntaxException;

public class MainBookActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ViewPager viewPagerGianHang;
    private BookHomePageFragment bookHomePageFragment;
    private BookStoreFragment bookStoreFragment;
    private PersonalCustomerFragment personalCustomerFragment;
    private NotificationFragment notificationFragment;
    private ExchangeFragment exchangeFragment;
    private MainBookPagerAdapter mainBookPagerAdapter;
    private MaterialSearchView materialSearchView;
    private Handler handler;
    private Runnable runnable;
    private  TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private ImageButton btnDrawable,btnMessage;
    private TextView btnSearchGianHang;
    private LinearLayout lnearGianHang;
    private Socket socket;
    private Dialog  dialogOptionBook;
    private View tab_2;
    //private UltimateTabLayout tabLayout;
    private TextView btnTrangChu,btnDanhMuc,btnkhuyenMai,btnBanChay,btnDanhGia,btnVeChungToi;

    @Override
    protected void requestAgain() {

    }


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





    private void initAction() {
        btnDrawable.setOnClickListener(this);
        btnSearchGianHang.setOnClickListener(this);
        btnVeChungToi.setOnClickListener(this);
        btnkhuyenMai.setOnClickListener(this);
        btnDanhMuc.setOnClickListener(this);
        btnDanhGia.setOnClickListener(this);
        btnBanChay.setOnClickListener(this);
        btnTrangChu.setOnClickListener(this);

        btnMessage.setOnClickListener(this);
    }

    private void initDrawLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void setUpViewPager() {
        bookHomePageFragment =new BookHomePageFragment();
        personalCustomerFragment =new PersonalCustomerFragment();
        bookStoreFragment =new BookStoreFragment();
        notificationFragment =new NotificationFragment();
        exchangeFragment =new ExchangeFragment();
        mainBookPagerAdapter.addFragment(bookHomePageFragment,R.drawable.ic_home_black_24dp);
        mainBookPagerAdapter.addFragment(notificationFragment,R.drawable.ic_notifications_active_black_24dp);
        mainBookPagerAdapter.addFragment(bookStoreFragment,R.drawable.ic_local_grocery_store_black_24dp);
        mainBookPagerAdapter.addFragment(personalCustomerFragment,R.drawable.ic_person_black_24dp);
        mainBookPagerAdapter.addFragment(exchangeFragment,R.drawable.ic_info_black_24dp);
        viewPagerGianHang.setAdapter(mainBookPagerAdapter);
        viewPagerGianHang.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPagerGianHang);
        mainBookPagerAdapter.notifyDataSetChanged();
        setupTabsIons();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupTabsIons() {
        final View tab_3 = LayoutInflater.from(this).inflate(R.layout.custom_tablayout_gian_hang, null);
        ((ImageView) tab_3.findViewById(R.id.imgTag)).setImageResource(R.drawable.custom_home);
        ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleX((float) 1.3);
        ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleY((float) 1.3);
        ((TextView) tab_3.findViewById(R.id.txtPage)).setText(getString(R.string.trang_chu));
        ((TextView) tab_3.findViewById(R.id.txtPage)).setVisibility(View.VISIBLE);

        final View tab_1 = LayoutInflater.from(this).inflate(R.layout.custom_tablayout_gian_hang, null);
        ((ImageView) tab_1.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_notifications_active_black_24dp);
        ((TextView) tab_1.findViewById(R.id.txtNotification)).setVisibility(View.VISIBLE);
        ((TextView) tab_1.findViewById(R.id.txtNotification)).setText("10");


        tab_2 = LayoutInflater.from(this).inflate(R.layout.custom_tablayout_gian_hang, null);
        ((ImageView) tab_2.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_local_grocery_store_black_24dp);
        ((TextView) tab_2.findViewById(R.id.txtNotification)).setVisibility(View.VISIBLE);

        getSizeStore();



        final View tab_4 = LayoutInflater.from(this).inflate(R.layout.custom_tablayout_gian_hang, null);
        ((ImageView) tab_4.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_person_black_24dp);

        final View tab_5 = LayoutInflater.from(this).inflate(R.layout.custom_tablayout_gian_hang, null);
        ((ImageView) tab_5.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_people_outline_black_24dp);



        tabLayout.getTabAt(0).setCustomView(tab_3).setIcon(R.drawable.custom_home);
        tabLayout.getTabAt(1).setCustomView(tab_1).setIcon(R.drawable.ic_notifications_active_black_24dp);
        tabLayout.getTabAt(2).setCustomView(tab_2).setIcon(R.drawable.ic_local_grocery_store_black_24dp);
        tabLayout.getTabAt(3).setCustomView(tab_4).setIcon(R.drawable.ic_person_black_24dp);
        tabLayout.getTabAt(4).setCustomView(tab_5).setIcon(R.drawable.ic_people_outline_black_24dp);
        viewPagerGianHang.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSizeStore();
                if (position==0){
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setImageResource(R.drawable.custom_home);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleX((float) 1.3);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleY((float) 1.3);
                    ((TextView) tab_3.findViewById(R.id.txtPage)).setText(getString(R.string.trang_chu));
                    ((TextView) tab_3.findViewById(R.id.txtPage)).setVisibility(View.VISIBLE);


                    ((TextView) tab_1.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_2.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_4.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_2.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_5.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);

                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_notifications_active_black_24dp);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_local_grocery_store_black_24dp);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_person_black_24dp);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_people_outline_black_24dp);
                }if (position==1){
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_home_black_24dp);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleX((float) 1.3);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleY((float) 1.3);
                    ((TextView) tab_3.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_2.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_4.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_5.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_1.findViewById(R.id.txtPage)).setText(getString(R.string.thong_bao));
                    ((TextView) tab_1.findViewById(R.id.txtPage)).setVisibility(View.VISIBLE);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setImageResource(R.drawable.custom_notifine);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_local_grocery_store_black_24dp);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_person_black_24dp);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_people_outline_black_24dp);
                }
                if (position==2){
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_home_black_24dp);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_notifications_active_black_24dp);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setImageResource(R.drawable.custom_store);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_person_black_24dp);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_people_outline_black_24dp);
                    ((TextView) tab_1.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_3.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_4.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_5.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleX((float) 1.3);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleY((float) 1.3);
                    ((TextView) tab_2.findViewById(R.id.txtPage)).setText(getString(R.string.giohang));
                    ((TextView) tab_2.findViewById(R.id.txtPage)).setVisibility(View.VISIBLE);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleY(1);
                }
                if (position==3){
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleX((float) 1.3);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleY((float) 1.3);
                    ((TextView) tab_1.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_2.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_3.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_5.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_4.findViewById(R.id.txtPage)).setText(getString(R.string.canhan));
                    ((TextView) tab_4.findViewById(R.id.txtPage)).setVisibility(View.VISIBLE);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_home_black_24dp);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_notifications_active_black_24dp);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_local_grocery_store_black_24dp);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setImageResource(R.drawable.custom_user);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_people_outline_black_24dp);
                }
                if (position==4){
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleX((float) 1.3);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setScaleY((float) 1.3);
                    ((TextView) tab_1.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_2.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_4.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_3.findViewById(R.id.txtPage)).setVisibility(View.INVISIBLE);
                    ((TextView) tab_5.findViewById(R.id.txtPage)).setText(getString(R.string.exchanged));
                    ((TextView) tab_5.findViewById(R.id.txtPage)).setVisibility(View.VISIBLE);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleX(1);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setScaleY(1);
                    ((ImageView) tab_3.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_home_black_24dp);
                    ((ImageView) tab_1.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_notifications_active_black_24dp);
                    ((ImageView) tab_2.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_local_grocery_store_black_24dp);
                    ((ImageView) tab_4.findViewById(R.id.imgTag)).setImageResource(R.drawable.ic_person_black_24dp);
                    ((ImageView) tab_5.findViewById(R.id.imgTag)).setImageResource(R.drawable.custom_people_outline);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void getSizeStore(){
        GetDataFromSqlite getDataFromSqlite=new GetDataFromSqlite();
        int size=getDataFromSqlite.getGioHang().size();
        if (size==0){
            ((TextView) tab_2.findViewById(R.id.txtNotification)).setVisibility(View.INVISIBLE);
        }else {
            ((TextView) tab_2.findViewById(R.id.txtNotification)).setVisibility(View.VISIBLE);
            ((TextView) tab_2.findViewById(R.id.txtNotification)).setText(size+"");
        }
    }


//    <com.athbk.ultimatetablayout.UltimateTabLayout
//    android:id="@+id/tabLayout"
//    android:layout_width="match_parent"
//    android:layout_height="?actionBarSize"
//    app:tab_orientation="horizontal"
//    app:tab_style="sliding"
//    app:tab_under_line_show="true"
//    app:tab_height_icon="24sp"
//    app:tab_width_icon="24sp"
//    app:tab_position_icon="icon_left"
//    app:tab_padding_left="10sp"
//    app:tab_padding_right="10sp"
//    app:tab_width="100sp"
//    app:tab_height="?actionBarSize"
//    app:tab_under_line_color="#FFFFFF"
//    app:tab_height_under_line="3sp"
//    android:layout_gravity="bottom"
//    android:background="#880E4F"
//            />


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initToolBar() {
        toolbar=findViewById(R.id.toolbarGianHang);
        setSupportActionBar(toolbar);
    }

    protected void initView() {
        setContentView(R.layout.activity_store);
        initToolBar();
        initDrawLayout();
        materialSearchView=findViewById(R.id.btnMaterSearchViewGianHang);
        mainBookPagerAdapter =new MainBookPagerAdapter(getSupportFragmentManager());
        viewPagerGianHang=findViewById(R.id.pagerGianHang);
        btnDrawable=findViewById(R.id.btnDrawable);
        btnSearchGianHang=findViewById(R.id.btnSearchGianHang);
        lnearGianHang=findViewById(R.id.lnearGianHang);
        btnMessage=findViewById(R.id.btnMessage);
        btnTrangChu=findViewById(R.id.btnTrangChu);
        btnBanChay=findViewById(R.id.btnBanChay);
        btnDanhGia=findViewById(R.id.btnDanhGia);
        btnDanhMuc=findViewById(R.id.btnDanhMuc);
        btnkhuyenMai=findViewById(R.id.btnkhuyenMai);
        btnVeChungToi=findViewById(R.id.btnVeChungToi);
        tabLayout=findViewById(R.id.tabLayout);

        setUpViewPager();
        initAction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_tin_nhan,menu);
        MenuItem item=menu.findItem(R.id.btnSearchTinNhan);
        materialSearchView.setMenuItem(item);
        materialSearchView.setVoiceSearch(true);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent =new Intent(MainBookActivity.this,BookOptionsActivity.class);
                intent.putExtra(Constants.SEARCH,query);
                intent.putExtra(Constants.CHECK,1);
                startActivity(intent);
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

    @Override
    protected void onPause() {
        super.onPause();
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
            lnearGianHang.setVisibility(View.INVISIBLE);
        }else {
            lnearGianHang.setVisibility(View.VISIBLE);
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main2,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDrawable:
               drawerLayout.openDrawer(Gravity.LEFT);
            break;
            case R.id.btnSearchGianHang:
                lnearGianHang.setVisibility(View.INVISIBLE);
                materialSearchView.showSearch();
                break;
            case R.id.btnMessage:
                if (Util.getUser(this)!=null){
                    Intent intent=new Intent(this,MessageActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, getString(R.string.not_login), Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnTrangChu:
                drawerLayout.closeDrawers();
                break;
            case R.id.btnDanhMuc:
                initDialogDanhMuc();
                drawerLayout.closeDrawers();
                break;
                case R.id.btnkhuyenMai:
                drawerLayout.closeDrawers();
                break;
            case R.id.btnBanChay:
                drawerLayout.closeDrawers();
                break;
            case R.id.btnDanhGia:
                drawerLayout.closeDrawers();
                break;
            case R.id.btnVeChungToi:
                drawerLayout.closeDrawers();
                break;

        }
    }

    public void initDialogDanhMuc() {
        dialogOptionBook = new Dialog(MainBookActivity.this,android.R.style.Theme_DeviceDefault_Dialog_MinWidth);
        dialogOptionBook.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogOptionBook.setContentView(R.layout.dilog_option_book);
        dialogOptionBook.setCancelable(true);
        final ImageButton btnCancle=dialogOptionBook.findViewById(R.id.btnCancle);
        final TextView btnSM=dialogOptionBook.findViewById(R.id.btnSM);
        final TextView btnBSL=dialogOptionBook.findViewById(R.id.btnBSL);
        final TextView btnKD=dialogOptionBook.findViewById(R.id.btnKD);
        final TextView btnKT=dialogOptionBook.findViewById(R.id.btnKT);
        final TextView btnTL=dialogOptionBook.findViewById(R.id.btnTL);
        final TextView btnVH=dialogOptionBook.findViewById(R.id.btnVH);
        final TextView btnTT=dialogOptionBook.findViewById(R.id.btnTT);
        final TextView btnTTeen=dialogOptionBook.findViewById(R.id.btnTTeen);
        final TextView btnNDT=dialogOptionBook.findViewById(R.id.btnNDT);
        final TextView btnKH=dialogOptionBook.findViewById(R.id.btnKH);
        final TextView btnNN=dialogOptionBook.findViewById(R.id.btnNN);
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
                Utils.stAnimationButton(MainBookActivity.this,btnCancle);
                dialogOptionBook.dismiss();
            }
        });
        dialogOptionBook.show();
    }

    private void requestDialog(int idLS,TextView textView)
    {
        Utils.stAnimationButton(MainBookActivity.this,textView);
        dialogOptionBook.dismiss();
        Intent intent=new Intent(this,BookOptionsActivity.class);
        intent.putExtra(Constants.ID,idLS);
        startActivity(intent);
    }


    private void dilogQuitApp(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.thong_bao));
        builder.setMessage(getString(R.string.question_quit_app));
        builder.setPositiveButton(getString(R.string.quit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAffinity();
            }
        });
        builder.setNegativeButton(getString(R.string.cancle), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {
        dilogQuitApp();
    }
}
