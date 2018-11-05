/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.KhoanPhiDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConfig;

/**
 *
 * @author Administrator
 */
public class KhoanPhiDAO {

    Scanner sc = new Scanner(System.in);
    Connection con = null;
    public String mess = "";
    Statement stt = null;
    final String SQLCREATE = "INSERT INTO KHOANPHI VALUES(?,?,?,?,?)";
    final String SQLUPDATE = "UPDATE KHOANPHI set TenLoaiHinh=?, LoaiHinh=?, GiaTri=?, NgayBatDau=?, NgayKetThuc=? WHERE ID=?";
    final String SQLDELETE = "DELETE FROM KHOANPHI WHERE ID=?";
    final String SQLREADBYALL = "SELECT * FROM KHOANPHI";

    public KhoanPhiDAO() {
        con = DBConfig.getConnection();
    }

    public boolean create(KhoanPhiDTO dto) {
        try {
            con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(SQLCREATE);
            ps.setString(1, dto.getTenLoaiHinh());
            ps.setString(2, dto.getLoaiHinh());
            ps.setInt(3, dto.getGiaTri());
            java.util.Date d1 = dto.getNgayBatDau();
            java.util.Date d2 = dto.getNgayKetThuc();
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
            String s = df.format(d1);
            String s1 = df.format(d2);
            Date date = Date.valueOf(s);
            Date date1 = Date.valueOf(s1);
//            Date date= new Date(d1.getTime());
//            Date date1= new Date(d2.getTime());
            ps.setDate(4, date);
            ps.setDate(5, date1);
            if (ps.executeUpdate() > 0) {
                mess = "them thanh cong";
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
        mess = "That bai";
        return false;
    }

    public ArrayList<KhoanPhiDTO> readByAll() {
        ArrayList<KhoanPhiDTO> list;
        try {
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<KhoanPhiDTO>();

                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(SQLREADBYALL);
                while (rs.next()) {
                    list.add(new KhoanPhiDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6)));
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

    public boolean update(KhoanPhiDTO dto) {
        con = DBConfig.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQLUPDATE);
            ps.setString(1, dto.getTenLoaiHinh());
            ps.setString(2, dto.getLoaiHinh());
            ps.setInt(3, dto.getGiaTri());
            java.util.Date d1 = dto.getNgayBatDau();
            java.util.Date d2 = dto.getNgayKetThuc();
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
            String s = df.format(d1);
            String s1 = df.format(d2);
            Date date = Date.valueOf(s);
            Date date1 = Date.valueOf(s1);
            ps.setDate(4, date);
            ps.setDate(5, date1);
            ps.setDate(4, date);
            ps.setDate(5, date1);
            ps.setInt(6, dto.getID());
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

    public boolean delete(KhoanPhiDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(SQLDELETE);
                ps.setInt(1, dto.getID());
                if (ps.executeUpdate() > 0) {
                    mess = "xoa thanh cong";
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
        mess = "xoa khong thanh cong";
        return false;
    }

    public ArrayList<String> readLoaiHinhKP() {
        ArrayList<String> list;
        try {
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<String>();
                String sql = "SELECT distinct LoaiHinh FROM KHOANPHI";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(rs.getString(1));
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

    public boolean checkDay(java.util.Date NBD, java.util.Date NKT, String LoaiHinh) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "select * from KHOANPHI where((? BETWEEN NgayBatDau and NgayKetThuc) OR (? between NgayBatDau and NgayKetThuc)) and LoaiHinh=?";
                PreparedStatement ps = con.prepareStatement(sql);
                SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
                String s = df.format(NBD);
                String s1 = df.format(NKT);
                Date date = Date.valueOf(s);
                Date date1 = Date.valueOf(s1);
                ps.setDate(1, date);
                ps.setDate(2, date1);
                ps.setString(3, LoaiHinh);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhoanPhiDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
     public boolean checkTenLH(KhoanPhiDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM KHOANPHI WHERE TenLoaiHinh=?";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setString(1, dto.getTenLoaiHinh());
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
}
