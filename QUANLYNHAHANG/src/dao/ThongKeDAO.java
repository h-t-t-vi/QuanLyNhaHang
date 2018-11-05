/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ThongKeNgay;
import dto.ThongKeDTO;
import gui.FrmDoanhThu;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConfig;

/**
 *
 * @author Administrator
 */
public class ThongKeDAO {

    Connection con = null;

    public ArrayList<ThongKeDTO> readByAll() {
        ArrayList<ThongKeDTO> list = new ArrayList<ThongKeDTO>();
        String sql = "select THUCPHAM.MaTP, TenTP, sum(Soluong), Gia,sum(Soluong)*Gia from CHITIETHD,THUCPHAM,HOADON \n"
                + "WHERE (THUCPHAM.MaTP=CHITIETHD.MaTP)AND(HOADON.MaHD=CHITIETHD.MaHD)AND ((NgayLapHD BETWEEN ? AND ? )OR\n"
                + "(convert(Date,NgayLapHD)=?)OR(convert(Date,NgayLapHD)=?))\n"
                + "GROUP BY THUCPHAM.MaTP,TenTP,Gia  ORDER BY SUM(Soluong) DESC";
        String sql1 = "select THUCPHAM.MaTP, TenTP, sum(Soluong), Gia,sum(Soluong)*Gia from CHITIETHD,THUCPHAM,HOADON \n"
                + "WHERE (THUCPHAM.MaTP=CHITIETHD.MaTP)AND(HOADON.MaHD=CHITIETHD.MaHD)AND (MaLTP=?)AND((NgayLapHD BETWEEN ? AND ? )OR\n"
                + "(convert(Date,NgayLapHD)=?)OR(convert(Date,NgayLapHD)=?))\n"
                + "GROUP BY THUCPHAM.MaTP,TenTP,Gia  ORDER BY SUM(Soluong) DESC";
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                if (FrmDoanhThu.LoaiTP.equals("Toàn bộ")) {
                    PreparedStatement ps = con.prepareStatement(sql);
                    Date date = new Date(FrmDoanhThu.NBD.getTime());
                    Date date1 = new Date(FrmDoanhThu.NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps.setDate(1, date);
                    ps.setDate(2, date1);
                    ps.setDate(3, date2);
                    ps.setDate(4, date3);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        ThongKeDTO dto = new ThongKeDTO();
                        dto.setMaTP(rs.getString(1));
                        dto.setTenTP(rs.getString(2));
                        dto.setSoLuong(rs.getInt(3));
                        dto.setGia(rs.getInt(4));
                        dto.setThanhTien(rs.getInt(5));
                        list.add(dto);
                    }
                } else {
                    String S;
                    if (FrmDoanhThu.LoaiTP.equals("Thức ăn")) {
                        S = "LTP01";
                    } else {
                        S = "LTP02";
                    }
                    PreparedStatement ps1 = con.prepareStatement(sql1);
                    Date date = new Date(FrmDoanhThu.NBD.getTime());
                    Date date1 = new Date(FrmDoanhThu.NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps1.setString(1, S);
                    ps1.setDate(2, date);
                    ps1.setDate(3, date1);
                    ps1.setDate(4, date2);
                    ps1.setDate(5, date3);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next()) {
                        ThongKeDTO dto = new ThongKeDTO();
                        dto.setMaTP(rs.getString(1));
                        dto.setTenTP(rs.getString(2));
                        dto.setSoLuong(rs.getInt(3));
                        dto.setGia(rs.getInt(4));
                        dto.setThanhTien(rs.getInt(5));
                        list.add(dto);
                    }
                }
                return list;

            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public String ngay() {
        con = DBConfig.getConnection();
        if (con != null) {
            String st = null;
            String sql = "SELECT GETDATE()";
            try {
                Statement stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    st = "" + rs.getDate(1);
                }
                return st;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public Date ngayHD() {
        con = DBConfig.getConnection();
        if (con != null) {
            Date st = null;
            String sql = "SELECT CONVERT(Date,NgayLapHD) from HOADON";
            try {
                Statement stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    st = rs.getDate(1);
                }
                return st;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public int readTongTien(String MaLTP, java.util.Date NBD, java.util.Date NKT) {
        con = DBConfig.getConnection();
        if (con != null) {
            int s = 0;
            String sql = "SELECT SUM(Gia*Soluong) FROM THUCPHAM,CHITIETHD,HOADON WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD AND "
                    + "((NgayLapHD BETWEEN ? AND ? )OR\n"
                    + "convert(Date,NgayLapHD)=? OR convert(Date,NgayLapHD)=?)";
            String sql1 = "SELECT SUM(Gia*Soluong) FROM THUCPHAM,CHITIETHD,HOADON WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD AND (MaLTP=?) (NgayLapHD BETWEEN ? AND ? )OR\n"
                    + "(convert(Date,NgayLapHD)=?)OR(convert(Date,NgayLapHD)=?)) ";
            try {
                if (MaLTP.equals("Toàn bộ")) {
                    PreparedStatement ps = con.prepareStatement(sql);
                    Date date = new Date(NBD.getTime());
                    Date date1 = new Date(NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps.setDate(1, date);
                    ps.setDate(2, date1);
                    ps.setDate(3, date2);
                    ps.setDate(4, date3);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        s = rs.getInt(1);
                    }
                    return s;
                } else {
                    String t;
                    if (MaLTP.equals("Thức ăn")) {
                        t = "LTP01";
                    } else {
                        t = "LTP02";
                    }
                    PreparedStatement ps = con.prepareStatement(sql1);
                    Date date = new Date(NBD.getTime());
                    Date date1 = new Date(NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps.setDate(1, date);
                    ps.setDate(2, date1);
                    ps.setString(3, t);
                    ps.setDate(4, date2);
                    ps.setDate(5, date3);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        s = rs.getInt(1);
                    }
                    return s;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return 0;
    }

    public ArrayList<ThongKeDTO> readHD(java.util.Date NBD, java.util.Date NKT) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<ThongKeDTO> list = new ArrayList<ThongKeDTO>();
            String sql = "SELECT HOADON.MaHD,NgayLapHD,sum(Gia*Soluong)FROM THUCPHAM,CHITIETHD,HOADON "
                    + "WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD AND ((NgayLapHD BETWEEN ? AND ? )OR\n"
                    + "(convert(Date,NgayLapHD)=?)OR(convert(Date,NgayLapHD)=?)) "
                    + "GROUP BY HOADON.MaHD,NgayLapHD";
            try {

                PreparedStatement ps = con.prepareStatement(sql);
                Date date = new Date(NBD.getTime());
                Date date1 = new Date(NKT.getTime());
                Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                ps.setDate(1, date);
                ps.setDate(2, date1);
                ps.setDate(3, date2);
                ps.setDate(4, date3);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ThongKeDTO dto = new ThongKeDTO();
                    dto.setMaHD(rs.getInt(1));
                    dto.setNgayLapHD(rs.getDate(2));
                    dto.setTongTien(rs.getInt(3));
                    list.add(dto);
                }
                return list;

            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public ArrayList<ThongKeNgay> readTKHD(java.util.Date NBD, java.util.Date NKT) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<ThongKeNgay> list = new ArrayList<ThongKeNgay>();
            String sql = "SELECT HOADON.MaHD,CONVERT(Date,NgayLapHD) as NgayLapHD,sum(Gia*Soluong)as ThanhTien\n"
                    + "FROM THUCPHAM,CHITIETHD,HOADON\n"
                    + "WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD\n"
                    + "AND ((CONVERT(Date,NgayLapHD) BETWEEN ? AND ?)OR convert(Date,NgayLapHD)=? OR convert(Date,NgayLapHD)=?)\n"
                    + "GROUP BY HOADON.MaHD,CONVERT(Date,NgayLapHD)";
            try {

                PreparedStatement ps = con.prepareStatement(sql);
                Date date = new Date(NBD.getTime());
                Date date1 = new Date(NKT.getTime());
                Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                ps.setDate(1, date);
                ps.setDate(2, date1);
                ps.setDate(3, date2);
                ps.setDate(4, date3);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ThongKeNgay dto = new ThongKeNgay();
                    dto.setMaHD(rs.getInt(1));
                    dto.setNgayLap(rs.getDate(2));
                    dto.setThanhTien(rs.getInt(3));
                    list.add(dto);
                }
                return list;

            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public int readGiamgia(String MaLTP, java.util.Date NBD, java.util.Date NKT) {
        con = DBConfig.getConnection();
        if (con != null) {
            int s = 0;
            String sql = "SELECT SUM(GiamGia) FROM THUCPHAM,CHITIETHD,HOADON WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD "
                    + "AND((NgayLapHD BETWEEN ? AND ?) OR convert(Date,NgayLapHD)=? OR convert(Date,NgayLapHD)=?)";
            String sql1 = "SELECT SUM(GiamGia) FROM THUCPHAM,CHITIETHD,HOADON WHERE (THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD) AND (MaLTP=?) AND ((NgayLapHD BETWEEN ? AND ?) OR convert(Date,NgayLapHD)=? OR convert(Date,NgayLapHD)=?)";
            try {
                if (MaLTP.equals("Toàn bộ")) {
                    PreparedStatement ps = con.prepareStatement(sql);
                    Date date = new Date(NBD.getTime());
                    Date date1 = new Date(NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps.setDate(1, date);
                    ps.setDate(2, date1);
                    ps.setDate(3, date2);
                    ps.setDate(4, date3);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        s = rs.getInt(1);
                    }
                    return s;
                } else {
                    String S;
                    if (MaLTP.equals("Thức ăn")) {
                        S = "LTP01";
                    } else {
                        S = "LTP02";
                    }
                    PreparedStatement ps = con.prepareStatement(sql1);
                    Date date = new Date(NBD.getTime());
                    Date date1 = new Date(NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps.setDate(1, date);
                    ps.setDate(2, date1);
                    ps.setString(3, S);
                    ps.setDate(4, date2);
                    ps.setDate(5, date3);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        s = rs.getInt(1);
                    }
                    return s;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return 0;
    }

    public int readPhiDV(String MaLTP, java.util.Date NBD, java.util.Date NKT) {
        con = DBConfig.getConnection();
        if (con != null) {
            int s = 0;
            String sql = "SELECT SUM(PhiDV) FROM THUCPHAM,CHITIETHD,HOADON WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD "
                    + "AND((NgayLapHD BETWEEN ? AND ?) OR convert(Date,NgayLapHD)=? OR convert(Date,NgayLapHD)=?)";
            String sql1 = "SELECT SUM(PhiDV) FROM THUCPHAM,CHITIETHD,HOADON WHERE (THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD) AND (MaLTP=?) AND ((NgayLapHD BETWEEN ? AND ?) OR convert(Date,NgayLapHD)=? OR convert(Date,NgayLapHD)=?)";
            try {
                if (MaLTP.equals("Toàn bộ")) {
                    PreparedStatement ps = con.prepareStatement(sql);
                    Date date = new Date(NBD.getTime());
                    Date date1 = new Date(NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps.setDate(1, date);
                    ps.setDate(2, date1);
                    ps.setDate(3, date2);
                    ps.setDate(4, date3);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        s = rs.getInt(1);
                    }
                    return s;
                } else {
                    String S;
                    if (MaLTP.equals("Thức ăn")) {
                        S = "LTP01";
                    } else {
                        S = "LTP02";
                    }
                    PreparedStatement ps = con.prepareStatement(sql1);
                    Date date = new Date(NBD.getTime());
                    Date date1 = new Date(NKT.getTime());
                    Date date2 = new Date(FrmDoanhThu.NBD.getTime());
                    Date date3 = new Date(FrmDoanhThu.NKT.getTime());
                    ps.setDate(1, date);
                    ps.setDate(2, date1);
                    ps.setString(3, S);
                    ps.setDate(4, date2);
                    ps.setDate(5, date3);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        s = rs.getInt(1);
                    }
                    return s;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return 0;
    }

}
