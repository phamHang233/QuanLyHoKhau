package controllers.hoKhauControllers;

import Beans.MemOfFamily;
import javafx.collections.ObservableList;

public final class ThanhVienHoHolder {
    private ObservableList<MemOfFamily> memOfFamilyObservableList;
    private final static ThanhVienHoHolder INSTANCE = new ThanhVienHoHolder();

    private ThanhVienHoHolder(){};

    public static ThanhVienHoHolder getInstance(){ return INSTANCE; }

    public void setListThanhVienHo(ObservableList<MemOfFamily> memOfFamilyObservableList){ this.memOfFamilyObservableList = memOfFamilyObservableList;}

    public ObservableList<MemOfFamily> getMemOfFamilyObservableList(){ return this.memOfFamilyObservableList; }
}
