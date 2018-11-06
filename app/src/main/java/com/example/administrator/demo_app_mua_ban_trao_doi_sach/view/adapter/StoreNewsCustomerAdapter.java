package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.DetailNewsActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.SQLiteData;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 1/6/2018.
 */

public class StoreNewsCustomerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<BaiDang>arrListNews;
    private ArrayList<BaiDang>arrListNews1;
    private Context context;
    private Animation animationImage;
    private ViewHolder holder1;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ButtonAnimation buttonAnimation;
    private SQLiteData sqLiteData;


    public StoreNewsCustomerAdapter(ArrayList<BaiDang> arrListNews, Context context, SQLiteData sqLiteData) {
        animationImage= AnimationUtils.loadAnimation(context,R.anim.image_alpha);
        buttonAnimation=new ButtonAnimation(context);
        this.arrListNews = arrListNews;
        this.arrListNews1 = arrListNews;
        this.context = context;
        this.sqLiteData=sqLiteData;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_luu_tru_bai_dang, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final BaiDang contact = arrListNews.get(position);
        holder1 = (ViewHolder) holder;
        holder1.txtTenNguoiDang.setText(contact.getTen());
        holder1.txtTenSach.setText(contact.getTenSach());
        holder1.txtNoiDung.setText(contact.getNoiDung());
        holder1.txtThoiGian.setText(contact.getThoiGian());
        holder1.txtGia.setText("Giá: "+contact.getGia()+" vnđ");
        Picasso.with(context).load(Constants.PORTIMAGE+contact.getAnhDaiDien()).resize(50,50).into(holder1.circleImageView);
//        Picasso.with(context).load(Constants.PORT+contact.getArrListImage().get(0).getHinh()).into(holder1.imgHinhNoiDung);
//        Utils.loadImage(context,Constants.PORT+contact.getArrListImage().get(0).getHinh(),holder1.imgHinhNoiDung,holder1.progressbarr);
//        holder1.imgHinhNoiDung.startAnimation(animationImage);
        final Animation animationLn=AnimationUtils.loadAnimation(context,R.anim.alpha_button);
        holder1.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.linearLayout.startAnimation(buttonAnimation.startAnimation());
                ((ViewHolder) holder).linearLayout.startAnimation(animationLn);
                Intent intent=new Intent(context, DetailNewsActivity.class);
                intent.putExtra(Constants.ID_BAI_DANG,contact);
                intent.putExtra(Constants.GIA_TRI,0);
                intent.putExtra(Constants.CHECK_LUU,1);
                context.startActivity(intent);
            }
        });

        holder1.btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.btnBinhLuan.startAnimation(buttonAnimation.startAnimation());
                Intent intent=new Intent(context, DetailNewsActivity.class);
                intent.putExtra(Constants.ID_BAI_DANG,contact);
                intent.putExtra(Constants.GIA_TRI,1);
                intent.putExtra(Constants.CHECK_LUU,1);
                context.startActivity(intent);
            }
        });

        holder1.btnCallList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + "0" + contact.getSdt();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                context.startActivity(intent);
            }
        });
        holder1.btnMessageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder1.btnXoaItemLuuTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intitDilog(arrListNews.get(position).getIdBaiDang());

            }
        });
    }

    private void intitDilog(final int idBaiDang){
//        final Dialog luuDilog=new Dialog(context);
//        luuDilog.setContentView(R.layout.quit_app_dilog);
//        luuDilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        luuDilog.setCanceledOnTouchOutside(false);
//        Button btnDongY=luuDilog.findViewById(R.id.btnThoatDialog);
//        Button btnHuy=luuDilog.findViewById(R.id.btnHuyExit);
//        TextView txtMessage=luuDilog.findViewById(R.id.txtMessageThongBao);
//        txtMessage.setText(context.getString(R.string.bai_dang_se_dc_luu));
//        btnDongY.setText(context.getString(R.string.delete));
//        btnHuy.setText(context.getString(R.string.cancle));
//        btnDongY.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sqLiteData.deleteTitle(idBaiDang);
//                arrListNews.clear();
//                arrListNews=sqLiteData.getBaiDang();
//                notifyDataSetChanged();
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

    @Override
    public int getItemCount() {
        return arrListNews.size();
    }


    private   class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        LinearLayout linearLayout;
        TextView txtTenNguoiDang,txtThoiGian,txtGia,txtNoiDung,txtTenSach;
        Button  btnBinhLuan,btnXoaItemLuuTru,btnCallList,btnMessageList;
        ProgressBar progressbarr;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.lnItemLuuTru);
            circleImageView=itemView.findViewById(R.id.imgHinhDaiDienLuuTru);
            txtGia=itemView.findViewById(R.id.txtGiaSachLuuTru);
            txtNoiDung=itemView.findViewById(R.id.txtNoiDungLuuTru);
            txtTenNguoiDang=itemView.findViewById(R.id.txtTenNguoiDangLuuTru);
            txtThoiGian=itemView.findViewById(R.id.txtThoiGianLuuTru);
            txtTenSach=itemView.findViewById(R.id.txtTenSachLuuTru);
            btnBinhLuan= itemView.findViewById(R.id.btnBinhLuanLuuTru);
            btnXoaItemLuuTru= itemView.findViewById(R.id.btnXoaItemLuuTru);
            btnCallList= itemView.findViewById(R.id.btnCallListLuuTru);
            btnMessageList= itemView.findViewById(R.id.btnMessageListLuuTru);
            progressbarr= itemView.findViewById(R.id.progressbarr);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return arrListNews.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


}
