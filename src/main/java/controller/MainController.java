package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
// quản lý màn hình trang chủ
public class MainController implements Initializable {
    SwitchScene switchScene;
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


}
