package controllers.ThongKe;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Main;

import java.io.IOException;

public class SwitchSceneTK {

    public void changeToXemThemNhanKhau(ActionEvent event, String soCanCuocCongDan) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        if(((Button)event.getSource()).getText().equals("Xem thêm thông tin gia đình")){
            loader.setLocation(getClass().getResource("/views/thongKe/giaDinhNK.fxml"));
            popUpStage.setTitle("Xem thêm gia đình nhân khẩu");

        }
        else{
            loader.setLocation(getClass().getResource("/views/thongKe/tieuSuNK.fxml"));
            popUpStage.setTitle("Xem thêm tiểu sử nhân khẩu");

        }
        Scene scene = new Scene(loader.load());
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        if(((Button)event.getSource()).getText().equals("Xem thêm thông tin gia đình")){
            GiaDinhNK giaDinhNK = loader.getController();
            giaDinhNK.setDataThongTinNhanKhau(soCanCuocCongDan);
        }
        else{
            TieuSuNK tieuSuNK = loader.getController();
            tieuSuNK.setDataTieuSu(soCanCuocCongDan);
        }
        popUpStage.showAndWait();
    }
    public void changeToXemThemHoKhau(ActionEvent event, String maHoKhau) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader ;
//        if(((Button)event.getSource()).getText().equals("Xem thêm gia đình hộ khẩu")){
//            loader = new FXMLLoader(getClass().getResource("/views/thongKe/giaDinhHK.fxml"));
//            popUpStage.setTitle("Xem thêm gia đình hộ khẩu");
//        }
//        else{
        loader = new FXMLLoader(getClass().getResource("/views/thongKe/lichSuHoKhau.fxml"));
        popUpStage.setTitle("Xem thêm lịch sử hộ khẩu");
//        }
        Scene scene = new Scene(loader.load());
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
//        if(((Button)event.getSource()).getText().equals("Xem thêm gia đình hộ khẩu")){
//            GiaDinhHoKhau giaDinhHoKhau = loader.getController()
//            giaDinhHoKhau.setDataGiaDinhNhanKhau(maHoKhau);
//        }
//        else{
        LichSuHoKhau lichSuHoKhau = loader.getController();
        lichSuHoKhau.setDataLichSu(maHoKhau);
//        }
        popUpStage.showAndWait();
    }
}
