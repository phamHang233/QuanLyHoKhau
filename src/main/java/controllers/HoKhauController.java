package controllers;
import Beans.HoKhauBean;
import controllers.hoKhauControllers.HoKhauHolder;
import controllers.hoKhauControllers.SceneSwitchHoKhau;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.HoKhauService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
// quản lý màn hình Hộ khẩu
public class HoKhauController implements Initializable {

    @FXML
    TableView<HoKhauBean> table;
    @FXML
    TableColumn<HoKhauBean, String> maHoKhau;
    @FXML
    TableColumn<HoKhauBean, String> hoTen;
    @FXML
    TableColumn<HoKhauBean, String> diaChi;
    @FXML
    Button hoKhauButton;

    HoKhauService hoKhauService;
    List<HoKhauBean> listHoKhauBeans;
    ObservableList<HoKhauBean> observableListHoKhauBeans;

    SceneSwitchHoKhau sceneSwitchHoKhau;
    SwitchScene switchScene;
    @FXML
    TextField search;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switchScene = new SwitchScene();

        sceneSwitchHoKhau = new SceneSwitchHoKhau();
        hoKhauService = new HoKhauService();
        setDataTable();
        setTimKiem();
    }
    public void setDataTable(){
        listHoKhauBeans = hoKhauService.getListHoKhau();
        observableListHoKhauBeans = FXCollections.observableList(listHoKhauBeans);
        maHoKhau.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getMaHoKhau()));
        hoTen.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getChuHo().getHoTen()));
        diaChi.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getDiaChi()));
        table.setItems(observableListHoKhauBeans);
    }

    public void setTimKiem(){
        // thanh tìm kiếm
        search.setPromptText("Tìm kiếm");
        FilteredList<HoKhauBean> hoKhauBeanFilteredList = new FilteredList(observableListHoKhauBeans, p -> true);//Pass the data to a filtered list

        table.setItems(hoKhauBeanFilteredList);//Set the table's items using the filtered list

        search.textProperty().addListener((obs, oldValue, newValue) -> {
            hoKhauBeanFilteredList.setPredicate((p -> p.getHoKhauModel().getMaHoKhau().toLowerCase().contains(newValue.toLowerCase().trim())));
        });
    }
    @FXML
    public void themMoi(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/ThemMoi.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Thêm mới hộ khẩu");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataTable();
    }
    @FXML
    public void tachHoKhau(ActionEvent event) throws IOException{
        sceneSwitchHoKhau.changeSceneTachHoKhau(event);
    }
    @FXML
    public void chuyenDi(ActionEvent event) throws IOException{
        sceneSwitchHoKhau.changeSceneChuyenDi(event);
    }

    @FXML
    public void xemChiTiet(MouseEvent event) throws IOException {
        HoKhauBean selectedHoKhau = table.getSelectionModel().getSelectedItem();
        HoKhauBean hoKhau = listHoKhauBeans.stream().filter(hoKhauBean -> hoKhauBean.getHoKhauModel().getMaHoKhau().equals(selectedHoKhau.getHoKhauModel().getMaHoKhau())).findFirst().orElse(new HoKhauBean());
        HoKhauHolder hoKhauHolder = HoKhauHolder.getInstance();
        hoKhauHolder.setHoKhauBean(hoKhau);
        if(event.getClickCount() == 2 && selectedHoKhau != null){
            sceneSwitchHoKhau.changeSceneChiTiet(event);
        }
    }

    @FXML
    void btnTrangChu(ActionEvent event) throws IOException {
        switchScene.changeToMain(event);
    }
    @FXML
    void btnHoKhau(ActionEvent event) throws IOException {
        switchScene.changeToHoKhau(event);
    }


    @FXML
    void btnNhaVH(ActionEvent event) throws IOException {
        switchScene.changeToNVH(event);
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
    void btnDangXuat(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/views/login-view.fxml")) ;
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,  600,322));
        stage.centerOnScreen();
    }
    @FXML
    void btnHoSo(ActionEvent event) throws IOException{


    }
    @FXML
    void btnThongBao(ActionEvent event) throws IOException{
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
