/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class HoaDonDTO {
    private int MaHD;
    private String MaBan;
    private boolean TinhTrangHD;
    private Date NgayLapHD;
    private String TenTK;

    public HoaDonDTO() {
    }

    public HoaDonDTO(int MaHD, String MaBan, boolean TinhTrangHD, Date NgayLapHD, String TenTK) {
        this.MaHD = MaHD;
        this.MaBan = MaBan;
        this.TinhTrangHD = TinhTrangHD;
        this.NgayLapHD = NgayLapHD;
        this.TenTK = TenTK;
    }

    /**
     * @return the MaHD
     */
    public int getMaHD() {
        return MaHD;
    }

    /**
     * @param MaHD the MaHD to set
     */
    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    /**
     * @return the MaBan
     */
    public String getMaBan() {
        return MaBan;
    }

    /**
     * @param MaBan the MaBan to set
     */
    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    /**
     * @return the TinhTrangHD
     */
    public boolean isTinhTrangHD() {
        return TinhTrangHD;
    }

    /**
     * @param TinhTrangHD the TinhTrangHD to set
     */
    public void setTinhTrangHD(boolean TinhTrangHD) {
        this.TinhTrangHD = TinhTrangHD;
    }

    /**
     * @return the NgayLapHD
     */
    public Date getNgayLapHD() {
        return NgayLapHD;
    }

    /**
     * @param NgayLapHD the NgayLapHD to set
     */
    public void setNgayLapHD(Date NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }

    /**
     * @return the TenTK
     */
    public String getTenTK() {
        return TenTK;
    }

    /**
     * @param TenTK the TenTK to set
     */
    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }
    
    
}
