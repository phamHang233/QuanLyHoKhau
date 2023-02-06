package controllers.NVH;

import controllers.SwitchScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.ThietBiNVHModel;
import services.NhaVanHoaService;
import services.SQLServerConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ThietBiNVHController implements Initializable {
    @FXML
    TitledPane NVHTitle;
    @FXML
    TextField search;
    @FXML
    private TableColumn<ThietBiNVHModel, String> ghiChu;

    @FXML
    private TableColumn<ThietBiNVHModel, Date> ngayCapNhat;

    @FXML
    private TableColumn<ThietBiNVHModel, Integer> soLuong;

    @FXML
    private TableColumn<ThietBiNVHModel, Integer> stt;

    @FXML
    private TableColumn<ThietBiNVHModel, String> tenThietBi;

    @FXML
    private TableColumn<ThietBiNVHModel, String> trangThai;
    @FXML
    private TableView<ThietBiNVHModel> table;
    @FXML
    private Label tong;
    SwitchScene switchScene;
    SceneSwitchNVH sceneSwitchNVH;
    List<ThietBiNVHModel> thietBiNVHModelList;
    NhaVanHoaService nvhService;
    ObservableList<ThietBiNVHModel> observableThietBiList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switchScene = new SwitchScene();
        sceneSwitchNVH = new SceneSwitchNVH();
        nvhService = new NhaVanHoaService();
        thietBiNVHModelList = nvhService.getAllListThietBi();
        setDataTable();
        setTimKiem();

    }

    public void chonSuaThietBi(MouseEvent event) throws IOException {
        ThietBiNVHModel thietBiNVHModel = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (thietBiNVHModel != null)){
            sceneSwitchNVH.changeToSuaThietBi(event, thietBiNVHModel);
            refreshData();
            setDataTable();
            setTimKiem();
        }
    }
    public void setTimKiem(){
        // thanh tìm kiếm
        search.setPromptText("Tìm kiếm");
        FilteredList<ThietBiNVHModel> thietBi = new FilteredList(observableThietBiList, p -> true);//Pass the data to a filtered list
        table.setItems(thietBi);//Set the table's items using the filtered list

        search.textProperty().addListener((obs, oldValue, newValue) -> {
            thietBi.setPredicate((p -> p.getTenThietBi().toLowerCase().contains(newValue.toLowerCase().trim())));
        });
    }
    public void setDataTable( ) {
        observableThietBiList = FXCollections.observableList(thietBiNVHModelList);
        stt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        tenThietBi.setCellValueFactory(new PropertyValueFactory<>("tenThietBi"));
        ngayCapNhat.setCellValueFactory(new PropertyValueFactory<>("ngayCapNhat"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        trangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        table.setItems(observableThietBiList);
        try {
            // tính tổng bản ghi
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM CSVC";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tong.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnThem(ActionEvent event) throws IOException {
        sceneSwitchNVH.changeToThemThietBi(event);
        refreshData();
        setDataTable();
        setTimKiem();
    }

    @FXML
    public void btnSua(ActionEvent event) throws IOException {


    }
    public void refreshData() {
        thietBiNVHModelList = nvhService.getAllListThietBi();
    }



    @FXML
    void btnHoKhau(ActionEvent event) throws IOException {
        switchScene.changeToHoKhau(event);
    }


    @FXML
    void btnNhaVH(ActionEvent event) throws IOException {

        NVHTitle.setExpanded(false);
    }

    @FXML
    void btnNhanKhau(ActionEvent event) throws IOException {
        switchScene.changeToNhanKhau(event);
    }

    @FXML
    void btnThongKe(ActionEvent event) throws IOException {
        switchScene.changeToThongKe(event);
    }

    @FXML
    void btnTrangChu(ActionEvent event) throws IOException {
        switchScene.changeToMain(event);
    }

    @FXML
    void btnDangXuat(ActionEvent event) throws IOException {
        switchScene.changToLogin(event);
    }

    @FXML
    void btnHoSo(ActionEvent event) throws IOException {    }

    @FXML
    void btnThongBao(ActionEvent event) throws IOException {
        System.out.println("Hiển thị thông báo!");
    }

    @FXML
    void btnCSVC (ActionEvent event) throws IOException{
        switchScene.changeToThietBi(event);
    }
    @FXML
    void btnChoThue (ActionEvent event) throws IOException{
        switchScene.changeToChoThue(event);
    }
    @FXML
    void btnSuDung (ActionEvent event) throws IOException{
        switchScene.changeToSuDung(event);

    }

}
