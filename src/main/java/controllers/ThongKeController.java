package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.NhanKhauModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static services.SQLServerConnection.getSqlConnection;

//Quản lý màn hình thống kê
public class ThongKeController implements Initializable {

    SwitchScene switchScene;
    @FXML
    private TitledPane NVHTitle;

    @FXML
    private AnchorPane nhanKhauMode;

    @FXML
    private Label chuyenMonNhanKhau;

    @FXML
    private Label danTocNhanKhau;

    @FXML
    private Label diaChiMoiNhanKhau;

    @FXML
    private Label diaChiNhanKhau;

    @FXML
    private Label ghiChuNhanKhau;

    @FXML
    private Label gioiTinhNhanKhau;

    @FXML
    private Label hoTenNhanKhau;

    @FXML
    private Label hocVanNhanKhau;

    @FXML
    private Label lyDoChuyenDenNhanKhau;

    @FXML
    private Label lyDoXoaNhanKhau;

    @FXML
    private Label ngayChuyenDenNhanKhau;

    @FXML
    private Label ngayChuyenDiNhanKhau;

    @FXML
    private Label ngaySinhNhanKhau;

    @FXML
    private Label ngayTaoNhanKhau;

    @FXML
    private Label ngayXoaNhanKhau;

    @FXML
    private Label ngheNghiepNhanKhau;

    @FXML
    private Label ngoaiNguNhanKhau;

    @FXML
    private Label nguoiTaoNhanKhau;

    @FXML
    private Label nguyenQuanNhanKhau;

    @FXML
    private Label noiLamViecNhanKhau;

    @FXML
    private Label noiSinhNhanKhau;

    @FXML
    private Label quocTichNhanKhau;

    @FXML
    private TextField soCanCuocCongDan;

    @FXML
    private Label soHoChieuNhanKhau;

    @FXML
    private Button thongKeSoLieuBtn;

    @FXML
    private AnchorPane thongTinTimKiemNhanKhau;

    @FXML
    private Label thuongTruNhanKhau;

    @FXML
    private Label tienAnNhanKhau;

    @FXML
    private Label tiengDanTocNhanKhau;

    @FXML
    private Button timKiemHoKhauBtn;

    @FXML
    private Button timKiemNhanKhauBtn;

    @FXML
    private Label tonGiaoNhanKhau;

    private void exceptionHandle(String message) {
    }

    @FXML
    void btnCSVC(ActionEvent event) throws IOException {
        switchScene.changeToThietBi(event);
    }

    @FXML
    void btnChoThue(ActionEvent event) throws IOException {
        switchScene.changeToChoThue(event);
    }

    @FXML
    void btnDangXuat(ActionEvent event) throws IOException {
        switchScene.changToLogin(event);
    }

    @FXML
    void btnHoKhau(ActionEvent event) throws IOException {
        switchScene.changeToHoKhau(event);
    }

    @FXML
    void btnHoSo(ActionEvent event) {

    }

    @FXML
    void btnNhanKhau(ActionEvent event) throws IOException {
        switchScene.changeToNhanKhau(event);
    }

    @FXML
    void btnSuDung(ActionEvent event) throws IOException {
        switchScene.changeToSuDung(event);
    }

    @FXML
    void btnThongBao(ActionEvent event) {
        System.out.println("Hiển thị thông báo!");
    }

    @FXML
    void btnThongKe(ActionEvent event) throws IOException {

    }

    @FXML
    void btnTrangChu(ActionEvent event) throws IOException {
        switchScene.changeToMain(event);
    }

    @FXML
    void timKiemNhanKhau(ActionEvent event) {
        String stringTemp = soCanCuocCongDan.getText();
//        System.out.println(stringTemp);
        NhanKhauModel nhanKhauModel = new NhanKhauModel();

        try {
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.id = chung_minh_thu.idNhanKhau WHERE soCMT = '" + stringTemp + "'";
//            System.out.println(query);
            PreparedStatement preparedStatement = getSqlConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                nhanKhauModel.setHoTen(rs.getString("hoTen"));
//                nhanKhauModel.setGioiTinh(rs.getString("gioiTinh"));
//                nhanKhauModel.setNamSinh(rs.getDate("namSinh"));
//                nhanKhauModel.setDiaChiHienNay(rs.getString("diaChiHienNay"));
//                nhanKhauModel.setNguyenQuan(rs.getString("nguyenQuan"));
//                nhanKhauModel.setTonGiao(rs.getString("tonGiao"));
//                nhanKhauModel.setDanToc(rs.getString("danToc"));
//                nhanKhauModel.setQuocTich(rs.getString("quocTich"));
//                nhanKhauModel.setNoiThuongTru(rs.getString("noiThuongTru"));
//                nhanKhauModel.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                hoTenNhanKhau.setText(rs.getString("hoTen"));
                soHoChieuNhanKhau.setText(rs.getString("soHoChieu"));
                quocTichNhanKhau.setText(rs.getString("quocTich"));
                gioiTinhNhanKhau.setText(rs.getString("gioiTinh"));
                ngaySinhNhanKhau.setText(rs.getString("namSinh"));
                noiSinhNhanKhau.setText(rs.getString("noiSinh"));
                nguyenQuanNhanKhau.setText(rs.getString("nguyenQuan"));
                thuongTruNhanKhau.setText(rs.getString("noiThuongTru"));
                diaChiNhanKhau.setText(rs.getString("diaChiHienNay"));
                danTocNhanKhau.setText(rs.getString("danToc"));
                tonGiaoNhanKhau.setText(rs.getString("tonGiao"));
                hocVanNhanKhau.setText(rs.getString("trinhDoHocVan"));
                chuyenMonNhanKhau.setText(rs.getString("trinhDoChuyenMon"));
                tiengDanTocNhanKhau.setText(rs.getString("bietTiengDanToc"));
                ngoaiNguNhanKhau.setText(rs.getString("trinhDoNgoaiNgu"));
                ngheNghiepNhanKhau.setText(rs.getString("ngheNghiep"));
                noiLamViecNhanKhau.setText(rs.getString("noiLamViec"));
                tienAnNhanKhau.setText(rs.getString("tienAn"));
                ngayChuyenDenNhanKhau.setText(rs.getString("ngayChuyenDen"));
                lyDoChuyenDenNhanKhau.setText(rs.getString("lyDoChuyenDen"));
                ngayChuyenDiNhanKhau.setText(rs.getString("ngayChuyenDi"));
                diaChiMoiNhanKhau.setText(rs.getString("diaChiMoi"));
                ngayTaoNhanKhau.setText(rs.getString("ngayTao"));
                nguoiTaoNhanKhau.setText(rs.getString("idNguoiTao"));
                ngayXoaNhanKhau.setText(rs.getString("ngayXoa"));
                lyDoXoaNhanKhau.setText(rs.getString("lyDoXoa"));
                ghiChuNhanKhau.setText(rs.getString("ghiChu"));
            }
            preparedStatement.close();
//            list.add(nhanKhauModel);
//            thongtinnhankhau.setItems(list);

            thongTinTimKiemNhanKhau.setVisible(true);
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());

        }
    }
        @FXML
        void xoaTimKiemNhanKhau (ActionEvent event){
            thongTinTimKiemNhanKhau.setVisible(false);
            soCanCuocCongDan.setText("");
            hoTenNhanKhau.setText("");
            soHoChieuNhanKhau.setText("");
            quocTichNhanKhau.setText("");
            gioiTinhNhanKhau.setText("");
            ngaySinhNhanKhau.setText("");
            noiSinhNhanKhau.setText("");
            nguyenQuanNhanKhau.setText("");
            thuongTruNhanKhau.setText("");
            diaChiNhanKhau.setText("");
            danTocNhanKhau.setText("");
            tonGiaoNhanKhau.setText("");
            hocVanNhanKhau.setText("");
            chuyenMonNhanKhau.setText("");
            tiengDanTocNhanKhau.setText("");
            ngoaiNguNhanKhau.setText("");
            ngheNghiepNhanKhau.setText("");
            noiLamViecNhanKhau.setText("");
            tienAnNhanKhau.setText("");
            ngayChuyenDenNhanKhau.setText("");
            lyDoChuyenDenNhanKhau.setText("");
            ngayChuyenDiNhanKhau.setText("");
            diaChiMoiNhanKhau.setText("");
            ngayTaoNhanKhau.setText("");
            nguoiTaoNhanKhau.setText("");
            ngayXoaNhanKhau.setText("");
            lyDoXoaNhanKhau.setText("");
            ghiChuNhanKhau.setText("");
        }
        @FXML
        void thongKeMode(ActionEvent event) throws IOException {
//            switchScene.changeToThongKe(event);
            nhanKhauMode.setVisible(true);
//            System.out.println("test thong ke Mode");
            thongTinTimKiemNhanKhau.setVisible(false);
        }
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            switchScene = new SwitchScene();
            nhanKhauMode.setVisible(false);
        }
}
