package controllers;


import Beans.NhanKhauBean;
import controllers.nhankhauControllers.SceneSwitchNhanKhau;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.NhanKhauService;
import models.NhanKhauModel;
import services.NhanKhauService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
// Quản lý màn hình Nhân Khẩu

public class NhanKhauController implements Initializable {
    @FXML
    TableView<NhanKhauModel> table;
    @FXML
    TableColumn ID;
    @FXML
    TableColumn hoTen;
    @FXML
    TableColumn CCCD;
    @FXML
    TableColumn namSinh;
    @FXML
    TableColumn gioiTinh;
    @FXML
    TableColumn diaChiHienNay;
    @FXML
    Button nhanKhauButton;
    @FXML
    private TitledPane NVHTitle;
    SwitchScene switchScene;
    private NhanKhauService nhanKhauService;
    private List<NhanKhauBean> listNhanKhauBeans;
    //ME added
    private ObservableList<NhanKhauModel> observablelistNhanKhau;
    private SceneSwitchNhanKhau sceneSwitchNhanKhau;
    private List<NhanKhauModel> listItem;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneSwitchNhanKhau = new SceneSwitchNhanKhau();
        nhanKhauService = new NhanKhauService();
        switchScene = new SwitchScene();
        setDataTable();
        nhanKhauButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");
    }

    public void setDataTable(){
        listNhanKhauBeans = nhanKhauService.getListNhanKhau();
        listItem = new ArrayList<>();
        listNhanKhauBeans.forEach(nhanKhau -> {
            listItem.add(nhanKhau.getNhanKhauModel());
        });
        observablelistNhanKhau = FXCollections.observableList(listItem);
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        CCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        namSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiHienNay.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        table.setItems(observablelistNhanKhau);

    }

    public void chonXemNhanKhau(MouseEvent event) throws IOException {
        NhanKhauModel nhanKhauModel = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (nhanKhauModel != null)){
            sceneSwitchNhanKhau.changeSceneChiTiet(event, nhanKhauModel);
        }
    }

    public void themMoi(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanKhau/ThemMoi.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Thêm mới nhân khẩu");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataTable();
    }

    public void dangKyTamVang(ActionEvent event) throws IOException {
        sceneSwitchNhanKhau.changeSceneTamVang(event);
    }

    public void dangKyTamTru(ActionEvent event) throws IOException{
        sceneSwitchNhanKhau.changeSceneTamTru(event);
    }

    public void khaiTu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanKhau/KhaiTu.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Khai tử");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataTable();
    }

    public void refreshData(NhanKhauBean nhanKhauBean) {
        listNhanKhauBeans = nhanKhauService.getListNhanKhau();
        updateAddedNhanKhau(nhanKhauBean);
    }

    public void updateAddedNhanKhau(NhanKhauBean nhanKhauBean){
        NhanKhauModel nhanKhauAdded = nhanKhauBean.getNhanKhauModel();
        observablelistNhanKhau.add(nhanKhauAdded);
    }

    @FXML
    void btnHoKhau(ActionEvent event) throws IOException {
        switchScene.changeToHoKhau(event);
    }


    @FXML
    void btnNhaVH(ActionEvent event) throws IOException {
        NVHTitle.setExpanded(true);

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
    void btnHoSo(ActionEvent event) throws IOException{


    }
    @FXML
    void btnThongBao(ActionEvent event) throws IOException{
        System.out.println("Hiển thị thông báo!");
    }



//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
////        sceneSwitchNhanKhau = new SceneSwitchNhanKhau();
//        nhanKhauService = new NhanKhauService();
////        sceneSwitch = new SceneSwitch();
////        setDataTable();
////        nhanKhauButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");
//    }





}
