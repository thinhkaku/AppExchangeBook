package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.CommentNewsAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.ImageNewsPostAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Singleton;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.SQLiteData;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.ResultUploadImage;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BinhLuan;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NumberLike;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork.XuLySuKien;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNewsActivity extends BaseActivity implements View.OnClickListener, MainView{
    Integer id;
    private RecyclerView recyclerView, recyclerBinhLuan;
    private ImageNewsPostAdapter listAdapter;
    private ArrayList<BinhLuan> arrBinhLuan = new ArrayList<>();
    private CommentNewsAdapter commentNewsAdapter;
    private List<Hinh> arrayList = new ArrayList<>();
    private BaiDang baiDang;
    private CircleImageView circleImageView, circleImageViewUser;
    private User user;
    private int giaTri;
    private Uri uri;
    private int checkLuu=0;
    private EditText edtNhapBinhLuan;
    private Button btnGuiBinhLuan, btnBinhLuanChiTiet, btnLike, btnGoi, btnLuu;
    private TextView txtTenNguoiDangChiTiet, txtThoiGianChiTiet, txtTenSachChiTiet, txtNoiDungChiTiet, txtGiaSachChiTiet;
    private String SERVER_SEND_RESULT_BINH_LUAN = "SERVER_SEND_RESULT_BINH_LUAN";
    private String CLIENT_REQUEST_LIST_BINH_LUAN = "CLIENT_REQUEST_LIST_BINH_LUAN";
    private String CLIENT_SEND_BINHLUAN = "CLIENT_SEND_BINHLUAN";
    private Toolbar toolbar;
    private XuLySuKien xuLySuKien;
   private MyLoadAppProgessBar myLoadAppProgessBar;
   private SQLiteData sqLiteData;
   private SOService soService;
   private ImageView imgHinhBinhLuan;
   private ImageButton btnCameraBinhLuan;
   private Bitmap bitmap;
   private boolean check;
    private int KET_QUA=6;


    @Override
    protected void requestAgain() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initSocket() {
        myLoadAppProgessBar.show();
        id = baiDang.getIdBaiDang();
        xuLySuKien=new XuLySuKien(this,DetailNewsActivity.this);
        xuLySuKien.sendToServer(CLIENT_REQUEST_LIST_BINH_LUAN, String.valueOf(id));
        xuLySuKien.serverSendData(SERVER_SEND_RESULT_BINH_LUAN);
    }

    private void addData() {
        Glide.with(DetailNewsActivity.this).load(Constants.PORTIMAGE + baiDang.getAnhDaiDien()).into(circleImageView);
        Glide.with(DetailNewsActivity.this).load(Constants.PORTIMAGE + user.getAnhDaiDien()).into(circleImageViewUser);
        toolbar.setTitle(baiDang.getTen());
        txtTenNguoiDangChiTiet.setText(baiDang.getTen());
        txtTenSachChiTiet.setText(baiDang.getTenSach());
        if (Utils.getDayCurent()==Integer.parseInt(Utils.getDay(baiDang.getThoiGian()))){
            txtThoiGianChiTiet.setText(Utils.formatHourM(baiDang.getThoiGian()));
        }else {
            txtThoiGianChiTiet.setText(Utils.formatThoiGian(baiDang.getThoiGian()));
        }
        txtNoiDungChiTiet.setText(baiDang.getNoiDung());
        txtGiaSachChiTiet.setText(getString(R.string.price)+ Utils.formatGia(baiDang.getGia()));
        btnLike.setText(baiDang.getLuotLike() + "");
        getLuotKike(baiDang.getIdBaiDang());

    }
    private void getLuotKike(int soLk){
        soService.getSoLuotLike(String.valueOf(soLk)).enqueue(new Callback<List<NumberLike>>() {
            @Override
            public void onResponse(Call<List<NumberLike>> call, retrofit2.Response<List<NumberLike>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    btnLike.setText(response.body().get(0).getSoLK()+"");
                }

            }

            @Override
            public void onFailure(Call<List<NumberLike>> call, Throwable t) {

            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        baiDang = (BaiDang) intent.getSerializableExtra(Constants.ID_BAI_DANG);
        giaTri = intent.getIntExtra(Constants.GIA_TRI, 0);
        checkLuu = intent.getIntExtra(Constants.CHECK_LUU, 0);
    }


    protected void initView() {
        setContentView(R.layout.activity_detail_news);
        toolbar = findViewById(R.id.toolbarChiTiet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myLoadAppProgessBar=new MyLoadAppProgessBar(this);
        soService= ApiUtils.getSOService();
        sqLiteData=new SQLiteData(this);
        getData();
        user= Util.getUser(this);
        initSocket();
        recyclerView2();
        recyclerView1();
        if (giaTri == 0) {
            recyclerBinhLuan.setVisibility(View.GONE);
        } else {
            recyclerBinhLuan.setVisibility(View.VISIBLE);
        }

        btnGoi = findViewById(R.id.btnGoi);
        btnLuu = findViewById(R.id.btnLuuChiTiet);
        if (checkLuu==1){
            btnLuu.setVisibility(View.GONE);
        }
        imgHinhBinhLuan = findViewById(R.id.imgHinhBinhLuan);
        btnCameraBinhLuan = findViewById(R.id.btnCameraBinhLuan);
        circleImageView = findViewById(R.id.imgHinhDaiDienChitiet);
        circleImageViewUser = findViewById(R.id.imgHinhDaiDienUserChitiet);
        txtNoiDungChiTiet = findViewById(R.id.txtNoiDungChiTiet);
        txtTenSachChiTiet = findViewById(R.id.txtTenSachChiTiet);
        txtThoiGianChiTiet = findViewById(R.id.txtThoiGianChiTiet);
        txtTenNguoiDangChiTiet = findViewById(R.id.txtTenNguoiDangChiTiet);
        txtGiaSachChiTiet = findViewById(R.id.txtGiaSachChiTiet);
        edtNhapBinhLuan = findViewById(R.id.edtBinhLuan);
        btnGuiBinhLuan = findViewById(R.id.btnGuiBinhLuan);
        btnGuiBinhLuan.setOnClickListener(this);
        btnBinhLuanChiTiet = findViewById(R.id.btnBinhLuanChiTiet);
        btnBinhLuanChiTiet.setOnClickListener(this);
        btnLike.setOnClickListener(this);
        btnGoi.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        btnCameraBinhLuan.setOnClickListener(this);
        imgHinhBinhLuan.setOnClickListener(this);
        addData();
    }

    private void recyclerView1() {

        btnLike = findViewById(R.id.btnLikeChiTiet);
        recyclerView = findViewById(R.id.recyclerHinh);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailNewsActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);

        soService.getHinhAnh(String.valueOf(id)).enqueue(new Callback<List<Hinh>>() {
            @Override
            public void onResponse(Call<List<Hinh>> call, Response<List<Hinh>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    arrayList=response.body();
                    listAdapter = new ImageNewsPostAdapter(arrayList, DetailNewsActivity.this);
                    recyclerView.setAdapter(listAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Hinh>> call, Throwable t) {

            }
        });
    }

    private void recyclerView2() {
        recyclerBinhLuan = findViewById(R.id.recyclerBinhLuan);
        recyclerBinhLuan.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailNewsActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerBinhLuan.setLayoutManager(linearLayoutManager);
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        commentNewsAdapter = new CommentNewsAdapter(arrBinhLuan, this, user,String.valueOf(id));
        recyclerBinhLuan.setAdapter(commentNewsAdapter);
    }

    private void intitDilog(final BaiDang baiDang){
//        final Dialog luuDilog=new Dialog(DetailNewsActivity.this);
//        luuDilog.setContentView(R.layout.quit_app_dilog);
//        luuDilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        luuDilog.setCanceledOnTouchOutside(false);
//        Button btnDongY=luuDilog.findViewById(R.id.btnThoatDialog);
//        Button btnHuy=luuDilog.findViewById(R.id.btnHuyExit);
//        TextView txtMessage=luuDilog.findViewById(R.id.txtMessageThongBao);
//        txtMessage.setText(getString(R.string.bai_dang_se_dc_luu));
//        btnDongY.setText(getString(R.string.save));
//        btnHuy.setText(getString(R.string.cancle));
//        btnDongY.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long ckeckSave=sqLiteData.insert(baiDang);
//                if (ckeckSave!=-1){
//                    Toast.makeText(DetailNewsActivity.this, getString(R.string.luu_gb_success), Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(DetailNewsActivity.this, getString(R.string.luu_gb_fails), Toast.LENGTH_SHORT).show();
//                }
//
//                luuDilog.dismiss();
//            }
//        });
//        btnHuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                luuDilog.dismiss();
//            }
//        });
//        luuDilog.show();
    }

    private void refechBitmap(){
        uri=null;
        imgHinhBinhLuan.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHinhBinhLuan:
                refechBitmap();
                break;
            case R.id.btnCameraBinhLuan:
                Utils.openAbum(this,null,KET_QUA);
                break;
            case R.id.btnLuuChiTiet:
                btnLuu.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                intitDilog(baiDang);
                break;
            case R.id.btnLikeChiTiet:
                btnLike.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                soService.getLike(String.valueOf(user.getId()),String.valueOf(id)).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                        if (response.isSuccessful()&&response.body()!=null&&response.body().equals("1")){
                            int i=baiDang.getLuotLike()+1;
                            btnLike.setText(i+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                break;
            case R.id.btnBinhLuanChiTiet:
                btnBinhLuanChiTiet.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                if (giaTri == 0) {
                    giaTri = 1;
                    recyclerBinhLuan.setVisibility(View.VISIBLE);

                } else {
                    giaTri = 0;
                    recyclerBinhLuan.setVisibility(View.GONE);

                }
                break;
            case R.id.btnGuiBinhLuan:
                btnGuiBinhLuan.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                if (bitmap==null){
                    String binhLuan = edtNhapBinhLuan.getText() + "";
                    if (binhLuan.isEmpty()) {
                        Singleton.Instance().toasty(DetailNewsActivity.this, getString(R.string.chua_nhap_noi_dung));
                    } else {
                        String query = baiDang.getIdBaiDang() + "," + user.getId() + "," + binhLuan+"&1";
                        xuLySuKien.sendToServer(CLIENT_SEND_BINHLUAN, query);
                        recyclerBinhLuan.setVisibility(View.VISIBLE);
                        edtNhapBinhLuan.setText("");
                    }
                }else {
                    myLoadAppProgessBar.show();
                    upLoadHinh();
                    bitmap=null;
                    imgHinhBinhLuan.setVisibility(View.GONE);
                }

                break;
            case R.id.btnGoi:
                btnGoi.startAnimation(ButtonAnimation.getInstance(this).startAnimation());
                String uri = "tel:" + "0" + baiDang.getSdt();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                startActivity(intent);
                break;
        }
    }

    private void upLoadHinh(){
        if (uri!=null){
            soService.uploadImage(Utils.getBody(this,uri)).enqueue(new Callback<ResultUploadImage>() {
                @Override
                public void onResponse(Call<ResultUploadImage> call, retrofit2.Response<ResultUploadImage> response) {
                    if (response.isSuccessful()&&response.body()!=null){
                        if (check==false){
                            String url=response.body().getName();
                                String query = baiDang.getIdBaiDang() + "," + user.getId() + ","+edtNhapBinhLuan.getText().toString()+"&" + url;
                                xuLySuKien.sendToServer(CLIENT_SEND_BINHLUAN, query);
                                recyclerBinhLuan.setVisibility(View.VISIBLE);
                                edtNhapBinhLuan.setText("");
                                edtNhapBinhLuan.setText("");
                                myLoadAppProgessBar.dismiss();
                                check=true;
                                CountDownTimer countDownTimer=new CountDownTimer(5000,1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        check=false;
                                    }
                                }.start();

                        }
                    }

                }

                @Override
                public void onFailure(Call<ResultUploadImage> call, Throwable t) {
                }
            });
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==KET_QUA &&resultCode==RESULT_OK&&data!=null){
            uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgHinhBinhLuan.setVisibility(View.VISIBLE);
                imgHinhBinhLuan.setImageBitmap(bitmap);
                Utils.scaleImage(this,imgHinhBinhLuan);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void traVeKetQua(Object b) {
        arrBinhLuan.clear();
        JSONArray jsonArray= (JSONArray) b;
        for (int i=0;i<jsonArray.length();i++){
            try {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int id =jsonObject.getInt("idBinhLuan");
                int idKhachHang=jsonObject.getInt("idKhachHang");
                String hinhDaiDien=jsonObject.getString("anhDaiDien");
                String ten=jsonObject.getString("ten");
                String binhLuan=jsonObject.getString("noiDung");
                String thoiGian=jsonObject.getString("thoiGian");
                arrBinhLuan.add(new BinhLuan(id,idKhachHang,hinhDaiDien,ten,binhLuan,thoiGian));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        btnBinhLuanChiTiet.setText(arrBinhLuan.size()+"");
        commentNewsAdapter.notifyDataSetChanged();
        myLoadAppProgessBar.dismiss();
    }


}
