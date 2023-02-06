package controllers.nhankhauControllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.TamVangModel;
import services.SQLServerConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class DangKyTamVangController implements Initializable {

    @FXML
    ImageView checkedIcon;
    @FXML
    private TextField soCMTText;
    @FXML
    private TextField maGiayTamVangText;
    @FXML
    private TextField noiTamTruText;
    @FXML
    private DatePicker tuNgayDP;
    @FXML
    private DatePicker denNgayDP;
    @FXML
    private TextArea lyDoText;
    @FXML
    private Button xacNhanButton;
    @FXML
    private Button huyButton;


    //    @FXML
//    TextField
    TamVangModel tamVangModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tamVangModel = new TamVangModel();
        checkedIcon.setVisible(false);
        maGiayTamVangText.setDisable(true);
        noiTamTruText.setDisable(true);
        tuNgayDP.setDisable(true);
        denNgayDP.setDisable(true);
        lyDoText.setDisable(true);
        xacNhanButton.setDisable(true);
        huyButton.setDisable(true);
        lyDoText.setWrapText(true);
    }

    public void check(ActionEvent event){
        String tempCMT = soCMTText.getText().trim();
        if (tempCMT.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập số CMT");
            alert.show();
            return;
        } else {
            try {
                Long.parseLong(tempCMT);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("Vui lòng nhập số CMT đúng định dạng");
                alert.show();
                return;
            }
        }
        int tempID = checkCMT(soCMTText.getText());
        if (tempID != -1){
            soCMTText.setEditable(false);
            checkedIcon.setVisible(true);
            maGiayTamVangText.setDisable(false);
            noiTamTruText.setDisable(false);
            tuNgayDP.setDisable(false);
            denNgayDP.setDisable(false);
            lyDoText.setDisable(false);
            xacNhanButton.setDisable(false);
            huyButton.setDisable(false);

            tamVangModel.setIdNhanKhau(tempID);
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Không tìm thấy số CMT trong hệ thống");
            alert.show();
        }
    }

    public void xacNhan(){
        if(!validateForm()){
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setTitle("Warning!");
            errorMessage.setContentText("Vui lòng nhập hết các trường bắt buộc!");
            errorMessage.show();
        } else {
            tamVangModel.setMaGiayTamVang(maGiayTamVangText.getText().trim());
            tamVangModel.setNoiTamTru(noiTamTruText.getText().trim());
            tamVangModel.setTuNgay(Date.from(tuNgayDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            tamVangModel.setDenNgay(Date.from(denNgayDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            tamVangModel.setLyDo(lyDoText.getText().trim());
            if(addNew(tamVangModel)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Thêm thành công");
                alert.show();
            }
        }
    }

    public void huy(ActionEvent event){
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
                return rs.getInt("ID");
            }
        } catch (Exception e) {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setTitle("Warning!");
            errorMessage.setContentText("Có lỗi xảy ra! vui lòng kiểm tra lại.");
            errorMessage.show();
        }
        return -1;
    }

    public boolean addNew(TamVangModel tamVangModel) {
        try {
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "INSERT INTO tam_vang(idNhanKhau, maGiayTamVang, noiTamTru, tuNgay, denNgay, lyDo)" + " value (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tamVangModel.getIdNhanKhau());
            preparedStatement.setString(2, tamVangModel.getMaGiayTamVang());
            preparedStatement.setString(3, tamVangModel.getNoiTamTru());
            java.sql.Date tuNgay = new java.sql.Date(tamVangModel.getTuNgay().getTime());
            preparedStatement.setDate(4, tuNgay);
            java.sql.Date denNgay = new java.sql.Date(tamVangModel.getDenNgay().getTime());
            preparedStatement.setDate(5, denNgay);
            preparedStatement.setString(6, tamVangModel.getLyDo());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setTitle("Warning!");
            errorMessage.setContentText("Có lỗi xảy ra! vui lòng kiểm tra lại.");
            errorMessage.show();
            return false;
        }
    }

    private boolean validateForm() {
        return !(maGiayTamVangText.getText().trim().isEmpty()
                || noiTamTruText.getText().trim().isEmpty()
                || lyDoText.getText().trim().isEmpty());
    }

}

