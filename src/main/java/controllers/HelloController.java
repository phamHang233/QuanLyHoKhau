package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.UserModel;
import services.SQLServerConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//điều khiển màn hình đăng nhập
public class HelloController {
@FXML
    private Button btnLogin;

    @FXML
    private TextField loginNameText;
    @FXML

    private PasswordField passwordText;


    // kết tập class UserModel
    public static UserModel currentUser = new UserModel();
    public HelloController(){}

    @FXML
    // click vào nút Login
    public void btnLogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
          String loginName = loginNameText.getText();
          String password = passwordText.getText();
        if (checkUserCredential(loginName, password)){
            Parent root =  FXMLLoader.load(getClass().getResource("/views/main-view.fxml")) ;
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1200,640));
            stage.centerOnScreen();
        }
        else {
            //  thông báo lỗi
            popUpWrongCreadentialAlert();
        }
    }

    //hiển thị màn hình thông báo lỗi
    public void popUpWrongCreadentialAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lỗi đăng nhập");
        alert.setHeaderText("Sai mật khẩu hoặc tài khoản");
        alert.setContentText("Vui lòng nhập lại");
        alert.show();
    }
    public boolean checkUserCredential(String userName, String password) throws SQLException, ClassNotFoundException {
        Connection connection = SQLServerConnection.getSqlConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE userName = '" + userName + "'");
        if (rs == null) {
            return false;
        }

        //kiểm tra mật khẩu
        while (rs.next()) {
            if (rs.getString("password") == null){
                if(password== null )
                {
                    HelloController.currentUser.setID(rs.getInt("ID"));
                    HelloController.currentUser.setUserName(rs.getString("userName"));
                    return true;
                }
            }
            else {
                if(rs.getString("password").equals(password)){
                    HelloController.currentUser.setID(rs.getInt("ID"));
                    HelloController.currentUser.setUserName(rs.getString("userName"));
                    return true;
                }

            }
        }
        connection.close();
        return false;
    }

}
