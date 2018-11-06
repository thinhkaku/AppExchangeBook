package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicPersonalCustomerFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.DetailNewsActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.ListDeleteBaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewPersonalCustomerFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 1/6/2018.
 */

public class NewsPostPersonalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ViewPersonalCustomerFragment {
    private List<BaiDang> arrListNews;
    private Context context;
    private SOService soService;
    private ViewHolder holder1;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ButtonAnimation buttonAnimation;
    private PresenterLogicPersonalCustomerFragment presenterLogicPersonalCustomerFragment;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private User user;


    public NewsPostPersonalAdapter(SOService soService, List<BaiDang> arrListNews, Context context, MyLoadAppProgessBar myLoadAppProgessBar, User user) {
        this.soService = soService;
        buttonAnimation = new ButtonAnimation(context);
        this.arrListNews = arrListNews;
        this.context = context;
        this.myLoadAppProgessBar = myLoadAppProgessBar;
        this.user=user;
        presenterLogicPersonalCustomerFragment = new PresenterLogicPersonalCustomerFragment(this, soService, myLoadAppProgessBar);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_luu_tru_bai_dang, parent, false);
        return new ViewHolder(view);
    }

    public void getNews(int id) {
        presenterLogicPersonalCustomerFragment.getNewsPersonal(id);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final BaiDang baiDang = arrListNews.get(position);
        holder1 = (ViewHolder) holder;
        holder1.txtTenNguoiDang.setText(baiDang.getTen());
        holder1.txtTenSach.setText(baiDang.getTenSach());
        holder1.txtNoiDung.setText(baiDang.getNoiDung());
        if (Utils.getDayCurent() == Integer.parseInt(Utils.getDay(baiDang.getThoiGian()))) {
            holder1.txtThoiGian.setText(Utils.formatHourM(baiDang.getThoiGian()));
        } else {
            holder1.txtThoiGian.setText(Utils.formatThoiGian(baiDang.getThoiGian()));
        }
        setImage(holder1.imgItemOne, holder1.imgItemTwo, holder1.imgItemThree, baiDang.getArrListImage());
        holder1.btnBinhLuan.setText(baiDang.getSoBL() + "");
        holder1.txtGia.setText("Giá: " + Utils.formatGia(baiDang.getGia()));
        Picasso.with(context).load(Constants.PORTIMAGE + baiDang.getAnhDaiDien()).resize(50, 50).into(holder1.circleImageView);
        final Animation animationLn = AnimationUtils.loadAnimation(context, R.anim.alpha_button);
        holder1.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.linearLayout.startAnimation(buttonAnimation.startAnimation());
                ((ViewHolder) holder).linearLayout.startAnimation(animationLn);
                openDetailNewsPost(baiDang, 0);
            }
        });

        holder1.btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.btnBinhLuan.startAnimation(buttonAnimation.startAnimation());
                openDetailNewsPost(baiDang, 1);
            }
        });

        holder1.btnCallList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + "0" + baiDang.getSdt();
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
                dilogDeleteNews(arrListNews.get(position).getIdBaiDang());
            }
        });
    }

    private void dilogDeleteNews(final int idNews){
        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.thong_bao));
        builder.setMessage(context.getString(R.string.question_delete_news));
        builder.setPositiveButton(context.getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenterLogicPersonalCustomerFragment.deleteNews(idNews);
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

    private void openDetailNewsPost(BaiDang baiDang, int i) {
        Intent intent = new Intent(context, DetailNewsActivity.class);
        intent.putExtra(Constants.ID_BAI_DANG, baiDang);
        intent.putExtra(Constants.GIA_TRI, i);
        context.startActivity(intent);
    }

    private void setImage(ImageView imgOne, ImageView imgTwo, ImageView imgThree, List<Hinh> arrListImage) {
        if (arrListImage != null && arrListImage.size() != 0) {
            if (arrListImage.size() == 1) {
                Picasso.with(context).load(arrListImage.get(0).getHinh()).into(imgOne);
                imgTwo.setVisibility(View.GONE);
                imgOne.setVisibility(View.VISIBLE);
                imgThree.setVisibility(View.GONE);
            } else if (arrListImage.size() == 2) {
                Picasso.with(context).load(arrListImage.get(0).getHinh()).into(imgOne);
                Picasso.with(context).load(arrListImage.get(1).getHinh()).into(imgTwo);
                imgTwo.setVisibility(View.VISIBLE);
                imgOne.setVisibility(View.VISIBLE);
                imgThree.setVisibility(View.GONE);
            } else {
                Picasso.with(context).load(arrListImage.get(0).getHinh()).into(imgOne);
                Picasso.with(context).load(arrListImage.get(1).getHinh()).into(imgTwo);
                Picasso.with(context).load(arrListImage.get(2).getHinh()).into(imgThree);
                imgTwo.setVisibility(View.VISIBLE);
                imgOne.setVisibility(View.VISIBLE);
                imgThree.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return arrListNews.size();
    }

    @Override
    public void onSuccessNewsPersonal(List<BaiDang> arrNews) {
        arrListNews = arrNews;
        presenterLogicPersonalCustomerFragment.getImage(arrNews);
        notifyDataSetChanged();
    }

    @Override
    public void onErr() {

    }

    @Override
    public void onSuccessLoadImage() {
        notifyDataSetChanged();
    }

    @Override
    public void onSuccessDeleteNews() {
        Toast.makeText(context, context.getString(R.string.xoa_bai_success), Toast.LENGTH_SHORT).show();
        presenterLogicPersonalCustomerFragment.getNewsPersonal(user.getId());
    }

    @Override
    public void onErrDeleteNews() {
        Toast.makeText(context,context.getString(R.string.xoa_bai_fails), Toast.LENGTH_SHORT).show();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        LinearLayout linearLayout;
        TextView txtTenNguoiDang, txtThoiGian, txtGia, txtNoiDung, txtTenSach;
        ImageView imgItemOne, imgItemTwo, imgItemThree;
        Button btnBinhLuan, btnXoaItemLuuTru, btnCallList, btnMessageList;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.lnItemLuuTru);
            circleImageView = itemView.findViewById(R.id.imgHinhDaiDienLuuTru);
            txtGia = itemView.findViewById(R.id.txtGiaSachLuuTru);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDungLuuTru);
            txtTenNguoiDang = itemView.findViewById(R.id.txtTenNguoiDangLuuTru);
            txtThoiGian = itemView.findViewById(R.id.txtThoiGianLuuTru);
            txtTenSach = itemView.findViewById(R.id.txtTenSachLuuTru);
            btnBinhLuan = itemView.findViewById(R.id.btnBinhLuanLuuTru);
            btnXoaItemLuuTru = itemView.findViewById(R.id.btnXoaItemLuuTru);
            btnCallList = itemView.findViewById(R.id.btnCallListLuuTru);
            btnMessageList = itemView.findViewById(R.id.btnMessageListLuuTru);
            imgItemOne = itemView.findViewById(R.id.imgItemOne);
            imgItemTwo = itemView.findViewById(R.id.imgItemTwo);
            imgItemThree = itemView.findViewById(R.id.imgItemThree);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return arrListNews.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


}
