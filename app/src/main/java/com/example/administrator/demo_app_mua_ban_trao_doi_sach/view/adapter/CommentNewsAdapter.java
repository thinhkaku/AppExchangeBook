package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.DetailImageActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BinhLuan;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork.XuLySuKien;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.MainView;

import java.util.ArrayList;

public class CommentNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MainView{
    private ArrayList<BinhLuan>arrBinhLuan;
    private Context context;
    private XuLySuKien xuLySuKien;
    private User user;
    private Dialog  dialogXoaBinhLuan;
    String id;
    private String CLIENT_SEND_XOA_BINH_LUAN="CLIENT_SEND_XOA_BINH_LUAN";
    private String SERVER_SEND_RESULT_XOA_BINH_LUAN="SERVER_SEND_RESULT_XOA_BINH_LUAN";
    private ButtonAnimation buttonAnimation;

    public CommentNewsAdapter(ArrayList<BinhLuan> arrBinhLuan, Context context, User user, String id) {
        buttonAnimation=new ButtonAnimation(context);
        this.arrBinhLuan = arrBinhLuan;
        this.context = context;
        this.user=user;
        this.id=id;
        xuLySuKien=new XuLySuKien(this,(Activity) context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new CommentNewsAdapter.ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final BinhLuan binhLuan=arrBinhLuan.get(position);
        ViewHodler viewHodler= (ViewHodler) holder;
        Glide.with(context).load(Constants.PORTIMAGE+binhLuan.getHinhDaiDien()).into(((ViewHodler) holder).imgHinhDaiDien);
        final String nDBinhLuan=binhLuan.getNoiDung();
        String[] noidung=nDBinhLuan.split("&");
        final String image=noidung[1];
        String noiDungBinhLuan=noidung[0];
        if (image.contains(".jpg")){
            ((ViewHodler) holder).txtTen.setText(binhLuan.getTen().toUpperCase());
            Glide.with(context).load(Constants.PORTIMAGE+image).into(((ViewHodler) holder).imgHinhBinhLuanList);
            ((ViewHodler) holder).imgHinhBinhLuanList.setVisibility(View.VISIBLE);
        }else {
            ((ViewHodler) holder).imgHinhBinhLuanList.setVisibility(View.GONE);
        }
        ((ViewHodler) holder).txtTen.setText(binhLuan.getTen().toUpperCase()+" \t "+noiDungBinhLuan);
        ((ViewHodler) holder).imgHinhBinhLuanList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailImageActivity.class);
                intent.putExtra(Constants.IMAGE,"/"+image);
                context.startActivity(intent);
            }
        });
        if (Utils.getDayCurent()==Integer.parseInt(Utils.getDay(binhLuan.getThoiGian()))){
            viewHodler.txtThoiGianBL.setText(Utils.formatHourM(binhLuan.getThoiGian()));
        }else {
            viewHodler.txtThoiGianBL.setText(Utils.formatThoiGian(binhLuan.getThoiGian()));
        }
        if (user.getId()!=binhLuan.getIdKhachHang()){
            ((ViewHodler) holder).btnXoaBinhLuan.setVisibility(View.INVISIBLE);
        }
        ((ViewHodler) holder).btnXoaBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewHodler) holder).btnXoaBinhLuan.startAnimation(buttonAnimation.startAnimation());
                //initDilogXoaBinhLuan(binhLuan.getId());
                dilogQuitApp(binhLuan.getId());
            }
        });

    }

    private void dilogQuitApp(final int idX){
        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.thong_bao));
        builder.setMessage(context.getString(R.string.question_delete_comment));
        builder.setPositiveButton(context.getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                xuLySuKien.sendToServer(CLIENT_SEND_XOA_BINH_LUAN,idX+"-"+user.getId()+"-"+id);
                xuLySuKien.serverSendData(SERVER_SEND_RESULT_XOA_BINH_LUAN);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancle), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public void traVeKetQua(Object b) {
        if(b.toString().equals("1")){
            Toast.makeText(context,"Xóa bình luận thành công",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Thất bại",Toast.LENGTH_SHORT).show();
        }
    }

    private class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView imgHinhDaiDien,imgHinhBinhLuanList;
        private TextView txtTen,txtThoiGianBL;
        private Button btnXoaBinhLuan;
        public ViewHodler(View itemView) {
            super(itemView);
            imgHinhDaiDien=itemView.findViewById(R.id.imgHinhDaiDienBinhLuan);
            txtTen=itemView.findViewById(R.id.txtTenNguoiBinhLuan);
            btnXoaBinhLuan=itemView.findViewById(R.id.btnDeleteBinhLuan);
            txtThoiGianBL=itemView.findViewById(R.id.txtThoiGianBL);
            imgHinhBinhLuanList=itemView.findViewById(R.id.imgHinhBinhLuanList);
        }
    }

    @Override
    public int getItemCount() {
        return arrBinhLuan.size();
    }

    private void initDilogXoaBinhLuan(final int idX){
        dialogXoaBinhLuan=new Dialog(context,android.R.style.Theme_DeviceDefault_Dialog_MinWidth);
        dialogXoaBinhLuan.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogXoaBinhLuan.setContentView(R.layout.dilog_delete_comment);
        dialogXoaBinhLuan.setCanceledOnTouchOutside(true);
        final Button btnXoa=dialogXoaBinhLuan.findViewById(R.id.btnXoaBinhLuan);
        final Button btnHuy=dialogXoaBinhLuan.findViewById(R.id.btnHuyXoaBinhLuan);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHuy.startAnimation(buttonAnimation.startAnimation());
                dialogXoaBinhLuan.dismiss();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnXoa.startAnimation(buttonAnimation.startAnimation());
                xuLySuKien.sendToServer(CLIENT_SEND_XOA_BINH_LUAN,idX+"-"+user.getId()+"-"+id);
                xuLySuKien.serverSendData(SERVER_SEND_RESULT_XOA_BINH_LUAN);
                dialogXoaBinhLuan.dismiss();
            }
        });
        dialogXoaBinhLuan.show();
    }
}
