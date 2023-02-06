package controllers.hoKhauControllers;

import Beans.HoKhauBean;

public final class HoKhauHolder {
    private HoKhauBean hoKhauBean;
    private static final HoKhauHolder INSTANCE = new HoKhauHolder();

    private HoKhauHolder(){};

    public static HoKhauHolder getInstance(){
        return INSTANCE;
    }

    public void setHoKhauBean(HoKhauBean hoKhauBean){
        this.hoKhauBean = hoKhauBean;
    }

    public HoKhauBean getHoKhauBean(){
        return this.hoKhauBean;
    }
}
