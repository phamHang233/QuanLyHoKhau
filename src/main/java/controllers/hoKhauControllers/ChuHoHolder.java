package controllers.hoKhauControllers;

import Beans.NhanKhauBean;

//Singleton class for passing data around
public final class ChuHoHolder {
    private NhanKhauBean nhanKhauBean;
    private final static ChuHoHolder INSTANCE = new ChuHoHolder();

    private ChuHoHolder(){}

    public static ChuHoHolder getInstance(){
        return INSTANCE;
    }

    public void setData(NhanKhauBean nhanKhauBean){
        this.nhanKhauBean = nhanKhauBean;
    }

    public NhanKhauBean getNhanKhauBean(){
        return this.nhanKhauBean;
    }
}
