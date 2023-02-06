package controllers.hoKhauControllers;

import Beans.HoKhauBean;
import Beans.MemOfFamily;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.NhanKhauModel;
import models.ThanhVienModel;
import services.HoKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TachHoKhauController implements Initializable {
    @FXML
    TableView<HoKhauBean> hoCanTachTable;
    @FXML
    TableColumn<HoKhauBean, String> maHoKhauColumn;
    @FXML
    TableColumn<HoKhauBean, String> hoTenChuHoColumn;
    @FXML
    TableColumn<HoKhauBean, String> diaChiColumn;

    @FXML
    TableView<MemOfFamily> nguoiSangHoMoiTable;
    @FXML
    TableColumn<MemOfFamily, String> IDColumn;
    @FXML
    TableColumn<MemOfFamily, String> hoTenColumn;
    @FXML
    TableColumn<MemOfFamily, String> ngaySinhColumn;
    @FXML
    TableColumn<MemOfFamily, String> quanHeVoiChuHoColumn;

    @FXML
    TableView<MemOfFamily> nguoiOHoMoiTable;
    @FXML
    TableColumn<MemOfFamily, String> ID2Column;
    @FXML
    TableColumn<MemOfFamily, String> hoTen2Column;
    @FXML
    TableColumn<MemOfFamily, String> ngaySinh2Column;
    @FXML
    TableColumn<MemOfFamily, String> quanHeVoiChuHo2Column;

    @FXML
    TextField chuHoHienTaiText;
    @FXML
    TextField maKhuVucText;
    @FXML
    TextField diaChiText;
    @FXML
    TextField maHoKhauMoiText;
    @FXML
    TextField chuHoMoiText;

    HoKhauService hoKhauService;
    List<HoKhauBean> listHoKhauBeans;
    ObservableList<HoKhauBean> observableListHoKhauBeans;
    ObservableList<MemOfFamily> memOfFamilies;
    ObservableList<MemOfFamily> memOfFamilies2;
    Dialog<String> dialog;
    TextField quanHeText;
    String quanHeBanDau;
    NhanKhauModel chuHo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hoKhauService = new HoKhauService();
        listHoKhauBeans = hoKhauService.getListHoKhau();
        //Set data table 1
        observableListHoKhauBeans = FXCollections.observableList(listHoKhauBeans);
        maHoKhauColumn.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getMaHoKhau()));
        hoTenChuHoColumn.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getChuHo().getHoTen()));
        diaChiColumn.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getDiaChi()));

        hoCanTachTable.setItems(observableListHoKhauBeans);
        nguoiOHoMoiTable.setItems(memOfFamilies2);
        nguoiSangHoMoiTable.setItems(memOfFamilies);

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
            if (dialogButton == addButtonType) {
                return quanHeText.getText();
            }
            return null;
        });
    }

    public void chonHoCanTach(MouseEvent event) {
        HoKhauBean hoKhauBean = hoCanTachTable.getSelectionModel().getSelectedItem();
        List<ThanhVienModel> thanhVienCuaHoModels = hoKhauBean.getListThanhVienCuaHo();
        List<NhanKhauModel> nhanKhauModels = hoKhauBean.getListNhanKhauModels();

        List<MemOfFamily> memOfFamilyList = new ArrayList<>();

        //Set thanh vien cua ho
        thanhVienCuaHoModels.stream().forEach(thanhVienCuaHoModel -> {
            MemOfFamily memOfFamily = new MemOfFamily();
            memOfFamily.setThanhVienCuaHoModel(thanhVienCuaHoModel);
            memOfFamilyList.add(memOfFamily);
        });

        //Set nhan khau bean
        for (int i = 0; i < memOfFamilyList.size(); i++) {
            memOfFamilyList.get(i).getNhanKhau().setNhanKhauModel(nhanKhauModels.get(i));
        }

        chuHoHienTaiText.setText(hoKhauBean.getChuHo().getHoTen());

        memOfFamilies = FXCollections.observableList(memOfFamilyList);
        IDColumn.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(String.valueOf(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getID())));
        hoTenColumn.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinhColumn.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeVoiChuHoColumn.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        nguoiSangHoMoiTable.setItems(memOfFamilies);

        memOfFamilies2 = FXCollections.observableList(new ArrayList<>());
        ID2Column.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(String.valueOf(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getID())));
        hoTen2Column.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinh2Column.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeVoiChuHo2Column.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        nguoiOHoMoiTable.setItems(memOfFamilies2);

    }

    public void add(ActionEvent event) {
        MemOfFamily memOfFamily = nguoiSangHoMoiTable.getSelectionModel().getSelectedItem();
        quanHeBanDau = memOfFamily.getThanhVienCuaHoModel().getQuanHeVoiChuHo();
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quanHe -> {
            memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHe);
        });
        memOfFamilies.remove(memOfFamily);
        memOfFamilies2.add(memOfFamily);

    }

    public void remove(ActionEvent event) {
        MemOfFamily memOfFamily = nguoiOHoMoiTable.getSelectionModel().getSelectedItem();
        memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHeBanDau);
        memOfFamilies.add(memOfFamily);
        memOfFamilies2.remove(memOfFamily);
    }

    public void chonChuHo(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/Chon.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataChuHo();
    }

    public void setDataChuHo() {
        ChuHoHolder chuHoHolder = ChuHoHolder.getInstance();
        chuHo = chuHoHolder.getNhanKhauBean().getNhanKhauModel();
        chuHoMoiText.setText(chuHo.getHoTen());

    }

    public void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    public void xacNhan(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (isMissingField()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hãy điền vào tất cả các trường thông tin cần thiết");
            alert.show();
            return;
        }
        ;
        HoKhauBean hoKhauBean = new HoKhauBean();
        //Set HoKhauModel
        hoKhauBean.getHoKhauModel().setMaHoKhau(maHoKhauMoiText.getText());
        hoKhauBean.getHoKhauModel().setIdChuHo(chuHo.getID());
        hoKhauBean.getHoKhauModel().setMaKhuVuc(maKhuVucText.getText());
        hoKhauBean.getHoKhauModel().setDiaChi(diaChiText.getText());
        //Set chu ho
        hoKhauBean.setChuHo(chuHo);
        //Set list thanh vien ho
        List<MemOfFamily> memOfNewFamilyList = new ArrayList<>(memOfFamilies2);
        List<ThanhVienModel> thanhVienCuaHoModelList = memOfNewFamilyList.stream().map(memOfNewFamily -> memOfNewFamily.getThanhVienCuaHoModel()).collect(Collectors.toList());
        hoKhauBean.setListThanhVienCuaHo(thanhVienCuaHoModelList);
        hoKhauService.tachHoKhau(hoKhauBean);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thêm thành công");
        alert.show();
        huy(event);

    }

    public boolean isMissingField() {
        if (chuHoHienTaiText.getText().isBlank() || maKhuVucText.getText().isBlank() || diaChiText.getText().isEmpty() || maHoKhauMoiText.getText().isEmpty() || chuHoMoiText.getText().isEmpty())
            return true;
        return false;
    }

}
