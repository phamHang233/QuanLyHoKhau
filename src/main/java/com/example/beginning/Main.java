package com.example.beginning;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("login-view.fxml")) ;
        Scene scene1 = new Scene(root, 640, 480);
        stage.setTitle("Quản Lý Nhân Khẩu !");

        stage.setScene(scene1);
        stage.show();
//         root =  FXMLLoader.load(getClass().getResource("main-view.fxml")) ;
////        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root, 640, 480));
    }

    public static void main(String[] args) {
        launch();
    }
}