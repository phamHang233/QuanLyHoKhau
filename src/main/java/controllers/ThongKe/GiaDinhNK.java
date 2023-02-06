package controllers.ThongKe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.GiaDinhModel;
import models.NhanKhauModel;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import static services.SQLServerConnection.getSqlConnection;

public class GiaDinhNK implements Initializable {
    @FXML
    private TableColumn<?, ?> diaChiNhanKhauXT;
    @FXML
    private TableColumn<GiaDinhModel, String> gioiTinhNhanKhauXT;

    @FXML
    private TableColumn<GiaDinhModel, String> hoTenNhanKhauXT;

    @FXML
    private TableColumn<GiaDinhModel, Integer> idNhanKhauXT;

    @FXML
    private TableColumn<GiaDinhModel, Date> ngaySinhNhanKhauXT;

    @FXML
    private TableColumn<GiaDinhModel, String> ngheNghiepNhanKhauXT;

    @FXML
    private TableColumn<GiaDinhModel, String> qhNhanKhauXT;

    @FXML
    private TableView<GiaDinhModel> thongTinGiaDinhXT;
    ObservableList<GiaDinhModel> list = FXCollections.observableArrayList();
    @FXML
    void btnOK(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    void setDataThongTinNhanKhau(String soCanCuocCongDan){
        try{
            String query = "SELECT * \n" +
                    "FROM gia_dinh\n" +
                    "WHERE gia_dinh.idNhanKhau = (SELECT nhan_khau.ID \n" +
                    "FROM nhan_khau INNER JOIN chung_minh_thu \n" +
                    "ON nhan_khau.id = chung_minh_thu.idNhanKhau \n" +
                    "WHERE soCMT = '" +soCanCuocCongDan+"'); ";
            System.out.println(query);
            PreparedStatement preparedStatement = getSqlConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                GiaDinhModel giaDinhModel = new GiaDinhModel();
                giaDinhModel.setHoTen(rs.getString("hoTen"));
                giaDinhModel.setGioiTinh(rs.getString("gioiTinh"));
                giaDinhModel.setNamSinh(rs.getDate("namSinh"));
                giaDinhModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                giaDinhModel.setQuanHeVoiNhanKhau(rs.getString("quanHeVoiNhanKhau"));
                giaDinhModel.setNgheNghiep(rs.getString("ngheNghiep"));
                giaDinhModel.setDiaChiHienTai(rs.getString("diaChiHienTai"));
                list.add(giaDinhModel);
            }
            preparedStatement.close();
            thongTinGiaDinhXT.setItems(list);


        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
    }

    private void exceptionHandle(String message) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idNhanKhauXT.setCellValueFactory(new PropertyValueFactory<>("idNhanKhau"));
        hoTenNhanKhauXT.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhNhanKhauXT.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        gioiTinhNhanKhauXT.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        qhNhanKhauXT.setCellValueFactory(new PropertyValueFactory<>("quanHeVoiNhanKhau"));
        ngheNghiepNhanKhauXT.setCellValueFactory(new PropertyValueFactory<>("ngheNghiep"));
        diaChiNhanKhauXT.setCellValueFactory(new PropertyValueFactory<>("diaChiHienTai"));
    }
}
