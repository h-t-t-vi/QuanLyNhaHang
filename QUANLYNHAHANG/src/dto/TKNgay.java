/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TKNgay {
    private String MaTP;
    private String TenTP;
    private int SoLuong;
    private int Gia;
    private int ThanhTien;
    private int MaHD;
    private Date NgayLapHD;
    private int TongTien;

    public TKNgay() {
    }

    public TKNgay(String MaTP, String TenTP, int SoLuong, int Gia, int ThanhTien, int MaHD, Date NgayLapHD, int TongTien) {
        this.MaTP = MaTP;
        this.TenTP = TenTP;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.ThanhTien = ThanhTien;
        this.MaHD = MaHD;
        this.NgayLapHD = NgayLapHD;
        this.TongTien = TongTien;
    }

    /**
     * @return the MaTP
     */
    public String getMaTP() {
        return MaTP;
    }

    /**
     * @param MaTP the MaTP to set
     */
    public void setMaTP(String MaTP) {
        this.MaTP = MaTP;
    }

    /**
     * @return the TenTP
     */
    public String getTenTP() {
        return TenTP;
    }

    /**
     * @param TenTP the TenTP to set
     */
    public void setTenTP(String TenTP) {
        this.TenTP = TenTP;
    }

    /**
     * @return the SoLuong
     */
    public int getSoLuong() {
        return SoLuong;
    }

    /**
     * @param SoLuong the SoLuong to set
     */
    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    /**
     * @return the Gia
     */
    public int getGia() {
        return Gia;
    }

    /**
     * @param Gia the Gia to set
     */
    public void setGia(int Gia) {
        this.Gia = Gia;
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
     * @return the TongTien
     */
    public int getTongTien() {
        return TongTien;
    }

    /**
     * @param TongTien the TongTien to set
     */
    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

  
}
