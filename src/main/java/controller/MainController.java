package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// quản lý màn hình trang chủ
public class MainController implements Initializable {
    SwitchScene switchScene;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchScene = new SwitchScene();
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


}
