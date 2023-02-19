package controllers.NVH;


import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.SuDungNVHModel;
import services.SQLServerConnection;

import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ThemMoi_SD implements Initializable {
    @FXML
    private TextField ghiChu;

    @FXML
    private TextField mucDich;

    @FXML
    private DatePicker ngaySD;

    @FXML
    private TextField stt;
    SuDungNVHModel sdNVHModel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sdNVHModel = new SuDungNVHModel();
    }

    @FXML
    void btnHuy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }




// khi ấn nút thêm
    @FXML
    void btnThem(ActionEvent event) {
        //tạo 1 sdNVHModel mới và set các trường vừa nhập vào sdNVHModel
        if (validateValueInForm()) {
            sdNVHModel.setStt(Integer.parseInt(stt.getText()));
            sdNVHModel.setMucDich(mucDich.getText());
            sdNVHModel.setNgaySuDung(Date.from(ngaySD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            sdNVHModel.setIDNguoiLap(LoginController.currentUser.getIDNhanKhau());
            sdNVHModel.setGhiChu(ghiChu.getText());

            try {
                if (themMoi(sdNVHModel)) {
                    Alert addSuccessfullyAlert = new Alert(Alert.AlertType.INFORMATION);
                    addSuccessfullyAlert.setContentText("Thêm thành công!");
                    addSuccessfullyAlert.show();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/nhaVanHoa/sudung.fxml"));
                    Parent parent = loader.load();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Warning");
                errorAlert.setContentText("Có lỗi xảy ra. Vui lòng kiểm tra lại!!");
                errorAlert.show();
            }
        }

    }



// truy vấn vào sql và thêm sử dụng NVH Model vừa được tạo vào table
    public boolean themMoi(SuDungNVHModel sdNVHModel) throws SQLException, ClassNotFoundException{
        Connection connection = SQLServerConnection.getSqlConnection();
        String query = "INSERT INTO suDungNVH(ID, ngaySuDung, mucDich, IDNguoiLap, ghiChu) VALUES (?,?,?,?,?)"   ;
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, sdNVHModel.getStt());
        java.sql.Date ngaySuDung = new java.sql.Date(sdNVHModel.getNgaySuDung().getTime());
        preparedStatement.setDate(2,ngaySuDung);
        preparedStatement.setString(3,sdNVHModel.getMucDich());
        preparedStatement.setInt(4,sdNVHModel.getIDNguoiLap());
        preparedStatement.setString(5,sdNVHModel.getGhiChu());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        connection.close();
        return true;
    }

    //kểm tra xem có nhập thiếu trường nào không?
    private boolean validateValueInForm(){
        if (stt.getText().trim().isEmpty()
            || mucDich.getText().trim().isEmpty()
            || ngaySD.getValue().toString().isEmpty()) {
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Warning!");
            missingFieldAlert.setContentText("Vui lòng nhập hết các trường bắt buộc");
            missingFieldAlert.show();
            return false;

        }
        if (ngaySD.getValue().isAfter(java.time.LocalDate.now())){
            System.out.println("lỗi");
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Warning!");
            missingFieldAlert.setContentText("Ngày không chính xác!");
            missingFieldAlert.show();
            return false;
        }
        return true;
    }



}


