package models;

import java.util.Date;

public class SuDungNVHModel {
    private int stt;
    private Date ngaySuDung;
    private String mucDich;
    private String nguoiLap;
    private String ghiChu;
    private int IDNguoiLap;


    public void setIDNguoiLap(int IDNguoiLap) {
        this.IDNguoiLap = IDNguoiLap;
    }

    public int getIDNguoiLap() {
        return IDNguoiLap;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public int getStt() {
        return stt;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public String getMucDich() {
        return mucDich;
    }

    public String getNguoiLap() {
        return nguoiLap;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public void setNgaySuDung(Date ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }

    public void setMucDich(String mucDich) {
        this.mucDich = mucDich;
    }

    public void setNguoiLap(String nguoiLap) {
        this.nguoiLap = nguoiLap;
    }


}
