package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.MessageActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.DetailMessageFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NhomChat;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork.XuLySuKien;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.MainView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupChatAdapter extends BaseAdapter implements MainView{
    private LayoutInflater inflater;
    private Context context;
    private List<NhomChat> arrNhomChat;
    private List<NhomChat>arrNhomChat1;
    private static XuLySuKien xuLySuKien;
    private static String CLIENT_SEND_REQUEST_XOA_NHOM_CHAT="CLIENT_SEND_REQUEST_XOA_NHOM_CHAT";
    private static String SERVER_SEND_RESULT_XOA_NHOM_CHAT="SERVER_SEND_RESULT_XOA_NHOM_CHAT";


    public GroupChatAdapter(Context context, List<NhomChat> arrNhomChat) {
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.arrNhomChat = arrNhomChat;
        this.arrNhomChat1=arrNhomChat;

        xuLySuKien=new XuLySuKien(this,(Activity)context);
    }

    @Override
    public int getCount() {
        return arrNhomChat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        View view=inflater.inflate(R.layout.item_group_chat,parent,false);
        if (viewHodler==null){
            viewHodler=new ViewHodler();
            viewHodler.txtTenNhanTin=view.findViewById(R.id.txtTenNhanTin);
            viewHodler.imgHinhDaiDienNhanTin=view.findViewById(R.id.imgHinhDaiDienNhanTin);
            viewHodler.btnXoaNhomChat=view.findViewById(R.id.btnXoaNhomChat);
            viewHodler.lnearNhanTin=view.findViewById(R.id.lnearNhanTin);
            view.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) view.getTag();
        }
        final NhomChat nhomChat=arrNhomChat.get(position);
        viewHodler.txtTenNhanTin.setText(nhomChat.getTen());
        Picasso.with(context).load(Constants.PORTIMAGE+nhomChat.getAnhDaiDien()).resize(100,100).into(viewHodler.imgHinhDaiDienNhanTin);
        viewHodler.btnXoaNhomChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDilog(nhomChat.getIdNhom());

            }
        });
        viewHodler.lnearNhanTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageActivity messageActivity = (MessageActivity) context;
                messageActivity.setTilteToolBar(nhomChat.getTen());
                DetailMessageFragment detailMessageFragment = messageActivity.getDetailMessageFragment();
                detailMessageFragment.getIdPhong(nhomChat.getIdPhong(),nhomChat.getIdKhachHang());
                messageActivity.swicthFrament(detailMessageFragment);
            }
        });
        return view;
    }

    public  void showDilog(final int id){
        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.thong_bao));
        builder.setMessage(context.getString(R.string.question_delete_group_chat));
        builder.setPositiveButton(context.getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                xuLySuKien.sendToServer(CLIENT_SEND_REQUEST_XOA_NHOM_CHAT,id+"");
                xuLySuKien.serverSendData(SERVER_SEND_RESULT_XOA_NHOM_CHAT);
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
    public void upDateNhomChat(String newtext){
        if (newtext.isEmpty()){
            arrNhomChat=arrNhomChat1;
        }else {
            List<NhomChat>arrNhomChat2=new ArrayList<>();
            for (NhomChat nhomChat:arrNhomChat1){
                if (nhomChat.getTen().toLowerCase().contains(newtext)){
                    arrNhomChat2.add(nhomChat);
                }
            }
            arrNhomChat=arrNhomChat2;
        }
        notifyDataSetChanged();
    }

    @Override
    public void traVeKetQua(Object b) {
        if(b.toString().equals("1")){
            Toast.makeText(context,"Xóa nhóm thành công",Toast.LENGTH_SHORT).show();
        }
    }

    private class ViewHodler{
        private TextView txtTenNhanTin;
        private CircleImageView imgHinhDaiDienNhanTin;
        private ImageButton btnXoaNhomChat;
        private LinearLayout lnearNhanTin;
    }
}
