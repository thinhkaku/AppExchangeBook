package com.example.administrator.demo_app_mua_ban_trao_doi_sach.config;

import android.net.Uri;

/**
 * Created by Administrator on 10/16/2017.
 */

public class LoginConfig {
    public static final String SERVER_ADRESS = "https://thinhkaku.000webhostapp.com/";

    public static String login(String userName, String passWord){
        userName = Uri.encode(userName);
        passWord = Uri.encode(passWord);
        return SERVER_ADRESS+"login.php?user_uid="+userName+"&user_upw="+passWord;
    }

    public static String layThongTinKhachHang(){
        return SERVER_ADRESS+"laythongtinkhachhang.php";
    }
    public  static String taiLenThongTinKhachKhang(String hinh, String tensp, int giasp, int soluong, String mausac, String size, String tenkhachhang, String sdt, String diachi){
        hinh= Uri.encode(hinh);
        tensp= Uri.encode(tensp);
        mausac= Uri.encode(mausac);
        size= Uri.encode(size);
        tenkhachhang= Uri.encode(tenkhachhang);
        sdt= Uri.encode(sdt);
        diachi= Uri.encode(diachi);
        return SERVER_ADRESS+"guithongtinkhachhang.php?hinh="+hinh+"&tensp="+tensp+"&giasp="+giasp+"&soluong="+soluong+"&mausac="+mausac+"&size="+size+"&tenkhachhang="+tenkhachhang+"&sdt="+sdt+"&diachi="+diachi;
    }
    public static String laydulieuShipper(){
        return SERVER_ADRESS+"laydulieushipper.php";
    }

    public static String xoadonhang(int id){
        return SERVER_ADRESS+"xoadonhang.php?id="+id;
    }
    public  static String guiShipper(String tensp, String giasp, String sl, String tenkh, String sdt, String diachi){
        tensp= Uri.encode(tensp);
        tenkh= Uri.encode(tenkh);
        sdt= Uri.encode(sdt);
        diachi= Uri.encode(diachi);
        return SERVER_ADRESS+"guichoshipper.php?tensp="+tensp+"&giasp="+giasp+"&sl="+sl+"&tenkh="+tenkh+"&sdt="+sdt+"&diachi="+diachi;
    }

    public  static String singup(String userName, String passWord, String email, String firstName, String lastName,String sdt, String hinhdaihien){
        userName = Uri.encode(userName);
        passWord = Uri.encode(passWord);
        email = Uri.encode(email);
        firstName = Uri.encode(firstName);
        lastName = Uri.encode(lastName);
        sdt = Uri.encode(sdt);
        hinhdaihien = Uri.encode(hinhdaihien);
        return SERVER_ADRESS+"sigup.php?user_uid="+userName+"&user_upw="+passWord+"&user_email="+email+"&user_fisrt="+firstName+"&user_last="+lastName+"&sdt="+sdt+"&hinhdaidien="+hinhdaihien;
    }

    public static String getSp(){
        return SERVER_ADRESS+"getsanpham.php";
    }
    public static String getDamVay(){
        return SERVER_ADRESS+"getDamVay.php";
    }
    public static String getTrangPhuc(){
        return SERVER_ADRESS+"getTrangPhuc.php";
    }
    public static String getQuan(){
        return SERVER_ADRESS+"getQuan.php";
    }
    public static String getDoLot(){
        return SERVER_ADRESS+"getDoLot.php";
    }
    public static String getAoKhoac(){
        return SERVER_ADRESS+"getAoKhoac.php";
    }
    public static String getAo(){
        return SERVER_ADRESS+"getAo.php";
    }

    public static String get1(){
        return SERVER_ADRESS+"get1.php";
    }
    public static String get2(){
        return SERVER_ADRESS+"get2.php";
    }
    public static String get3(){
        return SERVER_ADRESS+"get3.php";
    }
    public static String get4(){
        return SERVER_ADRESS+"get4.php";
    }
    public static String get5(){
        return SERVER_ADRESS+"get5.php";
    }
    public static String get6(){
        return SERVER_ADRESS+"get6.php";
    }
    public static String get7(){
        return SERVER_ADRESS+"get7.php";
    }
    public static String get8(){
        return SERVER_ADRESS+"get8.php";
    }
    public static String get9(){
        return SERVER_ADRESS+"get9.php";
    }
    public static String get10(){
        return SERVER_ADRESS+"get10.php";
    }
    public static String get11(){
        return SERVER_ADRESS+"get11.php";
    }
    public static String get12(){
        return SERVER_ADRESS+"get12.php";
    }
    public static String get13(){
        return SERVER_ADRESS+"get13.php";
    }
    public static String get14(){
        return SERVER_ADRESS+"get14.php";
    }
    public static String get15(){
        return SERVER_ADRESS+"get15.php";
    }
    public static String get16(){
        return SERVER_ADRESS+"get16.php";
    }
    public static String get17(){
        return SERVER_ADRESS+"get17.php";
    }
    public static String get18(){
        return SERVER_ADRESS+"get18.php";
    }
    public static String get19(){
        return SERVER_ADRESS+"get19.php";
    }
    public static String get20(){
        return SERVER_ADRESS+"get20.php";
    }
    public static String get21(){
        return SERVER_ADRESS+"get21.php";
    }
    public static String get22(){
        return SERVER_ADRESS+"get22.php";
    }
    public static String get23(){
        return SERVER_ADRESS+"get23.php";
    }
    public static String get24(){
        return SERVER_ADRESS+"get24.php";
    }
    public static String get25(){
        return SERVER_ADRESS+"get25.php";
    }
    public static String get26(){
        return SERVER_ADRESS+"get26.php";
    }
    public static String get27(){
        return SERVER_ADRESS+"get27.php";
    }
    public static String get28(){
        return SERVER_ADRESS+"get28.php";
    }
    public static String get29(){
        return SERVER_ADRESS+"get29.php";
    }
    public static String get30(){
        return SERVER_ADRESS+"get30.php";
    }
    public static String get31(){
        return SERVER_ADRESS+"get31.php";
    }
    public static String get32(){
        return SERVER_ADRESS+"get32.php";
    }
    public static String get33(){
        return SERVER_ADRESS+"get33.php";
    }
    public static String get34(){
        return SERVER_ADRESS+"get34.php";
    }
    public static String get35(){
        return SERVER_ADRESS+"get35.php";
    }
    public static String get36(){
        return SERVER_ADRESS+"get36.php";
    }
    public static String get37(){
        return SERVER_ADRESS+"get37.php";
    }
    public static String get38(){
        return SERVER_ADRESS+"get38.php";
    }
    public static String get39(){
        return SERVER_ADRESS+"get39.php";
    }
    public static String get40(){
        return SERVER_ADRESS+"get40.php";
    }
    public static String get41(){
        return SERVER_ADRESS+"get41.php";
    }
    public static String get42(){
        return SERVER_ADRESS+"get42.php";
    }
    public static String get43(){
        return SERVER_ADRESS+"get43.php";
    }
    public static String get44(){
        return SERVER_ADRESS+"get44.php";
    }
    public static String get45(){
        return SERVER_ADRESS+"get45.php";
    }
    public static String get46(){
        return SERVER_ADRESS+"get46.php";
    }
    public static String get47(){
        return SERVER_ADRESS+"get47.php";
    }
    public static String get48(){
        return SERVER_ADRESS+"get48.php";
    }
    public static String get49(){
        return SERVER_ADRESS+"get49.php";
    }
    public static String get50(){
        return SERVER_ADRESS+"get50.php";
    }
    public static String get51(){
        return SERVER_ADRESS+"get51.php";
    }
    public static String get52(){
        return SERVER_ADRESS+"get52.php";
    }
    public static String get53(){
        return SERVER_ADRESS+"get53.php";
    }
    public static String get54(){
        return SERVER_ADRESS+"get54.php";
    }
    public static String get55(){
        return SERVER_ADRESS+"get55.php";
    }
    public static String get56(){
        return SERVER_ADRESS+"get56.php";
    }
    public static String get57(){
        return SERVER_ADRESS+"get57.php";
    }
    public static String get58(){
        return SERVER_ADRESS+"get58.php";
    }
    public static String get59(){
        return SERVER_ADRESS+"get59.php";
    }
    public static String get60(){
        return SERVER_ADRESS+"get60.php";
    }
    public static String get61(){
        return SERVER_ADRESS+"get61.php";
    }
    public static String get62(){
        return SERVER_ADRESS+"get62.php";
    }
    public static String get63(){
        return SERVER_ADRESS+"get63.php";
    }
    public static String get64(){
        return SERVER_ADRESS+"get64.php";
    }
    public static String get65(){
        return SERVER_ADRESS+"get65.php";
    }
    public static String get66(){
        return SERVER_ADRESS+"get66.php";
    }




}
