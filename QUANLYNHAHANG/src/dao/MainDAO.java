/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ChiTietHDDTO;
import dto.HoaDonDTO;
import dto.MainDTO;
import gui.DlgLogin;
import gui.FrmMain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.DBConfig;

/**
 *
 * @author Administrator
 */
public class MainDAO {

    Connection con = null;
    Statement stt = null;
    public String mess = "";

    public ArrayList<MainDTO> readCTHD(String maban) {
        String sql = "SELECT TenTP,Soluong,Donvt,Gia,(Gia*Soluong)AS ThanhTien,HOADON.MaHD FROM THUCPHAM,CHITIETHD,HOADON WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD AND MaBan=? AND TinhTrangHD='False'";
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<MainDTO> list = new ArrayList<MainDTO>();
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maban);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new MainDTO(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHDDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<String> readTenTP(String TenLTP) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<String> list = new ArrayList<String>();
            try {
                String sql = "SELECT TenTP FROM THUCPHAM,LOAITHUCPHAM WHERE LOAITHUCPHAM.MaLTP=THUCPHAM.MaLTP AND TenLTP=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, TenLTP);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean thaydoi(String MaBanMoi, int MaHDCu, int MaHDMoi) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                ChiTietHDDTO d = new ChiTietHDDTO();
                ChiTietHDDAO dao = new ChiTietHDDAO();
                HoaDonDTO dt = new HoaDonDTO();
                dt.setMaHD(MaHDCu);
                d.setMaHD(MaHDCu);
                String sql = "SELECT * FROM CHITIETHD WHERE MaHD=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, MaHDCu);
                ArrayList<ChiTietHDDTO> list = new ArrayList<ChiTietHDDTO>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new ChiTietHDDTO(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
                for (ChiTietHDDTO dto : list) {
                    if (new ChiTietHDDAO().readMaTP(dto.getMaTP(), MaHDMoi)) {
                        if (new ChiTietHDDAO().updateSltang(dto.getMaTP(), MaHDMoi, dto.getSoluong()));
                    } else {
                        dto.setMaHD(MaHDMoi);
                        dao.create(dto);
                    }
                }
                new ChiTietHDDAO().delete(d);
                new HoaDonDAO().delete(dt);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(MainDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public void goiMon(String mon, String MaBan, int Soluong) {
        String MaTP = new ThucPhamDAO().readMaTP(mon);
        int MaHD = new MainDAO().readMaHD(MaBan);
        ChiTietHDDTO dto = new ChiTietHDDTO();
        dto.setMaHD(MaHD);
        dto.setMaTP(MaTP);
        dto.setSoluong(Soluong);
        if (new ChiTietHDDAO().readMaTP(MaTP, MaHD)) {
            if (new ChiTietHDDAO().updateSltang(MaTP, MaHD, Soluong)) {
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi");
            }
        } else {
            if (new ChiTietHDDAO().create(dto)) {
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi");
            }

        }
    }

    public void giamMon(String TenTP, int Soluong, String MaBan) {
        String MaTP = new ThucPhamDAO().readMaTP(TenTP);
        int MaHD = new MainDAO().readMaHD(MaBan);
        ChiTietHDDTO dto = new ChiTietHDDTO();
        dto.setMaHD(MaHD);
        dto.setMaTP(MaTP);
        dto.setSoluong(Soluong);
        if (new ChiTietHDDAO().readMaTP(MaTP, MaHD)) {
            if (new ChiTietHDDAO().updateSlgiam(MaTP, MaHD, Soluong)) {
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hóa đơn không có món này!!! ");
            return;
        }
    }

    public void xoaMon(String TenTP, String MaBan) {
        String MaTP = new ThucPhamDAO().readMaTP(TenTP);
        int MaHD = new MainDAO().readMaHD(MaBan);
        if (new ChiTietHDDAO().readMaTP(MaTP, MaHD)) {
            if (new ChiTietHDDAO().deleteMaTP(MaTP)) {
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi");
            }
        }
    }

    public boolean setTTHD(String MaBan) {
        int MaHD = new MainDAO().readMaHD(MaBan);
        boolean b = new HoaDonDAO().readTTHD(MaHD);
        return b;
    }

    public int readMaHD(String MaBan) {
        con = DBConfig.getConnection();
        if (con != null) {
            int s = 0;
            String sql = "SELECT MaHD FROM HOADON, BAN WHERE HOADON.MaBan=BAN.MaBan AND BAN.MaBan=? AND BAN.TinhTrang='True'";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, MaBan);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    s = rs.getInt(1);
                }
                return s;
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

    public boolean checkTTB(String MaBan) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "SELECT TinhTrang FROM BAN WHERE MaBan=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, MaBan);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getBoolean(1)) {
                        return true;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    public boolean chuyenBan(String MaBanMoi, int MaHD) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "UPDATE HOADON SET MaBan=? WHERE MaHD=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, MaBanMoi);
                ps.setInt(2, MaHD);
                if (ps.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean moBan(boolean TTB, String MaBan) {
        if (TTB) {
            JOptionPane.showMessageDialog(null, "Bàn đã mở ");
        } else if (new BanDAO().updateTT(true, MaBan)) {
            HoaDonDTO dto = new HoaDonDTO();
            dto.setMaBan(MaBan);
            dto.setTenTK(DlgLogin.nvien);
            if (new HoaDonDAO().create(dto)) {
                return true;
            }
        }
        mess = "Mở bàn không thành công";
        return false;
    }

    public boolean xoaBAn(ChiTietHDDTO dt, HoaDonDTO dto, String MaBan) {
        if (new ChiTietHDDAO().delete(dt)) {
            if (new HoaDonDAO().delete(dto)) {
                if (new BanDAO().updateTT(false, MaBan)) {
                    return true;
                }
            }
        }
        if (new HoaDonDAO().delete(dto)) {
            if (new BanDAO().updateTT(false, MaBan)) {
                return true;
            }
        } else {
            new BanDAO().updateTT(false, MaBan);
            return true;
        }
        return false;
    }

    public int readTongTien(String MaBan) {
        con = DBConfig.getConnection();
        if (con != null) {
            int s = 0;
            String sql = "SELECT SUM(Gia*Soluong) FROM THUCPHAM,CHITIETHD,HOADON WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND CHITIETHD.MaHD=HOADON.MaHD AND MaBan=? AND TinhTrangHD='False'";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, MaBan);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    s = rs.getInt(1);
                }
                return s;
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

    public String ngay() {
        con = DBConfig.getConnection();
        if (con != null) {
            String st = null;
            String sql = "SELECT GETDATE()";
            try {
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    st = "" + rs.getTime(1) + "_" + rs.getDate(1);
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

    public String ngayVao(int MaHD) {
        con = DBConfig.getConnection();
        if (con != null) {
            String st = null;
            String sql = "SELECT NgayLapHD FROM HOADON WHERE MaHD= ? AND TinhTrangHD='False'";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, MaHD);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    st = "" + rs.getTime(1) + "_" + rs.getDate(1);
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
        return "N";
    }

    public int khoanPhi(String LoaiHinh) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "select GiaTri from KHOANPHI where (GETDATE() BETWEEN NgayBatDau AND NgayKetThuc )AND (LoaiHinh=N'" + LoaiHinh + "')";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public boolean updateKhoanphi(int MaHD, float Phi, float GiamGia) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "UPDATE HOADON SET PhiDV=?, GiamGia=? WHERE MaHD=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setFloat(1, Phi);
                ps.setFloat(2, GiamGia);
                ps.setInt(3, MaHD);
                if (ps.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
