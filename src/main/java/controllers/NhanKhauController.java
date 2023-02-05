package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import services.NhanKhauService;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
// Quản lý màn hình Nhân Khẩu
public class NhanKhauController implements Initializable {
    @FXML
    private TitledPane NVHTitle;
    SwitchScene switchScene;
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    private NhanKhauService nhanKhauService;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
////        sceneSwitchNhanKhau = new SceneSwitchNhanKhau();
//        nhanKhauService = new NhanKhauService();
////        sceneSwitch = new SceneSwitch();
////        setDataTable();
////        nhanKhauButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");
//    }





}
