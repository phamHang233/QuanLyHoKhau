package controllers.hoKhauControllers;

import Beans.HoKhauBean;
import Beans.MemOfFamily;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.NhanKhauModel;
import models.ThanhVienModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChiTietController implements Initializable {
    @FXML
    TableView<MemOfFamily> table;
    @FXML
    TableColumn<MemOfFamily, String> hoTen;
    @FXML
    TableColumn<MemOfFamily, String> ngaySinh;
    @FXML
    TableColumn<MemOfFamily, String> gioiTinh;
    @FXML
    TableColumn<MemOfFamily, String> quanHeVoiChuHo;
    @FXML
    Label maHoKhau;
    @FXML
    Label hoTenChuHo;
    @FXML
    Label diaChi;
    @FXML
    Label ngayLap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        HoKhauHolder hoKhauHolder = HoKhauHolder.getInstance();
        HoKhauBean hoKhauBean = hoKhauHolder.getHoKhauBean();
        List<ThanhVienModel> thanhVienCuaHoModels = hoKhauBean.getListThanhVienCuaHo();
        List<NhanKhauModel> nhanKhauModels = hoKhauBean.getListNhanKhauModels();

        List<MemOfFamily> memOfFamilyList = new ArrayList<>();

        //Set thanh vien cua ho
        thanhVienCuaHoModels.stream().forEach(thanhVienCuaHoModel -> {
            MemOfFamily memOfFamily = new MemOfFamily();
            memOfFamily.setThanhVienCuaHoModel(thanhVienCuaHoModel);
            memOfFamilyList.add(memOfFamily);
        });

        //Set nhan khau bean
        for (int i = 0; i < memOfFamilyList.size(); i ++){
            memOfFamilyList.get(i).getNhanKhau().setNhanKhauModel(nhanKhauModels.get(i));
        }

        maHoKhau.setText(hoKhauBean.getHoKhauModel().getMaHoKhau());
        hoTenChuHo.setText(hoKhauBean.getChuHo().getHoTen());
        diaChi.setText(hoKhauBean.getHoKhauModel().getDiaChi());
        ngayLap.setText(hoKhauBean.getHoKhauModel().getNgayLap().toString());

        ObservableList<MemOfFamily> memOfFamilyObservableList = FXCollections.observableList(memOfFamilyList);
        hoTen.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinh.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        gioiTinh.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getGioiTinh()));
        quanHeVoiChuHo.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        table.setItems(memOfFamilyObservableList);
    }
}
