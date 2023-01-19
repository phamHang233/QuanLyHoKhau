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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button buttonGiaDinh;

    @FXML
    private Button buttonTieuSu;
    @FXML
    private TextField cccd;

    @FXML
    private TableColumn<NhanKhauModel, String> diachi;

    @FXML
    private TableColumn<NhanKhauModel, String> hoten;

    @FXML
    private TableColumn<NhanKhauModel, Date> ngaysinh;

    @FXML
    private TableColumn<NhanKhauModel, String> nghenghiep;

    @FXML
    private TableColumn<NhanKhauModel, String> nguyenquan;

    @FXML
    private TableColumn<NhanKhauModel, String> noilamviec;

    @FXML
    private TableColumn<NhanKhauModel, String> quoctich;

    @FXML
    private TableView<NhanKhauModel> thongtinnhankhau;

    @FXML
    private TableColumn<NhanKhauModel, String> thuongtru;

    @FXML
    private TableColumn<NhanKhauModel, String> tongiao;

    @FXML
    private TableColumn<NhanKhauModel, String> dantoc;
    ObservableList<NhanKhauModel> list = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchScene = new SwitchScene();
        hoten.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaysinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        diachi.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        nghenghiep.setCellValueFactory(new PropertyValueFactory<>("ngheNghiep"));
        nguyenquan.setCellValueFactory(new PropertyValueFactory<>("nguyenQuan"));
        noilamviec.setCellValueFactory(new PropertyValueFactory<>("noiLamViec"));
        quoctich.setCellValueFactory(new PropertyValueFactory<>("quocTich"));
        thuongtru.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tongiao.setCellValueFactory(new PropertyValueFactory<>("tonGiao"));
        dantoc.setCellValueFactory(new PropertyValueFactory<>("danToc"));
    }
    @FXML
    void btnHoKhau(ActionEvent event) throws IOException {
        switchScene.changeToHoKhau(event);
    }


    @FXML
    void btnNhaVH(ActionEvent event) throws IOException {
        switchScene.changeToNVH(event);
    }

    @FXML
    void btnNhanKhau(ActionEvent event) throws IOException {
        switchScene.changeToNhanKhau(event);
    }

    @FXML
    void btnThongKe(ActionEvent event) throws IOException {
        switchScene.changeToThongKe(event);
    }
    @FXML
    void btnTrangChu(ActionEvent event) throws IOException {
        switchScene.changeToMain(event);
    }
    @FXML
    void btnDangXuat(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/views/login-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,  600,322));
        stage.centerOnScreen();
    }
    @FXML
    void btnHoSo(ActionEvent event) throws IOException{


    }
    @FXML
    void btnThongBao(ActionEvent event) throws IOException{
        System.out.println("Hiển thị thông báo!");
    }
    @FXML
    void timkiem(ActionEvent event) {
        String socccd = cccd.getText();
        System.out.println(socccd);
        NhanKhauModel nhanKhauModel = new NhanKhauModel();

        try{
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.id = chung_minh_thu.idNhanKhau WHERE soCMT = '" + socccd + "'";
            System.out.println(query);
            PreparedStatement preparedStatement = getSqlConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                nhanKhauModel.setHoTen(rs.getString("hoTen"));
                nhanKhauModel.setGioiTinh(rs.getString("gioiTinh"));
                nhanKhauModel.setNamSinh(rs.getDate("namSinh"));
                nhanKhauModel.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                nhanKhauModel.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhauModel.setTonGiao(rs.getString("tonGiao"));
                nhanKhauModel.setDanToc(rs.getString("danToc"));
                nhanKhauModel.setQuocTich(rs.getString("quocTich"));
                nhanKhauModel.setNoiThuongTru(rs.getString("noiThuongTru"));
                nhanKhauModel.setDiaChiHienNay(rs.getString("diaChiHienNay"));
            }
            preparedStatement.close();
            list.add(nhanKhauModel);
            thongtinnhankhau.setItems(list);


        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
    }
    @FXML
    void xoa(ActionEvent event) {
        thongtinnhankhau.getItems().clear();
    }
    private void exceptionHandle(String message) {
    }

    public void displayGiaDinh(ActionEvent event) {
    }

    public void displayTieuSu(ActionEvent event) {
    }
}
