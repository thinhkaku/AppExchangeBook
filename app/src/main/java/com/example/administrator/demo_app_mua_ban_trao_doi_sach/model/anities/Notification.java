package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import java.io.Serializable;

public class Notification implements Serializable{
    private int idEvent;
    private String imgPath;

    public Notification(int idEvent, String imgPath) {
        this.idEvent = idEvent;
        this.imgPath = imgPath;
    }

    public Notification() {
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
