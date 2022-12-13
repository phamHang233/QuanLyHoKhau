package com.example.beginning.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

//    @FXML
    private Button select1;

//    @FXML
    private Button select2;

//    @FXML
    private Button select3;

//    @FXML
    private Button select4;

//    @FXML
    private Button select5;

//    @FXML
    void btnHoKhau(ActionEvent event) {

    }

//    @FXML
    void btnNhaVH(ActionEvent event) {

    }

//    @FXML
    void btnNhanKhau(ActionEvent event) {

    }

//    @FXML
    void btnThongKe(ActionEvent event) {

    }

//    @FXML
    void btnTrangChu(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("login-view.fxml")) ;
        Stage stage= (Stage) select1.getScene().getWindow();
        stage.setScene(new Scene(root, 640, 480));
    }

}
