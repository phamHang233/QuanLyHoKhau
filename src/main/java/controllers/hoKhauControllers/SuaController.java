package controllers.hoKhauControllers;

import Beans.MemOfFamily;
import Beans.NhanKhauBean;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.NhanKhauService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SuaController implements Initializable {
    @FXML
    TableView<NhanKhauBean> dataTable;
    @FXML
    TableColumn<NhanKhauBean, String> hoTen;
    @FXML
    TableColumn<NhanKhauBean, String> gioiTinh;
    @FXML
    TableColumn<NhanKhauBean, String> ngaySinh;
    @FXML
    TableColumn<NhanKhauBean, String> soCMT;
    @FXML
    TableColumn<NhanKhauBean, String> IDColumn;

    @FXML
    TableView<MemOfFamily> addedDataTable;
    @FXML
    TableColumn<MemOfFamily, String> hoTenAdded;
    @FXML
    TableColumn<MemOfFamily, String> ngaySinhAdded;
    @FXML
    TableColumn<MemOfFamily, String> quanHeVoiChuHo;

    List<NhanKhauBean> listNhanKhauBean;
    List<MemOfFamily> listMemOfFamily;
    NhanKhauService nhanKhauService;
    Dialog<String> dialog;

    ObservableList<MemOfFamily> memOfFamilyObservableList;
    ObservableList<NhanKhauBean> nhanKhauBeanObservableList;
    TextField quanHeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nhanKhauService = new NhanKhauService();
        listNhanKhauBean = nhanKhauService.getListNhanKhau();

        nhanKhauBeanObservableList = FXCollections.observableList(listNhanKhauBean);
        IDColumn.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(String.valueOf(nhanKhauBean.getValue().getNhanKhauModel().getID())));
        hoTen.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHoTen()));
        gioiTinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinh()));
        ngaySinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNamSinh().toString()));
        soCMT.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getChungMinhThuModel().getSoCMT()));
        dataTable.setItems(nhanKhauBeanObservableList);

        //Set data bang ben phai
        listMemOfFamily = new ArrayList<>();
        memOfFamilyObservableList = FXCollections.observableList(listMemOfFamily);
        hoTenAdded.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinhAdded.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeVoiChuHo.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        addedDataTable.setItems(memOfFamilyObservableList);

        //Tao "quan he voi chu ho dialog"
        dialog = new Dialog<>();
        ButtonType addButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        quanHeText = new TextField();
        Label quanHeLB = new Label("Nhập quan hệ với chủ hộ:");

        grid.add(quanHeLB, 0, 0);
        grid.add(quanHeText, 0, 1);

        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        quanHeText.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType){
                return quanHeText.getText();
            }
            return null;
        });

    }

    public void add(ActionEvent event){

        NhanKhauBean selectedNhanKhau = dataTable.getSelectionModel().getSelectedItem();
        MemOfFamily memOfFamily = new MemOfFamily();
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quanHe -> {
            memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHe);
        });
        memOfFamily.setNhanKhau(selectedNhanKhau);
        memOfFamily.getThanhVienCuaHoModel().setIdNhanKhau(selectedNhanKhau.getNhanKhauModel().getID());
        memOfFamilyObservableList.add(memOfFamily);
        quanHeText.setText("");

    }
    @FXML
    public void remove(ActionEvent event){
        MemOfFamily selectedMemOfFamily = addedDataTable.getSelectionModel().getSelectedItem();
        memOfFamilyObservableList.remove(selectedMemOfFamily);
    }

    @FXML
    public void save(ActionEvent event){
        ThanhVienHoHolder thanhVienHoHolder = ThanhVienHoHolder.getInstance();
        thanhVienHoHolder.setListThanhVienHo(memOfFamilyObservableList);
        huy(event);
    }
    @FXML
    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }
}
