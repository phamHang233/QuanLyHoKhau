package controllers.NVH;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ThietBiNVHModel;

import java.io.IOException;

public class SceneSwitchNVH {
    public void changeToThemChoThue(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/nhaVanHoa/them-thue.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
//        popUpStage.setTitle("Thêm lịch sử cho thuê nhà văn hoá");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();

    }
    public void changeToThemSuDung(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/nhaVanHoa/them-sd.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
//        popUpStage.setTitle("Thêm mới sử dụng nhà văn hoá");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();

    }
    public void changeToThemThietBi(ActionEvent event) throws  IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/nhaVanHoa/them-thietbi.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
//        popUpStage.setTitle("Thêm thiết bị nhà văn hoá");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
    }
    public void changeToSuaThietBi(MouseEvent event, ThietBiNVHModel thietBiNVHModel) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/nhaVanHoa/sua-thietbi.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Sua_ThietBiController suaThietBiController = loader.getController();
        suaThietBiController.setData(thietBiNVHModel);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
    }
}
