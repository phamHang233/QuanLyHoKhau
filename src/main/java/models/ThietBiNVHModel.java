package models;

import java.time.LocalDate;
import java.util.Date;

public class ThietBiNVHModel {
    private int sqlID;
    private int stt;
    private int soLuong;
    private String tenThietBi;
    private Date ngayCapNhat;
    private String trangThai;
    private String ghiChu;

    public int getSqlID() {
        return sqlID;
    }

    public void setSqlID(int sqlID) {
        this.sqlID = sqlID;
    }

    public int getStt() {
        return stt;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
