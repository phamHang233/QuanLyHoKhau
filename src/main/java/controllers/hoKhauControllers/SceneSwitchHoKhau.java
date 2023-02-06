package controllers.hoKhauControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitchHoKhau {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;


    public void changeSceneTachHoKhau(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/TachHoKhau.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Tách hộ khẩu");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }

    public void changeSceneChuyenDi(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/ChuyenDi.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Chuyển đi");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }

    public void changeSceneChiTiet(MouseEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/ChiTiet.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Chi tiết hộ khẩu");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }
}
