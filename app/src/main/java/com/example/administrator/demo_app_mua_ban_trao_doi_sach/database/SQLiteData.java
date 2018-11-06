package com.example.administrator.demo_app_mua_ban_trao_doi_sach.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SQLiteData {

    public static final String PATH = Environment.getDataDirectory().getPath()+"/data/com.example.administrator.demo_app_mua_ban_trao_doi_sach/database/";
    public static final String  DB_NAME = "giobai.sqlite";
    public static final String  TABLE_NAME = "baidang";
    public static final String  IDBAIDANG = "idBaiDang";
    public static final String  IDKHACHHANG = "idKhachhang";
    public static final String  SDT = "sdt";
    public static final String  TEN = "ten";
    public static final String  ANHDAIDIEN = "anhDaiDien";
    public static final String  TENSACH = "tenSach";
    public static final String  THOIGIAN = "thoiGian";
    public static final String  NOIDUNG = "noiDung";
    public static final String  HINH = "hinh";
    public static final String  GIA = "gia";
    public static final String  LUOTLIKE = "luotLike";
    public static final String  CHECKLIKE = "checkLike";
    private Context context;
    private SQLiteDatabase database;

    public SQLiteData(Context context) {
        this.context = context;
        copyDatabaseToProject();
    }
    private void  copyDatabaseToProject(){
        try {
            File file = new File(PATH+DB_NAME);
            if (file.exists()){
                return;
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = context.getAssets().open(DB_NAME);
            byte []b= new byte[1024];
            int count = inputStream.read(b);
            while (count!=-1){
                fileOutputStream.write(b,0,count);
                count= inputStream.read(b);
            }
            fileOutputStream.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void openDataBase(){
        database = context.openOrCreateDatabase(PATH+DB_NAME,Context.MODE_PRIVATE,null);

    }
    public void closeDataBase(){
        database.close();
    }

    public ArrayList<BaiDang>getBaiDang(){
        ArrayList<BaiDang> arrBaiDang = new ArrayList<>();
        openDataBase();
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        int indexID = cursor.getColumnIndex(IDBAIDANG);
        int indexIDKhachHang = cursor.getColumnIndex(IDKHACHHANG);
        int indexSDT = cursor.getColumnIndex(SDT);
        int indexTen= cursor.getColumnIndex(TEN);
        int indexADD = cursor.getColumnIndex(ANHDAIDIEN);
        int indexTenSach = cursor.getColumnIndex(TENSACH);
        int indexThoiGian = cursor.getColumnIndex(THOIGIAN);
        int indexNoiDung = cursor.getColumnIndex(NOIDUNG);
        int indexhinh = cursor.getColumnIndex(HINH);
        int indexGia = cursor.getColumnIndex(GIA);
        int indexLuotLike = cursor.getColumnIndex(LUOTLIKE);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            int id=cursor.getInt(indexID);
            int idKhachhang=cursor.getInt(indexIDKhachHang);
            int  sdt = cursor.getInt(indexSDT);
            String ten = cursor.getString(indexTen);
            String anhDaiDien = cursor.getString(indexADD);
            String tenSach = cursor.getString(indexTenSach);
            String thoiGian = cursor.getString(indexThoiGian);
            String noiDung = cursor.getString(indexNoiDung);
            String hinh = cursor.getString(indexhinh);
            List<Hinh>arrImage=new ArrayList<>();
            Hinh hinh1=new Hinh(hinh);
            arrImage.add(hinh1);
            int gia = cursor.getInt(indexGia);
            int luotLike = cursor.getInt(indexLuotLike);

            BaiDang baiDang=new BaiDang(id,idKhachhang,sdt,ten,anhDaiDien,tenSach,thoiGian,noiDung,gia,luotLike,false,arrImage);
            arrBaiDang.add(baiDang);
            cursor.moveToNext();
        }
        closeDataBase();
        return arrBaiDang;
    }
    public long insert(BaiDang baiDang){
        ContentValues values = new ContentValues();
        values.put(IDBAIDANG,baiDang.getIdBaiDang());
        values.put(IDKHACHHANG,baiDang.getIdKhachHang());
        values.put(SDT,baiDang.getSdt());
        values.put(TEN,baiDang.getTen());
        values.put(ANHDAIDIEN,baiDang.getAnhDaiDien());
        values.put(TENSACH,baiDang.getTenSach());
        values.put(THOIGIAN,baiDang.getThoiGian());
        values.put(NOIDUNG,baiDang.getNoiDung());
        values.put(HINH,baiDang.getArrListImage().get(0).getHinh());
        values.put(GIA,baiDang.getGia());
        values.put(LUOTLIKE,baiDang.getLuotLike());
        openDataBase();
        long id = database.insert(TABLE_NAME, null, values);
        closeDataBase();
        return id;
    }

    public boolean deleteTitle(int id)
    {
        openDataBase();
        boolean check=database.delete(TABLE_NAME, IDBAIDANG + "=" + id, null) > 0;
        closeDataBase();
        return check;
    }
}
