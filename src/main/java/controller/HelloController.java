package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
@FXML
    private Button btnLogin;

    @FXML
    private TextField loginName;
    @FXML

    private TextField password;
    public HelloController(){}
    @FXML
    public void btnLogin(ActionEvent event) throws IOException {

        Parent root =  FXMLLoader.load(getClass().getResource("/views/main-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1200,640));
        stage.centerOnScreen();

    }

}
