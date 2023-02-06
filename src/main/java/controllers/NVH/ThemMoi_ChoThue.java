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
import models.ChoThueNVHModel;

import services.NhaVanHoaService;
import services.SQLServerConnection;

import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ThemMoi_ChoThue implements Initializable {
    @FXML
    private TextField ghiChu;

    @FXML
    private TextField mucDich;

    @FXML
    private DatePicker ngayThue;

    @FXML
    private TextField nguoiThue;

    @FXML
    private TextField phi;

    ChoThueNVHModel choThueNVHModel;
    List<ChoThueNVHModel> choThueNVHModelList;
    NhaVanHoaService nvhService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choThueNVHModel = new ChoThueNVHModel();
        nvhService = new NhaVanHoaService();
        choThueNVHModelList = nvhService.getListChoThue();

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

            choThueNVHModel.setMucDich(mucDich.getText());
            choThueNVHModel.setNgaySuDung(Date.from(ngayThue.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            choThueNVHModel.setIDNguoiLap(LoginController.currentUser.getIDNhanKhau());
            choThueNVHModel.setNguoiThue(nguoiThue.getText());
            choThueNVHModel.setPhi(Integer.parseInt(phi.getText()));
            choThueNVHModel.setGhiChu(ghiChu.getText());

            try {
                if (themMoi(choThueNVHModel)) {
                    Alert addSuccessfullyAlert = new Alert(Alert.AlertType.INFORMATION);
                    addSuccessfullyAlert.setContentText("Thêm thành công!");
                    addSuccessfullyAlert.show();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/nhaVanHoa/chothue.fxml"));
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
    public boolean themMoi(ChoThueNVHModel chothueNVHModel) throws SQLException, ClassNotFoundException{
        Connection connection = SQLServerConnection.getSqlConnection();
        String query = "INSERT INTO cho_thue_NVH VALUES (?,?,?,?,?,?,?)"   ;

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, choThueNVHModelList.size()+1);
        java.sql.Date ngayChoThue = new java.sql.Date(chothueNVHModel.getNgaySuDung().getTime());
        preparedStatement.setDate(2,ngayChoThue);
        preparedStatement.setString(3,chothueNVHModel.getMucDich());
        preparedStatement.setInt(4,chothueNVHModel.getIDNguoiLap());
        preparedStatement.setInt(5, chothueNVHModel.getPhi());
        preparedStatement.setString(6, chothueNVHModel.getNguoiThue());
        preparedStatement.setString(7,chothueNVHModel.getGhiChu());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        connection.close();
        return true;
    }

    //kểm tra xem có nhập thiếu trường nào không?
    private boolean validateValueInForm(){
        if (    mucDich.getText().trim().isEmpty()
                || ngayThue.getValue().toString().isEmpty()
                || nguoiThue.getText().trim().isEmpty()
                || phi.getText().trim().isEmpty()) {
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Warning!");
            missingFieldAlert.setContentText("Vui lòng nhập hết các trường bắt buộc");
            missingFieldAlert.show();
            return false;

        }
        return true;
    }



}


