package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/login-view.fxml"));
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