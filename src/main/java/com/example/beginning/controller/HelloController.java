package com.example.beginning.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    private Button btnLogin;


    private TextField loginName;

    private TextField password;
    public HelloController(){}

    public void btnLogin(ActionEvent event) throws Exception{

        Parent root =  FXMLLoader.load(getClass().getResource("main-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 640, 480));
//        stage.centerOnScreen();

    }

}
