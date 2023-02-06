package Beans;

import models.ThanhVienModel;

/**
 *
 * @author Hai
 */
public class MemOfFamily {
    private NhanKhauBean nhanKhau;
    private ThanhVienModel thanhVienCuaHoModel;

    public MemOfFamily(NhanKhauBean nhanKhau, ThanhVienModel thanhVienCuaHoModel) {
        this.nhanKhau = nhanKhau;
        this.thanhVienCuaHoModel = thanhVienCuaHoModel;
    }
    
    public MemOfFamily() {
        this.nhanKhau = new NhanKhauBean();
        this.thanhVienCuaHoModel = new ThanhVienModel();
    }
    
    
    public NhanKhauBean getNhanKhau() {
        return nhanKhau;
    }

    public void setNhanKhau(NhanKhauBean nhanKhau) {
        this.nhanKhau = nhanKhau;
    }

    public ThanhVienModel getThanhVienCuaHoModel() {
        return thanhVienCuaHoModel;
    }

    public void setThanhVienCuaHoModel(ThanhVienModel thanhVienCuaHoModel) {
        this.thanhVienCuaHoModel = thanhVienCuaHoModel;
    }
    
    
}
