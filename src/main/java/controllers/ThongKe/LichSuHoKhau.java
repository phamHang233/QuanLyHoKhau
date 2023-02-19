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

public class LichSuHoKhau implements Initializable {
    @FXML
    private Label ngayThayDoi;

    @FXML
    private Label nguoiThayDoi;

    @FXML
    private Label thayDoiThanh;

    @FXML
    private Label thayDoiTu;

    @FXML
    private Label thongTinThayDoi;

    @FXML
    void btnOK(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setDataLichSu(String maHoKhau){
        try {
            String query = "SELECT dinh_chinh.thongTinThayDoi, dinh_chinh.thayDoiTu, dinh_chinh.thayDoiThanh, dinh_chinh.ngayThayDoi, dinh_chinh.nguoiThayDoi\n" +
                    "FROM dinh_chinh, ho_khau\n" +
                    "WHERE dinh_chinh.idHoKhau = ho_khau.ID\n" +
                    "AND ho_khau.maHoKhau = '" + maHoKhau+"'";
//            System.out.println(query);
            PreparedStatement preparedStatement = getSqlConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                thongTinThayDoi.setText(rs.getString("thongTinThayDoi"));
                thayDoiTu.setText(rs.getString("thayDoiTu"));
                thayDoiThanh.setText(rs.getString("thayDoiThanh"));
                ngayThayDoi.setText(rs.getString("ngayThayDoi"));
                nguoiThayDoi.setText(rs.getString("nguoiThayDoi"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
