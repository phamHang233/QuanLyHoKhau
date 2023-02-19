package controllers.ThongKe;

import Beans.NhanKhauBean;
import controllers.SwitchScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.NhanKhauModel;
import org.w3c.dom.Text;
import services.NhanKhauService;
import services.StringService;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static services.SQLServerConnection.getSqlConnection;

//Quản lý màn hình thống kê
public class ThongKeController implements Initializable {

    SwitchScene switchScene;
    @FXML
    private TitledPane NVHTitle;

    @FXML
    private TableView<NhanKhauModel> bangThongKe;

    @FXML
    private ComboBox<String> capDoBox;

    @FXML
    private AnchorPane capDoMode;

    @FXML
    private ComboBox<String> chonGioiTinhThongKe;

    @FXML
    private Label chuyenMonNhanKhau;

    @FXML
    private Label danTocNhanKhau;

    @FXML
    private TextField denTuoiText;

    @FXML
    private Label diaChiHoKhau;

    @FXML
    private Label diaChiMoiNhanKhau;

    @FXML
    private Label diaChiNhanKhau;

    @FXML
    private TableColumn<?, ?> diaChiThongKe;

    @FXML
    private AnchorPane doTuoiMode;

    @FXML
    private Label ghiChuNhanKhau;

    @FXML
    private Button giaDinhHoKhau;

    @FXML
    private Label gioiTinhNhanKhau;

    @FXML
    private TableColumn<?, ?> gioiTinhThongKe;

    @FXML
    private AnchorPane hienThiMacDinh;

    @FXML
    private AnchorPane hoKhauMode;

    @FXML
    private Label hoTenChuHo;

    @FXML
    private Label hoTenNhanKhau;

    @FXML
    private TableColumn<?, ?> hoTenThongKe;

    @FXML
    private Label hocVanNhanKhau;

    @FXML
    private TableColumn<?, ?> idThongKe;

    @FXML
    private Button lichSuThayDoi;

    @FXML
    private Label lyDoChuyenDenNhanKhau;

    @FXML
    private Label lyDoChuyenHoKhau;

    @FXML
    private Label lyDoXoaNhanKhau;

    @FXML
    private TextField maHoKhau;

    @FXML
    private Label maKhuVucHoKhau;

    @FXML
    private Label ngayChuyenDenNhanKhau;

    @FXML
    private Label ngayChuyenDiHoKhau;

    @FXML
    private Label ngayChuyenDiNhanKhau;

    @FXML
    private Label ngayLapHoKhau;

    @FXML
    private Label ngaySinhNhanKhau;

    @FXML
    private TableColumn<?, ?> ngaySinhThongKe;

    @FXML
    private Label ngayTaoNhanKhau;

    @FXML
    private Label ngayXoaNhanKhau;

    @FXML
    private Label ngheNghiepNhanKhau;

    @FXML
    private Label ngoaiNguNhanKhau;

    @FXML
    private Label nguoiTaoNhanKhau;

    @FXML
    private Label nguoiThucHienHoKhau;

    @FXML
    private Label nguyenQuanNhanKhau;

    @FXML
    private AnchorPane nhanKhauMode;

    @FXML
    private Label noiLamViecNhanKhau;

    @FXML
    private Label noiSinhNhanKhau;

    @FXML
    private Label quocTichNhanKhau;

    @FXML
    private TextField soCanCuocCongDan;

    @FXML
    private TableColumn<?, ?> soCanCuocThongKe;

    @FXML
    private Label soHoChieuNhanKhau;

    @FXML
    private CheckBox theoCapDo;

    @FXML
    private CheckBox theoDoTuoi;

    @FXML
    private AnchorPane thongKeMode;

    @FXML
    private Button thongKeSoLieuBtn;

    @FXML
    private AnchorPane thongTinTimKiemHoKhau;

    @FXML
    private AnchorPane thongTinTimKiemNhanKhau;

    @FXML
    private Label thuongTruNhanKhau;

    @FXML
    private Label tienAnNhanKhau;

    @FXML
    private Label tiengDanTocNhanKhau;

    @FXML
    private Button timKiemHoKhauBtn;

    @FXML
    private Button timKiemNhanKhauBtn;

    @FXML
    private ComboBox<String> tinhTrangThongKe;

    @FXML
    private Label tonGiaoNhanKhau;

    @FXML
    private TextField tuTuoiText;

    @FXML
    private Button xemThemGiaDinh;

    @FXML
    private Button xemThemTieuSu;
    @FXML
    private TextField denNamText;
    @FXML
    private TextField tuNamText;

    List<NhanKhauBean> listNhanKhauBeans;
    NhanKhauService nhanKhauService;
    ObservableList<NhanKhauModel> observablelistNhanKhau;
    ObservableList<String> gioiTinhList;
    ObservableList<String> tinhTrangList;
    ObservableList<String> capDoList;
    private SwitchSceneTK switchSceneTK;
    int accessCount = 0;
    private void exceptionHandle(String message) {
    }
    // Nut chung cho cac che do
    @FXML
    void btnCSVC(ActionEvent event) throws IOException {
        switchScene.changeToThietBi(event);
    }

    @FXML
    void btnChoThue(ActionEvent event) throws IOException {
        switchScene.changeToChoThue(event);
    }

    @FXML
    void btnDangXuat(ActionEvent event) throws IOException {
        switchScene.changToLogin(event);
    }

    @FXML
    void btnHoKhau(ActionEvent event) throws IOException {
        switchScene.changeToHoKhau(event);
    }

    @FXML
    void btnHoSo(ActionEvent event) {

    }

    @FXML
    void btnNhanKhau(ActionEvent event) throws IOException {
        switchScene.changeToNhanKhau(event);
    }

    @FXML
    void btnSuDung(ActionEvent event) throws IOException {
        switchScene.changeToSuDung(event);
    }

    @FXML
    void btnThongBao(ActionEvent event) {
        System.out.println("Hiển thị thông báo!");
    }

    @FXML
    void btnThongKe(ActionEvent event) throws IOException {

    }

    @FXML
    void btnTrangChu(ActionEvent event) throws IOException {
        switchScene.changeToMain(event);
    }
    // Che do tim kiem nhan khau
    @FXML
    void timKiemNhanKhau(ActionEvent event) {
        String stringTemp = soCanCuocCongDan.getText();
//        System.out.println(stringTemp);
//        NhanKhauModel nhanKhauModel = new NhanKhauModel();

        try {
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.id = chung_minh_thu.idNhanKhau WHERE soCMT = '" + stringTemp + "'";
//            System.out.println(query);
            PreparedStatement preparedStatement = getSqlConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                nhanKhauModel.setHoTen(rs.getString("hoTen"));
//                nhanKhauModel.setGioiTinh(rs.getString("gioiTinh"));
//                nhanKhauModel.setNamSinh(rs.getDate("namSinh"));
//                nhanKhauModel.setDiaChiHienNay(rs.getString("diaChiHienNay"));
//                nhanKhauModel.setNguyenQuan(rs.getString("nguyenQuan"));
//                nhanKhauModel.setTonGiao(rs.getString("tonGiao"));
//                nhanKhauModel.setDanToc(rs.getString("danToc"));
//                nhanKhauModel.setQuocTich(rs.getString("quocTich"));
//                nhanKhauModel.setNoiThuongTru(rs.getString("noiThuongTru"));
//                nhanKhauModel.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                hoTenNhanKhau.setText(rs.getString("hoTen"));
                soHoChieuNhanKhau.setText(rs.getString("soHoChieu"));
                quocTichNhanKhau.setText(rs.getString("quocTich"));
                gioiTinhNhanKhau.setText(rs.getString("gioiTinh"));
                ngaySinhNhanKhau.setText(rs.getString("namSinh"));
                noiSinhNhanKhau.setText(rs.getString("noiSinh"));
                nguyenQuanNhanKhau.setText(rs.getString("nguyenQuan"));
                thuongTruNhanKhau.setText(rs.getString("noiThuongTru"));
                diaChiNhanKhau.setText(rs.getString("diaChiHienNay"));
                danTocNhanKhau.setText(rs.getString("danToc"));
                tonGiaoNhanKhau.setText(rs.getString("tonGiao"));
                hocVanNhanKhau.setText(rs.getString("trinhDoHocVan"));
                chuyenMonNhanKhau.setText(rs.getString("trinhDoChuyenMon"));
                tiengDanTocNhanKhau.setText(rs.getString("bietTiengDanToc"));
                ngoaiNguNhanKhau.setText(rs.getString("trinhDoNgoaiNgu"));
                ngheNghiepNhanKhau.setText(rs.getString("ngheNghiep"));
                noiLamViecNhanKhau.setText(rs.getString("noiLamViec"));
                tienAnNhanKhau.setText(rs.getString("tienAn"));
                ngayChuyenDenNhanKhau.setText(rs.getString("ngayChuyenDen"));
                lyDoChuyenDenNhanKhau.setText(rs.getString("lyDoChuyenDen"));
                ngayChuyenDiNhanKhau.setText(rs.getString("ngayChuyenDi"));
                diaChiMoiNhanKhau.setText(rs.getString("diaChiMoi"));
                ngayTaoNhanKhau.setText(rs.getString("ngayTao"));
                nguoiTaoNhanKhau.setText(rs.getString("idNguoiTao"));
                ngayXoaNhanKhau.setText(rs.getString("ngayXoa"));
                lyDoXoaNhanKhau.setText(rs.getString("lyDoXoa"));
                ghiChuNhanKhau.setText(rs.getString("ghiChu"));
            }
            preparedStatement.close();
//            list.add(nhanKhauModel);
//            thongtinnhankhau.setItems(list);

            thongTinTimKiemNhanKhau.setVisible(true);
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());

        }
    }
    @FXML
    void xoaTimKiemNhanKhau (){
        thongTinTimKiemNhanKhau.setVisible(false);
        soCanCuocCongDan.setText("");
        hoTenNhanKhau.setText("");
        soHoChieuNhanKhau.setText("");
        quocTichNhanKhau.setText("");
        gioiTinhNhanKhau.setText("");
        ngaySinhNhanKhau.setText("");
        noiSinhNhanKhau.setText("");
        nguyenQuanNhanKhau.setText("");
        thuongTruNhanKhau.setText("");
        diaChiNhanKhau.setText("");
        danTocNhanKhau.setText("");
        tonGiaoNhanKhau.setText("");
        hocVanNhanKhau.setText("");
        chuyenMonNhanKhau.setText("");
        tiengDanTocNhanKhau.setText("");
        ngoaiNguNhanKhau.setText("");
        ngheNghiepNhanKhau.setText("");
        noiLamViecNhanKhau.setText("");
        tienAnNhanKhau.setText("");
        ngayChuyenDenNhanKhau.setText("");
        lyDoChuyenDenNhanKhau.setText("");
        ngayChuyenDiNhanKhau.setText("");
        diaChiMoiNhanKhau.setText("");
        ngayTaoNhanKhau.setText("");
        nguoiTaoNhanKhau.setText("");
        ngayXoaNhanKhau.setText("");
        lyDoXoaNhanKhau.setText("");
        ghiChuNhanKhau.setText("");
    }
    @FXML
    void chonThongKeMode(ActionEvent event) throws IOException {
//            switchScene.changeToThongKe(event);
        hienThiMacDinh.setVisible(false);
        if(event.getSource() == timKiemNhanKhauBtn){
            nhanKhauMode.setVisible(true);
            xoaTimKiemNhanKhau();
            hoKhauMode.setVisible(false);
            thongKeMode.setVisible(false);
        }
        else if(event.getSource() == timKiemHoKhauBtn){
            hoKhauMode.setVisible(true);
            xoaTimKiemHoKhau();
            nhanKhauMode.setVisible(false);
            thongKeMode.setVisible(false);
        }
        else{
            thongKeMode.setVisible(true);
            hoKhauMode.setVisible(false);
            nhanKhauMode.setVisible(false);
        }
    }
    @FXML
    void xemThemThongTinNhanKhau(ActionEvent event) throws IOException {
        switchSceneTK.changeToXemThemNhanKhau(event, soCanCuocCongDan.getText());
    }
    // Che do tim kiem ho khau
    @FXML
    void xoaTimKiemHoKhau(){
        thongTinTimKiemHoKhau.setVisible(false);
        hoTenChuHo.setText("");
        maKhuVucHoKhau.setText("");
        diaChiHoKhau.setText("");
        ngayLapHoKhau.setText("");
        ngayChuyenDiHoKhau.setText("");
        ngayLapHoKhau.setText("");
        ngayChuyenDiHoKhau.setText("");
        lyDoChuyenHoKhau.setText("");
        nguoiThucHienHoKhau.setText("");
        maHoKhau.setText("");
    }
    public void timKiemHoKhau(ActionEvent event) {
        String stringTemp = maHoKhau.getText();
        try {
//            String query = "SELECT nk.maNhanKhau, nk.hoTen, nk.namSinh, nk.gioiTinh, nk.diaChiHienNay\n" +
//                    "FROM nhan_khau as nk, thanh_vien_cua_ho as tv, ho_khau as hk\n" +
//                    "WHERE hk.ID = tv.idHoKhau\n" +
//                    "AND tv.idNhanKhau = nk.ID\n" +
//                    "AND hk.maHoKhau = '" + stringTemp+ "'";
//            System.out.println(query);
            String query = "SELECT nk.hoTen, hk.maKhuVuc, hk.diaChi, hk.ngayLap, hk.ngayChuyenDi, hk.lyDoChuyen, hk.nguoiThucHien\n" +
                    "FROM ho_khau as hk, nhan_khau as nk\n" +
                    "WHERE hk.idChuHo = nk.ID\n" +
                    "AND hk.maHoKhau = '"+stringTemp+"'";
            PreparedStatement preparedStatement = getSqlConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                hoTenChuHo.setText(rs.getString("hoTen"));
                maKhuVucHoKhau.setText(rs.getString("maKhuVuc"));
                diaChiHoKhau.setText(rs.getString("diaChi"));
                ngayLapHoKhau.setText(rs.getString("ngayLap"));
                ngayChuyenDiHoKhau.setText(rs.getString("ngayChuyenDi"));
                lyDoChuyenHoKhau.setText(rs.getString("lyDoChuyen"));
                nguoiThucHienHoKhau.setText(rs.getString("nguoiThucHien"));
            }
            preparedStatement.close();
//            list.add(nhanKhauModel);
//            thongtinnhankhau.setItems(list);

            thongTinTimKiemHoKhau.setVisible(true);
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());

        }
    }
    public void xemThemThongTinHoKhau(ActionEvent event) throws IOException {
        switchSceneTK.changeToXemThemHoKhau(event, maHoKhau.getText());
    }
    // Che do thong ke so lieu
    public void timKiemThongKe(ActionEvent event) {
        if(theoCapDo.isSelected()){
            String capDo = "Mầm non";
            capDo = StringService.covertToString(capDoBox.getSelectionModel().getSelectedItem());
            System.out.println(capDo);
            if(capDo.equals("Mam non")) {
                tuTuoiText.setText("0");
                denTuoiText.setText("6");
            } else if (capDo.equals("Cap 1")) {
                tuTuoiText.setText("7");
                denTuoiText.setText("11");
            } else if (capDo.equals("Cap 2")) {
                tuTuoiText.setText("12");
                denTuoiText.setText("15");
            } else if (capDo.equals("Cap 3")) {
                tuTuoiText.setText("16");
                denTuoiText.setText("18");
            } else if (capDo.equals("Lao Đong")) {
                tuTuoiText.setText("19");
                denTuoiText.setText("65");
            } else {
                tuTuoiText.setText("66");
                denTuoiText.setText("1000");
            }
        }
        setData();
    }
    public void xoaTimKiemThongKe(ActionEvent event) {
        bangThongKe.getItems().clear();
    }
    public void checkDoTuoi(ActionEvent event){
        if(((CheckBox)event.getSource()).getText().equals("Theo độ tuổi")){
            theoDoTuoi.setSelected(true);
            theoCapDo.setSelected(false);
            doTuoiMode.setVisible(true);
            capDoMode.setVisible(false);
        }
        else {
            theoDoTuoi.setSelected(false);
            theoCapDo.setSelected(true);
            doTuoiMode.setVisible(false);
            capDoMode.setVisible(true);
        }
    }
    public void setData() {
        int tuTuoi = -1;
        int denTuoi = 200;
        int tuNam = 0;
        int denNam = 2100;
        String gender = "Toan Bo";
        String status = "Toan Bo";

        gender = StringService.covertToString(chonGioiTinhThongKe.getSelectionModel().getSelectedItem());
        if(gender.equals("Nu")) gender = "Nữ";
        status = StringService.covertToString(tinhTrangThongKe.getSelectionModel().getSelectedItem());


        try {
            if (!tuTuoiText.getText().trim().isEmpty()) {
                tuTuoi = Integer.parseInt(tuTuoiText.getText().trim());
            } else {
                tuTuoi = -1;
            }
            if (!denTuoiText.getText().trim().isEmpty()) {
                denTuoi = Integer.parseInt(denTuoiText.getText().trim());
            } else {
                denTuoi = 200;
            }
            if (!tuNamText.getText().trim().isEmpty()) {
                tuNam = Integer.parseInt(tuNamText.getText().trim());
            }
            if (!denNamText.getText().trim().isEmpty()) {
                denNam = Integer.parseInt(denNamText.getText().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập đúng kiểu dữ liệu");
            alert.show();
        }
        System.out.println(tuTuoi + denTuoi + gender + status);
        listNhanKhauBeans = nhanKhauService.statisticNhanKhau(tuTuoi, denTuoi, gender, status, tuNam, denNam);
        setDataTable();
    }
    public void setDataTable() {
        List<NhanKhauModel> listItem = new ArrayList<>();
        listNhanKhauBeans.forEach(nhanKhau -> {
            listItem.add(nhanKhau.getNhanKhauModel());
        });
        observablelistNhanKhau = FXCollections.observableList(listItem);
        idThongKe.setCellValueFactory(new PropertyValueFactory<>("ID"));
        hoTenThongKe.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhThongKe.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        gioiTinhThongKe.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiThongKe.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        bangThongKe.setItems(observablelistNhanKhau);
    }
    // Ham khoi tao cho thongKeController
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        switchScene = new SwitchScene();
        hienThiMacDinh.setVisible(true);
        nhanKhauMode.setVisible(false);
        hoKhauMode.setVisible(false);
        thongKeMode.setVisible(false);
        switchSceneTK = new SwitchSceneTK();
        // Thong ke so lieu
        nhanKhauService = new NhanKhauService();
        gioiTinhList = FXCollections.observableArrayList("Toàn bộ", "Nam", "Nữ");
        tinhTrangList = FXCollections.observableArrayList("Toàn bộ", "Thường trú", "Tạm trú", "Tạm vắng");
        capDoList = FXCollections.observableArrayList("Mầm non", "Cấp 1", "Cấp 2", "Cấp 3", "Lao Động", "Nghỉ hưu");
        chonGioiTinhThongKe.setItems(gioiTinhList);
        chonGioiTinhThongKe.getSelectionModel().selectFirst();
        tinhTrangThongKe.setItems(tinhTrangList);
        tinhTrangThongKe.getSelectionModel().selectFirst();
        capDoBox.setItems(capDoList);
        capDoBox.getSelectionModel().selectFirst();
        theoDoTuoi.setSelected(true);
        doTuoiMode.setVisible(true);
        capDoMode.setVisible(false);
    }
}
