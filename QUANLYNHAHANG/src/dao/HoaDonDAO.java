/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.HoaDonDTO;
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
public class HoaDonDAO {
    Connection con = null;
    public String mess = "";
    Statement stt = null;
    final static String SQLCREATE = "INSERT INTO HOADON (MaBan,TinhTrangHD,NgayLapHD,TenTK) VALUES(?,'False',GETDATE(),?)";
    final static String SQLREADBYALL = "SELECT * FROM HOADON";
    final static String SQLUPDATE = "UPDATE HOADON SET MaBan=?,TinhTrang=?,NgayLapHD=?,ID=? WHERE MaHD=?";
    final static String SQLDELETE = "DELETE FROM HOADON WHERE MaHD=?";

    public HoaDonDAO() {
        con = DBConfig.getConnection();
    }

    public boolean create(HoaDonDTO dto) {
        try {
            con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(SQLCREATE);
            ps.setString(1, dto.getMaBan());
            ps.setString(2, dto.getTenTK());
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
        mess = "Khon the them";
        return false;
    }

    public ArrayList<HoaDonDTO> readByAll() {
        ArrayList<HoaDonDTO> list;
        try {
            con = DBConfig.getConnection();
            if (con != null) {
                list = new ArrayList<HoaDonDTO>();

                stt = con.createStatement();
                ResultSet rs = stt.executeQuery(SQLREADBYALL);
                while (rs.next()) {
                    list.add(new HoaDonDTO(rs.getInt(1), rs.getString(2), rs.getBoolean(3),
                            rs.getDate(4), rs.getString(5)));
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

    public boolean update(HoaDonDTO dto) {
        con = DBConfig.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQLUPDATE);
            ps.setString(1, dto.getMaBan());
            ps.setBoolean(2, dto.isTinhTrangHD());
            ps.setDate(3, dto.getNgayLapHD());
            ps.setString(4, dto.getTenTK());
            ps.setInt(5, dto.getMaHD());
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
     public boolean updateBan(String MaBan) {
        con = DBConfig.getConnection();
        try {
            String sql = "UPDATE HOADON SET MaBan=null WHERE MaBan=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaBan);
            if (ps.executeUpdate()>= 0) {
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

    public boolean delete(HoaDonDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(SQLDELETE);
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


     public boolean updateTT(boolean TTHD,int MaHD) {
        con = DBConfig.getConnection();
        try {
            String sql="UPDATE HOADON SET TinhTrangHD=? WHERE MaHD=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, TTHD);
            ps.setInt(2,MaHD);
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
    public boolean readTTHD(int MaHD){
        con=DBConfig.getConnection();
        if(con!=null){
            try {
                String sql="SELECT TinhTrangHD FROM HOADON WHERE MaHD=?";
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setInt(1, MaHD);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getBoolean(1))
                        return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
