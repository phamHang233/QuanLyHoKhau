package controllers.nhankhauControllers;

import models.NhanKhauModel;

import java.io.IOException;

        import controllers.NhanKhauController;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Modality;
        import javafx.stage.Stage;
        import models.NhanKhauModel;

        import java.io.IOException;

public class SceneSwitchNhanKhau {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;


    public void changeSceneTamVang(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/NhanKhau/TamVang.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Đăng ký tạm vắng");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }

    public void changeSceneTamTru(ActionEvent event) throws IOException{
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/NhanKhau/TamTru.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Đăng ký tạm trú");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }


    public void changeSceneChiTiet(MouseEvent event, NhanKhauModel nhanKhauModel) throws IOException{
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/NhanKhau/ChiTiet.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        ChiTietController chiTietController = loader.getController();
        chiTietController.setData(nhanKhauModel);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Chi tiết nhân khẩu");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }
}