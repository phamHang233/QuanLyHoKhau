package controllers.nhankhauControllers;

import models.TamTruModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ResourceBundle;


        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.control.*;
        import javafx.scene.image.ImageView;
        import javafx.stage.Stage;
        import models.TamTruModel;
        import services.SQLServerConnection;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.Date;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.time.ZoneId;
        import java.util.ResourceBundle;

public class DangKyTamTruController implements Initializable {

    @FXML
    ImageView checkedIcon;
    @FXML
    private TextField soCMTText;
    @FXML
    private TextField maGiayTamTruText;
    @FXML
    private TextField soDienThoaiText;
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
    TamTruModel tamTruModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tamTruModel = new TamTruModel();
        setUpForCheck();
    }

    public void setUpForCheck(){
        checkedIcon.setVisible(false);
        maGiayTamTruText.setDisable(true);
        soDienThoaiText.setDisable(true);
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
            maGiayTamTruText.setDisable(false);
            soDienThoaiText.setDisable(false);
            tuNgayDP.setDisable(false);
            denNgayDP.setDisable(false);
            lyDoText.setDisable(false);
            xacNhanButton.setDisable(false);
            huyButton.setDisable(false);

            tamTruModel.setIdNhanKhau(tempID);
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
            tamTruModel.setMaGiayTamTru(maGiayTamTruText.getText().trim());
            tamTruModel.setSoDienThoaiNguoiDangKy(soDienThoaiText.getText().trim());
            tamTruModel.setTuNgay(Date.from(tuNgayDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            tamTruModel.setDenNgay(Date.from(denNgayDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            tamTruModel.setLyDo(lyDoText.getText().trim());
            if(addNew(tamTruModel)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Thêm thành công");
                alert.show();
                soCMTText.setText("");
                maGiayTamTruText.setText("");
                soDienThoaiText.setText("");
                tuNgayDP.setValue(null);
                denNgayDP.setValue(null);
                lyDoText.setText("");
                setUpForCheck();
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

    public boolean addNew(TamTruModel tamTruModel) {
        try {
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "INSERT INTO tam_tru(idNhanKhau, maGiayTamTru, soDienThoaiNguoiDangKy, tuNgay, denNgay, lyDo)" + " value (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tamTruModel.getIdNhanKhau());
            preparedStatement.setString(2, tamTruModel.getMaGiayTamTru());
            preparedStatement.setString(3, tamTruModel.getSoDienThoaiNguoiDangKy());
            Date tuNgay = new Date(tamTruModel.getTuNgay().getTime());
            preparedStatement.setDate(4, tuNgay);
            Date denNgay = new Date(tamTruModel.getDenNgay().getTime());
            preparedStatement.setDate(5, denNgay);
            preparedStatement.setString(6, tamTruModel.getLyDo());
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
        return !(maGiayTamTruText.getText().trim().isEmpty()
                || soDienThoaiText.getText().trim().isEmpty()
                || lyDoText.getText().trim().isEmpty());
    }

}
