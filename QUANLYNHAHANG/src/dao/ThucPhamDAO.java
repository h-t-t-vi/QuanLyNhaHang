/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ThucPhamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConfig;

/**
 *
 * @author Administrator
 */
public class ThucPhamDAO {

    Scanner sc = new Scanner(System.in);
    Connection con = null;
    public String mess = "";
    Statement stt = null;
    final String SQLCREATE = "INSERT INTO THUCPHAM VALUES(?,?,?,?,?)";
    final String SQLUPDATE = "UPDATE THUCPHAM set TenTP=?, MaLTP=?, Gia=?, Donvt=? WHERE MaTP=?";
    final String SQLDELETE = "DELETE FROM THUCPHAM WHERE MaTP=?";
    final String SQLREADBYALL = "SELECT * FROM THUCPHAM";

    public ThucPhamDAO() {
        con = DBConfig.getConnection();
    }

    public boolean create(ThucPhamDTO dto) {
        try {
            con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(SQLCREATE);
            ps.setString(1, dto.getMaTP());
            ps.setString(2, dto.getTenTP());
            ps.setString(3, dto.getMaLTP());
            ps.setInt(4, dto.getGia());
            ps.setString(5, dto.getDonvt());
            if (ps.executeUpdate() > 0) {
                mess = "them thuc pham thanh cong";
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        mess = "Khon the chen thuc pham";
        return false;
    }

    public ArrayList<ThucPhamDTO> readByAll() {
        ArrayList<ThucPhamDTO> list = null;
        try {
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<ThucPhamDTO>();

                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(SQLREADBYALL);
                while (rs.next()) {
                    list.add(new ThucPhamDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getString(5)));
                }

                return list;
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
        return null;
    }

    public boolean update(ThucPhamDTO dto) {
        con = DBConfig.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQLUPDATE);
            ps.setString(1, dto.getTenTP());
            ps.setString(2, dto.getMaLTP());
            ps.setInt(3, dto.getGia());
            ps.setString(4, dto.getDonvt());
            ps.setString(5, dto.getMaTP());
            if (ps.executeUpdate() > 0) {
                mess = " cap nhat thanh cong";
                return true;
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
        mess = "update khong thanh cong";
        return false;
    }

    public boolean delete(ThucPhamDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(SQLDELETE);
                ps.setString(1, dto.getMaTP());
                if (new ChiTietHDDAO().deleteTP(dto)) {
                    if (ps.executeUpdate() > 0) {
                        return true;
                    }
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
        return false;
    }

    public ThucPhamDTO readByCode(String MaTP) {
        con = DBConfig.getConnection();
        ThucPhamDTO dto = new ThucPhamDTO();
        if (con != null) {
            try {
                String sql = "SELECT * FROM THUCPHAM WHERE MaTP=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, MaTP);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    dto = new ThucPhamDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getString(5));
                }
                return dto;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<ThucPhamDTO> readByName(String TenTP) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<ThucPhamDTO> list = new ArrayList<ThucPhamDTO>();
            try {
                String sql = "SELECT * FROM THUCPHAM WHERE TenTP LIKE N'%" + TenTP + "%'";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new ThucPhamDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getString(5)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<ThucPhamDTO> tkRieng(String TenTP, String MaTP) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<ThucPhamDTO> list = new ArrayList<ThucPhamDTO>();
            try {
                String sql = "SELECT * FROM THUCPHAM WHERE TenTP LIKE N'%" + TenTP + "%'AND MaLTP='" + MaTP + "'";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new ThucPhamDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getString(5)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<ThucPhamDTO> readAllName(String TenLTP) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<ThucPhamDTO> list = new ArrayList<ThucPhamDTO>();
            try {
                String sql = "SELECT * FROM THUCPHAM,LOAITHUCPHAM WHERE LOAITHUCPHAM.MaLTP=THUCPHAM.MaLTP AND TenLTP=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, TenLTP);
//                stt = con.createStatement();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new ThucPhamDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getString(5)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean checkMaTP(ThucPhamDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM THUCPHAM WHERE MaTP=?";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setString(1, dto.getMaTP());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return true;
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

        return false;
    }

    public ArrayList<String> readDonvt() {
        con = DBConfig.getConnection();
        ArrayList<String> list = new ArrayList<String>();
        if (con != null) {
            try {
                String sql = "SELECT DISTINCT Donvt FROM THUCPHAM";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
        }
        return null;
    }

    public String readMaTP(String TenTP) {
        con = DBConfig.getConnection();
        if (con != null) {
            String s = "";
            try {
                String sql = "SELECT MaTP FROM THUCPHAM WHERE TenTP=N'" + TenTP + "'";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    s = rs.getString(1);
                }
                return s;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<String> readAllTenTP() {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<String> list = new ArrayList<String>();
            try {
                String sql = "SELECT TenTP FROM THUCPHAM";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
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
}
