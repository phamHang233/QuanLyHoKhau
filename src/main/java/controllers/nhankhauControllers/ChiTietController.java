package controllers.nhankhauControllers;

import models.NhanKhauModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.NhanKhauService;
import services.SQLServerConnection;


public class ChiTietController implements Initializable {
    @FXML
    Label hoTen;
    @FXML
    Label namSinh;
    @FXML
    Label gioiTinh;
    @FXML
    Label nguyenQuan;
    @FXML
    Label danToc;
    @FXML
    Label tonGiao;
    @FXML
    Label quocTich;
    @FXML
    Label soCMT;

    NhanKhauService nhankhauService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nhankhauService = new NhanKhauService();

    }

    public void setData(NhanKhauModel nhanKhauModel) {
        String cmtFromID = NhanKhauService.getCMTfromID(nhanKhauModel.getID());
        hoTen.setText(nhanKhauModel.getHoTen());
        namSinh.setText(String.valueOf(nhanKhauModel.getNamSinh()));
        gioiTinh.setText(nhanKhauModel.getGioiTinh());
        nguyenQuan.setText(nhanKhauModel.getNguyenQuan());
        danToc.setText(nhanKhauModel.getDanToc());
        tonGiao.setText(nhanKhauModel.getTonGiao());
        quocTich.setText(nhanKhauModel.getQuocTich());
        soCMT.setText(cmtFromID);
    }

}
