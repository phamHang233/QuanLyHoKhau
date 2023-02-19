package controllers.nhankhauControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.KhaiTuModel;
import services.HoKhauService;
import services.SQLServerConnection;
import services.NhanKhauService;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class KhaiTuController implements Initializable {

    @FXML
    ImageView checkedIcon;
    @FXML
    TextField soCMTnguoiChet;
    @FXML
    TextField tenNguoiKhai;
    @FXML
    TextField soCMTnguoiKhai;
    @FXML
    TextField soGiayKhaiTu;
    @FXML
    DatePicker ngayKhai;
    @FXML
    DatePicker ngayMat;
    @FXML
    TextArea lyDoChet;
    @FXML
    Button checkButton;
    @FXML
    Button xacNhanButton;
    @FXML
    Button huyButton;


    boolean isValid, isValid1;
    int idNguoiChet;
    NhanKhauService nhanKhauService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nhanKhauService = new NhanKhauService();
        checkedIcon.setVisible(false);
        soGiayKhaiTu.setDisable(true);
        ngayKhai.setDisable(true);
        ngayMat.setDisable(true);
        lyDoChet.setDisable(true);
    }

    public void check(ActionEvent event) {
        String tempCMTNguoiChet = soCMTnguoiChet.getText().trim();
        String tempCMTNguoiKhai = soCMTnguoiKhai.getText().trim();
        if (tempCMTNguoiChet.isEmpty()|| tempCMTNguoiKhai.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập số CMT");
            alert.show();
            return;
        } else {
            try {
                Long.parseLong(tempCMTNguoiChet);
                Long.parseLong(tempCMTNguoiKhai);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("Vui lòng nhập số CMT đúng định dạng");
                alert.show();
                return;
            }
        }
        int tempIDNguoiChet = checkCMT(tempCMTNguoiChet);
        int tempIDNguoiKhai = checkCMT(tempCMTNguoiKhai);
        if (tempIDNguoiChet != -1 && tempIDNguoiKhai!= -1 && !tenNguoiKhai.getText().isBlank() ) {

            soCMTnguoiKhai.setEditable(false);
            checkedIcon.setVisible(true);
            soGiayKhaiTu.setDisable(false);
            ngayKhai.setDisable(false);
            ngayMat.setDisable(false);
            lyDoChet.setDisable(false);
            xacNhanButton.setDisable(false);
            huyButton.setDisable(false);
            idNguoiChet = checkCMT(soCMTnguoiChet.getText());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Chưa nhập đủ thông tin người khai hoặc không tìm thấy số CMT trong hệ thống");
            alert.show();
        }
    }

    public void xacNhan(ActionEvent event) {

        KhaiTuModel khaiTuModel = new KhaiTuModel();
        khaiTuModel.setSoCMTnguoiKhai(soCMTnguoiKhai.getText());
        khaiTuModel.setSoCMTnguoiMat(soCMTnguoiChet.getText());
        khaiTuModel.setTenNguoiKhai(tenNguoiKhai.getText());
        khaiTuModel.setNgayMat(ngayMat.getValue().toString());
        khaiTuModel.setNgayKhai(ngayKhai.getValue().toString());
        khaiTuModel.setLyDoChet(lyDoChet.getText());
        khaiTuModel.setSoGiayKhaiTu(soGiayKhaiTu.getText());

        nhanKhauService.themKhaiTu(khaiTuModel);
        nhanKhauService.khaiTu(idNguoiChet);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thêm thành công");
        alert.show();
        huy(event);
    }

    public void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }


    public int checkCMT(String cmt) {
        try {
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT * FROM nhan_khau LEFT JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE soCMT = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cmt);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("idNhanKhau");
            }
        } catch (Exception e) {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setTitle("Warning!");
            errorMessage.setContentText("Có lỗi xảy ra! vui lòng kiểm tra lại.");
            errorMessage.show();
        }
        return -1;
    }

}

