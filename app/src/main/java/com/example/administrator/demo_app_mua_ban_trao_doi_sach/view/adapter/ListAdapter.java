package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicExchangeFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.DetailNewsActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.MessageActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.SQLiteData;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewExchangeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 1/6/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SwipeRefreshLayout.OnRefreshListener, ViewExchangeFragment {
    private List<BaiDang> arrListNews;
    private Context context;
    private User user;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ButtonAnimation buttonAnimation;
    private int page;
    private boolean checkLoad = false;
    private SwipeRefreshLayout swipeRefreshLayout;
    private myHandler myHandler;
    private ProgressBar progressBarLoadNews;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private SOService soService;
    private PresenterLogicExchangeFragment presenterLogicExchangeFragment;
    private Drawable img, img1;


    public ListAdapter(List<BaiDang> arrListNews, Context context, User user, int page, SwipeRefreshLayout swipeRefreshLayout, ProgressBar progressBarLoadNews) {
        buttonAnimation = new ButtonAnimation(context);
        this.arrListNews = arrListNews;
        this.context = context;
        this.user = user;
        this.page = page;
        soService = ApiUtils.getSOService();
        this.progressBarLoadNews = progressBarLoadNews;
        this.swipeRefreshLayout = swipeRefreshLayout;
        myHandler = new myHandler();
        swipeRefreshLayout.setOnRefreshListener(this);
        myLoadAppProgessBar = new MyLoadAppProgessBar(context);
        presenterLogicExchangeFragment = new PresenterLogicExchangeFragment(this, soService, user.getId());
        img = context.getResources().getDrawable(R.drawable.ic_button_like_blue);
        img.setBounds(0, 0, 50, 50);
        img1 = context.getResources().getDrawable(R.drawable.ic_thumb_up_black_24dp);
        img1.setBounds(0, 0, 50, 50);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    public void getBaiDang() {
        myLoadAppProgessBar.show();
        getDataBaiDang(page);
    }

    public void refetchBaiDang() {
        arrListNews.clear();
        notifyDataSetChanged();
        page = 1;
        myLoadAppProgessBar.show();
        getDataBaiDang(page);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position == arrListNews.size() - 1 && arrListNews.size() != 0) {
            if (checkLoad == false) {
                checkLoad = true;
                myThread myThread = new myThread();
                myThread.start();
            }
        }
        final BaiDang baiDang = arrListNews.get(position);
        final ViewHolder holder1 = (ViewHolder) holder;
        setImage(holder1.imgItemOne, holder1.imgItemTwo, holder1.imgItemThree, baiDang.getArrListImage());
        holder1.txtTenNguoiDang.setText(baiDang.getTen());
        holder1.txtTenSach.setText(baiDang.getTenSach());
        holder1.txtNoiDung.setText(baiDang.getNoiDung());
        if (Utils.getDayCurent() == Integer.parseInt(Utils.getDay(baiDang.getThoiGian()))) {
            holder1.txtThoiGian.setText(Utils.formatHourM(baiDang.getThoiGian()));
        } else {
            holder1.txtThoiGian.setText(Utils.formatThoiGian(baiDang.getThoiGian()));
        }
        holder1.txtGia.setText("Giá: " + Utils.formatGia(baiDang.getGia()));
        if (user.getId() == baiDang.getIdKhachHang()) {
            holder1.btnMessageList.setVisibility(View.INVISIBLE);
        }
        if (baiDang.isCheckLike()) {
            holder1.btnLike.setCompoundDrawables(img, null, null, null);
        } else {
            holder1.btnLike.setCompoundDrawables(img1, null, null, null);
        }
        if (baiDang.getLuotLike() != null) {
            holder1.btnLike.setText(baiDang.getLuotLike() + "");
        }
        if (baiDang.getSoBL() != null) {
            holder1.btnBinhLuan.setText(baiDang.getSoBL() + "");
        }
        Picasso.with(context).load(Constants.PORTIMAGE + baiDang.getAnhDaiDien()).resize(50, 50).into(holder1.circleImageView);
        holder1.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.linearLayout.startAnimation(buttonAnimation.startAnimation());
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
        holder1.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.btnLike.startAnimation(buttonAnimation.startAnimation());
                presenterLogicExchangeFragment.getLike(user.getId(), baiDang);
            }
        });


        holder1.btnLuuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder1.btnLuuItem.startAnimation(buttonAnimation.startAnimation());
                presenterLogicExchangeFragment.saveStore(context,baiDang);
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
                int idUser = user.getId();
                int idKH = baiDang.getIdKhachHang();
                if (idUser < idKH) {
                    insertNhomChar(idUser, idKH);
                } else {
                    insertNhomChar(idKH, idUser);
                }

            }
        });
    }

    private void setImage(ImageView imgOne, ImageView imgTwo, ImageView imgThree, List<Hinh> arrListImage) {
        if (arrListImage != null && arrListImage.size() != 0) {
            if (arrListImage.size() == 1) {
                Picasso.with(context).load(Constants.PORTIMAGE+arrListImage.get(0).getHinh()).into(imgOne);
                imgTwo.setVisibility(View.GONE);
                imgOne.setVisibility(View.VISIBLE);
                imgThree.setVisibility(View.GONE);
            } else if (arrListImage.size() == 2) {
                Picasso.with(context).load(Constants.PORTIMAGE+arrListImage.get(0).getHinh()).into(imgOne);
                Picasso.with(context).load(Constants.PORTIMAGE+arrListImage.get(1).getHinh()).into(imgTwo);
                imgTwo.setVisibility(View.VISIBLE);
                imgOne.setVisibility(View.VISIBLE);
                imgThree.setVisibility(View.GONE);
            } else {
                Picasso.with(context).load(Constants.PORTIMAGE+arrListImage.get(0).getHinh()).into(imgOne);
                Picasso.with(context).load(Constants.PORTIMAGE+arrListImage.get(1).getHinh()).into(imgTwo);
                Picasso.with(context).load(Constants.PORTIMAGE+arrListImage.get(2).getHinh()).into(imgThree);
                imgTwo.setVisibility(View.VISIBLE);
                imgOne.setVisibility(View.VISIBLE);
                imgThree.setVisibility(View.VISIBLE);
            }
        }
    }

    private void openDetailNewsPost(BaiDang baiDang, int i) {
        Intent intent = new Intent(context, DetailNewsActivity.class);
        intent.putExtra(Constants.ID_BAI_DANG, baiDang);
        intent.putExtra(Constants.GIA_TRI, i);
        context.startActivity(intent);
    }


    private void insertNhomChar(int idUser, int idKH) {
        String api = Constants.PORT + "/insertnhomchat/" + idUser + "/" + idKH;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(context, MessageActivity.class);
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    @Override
    public int getItemCount() {
        return arrListNews.size();
    }


    private void getDataBaiDang(final int page) {
        presenterLogicExchangeFragment.getNewsPost(page, context);
    }

    @Override
    public void onRefresh() {
        refetchBaiDang();
    }

    @Override
    public void onSuccessGetComment() {
        notifyDataSetChanged();
    }

    @Override
    public void onErr() {

    }

    @Override
    public void onErrResult(String err) {
        Toast.makeText(context, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessGetNewsPost(List<BaiDang> arrNewsPost) {
        final int h = arrListNews.size();
        if (arrNewsPost != null) {
            arrListNews.addAll(arrNewsPost);
            checkLoad = false;
        }
        presenterLogicExchangeFragment.getNumberComment(h, arrListNews);
        notifyDataSetChanged();
        myLoadAppProgessBar.dismiss();
        progressBarLoadNews.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccessLike(BaiDang baiDang) {
        presenterLogicExchangeFragment.getLuotKike(baiDang);
        baiDang.setCheckLike(true);
        notifyDataSetChanged();
    }

    @Override
    public void onSuccessNumberLike() {
        notifyDataSetChanged();
    }

    @Override
    public void onSuccessLoadImage() {
        notifyDataSetChanged();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        LinearLayout linearLayout;
        TextView txtTenNguoiDang, txtThoiGian, txtGia, txtNoiDung, txtTenSach;
        ImageView imgItemOne, imgItemTwo, imgItemThree;
        Button btnLike, btnBinhLuan, btnCallList, btnMessageList;
        ImageView btnLuuItem;
        ProgressBar progressbarr;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.lnItem);
            circleImageView = itemView.findViewById(R.id.imgHinhDaiDien);
            txtGia = itemView.findViewById(R.id.txtGiaSach);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDung);
            txtTenNguoiDang = itemView.findViewById(R.id.txtTenNguoiDang);
            txtThoiGian = itemView.findViewById(R.id.txtThoiGian);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            imgItemOne = itemView.findViewById(R.id.imgItemOne);
            imgItemTwo = itemView.findViewById(R.id.imgItemTwo);
            imgItemThree = itemView.findViewById(R.id.imgItemThree);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnBinhLuan = itemView.findViewById(R.id.btnBinhLuan);
            btnLuuItem = itemView.findViewById(R.id.btnLuuItem);
            btnCallList = itemView.findViewById(R.id.btnCallList);
            btnMessageList = itemView.findViewById(R.id.btnMessageList);
            progressbarr = itemView.findViewById(R.id.progressbarr);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return arrListNews.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class myHandler extends Handler {
        public myHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    page++;
                    progressBarLoadNews.setVisibility(View.VISIBLE);
                    getDataBaiDang(page);
                    break;
                case 1:
                    checkLoad = false;
                    break;
            }
        }
    }

    public class myThread extends Thread {
        @Override
        public void run() {
            try {
                myHandler.sendEmptyMessage(0);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
        }
    }
}
