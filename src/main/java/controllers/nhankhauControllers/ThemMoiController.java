package controllers.nhankhauControllers;


import Beans.NhanKhauBean;
import controllers.LoginController;
import controllers.NhanKhauController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.CanCuocCongDanModel;
import models.NhanKhauModel;
import services.SQLServerConnection;

import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ThemMoiController implements Initializable {
    @FXML
    TextField id;
    @FXML
    TextField bietDanhTxb;
    @FXML
    TextField hoTenTxb;
    @FXML
    DatePicker namSinhDateC;
    @FXML
    ComboBox<String> gioiTinhCbb;
    @FXML
    TextField nguyenQuanTxb;
    @FXML
    TextField tonGiaoTxb;
    @FXML
    TextField danTocTxb;
    @FXML
    TextField quocTichTxb;
    @FXML
    TextField soCMTTxb;
    @FXML
    TextField soHoChieuTxb;
    @FXML
    TextField noiThuongTruTxb;
    @FXML
    TextField diaChiHienNayTxb;
    @FXML
    TextField trinhDoHocVanTxb;
    @FXML
    TextField trinhDoChuyenMonTxb;
    @FXML
    TextField bietTiengDanTocTxb;
    @FXML
    TextField ngheNghiepTxb;
    @FXML
    TextField noiLamViecTxb;

    private NhanKhauBean nhanKhauBean;
    ObservableList gioiTinhList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set options up for gioiTinhCB
        gioiTinhList = FXCollections.observableArrayList("Nam", "Nữ");
        gioiTinhCbb.setItems(gioiTinhList);
        gioiTinhCbb.getSelectionModel().selectFirst();

        nhanKhauBean = new NhanKhauBean();
    }

    public void cancel(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    public void create(ActionEvent event){
        if (validateValueInForm()) {
            // tao moi 1 doi tuong nhan khau
            NhanKhauModel temp = this.nhanKhauBean.getNhanKhauModel();
            CanCuocCongDanModel cmt = this.nhanKhauBean.getCanCuocCongDanModel();
            temp.setID(Integer.parseInt(id.getText()));
            temp.setBietDanh(bietDanhTxb.getText());
            temp.setHoTen(hoTenTxb.getText());

            //Convert LocalDate to Date
            temp.setNamSinh(Date.from(namSinhDateC.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            temp.setGioiTinh(gioiTinhCbb.getValue());
            temp.setNguyenQuan(nguyenQuanTxb.getText());
            temp.setTonGiao(tonGiaoTxb.getText());
            temp.setDanToc(danTocTxb.getText());
            temp.setQuocTich(quocTichTxb.getText());
            cmt.setSoCMT(soCMTTxb.getText());
            temp.setSoHoChieu(soHoChieuTxb.getText());
            temp.setNoiThuongTru(noiThuongTruTxb.getText());
            temp.setDiaChiHienNay(diaChiHienNayTxb.getText());
            temp.setTrinhDoHocVan(trinhDoHocVanTxb.getText());
            temp.setTrinhDoChuyenMon(trinhDoChuyenMonTxb.getText());
            temp.setBietTiengDanToc(bietTiengDanTocTxb.getText());
            temp.setNgheNghiep(ngheNghiepTxb.getText());
            temp.setNoiLamViec(noiLamViecTxb.getText());

            try {
                if (addNewPeople(nhanKhauBean)) {
                    Alert addSuccessfullyAlert = new Alert(Alert.AlertType.INFORMATION);
                    addSuccessfullyAlert.setContentText("Thêm thành công!");
                    addSuccessfullyAlert.show();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NhanKhau.fxml"));
                    Parent parent = loader.load();
                    //Test
                    NhanKhauController nhanKhauController = loader.getController();
                    nhanKhauController.refreshData(nhanKhauBean);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Warning");
                errorAlert.setContentText("Có lỗi xảy ra. Vui lòng kiểm tra lại!!");
                errorAlert.show();
            }
        }
    }

    public boolean addNewPeople(NhanKhauBean nhanKhauBean) throws SQLException, ClassNotFoundException{
        NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
        CanCuocCongDanModel chungMinhThu = nhanKhauBean.getCanCuocCongDanModel();
        Connection connection = SQLServerConnection.getSqlConnection();
        // 1 - 19
        String query = "INSERT INTO nhan_khau (ID,hoTen, bietDanh, namSinh, gioiTinh, noiSinh, nguyenQuan, danToc, tonGiao, quocTich, soHoChieu, noiThuongTru, diaChiHienNay, trinhDoHocVan, TrinhDoChuyenMon, bietTiengDanToc, trinhDoNgoaiNgu, ngheNghiep, noiLamViec) values(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, nhanKhau.getID());
        preparedStatement.setString(2, nhanKhau.getHoTen());
        preparedStatement.setString(3, nhanKhau.getBietDanh());
        java.sql.Date namSinh = new java.sql.Date(nhanKhau.getNamSinh().getTime());
        preparedStatement.setDate(4, namSinh);
        preparedStatement.setString(5, nhanKhau.getGioiTinh());
        preparedStatement.setString(6, nhanKhau.getNoiSinh());
        preparedStatement.setString(7, nhanKhau.getNguyenQuan());
        preparedStatement.setString(8, nhanKhau.getDanToc());
        preparedStatement.setString(9, nhanKhau.getTonGiao());
        preparedStatement.setString(10, nhanKhau.getQuocTich());
        preparedStatement.setString(11, nhanKhau.getSoHoChieu());
        preparedStatement.setString(12, nhanKhau.getNoiThuongTru());
        preparedStatement.setString(13, nhanKhau.getDiaChiHienNay());
        preparedStatement.setString(14, nhanKhau.getTrinhDoHocVan());
        preparedStatement.setString(15, nhanKhau.getTrinhDoChuyenMon());
        preparedStatement.setString(16, nhanKhau.getBietTiengDanToc());
        preparedStatement.setString(17, nhanKhau.getTrinhDoNgoaiNgu());
        preparedStatement.setString(18, nhanKhau.getNgheNghiep());
        preparedStatement.setString(19, nhanKhau.getNoiLamViec());

        java.sql.Date createDate = new java.sql.Date(view.Main.calendar.getTime().getTime());


        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int genID = rs.getInt(1);
            String sql = "INSERT INTO chung_minh_thu(ID,idNhanKhau, soCMT)"
                    + " values (?,?,?)";
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, genID);
            prst.setInt(2, genID);
            prst.setString(3, chungMinhThu.getSoCMT());
            prst.execute();
            nhanKhauBean.getListTieuSuModels().forEach(tieuSu -> {
                try {
                    String sql_tieu_su = "INSERT INTO tieu_su(ID, idNhanKhau, tuNgay, denNgay, diaChi, ngheNghiep, noiLamViec) values (?,?, ?, ?, ?, ?, ?)";
                    PreparedStatement preStatement = connection.prepareStatement(sql_tieu_su);
                    preStatement.setInt(1, genID);
                    preStatement.setInt(2, genID);
                    Date tuNgay = new Date(tieuSu.getTuNgay().getTime());
                    preStatement.setDate(3, tuNgay);
                    preStatement.setDate(4, new Date(tieuSu.getDenNgay().getTime()));
                    preStatement.setString(5, tieuSu.getDiaChi());
                    preStatement.setString(6, tieuSu.getNgheNghiep());
                    preStatement.setString(7, tieuSu.getNoiLamViec());
                    preStatement.execute();
                    preStatement.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
            nhanKhauBean.getListGiaDinhModels().forEach(giaDinh -> {
                try {
                    String sql_tieu_su = "INSERT INTO gia_dinh(ID, idNhanKhau, hoTen, namSinh, gioiTinh, quanHeVoiNhanKhau, ngheNghiep, diaChiHienTai) values (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preStatement = connection.prepareStatement(sql_tieu_su);
                    preStatement.setInt(1, genID);
                    preStatement.setInt(2, genID);
                    preStatement.setString(3, giaDinh.getHoTen());
                    preStatement.setDate(4, new Date(giaDinh.getNamSinh().getTime()));
                    preStatement.setString(5, giaDinh.getGioiTinh());
                    preStatement.setString(6, giaDinh.getQuanHeVoiNhanKhau());
                    preStatement.setString(7, giaDinh.getNgheNghiep());
                    preStatement.setString(8, giaDinh.getDiaChiHienTai());
                    preStatement.execute();
                    preStatement.close();
                } catch (Exception e) {
                    System.out.println("controllers.NhanKhauManagerController.AddNewController.addNewPeople()");
                }
            });
        }
        connection.close();
        return true;
    }

    // check cac gia tri duoc nhap vao form
    private boolean validateValueInForm() {
        // check null
        if (hoTenTxb.getText().trim().isEmpty()
                || nguyenQuanTxb.getText().trim().isEmpty()
                || tonGiaoTxb.getText().trim().isEmpty()
                || danTocTxb.getText().trim().isEmpty()
                || quocTichTxb.getText().trim().isEmpty()
                || soCMTTxb.getText().trim().isEmpty()
                || noiThuongTruTxb.getText().trim().isEmpty()
                || diaChiHienNayTxb.getText().trim().isEmpty()){
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Warning!");
            missingFieldAlert.setContentText("Vui lòng nhập hết các trường bắt buộc");
            missingFieldAlert.show();
            return false;
        }
        // check dinh dang so chung minh thu
        try {
            long d = Long.parseLong(soCMTTxb.getText());
        } catch (Exception e) {
            Alert invalidCMTAlert = new Alert(Alert.AlertType.ERROR);
            invalidCMTAlert.setTitle("Warning!");
            invalidCMTAlert.setContentText("Số CMT không thể chứa các ký tự");
            invalidCMTAlert.show();
            return false;
        }
        if (namSinhDateC.getValue().isAfter(java.time.LocalDate.now())){
            System.out.println("lỗi");
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Warning!");
            missingFieldAlert.setContentText("Ngày sinh không chính xác!");
            missingFieldAlert.show();
            return false;
        }
        // kiem tra do dai cmt
        if (soCMTTxb.getText().length() != 9 && soCMTTxb.getText().length() != 12) {
            Alert invalidCMTAlert = new Alert(Alert.AlertType.ERROR);
            invalidCMTAlert.setTitle("Warning!");
            invalidCMTAlert.setContentText("Vui lòng nhập đúng định dạng CMT");
            invalidCMTAlert.show();
            return false;
        }
        return true;
    }

}
