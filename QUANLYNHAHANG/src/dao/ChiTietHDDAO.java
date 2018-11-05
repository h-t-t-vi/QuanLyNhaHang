/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ChiTietHDDTO;
import dto.ThucPhamDTO;
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
public class ChiTietHDDAO {

    ArrayList<ChiTietHDDTO> list;
    Connection con = null;
    String mess = null;
    Statement stt = null;
    String SQL = "INSERT INTO CHITIETHD VALUES(?,?,?)";

    public ChiTietHDDAO() {
        Connection con = null;
    }

    public boolean create(ChiTietHDDTO dto) {
        try {
            con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, dto.getMaHD());
            ps.setString(2, dto.getMaTP());
            ps.setInt(3, dto.getSoluong());
            if (ps.executeUpdate() > 0) {
                mess = "Chèn thành công";
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
        mess = "Không thể chèn";
        return false;
    }

    public ArrayList<ChiTietHDDTO> readByAll() {
        try {
            con = DBConfig.getConnection();
            String sql = "SELECT * FROM CHITIETHD";
            if (con != null) {
                list = new ArrayList<ChiTietHDDTO>();
                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new ChiTietHDDTO(rs.getInt(1), rs.getString(2), rs.getInt(3)));
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

    public boolean update(ChiTietHDDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            String sql = "UPDATE CHITIETHD SET MaTP=?,Soluong=? WHERE MaHD=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, dto.getMaHD());
                ps.setString(2, dto.getMaTP());
                ps.setInt(3, dto.getSoluong());
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
        }
        mess = "update khong thanh cong";
        return false;
    }

    public boolean delete(ChiTietHDDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            String sql = "DELETE FROM CHITIETHD WHERE MaHD=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, dto.getMaHD());
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
    public boolean deleteTP(ThucPhamDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            String sql = "UPDATE CHITIETHD SET MaTP=null FROM THUCPHAM WHERE THUCPHAM.MaTP=CHITIETHD.MaTP AND MaLTP=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dto.getMaLTP());
                if (ps.executeUpdate() >= 0) {
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

    public boolean readMaTP(String MaTP, int MaHD) {
        con = DBConfig.getConnection();
        ArrayList<String> list = new ArrayList<>();
        if (con != null) {
            try {
                String sql = "SELECT MaTP FROM CHITIETHD WHERE MaHD=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, MaHD);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
                for (String s : list) {
                    if (MaTP.equals(s)) {
                        return true;
                    }
                }
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHDDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHDDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean updateSltang(String MaTP, int MaHD, int Soluong) {
        con = DBConfig.getConnection();
        try {
            String sql = "UPDATE CHITIETHD SET Soluong=Soluong+? WHERE MaTP=? AND MaHD=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Soluong);
            ps.setString(2, MaTP);
            ps.setInt(3, MaHD);
            if (ps.executeUpdate() > 0) {
                mess = " them mon thanh cong thanh cong";
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

    public boolean updateSlgiam(String MaTP, int MaHD, int Soluong) {
        con = DBConfig.getConnection();
        try {
            String sql = "UPDATE CHITIETHD SET Soluong=Soluong-? WHERE MaTP=? AND MaHD=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Soluong);
            ps.setString(2, MaTP);
            ps.setInt(3, MaHD);
            if (ps.executeUpdate() > 0) {
                mess = " them mon thanh cong thanh cong";
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

    public boolean deleteMaTP(String MaTP) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sql = "DELETE FROM CHITIETHD WHERE MaTP=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, MaTP);
                if (ps.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHDDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHDDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return false;
    }
}
