package controllers.NVH;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.ThietBiNVHModel;
import services.NhaVanHoaService;
import services.SQLServerConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Sua_ThietBiController implements Initializable {

        @FXML
        private TextField ghiChu;

        @FXML
        private TextField soLuong;

        @FXML
        private Label tenThietBi;

        @FXML
        private ChoiceBox trangThaiBox;
//
//        private Integer soLuongPre;
        private String trangThaiPre;
//        private String ghiChuPre;

    ThietBiNVHModel thietBiNVHModel;
    List<ThietBiNVHModel> thietBiNVHModelList;
    NhaVanHoaService nvhService;

        @FXML
        void btnHuy(ActionEvent event) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
        }
        @FXML
        void btnOK(ActionEvent event) {
            if (validateValueInForm()) {


                thietBiNVHModel.setNgayCapNhat(Date.from(java.time.LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                thietBiNVHModel.setSoLuong(Integer.parseInt(soLuong.getText()));
                thietBiNVHModel.setTrangThai(trangThaiBox.getValue().toString());
                thietBiNVHModel.setGhiChu(ghiChu.getText());
                try {
                    if (sua(thietBiNVHModel)){
                        Alert addSuccessfullyAlert = new Alert(Alert.AlertType.INFORMATION);
                        addSuccessfullyAlert.setContentText("Sửa thành công!");
                        addSuccessfullyAlert.show();

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.hide();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/nhaVanHoa/thietbi.fxml"));
                        Parent parent = loader.load();
//                    ThietBiNVHController tbNVHController = loader.getController();
//                    tbNVHController.refreshData();
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Warning");
                    errorAlert.setContentText("Có lỗi xảy ra. Vui lòng kiểm tra lại!!");
                    errorAlert.show();
                }

            }
        }

    public boolean sua(ThietBiNVHModel thietBiNVHModel) throws SQLException, ClassNotFoundException {

        java.sql.Date ngay = new java.sql.Date(thietBiNVHModel.getNgayCapNhat().getTime());
        Connection connection = SQLServerConnection.getSqlConnection();

        if (!trangThaiPre.equals(thietBiNVHModel.getTrangThai()) ){
            int i;
            for (  i=0; i< thietBiNVHModelList.size(); i++) {

                // nếu thiết bị đã có trong danh sách và trạng thái thiết bị như nhau
                if (tenThietBi.getText().toString().equals(thietBiNVHModelList.get(i).getTenThietBi().toString())
                        && thietBiNVHModel.getTrangThai().toString().equals(thietBiNVHModelList.get(i).getTrangThai().toString())) {

                    // thêm số lượng của thiết bị

                    String query = "UPDATE CSVC " +
                            "SET soLuong = soLuong + " + thietBiNVHModel.getSoLuong() +
                            ", ngayCapNhat = '" + ngay + "'" +
                            ",ghiChu= N'"+ thietBiNVHModel.getGhiChu()+"'"+
                            "WHERE thietBi= N'" + tenThietBi.getText().toString() + "'"
                            + "AND tinhTrang= N'" +  thietBiNVHModel.getTrangThai() + "'";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    int updateCount = preparedStatement.executeUpdate();

                    query = "UPDATE CSVC\n" +
                            "SET soLuong =  0   , ngayCapNhat =  '" +ngay+"'" +

                            "WHERE thietBi = N'" + tenThietBi.getText().toString()+ "' " +
                            "AND tinhTrang = N'" +  trangThaiPre +"'";
                     preparedStatement = connection.prepareStatement(query);
                     updateCount = preparedStatement.executeUpdate();
                    preparedStatement.close();
                    return true;
                }
            }


            if (i== thietBiNVHModelList.size()) {
                String query = "UPDATE CSVC " +
                        "SET soLuong = " + thietBiNVHModel.getSoLuong() +
                        ", tinhTrang = N'" + thietBiNVHModel.getTrangThai() + "' " +
                        ", ngayCapNhat = '" + ngay + "'" +
                        ",ghiChu= N'"+ thietBiNVHModel.getGhiChu()+"'"+
                        "WHERE ID=" + thietBiNVHModel.getSqlID();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int updateCount = preparedStatement.executeUpdate();
                preparedStatement.close();
                return true;

            }
        }
        else {
                String query = "UPDATE CSVC " +
                        "SET soLuong = " + thietBiNVHModel.getSoLuong() +
                        ", ngayCapNhat = '" + ngay + "'" +
                        ",ghiChu= N'"+ thietBiNVHModel.getGhiChu()+"'"+
                        "WHERE ID=" + thietBiNVHModel.getSqlID();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int updateCount = preparedStatement.executeUpdate();
                preparedStatement.close();
                return true;
        }
        return false;
    }



    public void setData(ThietBiNVHModel tbNVHModel) {
            thietBiNVHModel.setSqlID(tbNVHModel.getSqlID());
        thietBiNVHModel.setStt(tbNVHModel.getStt()); //stt
        thietBiNVHModel.setTenThietBi(tbNVHModel.getTenThietBi()); //ten
        thietBiNVHModel.setSoLuong(tbNVHModel.getSoLuong());
        thietBiNVHModel.setTrangThai(tbNVHModel.getTrangThai());
        thietBiNVHModel.setGhiChu(tbNVHModel.getGhiChu());
        trangThaiPre = tbNVHModel.getTrangThai();

        tenThietBi.setText(tbNVHModel.getTenThietBi());
        soLuong.setText(String.valueOf(thietBiNVHModel.getSoLuong()));
        trangThaiBox.setValue(thietBiNVHModel.getTrangThai());
        ghiChu.setText(thietBiNVHModel.getGhiChu());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        trangThaiBox.getItems().addAll( "Tốt", "Hỏng");
         thietBiNVHModel = new ThietBiNVHModel() ;
        nvhService = new NhaVanHoaService();
        thietBiNVHModelList = nvhService.getAllListThietBi();

    }
    private boolean validateValueInForm(){
        if ( Integer.parseInt(soLuong.getText()) == thietBiNVHModel.getSoLuong()
                && trangThaiBox.getValue().toString().equals(thietBiNVHModel.getTrangThai())
                && ghiChu.getText().toString().equals(thietBiNVHModel.getGhiChu())) {
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Warning!");
            missingFieldAlert.setContentText("Dữ liệu không thay đổi!");
            missingFieldAlert.show();
            return false;
        }
        return true;
    }
}