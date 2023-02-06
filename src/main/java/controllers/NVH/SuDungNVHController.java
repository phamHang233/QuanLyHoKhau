package controllers.NVH;

import controllers.SwitchScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import models.SuDungNVHModel;
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

public class SuDungNVHController implements Initializable {
    @FXML
    private TitledPane NVHTitle;
    @FXML
    private TableView<SuDungNVHModel> table;


    @FXML
    private TableColumn<SuDungNVHModel, String> mucDich;

    @FXML
    private TableColumn<SuDungNVHModel, Date> ngaySuDung;

    @FXML
    private TableColumn<SuDungNVHModel, String> nguoiLap;
    @FXML
    private TableColumn<SuDungNVHModel, String> ghiChu;

    @FXML
    private TableColumn<SuDungNVHModel, Integer> stt;
    @FXML
    private Label tong;


    SwitchScene switchScene;
    NhaVanHoaService sdNVHService;
    List<SuDungNVHModel> listSuDung;
    ObservableList<SuDungNVHModel> observableListSD;
    SceneSwitchNVH sceneSwitchNVH;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switchScene = new SwitchScene();
        sceneSwitchNVH = new SceneSwitchNVH();
         sdNVHService = new NhaVanHoaService();
        setDataTable();

    }


    public void setDataTable() {
        listSuDung = sdNVHService.getListSuDung();
        observableListSD = FXCollections.observableList(listSuDung);
        stt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        mucDich.setCellValueFactory(new PropertyValueFactory<>("mucDich"));
        ngaySuDung.setCellValueFactory(new PropertyValueFactory<>("ngaySuDung"));
        nguoiLap.setCellValueFactory(new PropertyValueFactory<>("nguoiLap"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        table.setItems(observableListSD);
        try {
            // tính tổng bản ghi
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM suDungNVH";
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
        sceneSwitchNVH.changeToThemSuDung(event);
            setDataTable();
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
    void btnHoSo(ActionEvent event) throws IOException {


    }

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


