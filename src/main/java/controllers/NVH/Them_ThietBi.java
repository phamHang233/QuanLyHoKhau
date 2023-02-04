package controllers.NVH;

import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.SuDungNVHModel;
import models.ThietBiNVHModel;
import services.NhaVanHoaService;
import services.SQLServerConnection;

import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Them_ThietBi implements Initializable {

    @FXML
    private TextField ghiChu;

    @FXML
    private TextField soLuong;

    @FXML
    private TextField tenThietBi;

    @FXML
            private ChoiceBox trangThaiBox;
    ThietBiNVHModel thietBiNVHModel;
    List<ThietBiNVHModel> thietBiNVHModelList;
    NhaVanHoaService nvhService;

    @FXML
    void btnHuy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    void btnThem(ActionEvent event) {
        if (validateValueInForm()) {

            thietBiNVHModel.setTenThietBi(tenThietBi.getText());
            thietBiNVHModel.setNgayCapNhat(Date.from(java.time.LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            thietBiNVHModel.setSoLuong(Integer.parseInt(soLuong.getText()));
            thietBiNVHModel.setTrangThai(trangThaiBox.getValue().toString());
            thietBiNVHModel.setGhiChu(ghiChu.getText());

            try {
                if (themMoi(thietBiNVHModel)) {
                    Alert addSuccessfullyAlert = new Alert(Alert.AlertType.INFORMATION);
                    addSuccessfullyAlert.setContentText("Thêm thành công!");
                    addSuccessfullyAlert.show();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/nhaVanHoa/thietbi.fxml"));
                    Parent parent = loader.load();
//                    ThietBiNVHController tbNVHController = loader.getController();
//                    tbNVHController.refreshData();
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
    //thêm dữ liệu mới nhập vào database
    public boolean themMoi(ThietBiNVHModel thietBiNVHModel) throws SQLException, ClassNotFoundException{

        String tenThietBi = thietBiNVHModel.getTenThietBi();
        Integer soLuong = thietBiNVHModel.getSoLuong();
        java.sql.Date ngayCapNhat = new java.sql.Date(thietBiNVHModel.getNgayCapNhat().getTime());
        String trangThai = thietBiNVHModel.getTrangThai();
        String ghiChu = thietBiNVHModel.getGhiChu();
        int i ;
        for ( i=0; i< thietBiNVHModelList.size(); i++) {
            // nếu thiết bị đã có trong danh sách và trạng thái thiết bị như nhau
            if (tenThietBi.equals(thietBiNVHModelList.get(i).getTenThietBi().toString())
                    && trangThai.equals(thietBiNVHModelList.get(i).getTrangThai().toString()) ) {
                // thêm số lượng của thiết bị đó

                Connection connection = SQLServerConnection.getSqlConnection();

                String query = "UPDATE CSVC " +
                        "SET soLuong = soLuong + " + soLuong +
                        ", ngayCapNhat = '"+ ngayCapNhat +"'"+
                        "WHERE thietBi= N'" + tenThietBi + "'"
                        + "AND tinhTrang= N'" +trangThai +"'"  ;
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int updateCount = preparedStatement.executeUpdate();
                preparedStatement.close();
                return true;
            }
        }
        if (i== thietBiNVHModelList.size()) {
            // nếu thiết bị chưa có trong danh sách
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "INSERT INTO CSVC VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, thietBiNVHModelList.size()+1);
            preparedStatement.setString(2, thietBiNVHModel.getTenThietBi());
            preparedStatement.setInt(3, thietBiNVHModel.getSoLuong());
            preparedStatement.setString(4, thietBiNVHModel.getTrangThai());

            preparedStatement.setDate(5, ngayCapNhat);
            preparedStatement.setString(6, thietBiNVHModel.getGhiChu());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            connection.close();

            return true;
        }
        return false;
    }


    //kểm tra xem có nhập thiếu trường nào không?
    private boolean validateValueInForm(){
        if (  tenThietBi.getText().trim().isEmpty()
                || soLuong.getText().trim().isEmpty()
                || trangThaiBox.getValue().toString().trim().isEmpty()) {
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Warning!");
            missingFieldAlert.setContentText("Vui lòng nhập hết các trường bắt buộc");
            missingFieldAlert.show();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trangThaiBox.getItems().addAll( "Tốt", "Hỏng");
        thietBiNVHModel = new ThietBiNVHModel();
        nvhService = new NhaVanHoaService();
        thietBiNVHModelList = nvhService.getAllListThietBi();

    }
}
