package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.ChangeImageTransform;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailImageActivity extends BaseActivity {
    private String img;
    private ImageView imgHinh;
    private Button btnTai, btnShare;
    private Toolbar toolbar;
    private ProgressBar progesstBarLoad;
    private ViewGroup container;
    private boolean expanded;

    @Override
    protected void requestAgain() {

    }


    private void addEvent() {
        btnTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShare();
            }
        });
        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expanded==false){
                    expanded=true;
                    scaleImage();
                }else {
                    expanded=false;
                    scaleImage();
                }
            }
        });
    }


    protected void initView() {
        setContentView(R.layout.activity_detail_image);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getData();
        container=findViewById(R.id.container);
        imgHinh=findViewById(R.id.imgChiTietHinhAnh);
        btnShare=findViewById(R.id.btnShareImage);
        btnTai=findViewById(R.id.btnTaiImage);
        progesstBarLoad=findViewById(R.id.progesstBarLoad);
        Utils.loadImage(this,Constants.PORTIMAGE+img,imgHinh,progesstBarLoad);
        addEvent();
    }

    private void getData() {
        Intent intent=getIntent();
        img=intent.getStringExtra(Constants.IMAGE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void scaleImage(){
        TransitionManager.beginDelayedTransition(container, new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new ChangeImageTransform()));
        ViewGroup.LayoutParams params = imgHinh.getLayoutParams();
        params.height = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
                ViewGroup.LayoutParams.WRAP_CONTENT;
        imgHinh.setLayoutParams(params);
        imgHinh.setScaleType(expanded ? ImageView.ScaleType.CENTER_CROP :
                ImageView.ScaleType.FIT_CENTER);
    }

    private void saveFile(){
        FileOutputStream outputStream = null;
        File file=getDisc();
        if (!file.exists()&&!file.mkdirs()){
            Toast.makeText(DetailImageActivity.this,getString(R.string.no_create_foder_image),Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_URL_IMAGE);
        String date=simpleDateFormat.format(new Date());
        String name="Img"+date+".jpg";
        String file_name=file.getAbsolutePath()+"/"+name;
        File new_file=new File(file_name);
        try {
            outputStream=new FileOutputStream(new_file);
            Bitmap bitmap= viewBitMap(imgHinh,imgHinh.getWidth(),imgHinh.getHeight());
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            Toast.makeText(DetailImageActivity.this,getString(R.string.luu_anh_success),Toast.LENGTH_SHORT).show();
            outputStream.flush();
            outputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        refeshGallery(new_file);
    }

    private void refeshGallery(File new_file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(new_file));
        sendBroadcast(intent);
    }
    private File getDisc(){
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(file,"Image");
    }


    private void  startShare(){
        Bitmap bitmap =  viewBitMap(imgHinh,imgHinh.getWidth(),imgHinh.getHeight());
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        File file= new File(Environment.getExternalStorageDirectory()+File.separator+"ImageDemo.jpg");
        try {
            file.createNewFile();
            FileOutputStream outputStream=new FileOutputStream(file);
            outputStream.write(byteArrayOutputStream.toByteArray());
        }catch (IOException e){
            e.fillInStackTrace();
        }
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/ImageDemo.jpg"));
        startActivity(Intent.createChooser(shareIntent,getString(R.string.share_image)));
    }

    private   static Bitmap viewBitMap(View view,int with, int height){
        Bitmap bitmap = Bitmap.createBitmap(with,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
