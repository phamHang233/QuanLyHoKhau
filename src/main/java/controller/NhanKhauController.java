package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
// Quản lý màn hình Nhân Khẩu
public class NhanKhauController implements Initializable {
    @FXML
    private Button select1;

    @FXML
    private Button select2;

    @FXML
    private Button select3;

    @FXML
    private Button select4;

    @FXML
    private Button select5;
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
        switchScene.changeToNVH(event);
    }

    @FXML
    void btnTrangChu(ActionEvent event) throws IOException {
        switchScene.changeToMain(event);
    }

    @FXML
    void btnThongKe(ActionEvent event) throws IOException {
        switchScene.changeToThongKe(event);
    }
}
