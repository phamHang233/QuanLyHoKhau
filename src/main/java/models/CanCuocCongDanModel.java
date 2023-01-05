package models;

import java.util.Date;

public class CanCuocCongDanModel {
    private int ID;
    private int idNhanKhau;
    private String soCMT;
    private Date ngayCap;
    private String noiCap;

    public int getID() {
        return ID;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public String getSoCMT() {
        return soCMT;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public void setSoCMT(String soCMT) {
        this.soCMT = soCMT;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }
}
