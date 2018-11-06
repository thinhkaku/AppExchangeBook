package com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.ResultUploadImage;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Sach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.UuDai;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.CheckLike;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.DanhGia;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.LikeResult;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.ListDeleteBaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NhomChat;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NumberComment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NumberLike;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.notifiresult.MessageResult;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.notifiresult.ResultDangBai;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SOService {
    @GET("/getbaidang")
    Call<ArrayList<BaiDang>>getBaiDang();

    @FormUrlEncoded
    @POST(Constants.CHECKLOGIN)
    Call<List<User>>checkLogin(@Field(Constants.USERPASS) String userPass);

    @FormUrlEncoded
    @POST(Constants.KIEMTRATAIKHOAN)
    Call<String>kiemTraTaiKhoan(@Field(Constants.TAIKHOANCHECK) String taikhoan);

    @FormUrlEncoded
    @POST(Constants.INSERTKHACHHANG)
    Call<String>insertKhachHang(@Field(Constants.DATA) String data);

    @FormUrlEncoded
    @POST(Constants.SACHMOINHAT)
    Call<List<BiaSach>>getSachMoiNhat(@Field(Constants.ID) int id);

    @FormUrlEncoded
    @POST(Constants.GETSACH)
    Call<List<BiaSach>>getSach(@Field(Constants.ID) int id);

    @FormUrlEncoded
    @POST(Constants.CHITIETSACH)
    Call<List<Sach>>getChiTietSach(@Field(Constants.IDS) int id);

    @POST(Constants.UUDAI)
    Call<List<UuDai>>getUuDai();

    @FormUrlEncoded
    @POST(Constants.GETSEARCH)
    Call<List<BiaSach>>getSearchSach(
            @Field(Constants.SEARCH) String query
    );

    @FormUrlEncoded
    @POST(Constants.GETNHOMCHAT)
    Call<List<NhomChat>>getNhomChat(
            @Field(Constants.IDUSER)String idUser
    );

    @FormUrlEncoded
    @POST(Constants.GETSACH)
    Call<List<BiaSach>>getSachGoiY(
                    @Field(Constants.ID) int idLS
            );

    @FormUrlEncoded
    @POST(Constants.GET_BAI_DANG)
    Call<List<BaiDang>>getBaiDang(@Field(Constants.PAGE)String page);

    @FormUrlEncoded
    @POST(Constants.GETSOBL)
    Call<List<NumberComment>>getSoBL(@Field(Constants.IDBL)String idBD);

    @FormUrlEncoded
    @POST(Constants.GETSOLK)
    Call<List<NumberLike>>getSoLuotLike(@Field(Constants.IDBL)String idBD);

    @FormUrlEncoded
    @POST(Constants.DELETEBAIDANG)
    Call<ListDeleteBaiDang>deleteNews(@Field(Constants.IDBL)String idBD);

    @FormUrlEncoded
    @POST(Constants.GETDANGBAI)
    Call<MessageResult>getDangBai(@Field(Constants.IDKHACHHANG)String idKH,
                                  @Field(Constants.TENS)String tenS,
                                  @Field(Constants.NOIDUNG)String noiDung,
                                  @Field(Constants.GIAS)String giaS);

    @FormUrlEncoded
    @POST(Constants.GETBAIDANGCANHAN)
    Call<List<BaiDang>>getBaiDangCaNhan(@Field(Constants.IDKHACHHANG)String idKH);

    @FormUrlEncoded
    @POST(Constants.GETLUOTLIKE)
    Call<String>getLike(@Field(Constants.IDKHACHHANG)String idKH, @Field(Constants.ID_BAI_DANG)String idBD);

    @POST(Constants.GET_LIKE)
    Call<List<LikeResult>>getSoLike();


    @FormUrlEncoded
    @POST(Constants.GETHINHANH)
    Call<List<Hinh>>getHinhAnh(@Field(Constants.ID_BAI_DANG)String idBD);

    @FormUrlEncoded
    @POST(Constants.GET_DANH_GIA)
    Call<List<DanhGia>>getDanhGia(@Field(Constants.IDS)String idS);

    @FormUrlEncoded
    @POST(Constants.CHECK_LIKE)
    Call<List<CheckLike>>checkLike(@Field(Constants.ID_LIKE)String idLike);

    @POST(Constants.GET_SO_BD)
    Call<List<NumberComment>>getSoBaiDang();

    @FormUrlEncoded
    @POST(Constants.GUI_DANH_GIA)
    Call<ResultDangBai>guiDanhGia(@Field(Constants.IDS)String idS, @Field(Constants.DANH_GIA)String danhG, @Field(Constants.GIA_T)String giaTri,
                                        @Field(Constants.TEN_DG)String ten);

    @Multipart
    @POST(Constants.UPLOADIMAGE)
    Call<ResultUploadImage>uploadImage(
            @Part MultipartBody.Part file
    );








}
