package controllers.hoKhauControllers;

import Beans.HoKhauBean;
import Beans.NhanKhauBean;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.HoKhauService;
import services.NhanKhauService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChonController implements Initializable {
    @FXML
    TableView<NhanKhauBean> table;
    @FXML
    TableColumn<NhanKhauBean, String> hoTen;
    @FXML
    TableColumn<NhanKhauBean, String> gioiTinh;
    @FXML
    TableColumn<NhanKhauBean, String> ngaySinh;
    @FXML
    TableColumn<NhanKhauBean, String> soCMT;
    @FXML
    TableColumn<NhanKhauBean, String> diaChiHienNay;

    List<NhanKhauBean> listNhanKhauBean;
    NhanKhauService nhanKhauService;
    List<HoKhauBean> listHoKhauBeans;

    HoKhauService hoKhauService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nhanKhauService = new NhanKhauService();
        listNhanKhauBean = nhanKhauService.getListNhanKhau();
        hoKhauService = new HoKhauService();
        listHoKhauBeans = hoKhauService.getListHoKhau();
        ObservableList nhanKhauBeanObservableList = FXCollections.observableList(listNhanKhauBean);
        hoTen.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHoTen()));
        gioiTinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinh()));
        ngaySinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNamSinh().toString()));
        soCMT.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getCanCuocCongDanModel().getSoCMT()));
        diaChiHienNay.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));
        table.setItems(nhanKhauBeanObservableList);

    }

    @FXML
    public void chonChuHo(MouseEvent event) throws IOException {
        NhanKhauBean selectedNhanKhauBean = table.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && selectedNhanKhauBean != null){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChuHoHolder holder = ChuHoHolder.getInstance();
            holder.setData(selectedNhanKhauBean);
            if(checkChuHo(selectedNhanKhauBean)){

                stage.close();
            }
        }
    }
    public boolean checkChuHo(NhanKhauBean selectedNK){
        Integer idNK = selectedNK.getNhanKhauModel().getID();

        int i;
        for ( i=0; i<listHoKhauBeans.size(); i++){


            if(     idNK == listHoKhauBeans.get(i).getChuHo().getID()){
                Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
                missingFieldAlert.setContentText("Kh??ng th??? ch???n ng?????i n??y!");
                missingFieldAlert.show();
                return false;
            }
        }
        return true;

    }
    @FXML
    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }
}
