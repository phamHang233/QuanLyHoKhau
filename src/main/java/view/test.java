package view;

import controllers.NhanKhauController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;


public class test extends Application {

    public static Calendar calendar;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/nhankhau-view.fxml"));
        Scene scene1 = new Scene(root, 600, 322);
        stage.setTitle("Quản Lý Nhân Khẩu !");
        stage.setScene(scene1);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args)  {
        launch();

    }
}
