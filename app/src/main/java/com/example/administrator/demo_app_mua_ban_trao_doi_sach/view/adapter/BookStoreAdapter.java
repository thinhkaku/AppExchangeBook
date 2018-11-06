package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.GetDataFromSqlite;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHang;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GioHang>arrGioHang;
    private LayoutInflater inflater;
    private GetDataFromSqlite getDataFromSqlite;
    private TextView txtMessage, txtTongTien;

    public BookStoreAdapter(Context context, List<GioHang> arrGioHang, GetDataFromSqlite getDataFromSqlite, TextView txtMessage, TextView txtTongTien) {
        this.context = context;
        this.arrGioHang = arrGioHang;
        this.txtMessage=txtMessage;
        this.txtTongTien=txtTongTien;
        this.getDataFromSqlite=getDataFromSqlite;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_sach_cho_thanh_toan,parent,false);
        return new ViewHodler(view);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (arrGioHang.size()==0){
            txtMessage.setVisibility(View.VISIBLE);
        }else {
            txtMessage.setVisibility(View.INVISIBLE);
        }
        final GioHang gioHang=arrGioHang.get(position);
        final int check=gioHang.getCheck();
        final ViewHodler viewHodler= (ViewHodler) holder;
        Picasso.with(context).load(gioHang.getHinhS()).into(viewHodler.imgS);
        viewHodler.txtTenS.setText(gioHang.getTenS());
        viewHodler.txtSL.setText(gioHang.getSoLuong()+"");
        if(check==0){
            viewHodler.ckSelect.setChecked(false);
        }else {
            viewHodler.ckSelect.setChecked(true);
        }
        viewHodler.ckSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    GioHang gioHang1=new GioHang(gioHang.getId(),gioHang.getTenS(),gioHang.getHinhS(),gioHang.getGiaS(),gioHang.getSoLuong(),1);
                    getDataFromSqlite.update(gioHang1);
                    arrGioHang=getDataFromSqlite.getGioHang();
                    notifyDataSetChanged();
                    getTongTien(arrGioHang);
                }else {
                    GioHang gioHang1=new GioHang(gioHang.getId(),gioHang.getTenS(),gioHang.getHinhS(),gioHang.getGiaS(),gioHang.getSoLuong(),0);
                    getDataFromSqlite.update(gioHang1);
                    arrGioHang=getDataFromSqlite.getGioHang();
                    notifyDataSetChanged();
                    getTongTien(arrGioHang);
                }
            }
        });
        viewHodler.btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang gioHang1=new GioHang(gioHang.getId(),gioHang.getTenS(),gioHang.getHinhS(),gioHang.getGiaS(),gioHang.getSoLuong()+1,check);
                getDataFromSqlite.update(gioHang1);
                arrGioHang=getDataFromSqlite.getGioHang();
                notifyDataSetChanged();
                viewHodler.btnT.setAlpha(1f);
                getTongTien(arrGioHang);

            }
        });
        viewHodler.btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gioHang.getSoLuong()!=1)
                {
                    GioHang gioHang1=new GioHang(gioHang.getId(),gioHang.getTenS(),gioHang.getHinhS(),gioHang.getGiaS(),gioHang.getSoLuong()-1,check);
                    getDataFromSqlite.update(gioHang1);
                    arrGioHang=getDataFromSqlite.getGioHang();
                    notifyDataSetChanged();
                    if (gioHang.getSoLuong()==1){
                        viewHodler.btnT.setAlpha(0.1f);
                    }
                    getTongTien(arrGioHang);
                }
            }
        });
        if (gioHang.getSoLuong()==1){
            viewHodler.btnT.setAlpha(0.1f);
        }
        viewHodler.btnDeleteGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilogDeleteGioHang(gioHang);
            }
        });

    }

    private void getTongTien(List<GioHang>arrGioHang){
        int tongTien=0;
        for (int i=0;i<arrGioHang.size();i++)
        {
            if (arrGioHang.get(i).getCheck()==1){
                tongTien+=arrGioHang.get(i).getGiaS()*arrGioHang.get(i).getSoLuong();
            }
        }
        txtTongTien.setText(Utils.formatGia(tongTien));
    }

    private void dilogDeleteGioHang(final GioHang gioHang){
        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.thong_bao));
        builder.setMessage(context.getString(R.string.question_delete_product));
        builder.setPositiveButton(context.getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getDataFromSqlite.delete(gioHang);
                arrGioHang=getDataFromSqlite.getGioHang();
                notifyDataSetChanged();
                if (arrGioHang.size()==0){
                    txtMessage.setVisibility(View.VISIBLE);
                }else {
                    txtMessage.setVisibility(View.INVISIBLE);
                }
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
    public int getItemCount() {
        return arrGioHang.size();
    }

    private class ViewHodler extends RecyclerView.ViewHolder{
        ImageView imgS;
        TextView txtTenS, txtSL;
        ImageButton btnT, btnC, btnDeleteGioHang;
        CheckBox ckSelect;

        public ViewHodler(View itemView) {
            super(itemView);
            imgS=itemView.findViewById(R.id.imgSC);
            txtTenS=itemView.findViewById(R.id.txtTenSC);
            txtSL=itemView.findViewById(R.id.txtSLC);
            btnC=itemView.findViewById(R.id.btnAddSlC);
            btnT=itemView.findViewById(R.id.btnRemoveSlC);
            ckSelect=itemView.findViewById(R.id.ckSelectS);
            btnDeleteGioHang=itemView.findViewById(R.id.btnDeleteGioHang);
        }
    }
}
