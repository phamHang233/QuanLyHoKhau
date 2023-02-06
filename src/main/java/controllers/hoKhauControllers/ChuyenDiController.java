package controllers.hoKhauControllers;

import Beans.HoKhauBean;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.HoKhauService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChuyenDiController implements Initializable {
    @FXML
    TableView<HoKhauBean> table;
    @FXML
    TableColumn<HoKhauBean, String> maHoKhauColumn;
    @FXML
    TableColumn<HoKhauBean, String> hoTenChuHoColumn;
    @FXML
    TableColumn<HoKhauBean, String> diaChiColumn;
    @FXML
    TextField maHoKhau;
    @FXML
    TextField tenChuHo;
    @FXML
    TextField diaChiHienTai;
    @FXML
    TextField diaChiChuyenDen;
    @FXML
    TextArea lyDoChuyenDi;

    List<HoKhauBean> hoKhauBeanList;
    ObservableList<HoKhauBean> hoKhauBeanObservableList;
    HoKhauService hoKhauService;
    HoKhauBean hoKhau;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hoKhauService = new HoKhauService();
        hoKhauBeanList = hoKhauService.getListHoKhau();

        hoKhauBeanObservableList = FXCollections.observableList(hoKhauBeanList);
        maHoKhauColumn.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getMaHoKhau()));
        hoTenChuHoColumn.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getChuHo().getHoTen()));
        diaChiColumn.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getDiaChi()));
        table.setItems(hoKhauBeanObservableList);
    }

    public void chon(MouseEvent event) {
        HoKhauBean selectedHoKhau = table.getSelectionModel().getSelectedItem();
        hoKhau = hoKhauBeanList.stream().filter(hoKhauBean -> hoKhauBean.getHoKhauModel().getMaHoKhau().equals(selectedHoKhau.getHoKhauModel().getMaHoKhau())).findFirst().orElse(new HoKhauBean());
        maHoKhau.setText(hoKhau.getHoKhauModel().getMaHoKhau());
        tenChuHo.setText(hoKhau.getChuHo().getHoTen());
        diaChiHienTai.setText(hoKhau.getHoKhauModel().getDiaChi());
    }

    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    public void xacNhan(ActionEvent event){
        if(maHoKhau.getText().isBlank() || tenChuHo.getText().isBlank() || diaChiHienTai.getText().isBlank() || diaChiChuyenDen.getText().isBlank() || lyDoChuyenDi.getText().isBlank()){
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setContentText("Bạn cần điền đầy đủ tất cả các trường thông tin yêu cầu");
            missingFieldAlert.show();
            return;
        } else {
            hoKhauService.chuyenDi(hoKhau.getHoKhauModel().getID(), diaChiChuyenDen.getText(), lyDoChuyenDi.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sửa thành công!");
            alert.show();
            huy(event);
        }
    }

}