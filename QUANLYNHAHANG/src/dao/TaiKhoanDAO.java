/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TaiKhoanDTO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;
import util.DBConfig;

/**
 *
 * @author Administrator
 */
public class TaiKhoanDAO {
    ArrayList<TaiKhoanDTO> list = new ArrayList<>();
    Connection con = null;
    public String mess = ""; 
    Statement stt;
    public boolean isLogin(String user, String pass) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sqlIsLogin = "SELECT * FROM TAIKHOAN WHERE TenTK = ? AND MatKhau = ?";
                PreparedStatement pst = con.prepareStatement(sqlIsLogin);
                pst.setString(1, user);
                pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        mess = "Sai tên tài khoản hoặc mật khẩu";
        return false;
    }

    public boolean updateAcc(TaiKhoanDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sqlUpdate = "UPDATE TAIKHOAN SET LoaiTK = ?, MatKhau = ?  WHERE TenTK = ?";
                PreparedStatement pst = con.prepareStatement(sqlUpdate);
                pst.setString(1, dto.getLoaiTK());
                pst.setString(2, dto.getMatKhau());
                pst.setString(3, dto.getTenTK());
                if (pst.executeUpdate() > 0) {
                    mess = "Cập nhật thành công";
                    return true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        mess = "Tài khoản không tồn tại";
        return false;
    }

    public boolean createAcc(TaiKhoanDTO dto) {
        con = DBConfig.getConnection();
        if (con != null) {

            try {
                String sqlCreate = "INSERT INTO TAIKHOAN values(?,?,?)";
                PreparedStatement ps = con.prepareStatement(sqlCreate);
                ps.setString(1, dto.getTenTK());
                ps.setString(2, dto.getLoaiTK());
                ps.setString(3, dto.getMatKhau());
                if (ps.executeUpdate() > 0) {
                    mess = "Tạo tài khoản thành công.";
                    return true;
                }
            } catch (SQLException ex) {
                //Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        mess = "Tài khoản đã tồn tại";
        return false;
    }

    public ArrayList<TaiKhoanDTO> readByAll() {
        TaiKhoanDTO dto = new TaiKhoanDTO();
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sqlReadByAll = "SELECT * FROM TAIKHOAN";
                PreparedStatement pst = con.prepareStatement(sqlReadByAll);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new TaiKhoanDTO(rs.getString(1), rs.getString(2), rs.getString(3)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    public ArrayList<String> readAllLTP() {
        con = DBConfig.getConnection();
        if (con != null) {
            ArrayList<String> list = new ArrayList<String>();
            try {
                String sql = "SELECT LoaiTK FROM TAIKHOAN";
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


    public boolean deleteAcc(String user) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sqlDelete = "DELETE FROM TAIKHOAN where TenTK = ?";
                PreparedStatement pst = con.prepareStatement(sqlDelete);
                pst.setString(1, user);
                if (pst.executeUpdate() > 0) {
                    mess = "Xóa thành công";
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        mess = "Tài khoản không tồn tại";
        return false;
    }

    public ArrayList<TaiKhoanDTO> readByName(String tenTK) {
        con = DBConfig.getConnection();
        if (con != null) {
            try {
                String sqlReadByName = "SELECT * FROM TAIKHOAN WHERE TenTK LIKE '%"+ tenTK +"%'";
                Statement st = con.createStatement();                
                ResultSet rs = st.executeQuery(sqlReadByName);
                while (rs.next()) {
                    list.add(new TaiKhoanDTO(rs.getString(1), rs.getString(2), rs.getString(3)));
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;
    }

}
