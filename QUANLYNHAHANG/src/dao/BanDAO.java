/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConfig;

/**
 *
 * @author Administrator
 */
public class BanDAO {

    Connection con = null;
    public String mess = null;
    Statement stt = null;
    final String SQL = "SELECT * FROM BAN";
    final static String SQLCREATE = "INSERT INTO BAN VALUES(?,?,?)";
    final static String SQLUPDATE = "UPDATE BAN SET LoaiBan=?,TinhTrang=? WHERE MaBan=?";
    final static String SQLUPDATETT = "UPDATE BAN SET TinhTrang=? WHERE MaBan=?";
    final static String SQLDELETE = "DELETE FROM BAN WHERE MaBan=?";

    public boolean readTT(String MaBan) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "SELECT TinhTrang FROM BAN WHERE MaBan=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, MaBan);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean create(BanDTO dto) {
        try {
            con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(SQLCREATE);
            ps.setString(1, dto.getMaBan());
            ps.setString(2, dto.getLoaiBan());
            ps.setBoolean(3, dto.isTinhTrang());
            if (ps.executeUpdate() > 0) {
                mess = "them ban thanh cong";
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
        mess = "Khon the chen them ban";
        return false;
    }

    public boolean update(BanDTO dto) {
        con = DBConfig.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQLUPDATE);
            ps.setString(1, dto.getLoaiBan());
            ps.setBoolean(2, dto.isTinhTrang());
            ps.setString(3, dto.getMaBan());
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

    public boolean updateTT(boolean TinhTrang, String MaBan) {
        con = DBConfig.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQLUPDATETT);
            ps.setBoolean(1, TinhTrang);
            ps.setString(2, MaBan);
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

    public boolean delete(BanDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(SQLDELETE);
                ps.setString(1, dto.getMaBan());
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

    public ArrayList<BanDTO> readByAll() {

        ArrayList<BanDTO> list;
        try {
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<BanDTO>();
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(SQL);
                while (rs.next()) {
                    list.add(new BanDTO(rs.getString(1), rs.getString(2), rs.getBoolean(3)));
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
     public ArrayList<BanDTO> readBanTrong(String MaBan) {

        ArrayList<BanDTO> list;
        try {
            String sql = "SELECT * FROM BAN WHERE TinhTrang='false' AND MaBan<>'"+MaBan+"'";
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<BanDTO>();
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new BanDTO(rs.getString(1), rs.getString(2), rs.getBoolean(3)));
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
      public ArrayList<BanDTO> readBanKhongTrong(String MaBan) {

        ArrayList<BanDTO> list;
        try {
            String sql = "SELECT * FROM BAN WHERE TinhTrang='true' AND MaBan<>'"+MaBan+"'";
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<BanDTO>();
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new BanDTO(rs.getString(1), rs.getString(2), rs.getBoolean(3)));
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

    public boolean checkMaBan(BanDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "SELECT * FROM BAN WHERE MaBan=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dto.getMaBan());
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

    public ArrayList<BanDTO> readByName(String MaBan) {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<BanDTO> list = new ArrayList<BanDTO>();
            try {
                String sql = "SELECT * FROM BAN WHERE MaBan LIKE N'%" + MaBan + "%'";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new BanDTO(rs.getString(1), rs.getString(2),
                            rs.getBoolean(3)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(ThucPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
     public ArrayList<String> readAllTTB() {
        ArrayList<String> list;
        try {
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<String>();
                String sql = "SELECT distinct TinhTrang FROM BAN";
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(""+rs.getBoolean(1));
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
}
