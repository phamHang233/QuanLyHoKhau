package controllers.ThongKe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static services.SQLServerConnection.getSqlConnection;

public class TieuSuNK implements Initializable {
    @FXML
    private Label denNgayTieuSu;

    @FXML
    private Label diaChiTieuSu;

    @FXML
    private Label ngheNghiepTieuSu;

    @FXML
    private Label noiLamViecTieuSu;

    @FXML
    private Label tuNgayTieuSu;
    public void setDataTieuSu(String soCanCuocCongDan){
        try {
            String query = "SELECT * \n" +
                    "FROM tieu_su\n" +
                    "WHERE tieu_su.idNhanKhau = (SELECT nhan_khau.ID \n" +
                    "FROM nhan_khau INNER JOIN chung_minh_thu \n" +
                    "ON nhan_khau.id = chung_minh_thu.idNhanKhau \n" +
                    "WHERE soCMT = '" +soCanCuocCongDan+"');";
//            System.out.println(query);
            PreparedStatement preparedStatement = getSqlConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                tuNgayTieuSu.setText(rs.getString("tuNgay"));
                System.out.println(rs.getString("tuNgay"));
                denNgayTieuSu.setText(rs.getString("denNgay"));
                diaChiTieuSu.setText(rs.getString("diaChi"));
                ngheNghiepTieuSu.setText(rs.getString("ngheNghiep"));
                noiLamViecTieuSu.setText(rs.getString("noiLamViec"));
            }
            preparedStatement.close();
//            list.add(nhanKhauModel);
//            thongtinnhankhau.setItems(list);

        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());

        }
    }

    private void exceptionHandle(String message) {
    }

    @FXML
    void btnOK(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
