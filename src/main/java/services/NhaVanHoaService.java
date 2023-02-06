package services;

import models.ChoThueNVHModel;
import models.SuDungNVHModel;
import models.ThietBiNVHModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhaVanHoaService {
    public  List<SuDungNVHModel> getListSuDung(){
        List<SuDungNVHModel> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT ngaySuDung, mucDich, hoTen, sd.ghiChu FROM nhan_khau as nk, suDungNVH as sd \n" +
                    " WHERE  nk.ID= sd.IDNguoiLap\n" +
                    " ORDER BY ngaySuDung desc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int stt =1;
            while (rs.next()){
                SuDungNVHModel sdNVHModel = new SuDungNVHModel();
                sdNVHModel.setStt(stt++);
                sdNVHModel.setNgaySuDung(rs.getDate("ngaySuDung"));
                sdNVHModel.setMucDich(rs.getString("mucDich"));
                sdNVHModel.setNguoiLap(rs.getString("hoTen"));
                sdNVHModel.setGhiChu(rs.getString("ghiChu"));
                list.add(sdNVHModel);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;

    }
    public  List<ChoThueNVHModel> getListChoThue(){
        List<ChoThueNVHModel> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT ngayChoThue, mucDich, hoTen, thue.ghiChu,phi, [nguoi-toChucThue] FROM nhan_khau as nk, cho_thue_NVH as thue \n" +
                    "WHERE  nk.ID= thue.IDNguoiChoThue  \n" +
                    "ORDER BY ngayChoThue desc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int stt=1;
            while (rs.next()){
                ChoThueNVHModel choThueNVHModel = new ChoThueNVHModel();
                choThueNVHModel.setStt(stt++);
                choThueNVHModel.setNgaySuDung(rs.getDate("ngayChoThue"));
                choThueNVHModel.setMucDich(rs.getString("mucDich"));
                choThueNVHModel.setNguoiLap(rs.getString("hoTen"));
                choThueNVHModel.setGhiChu(rs.getString("ghiChu"));
                choThueNVHModel.setNguoiThue(rs.getString("nguoi-toChucThue"));
                choThueNVHModel.setPhi(rs.getInt("phi"));
                list.add(choThueNVHModel);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;

    }
    public List<ThietBiNVHModel> getAllListThietBi() {
        List<ThietBiNVHModel> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getSqlConnection();
            String query = "SELECT * FROM CSVC ORDER BY thietBi, tinhTrang";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i =1;
            while (rs.next()){

                ThietBiNVHModel thietBiNVHModel = new ThietBiNVHModel();
                thietBiNVHModel.setSqlID(rs.getInt("ID"));
                thietBiNVHModel.setStt(i++);
                thietBiNVHModel.setNgayCapNhat(rs.getDate("ngayCapNhat"));
                thietBiNVHModel.setTenThietBi(rs.getString("thietBi"));
                thietBiNVHModel.setSoLuong(rs.getInt("soLuong"));
                thietBiNVHModel.setGhiChu(rs.getString("ghiChu"));
                thietBiNVHModel.setTrangThai(rs.getString("tinhTrang"));

                list.add(thietBiNVHModel);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


}