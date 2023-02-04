package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import services.SQLServerConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

// quản lý màn hình trang chủ
public class MainController implements Initializable {
    public MainController(){

    }
    SwitchScene switchScene;
    @FXML
    private TitledPane NVHTitle;

    @FXML
    private Label hoTen;
    @FXML
    private Label ngaySinh;
    @FXML
    private Label gioiTinh;
    @FXML
    private Label chucVu;
    @FXML
    private Label sdt;
    @FXML
    private Label soNhanKhau;
    @FXML
    private Label soHoKhau;
    @FXML
    private Label soNKTamVang;
    @FXML
    private Label soNKTamTru;

    // gán các chỉ số cho các label
    public void setData() {
        try {

            // tính tổng số nhân khẩu
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM nhan_khau";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.soNhanKhau.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            // set thông tin của người đăng nhập lên màn hình
            query = "SELECT hoTen, namSinh, gioiTinh, chucVu, SDT FROM nhan_khau, users WHERE users.IDNhanKhau= nhan_khau.ID and users.userName= '" + LoginController.currentUser.getUserName() + "'";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            LoginController.currentUser.setIDNhanKhau(rs.getInt("IDNhanKhau"));
                this.hoTen.setText(rs.getString("hoTen"));
                this.chucVu.setText(rs.getString("chucVu"));
                this.gioiTinh.setText(rs.getString("gioiTinh"));
                this.ngaySinh.setText(String.valueOf(rs.getDate("namSinh")));
                this.sdt.setText(String.valueOf(rs.getString("SDT")));

            preparedStatement.close();

            // tính tổng số hộ khẩu
            query = "SELECT COUNT(*) as tong FROM ho_khau";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
               this.soHoKhau.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();



            // tính tổng số nhân khẩu tạm trú
            query = "SELECT COUNT(*) AS tong FROM tam_tru WHERE denNgay < NOW()";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.soNKTamTru.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            System.out.println("HEllo");
            System.out.println(LoginController.currentUser.getUserName());

            //tính số nhân khẩu tạm vắng
            query = "SELECT COUNT(*) AS tong FROM tam_vang WHERE denNgay < NOW()";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.soNKTamVang.setText(String.valueOf(rs.getInt("tong")));
            }

            preparedStatement.close();
//

            connection.close();
        } catch (Exception e) {
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        switchScene = new SwitchScene();

    }

    @FXML
    void btnHoKhau(ActionEvent event) throws IOException {
        switchScene.changeToHoKhau(event);
    }


    @FXML
    void btnNhaVH(ActionEvent event) throws IOException {
        NVHTitle.setExpanded(true);

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
        switchScene.changToLogin(event);
    }
    @FXML
    void btnHoSo(ActionEvent event) throws IOException{


    }
    @FXML
    void btnThongBao(ActionEvent event) throws IOException{
        System.out.println("Hiển thị thông báo!");
    }
    @FXML
    void btnCSVC (ActionEvent event) throws IOException{
        switchScene.changeToThietBi(event);
    }
    @FXML
    void btnChoThue (ActionEvent event) throws IOException{
        switchScene.changeToChoThue(event);
    }
    @FXML
    void btnSuDung (ActionEvent event) throws IOException{
        switchScene.changeToSuDung(event);

    }
}
