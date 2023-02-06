package controllers.hoKhauControllers;

import Beans.HoKhauBean;
import Beans.MemOfFamily;
import Beans.NhanKhauBean;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.HoKhauModel;
import models.NhanKhauModel;
import models.ThanhVienModel;
import services.HoKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThemMoiController implements Initializable{
    @FXML
    TextField maHoKhau;
    @FXML
    TextField maKhuVuc;
    @FXML
    TextField diaChi;
    @FXML
    TextField chuHo;
    @FXML
    TextField ngaySinhChuHo;
    @FXML
    TextField soCMTChuHo;
    @FXML
    TableView<MemOfFamily> table;
    @FXML
    TableColumn<MemOfFamily, String> hoTen;
    @FXML
    TableColumn<MemOfFamily, String> ngaySinh;
    @FXML
    TableColumn<MemOfFamily, String> quanHeVoiChuHo;

    ObservableList<MemOfFamily> memOfFamilyObservableList;
    List<MemOfFamily> memOfFamilyList;
    NhanKhauBean selectedNhanKhau;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memOfFamilyList = new ArrayList<>();
        memOfFamilyObservableList = FXCollections.observableList(memOfFamilyList);
        hoTen.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinh.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeVoiChuHo.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        table.setItems(memOfFamilyObservableList);

    }


    public void chon(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/Chon.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataChuHo();
    }

    public void setDataChuHo(){
        selectedNhanKhau = ChuHoHolder.getInstance().getNhanKhauBean();
        chuHo.setText(selectedNhanKhau.getNhanKhauModel().getHoTen());
        ngaySinhChuHo.setText(selectedNhanKhau.getNhanKhauModel().getNamSinh().toString());
        soCMTChuHo.setText(selectedNhanKhau.getChungMinhThuModel().getSoCMT());
    }

    public void them(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/Sua.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        addDataToTable();
    }

    public void xoa(ActionEvent event){
        MemOfFamily selectedMemOfFamily = table.getSelectionModel().getSelectedItem();
        memOfFamilyObservableList.remove(selectedMemOfFamily);
    }

    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    public void luu(ActionEvent event) throws SQLException, ClassNotFoundException {

        HoKhauBean hoKhauBean = new HoKhauBean();
        //Set thong tin ho khau
        HoKhauModel hoKhau = new HoKhauModel();
        hoKhau.setMaHoKhau(maHoKhau.getText());
        hoKhau.setMaKhuVuc(maKhuVuc.getText());
        hoKhau.setDiaChi(diaChi.getText());
        hoKhau.setIdChuHo(selectedNhanKhau.getNhanKhauModel().getID());
        hoKhauBean.setHoKhauModel(hoKhau);
        //set thong tin chu ho
        NhanKhauModel chuHo = selectedNhanKhau.getNhanKhauModel();
        hoKhauBean.setChuHo(chuHo);
        //set thanh vien cua ho
        List<ThanhVienModel> listThanhVienCuaHo = new ArrayList<>();
        memOfFamilyObservableList.stream().forEach(memOfFamily -> {
            listThanhVienCuaHo.add(memOfFamily.getThanhVienCuaHoModel());
        });
        hoKhauBean.setListThanhVienCuaHo(listThanhVienCuaHo);
        new HoKhauService().addNew(hoKhauBean);
        huy(event);
    }

    public void addDataToTable(){
        ThanhVienHoHolder thanhVienHoHolder = ThanhVienHoHolder.getInstance();
        memOfFamilyObservableList.addAll(thanhVienHoHolder.getMemOfFamilyObservableList());
        List<MemOfFamily> list = new ArrayList<>(memOfFamilyObservableList);
        System.out.println(list.get(0).getNhanKhau().getNhanKhauModel().getHoTen());
        table.refresh();
    }

}
