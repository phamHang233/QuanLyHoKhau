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
import models.ChoThueNVHModel;
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

public class ChoThueNVHController implements Initializable {
    @FXML
    private TitledPane NVHTitle;
    @FXML
    private TableView<ChoThueNVHModel> table;


    @FXML
    private TableColumn<ChoThueNVHModel, String> mucDich;

    @FXML
    private TableColumn<ChoThueNVHModel, Date> ngayThue;

    @FXML
    private TableColumn<ChoThueNVHModel, String> nguoiLap;
    @FXML
    private TableColumn<ChoThueNVHModel, String> ghiChu;

    @FXML
    private TableColumn<ChoThueNVHModel, Integer> stt;
    @FXML
    private Label tong;
    @FXML
    private TableColumn<ChoThueNVHModel, String> nguoiThue;

    @FXML
    private TableColumn<ChoThueNVHModel, Integer> phi;

    SwitchScene switchScene;
    NhaVanHoaService nvhService;
    List<ChoThueNVHModel> listChoThue;
    ObservableList<ChoThueNVHModel> observableListChoThue;
    SceneSwitchNVH sceneSwitchNVH;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switchScene = new SwitchScene();
        sceneSwitchNVH = new SceneSwitchNVH();
        nvhService = new NhaVanHoaService();
        setDataTable();
    }


    public void setDataTable() {
        listChoThue = nvhService.getListChoThue();
        observableListChoThue = FXCollections.observableList(listChoThue);
        stt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        mucDich.setCellValueFactory(new PropertyValueFactory<>("mucDich"));
        ngayThue.setCellValueFactory(new PropertyValueFactory<>("ngaySuDung"));
        nguoiLap.setCellValueFactory(new PropertyValueFactory<>("nguoiLap"));
        phi.setCellValueFactory(new PropertyValueFactory<>("phi"));
        nguoiThue.setCellValueFactory(new PropertyValueFactory<>("nguoiThue"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        table.setItems(observableListChoThue);
        try {
            // tính tổng bản ghi
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM cho_thue_NVH";
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
        sceneSwitchNVH.changeToThemChoThue(event);
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
