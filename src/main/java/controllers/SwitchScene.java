package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// Class với các method để chuyển màn hình từ trang chủ -> hộ khẩu -> nhân khẩu->nhà văn hoá
public class SwitchScene {
    public SwitchScene(){}
    void changeToMain(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/views/main-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1200,640));
        stage.centerOnScreen();
    }

    void changeToNhanKhau(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/views/nhankhau-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1200,640));
        stage.centerOnScreen();
    }
    void changeToHoKhau(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/views/hokhau-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1200,640));
        stage.centerOnScreen();
    }
    void changeToThongKe(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/views/thongke-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1200,640));
        stage.centerOnScreen();
        stage.show();
    }
    void changeToNVH(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/views/nhavh-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1200,640));
        stage.centerOnScreen();
    }
}


