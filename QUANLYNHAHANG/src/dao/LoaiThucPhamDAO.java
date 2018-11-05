/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.LoaiThucPhamDTO;
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
public class LoaiThucPhamDAO {

    Scanner sc = new Scanner(System.in);
    ArrayList<LoaiThucPhamDTO> list;
    Connection con = null;
     public static String mess = "";
    Statement stt = null;

    public LoaiThucPhamDAO() {
        list = new ArrayList<LoaiThucPhamDTO>();
        con = DBConfig.getConnection();
    }

    final String SQLCREATE = "INSERT INTO LOAITHUCPHAM VALUES(?,?)";
    final String SQLUPDATE = "UPDATE LOAITHUCPHAM set TenLTP=? WHERE MaLTP=?";
    final String SQLDELETE = "DELETE FROM LOAITHUCPHAM WHERE MaLTP=?";
    final String SQLREADBYALL = "SELECT * FROM LOAITHUCPHAM";

    public boolean create(LoaiThucPhamDTO dto) {
        try {
            con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(SQLCREATE);
            ps.setString(1, dto.getMaLTP());
            ps.setString(2, dto.getTenLTP());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        mess = "Khon the chen thuc pham";
        System.out.println("" + mess);
        return false;
    }

    public ArrayList<LoaiThucPhamDTO> readByALL() {
        try {
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<LoaiThucPhamDTO>();
            }
            stt = con.createStatement();
            ResultSet rs = stt.executeQuery(SQLREADBYALL);
            while (rs.next()) {
                list.add(new LoaiThucPhamDTO(rs.getString(1), rs.getString(2)));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> readTenLTP() {
        ArrayList<String> list1 = new ArrayList<>();
        try {
            con = DBConfig.getConnection();
            if (con != null) {
            }
            String sql = "SELECT TenLTP FROM LOAITHUCPHAM";
            stt = con.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                list1.add(rs.getString(1));
            }
            return list1;
        } catch (SQLException ex) {
            Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> readMTLP() {
        con = DBConfig.getConnection();
        if (con != null) {
        }
        try {
            ArrayList<String> list = new ArrayList<String>();
            String SQL = "SELECT MaLTP FROM LOAITHUCPHAM ";
            stt = con.createStatement();
            ResultSet rs = stt.executeQuery(SQL);
            while (rs.next()) {
                list.add(rs.getString(1));
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
        return null;
    }

    public boolean checkMaLTP(LoaiThucPhamDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM LOAITHUCPHAM WHERE MaLTP=?";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setString(1, dto.getMaLTP());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoaiThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoaiThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public ArrayList<LoaiThucPhamDTO> readByName(String TenLTP) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<LoaiThucPhamDTO> list = new ArrayList<LoaiThucPhamDTO>();
            try {
                String sql = "SELECT * FROM LOAITHUCPHAM WHERE TenLTP LIKE N'%" + TenLTP + "%'";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new LoaiThucPhamDTO(rs.getString(1), rs.getString(2)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(LoaiThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean delete(LoaiThucPhamDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(SQLDELETE);
                ps.setString(1, dto.getMaLTP());
                if (ps.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                mess="Thực phẩm thuộc loại thực phẩm này hiện tại vẫn tồn tại trong menu, không xóa được";
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoaiThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean update(LoaiThucPhamDTO dto) {
        con = DBConfig.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQLUPDATE);
            ps.setString(1, dto.getTenLTP());
            ps.setString(2, dto.getMaLTP());
            if (ps.executeUpdate() > 0) {
                mess = "Cập nhật thành công";
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoaiThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        mess = "Update không thành công";
        return false;
    }

    public String readMaLTP(String TenLTP) {
        con = DBConfig.getConnection();
        if (con != null) {
        }
        try {
            String s = "";
            String SQL = "SELECT MaLTP FROM LOAITHUCPHAM WHERE TenLTP=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, TenLTP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s = rs.getString(1);
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
        return null;
    }
}
