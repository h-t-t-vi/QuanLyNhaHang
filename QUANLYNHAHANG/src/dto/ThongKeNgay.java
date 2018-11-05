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
public class ThongKeNgay {
    private int MaHD;
    private Date NgayLap;
    private int ThanhTien;

    public ThongKeNgay() {
    }

    public ThongKeNgay(int MaHD, Date NgayLap, int ThanhTien) {
        this.MaHD = MaHD;
        this.NgayLap = NgayLap;
        this.ThanhTien = ThanhTien;
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
     * @return the NgayLap
     */
    public Date getNgayLap() {
        return NgayLap;
    }

    /**
     * @param NgayLap the NgayLap to set
     */
    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    /**
     * @return the ThanhTien
     */
    public int getThanhTien() {
        return ThanhTien;
    }

    /**
     * @param ThanhTien the ThanhTien to set
     */
    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
