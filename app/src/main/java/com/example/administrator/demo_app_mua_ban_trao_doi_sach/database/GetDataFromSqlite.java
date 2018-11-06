package com.example.administrator.demo_app_mua_ban_trao_doi_sach.database;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHang;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class GetDataFromSqlite {
    private Realm realm;

    public GetDataFromSqlite() {
        realm = Realm.getDefaultInstance();
    }

    public RealmResults<GioHang> getGioHang() {
        realm.beginTransaction();
        RealmResults<GioHang> rs = realm.where(GioHang.class).findAllSorted("id", Sort.DESCENDING);
        realm.commitTransaction();
        return rs;
    }

    public void insert(GioHang gioHang) {
        realm.beginTransaction();
        if (realm.where(GioHang.class).findAll().size() > 0) {
            int id_new = realm.where(GioHang.class).max("id").intValue() + 1;
            gioHang.setId(id_new);
        } else {
            gioHang.setId(1);
        }
        realm.insertOrUpdate(gioHang);
        realm.commitTransaction();
    }
    public void update(GioHang gioHang) {
        realm.beginTransaction();
        realm.insertOrUpdate(gioHang);
        realm.commitTransaction();
    }

    public void delete(GioHang gioHang) {
        realm.beginTransaction();
        gioHang.deleteFromRealm();
        realm.commitTransaction();
    }
}
