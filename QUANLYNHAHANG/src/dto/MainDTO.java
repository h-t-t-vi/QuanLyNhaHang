/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Administrator
 */
public class MainDTO {
    private String TenTP;
    private int Soluong;
    private String Donvt;
    private int Gia;
    private int Thanhtien;
    private int MaHD;

    public MainDTO() {
    }

    public MainDTO(String TenTP, int Soluong, String Donvt, int Gia, int Thanhtien, int MaHD) {
        this.TenTP = TenTP;
        this.Soluong = Soluong;
        this.Donvt = Donvt;
        this.Gia = Gia;
        this.Thanhtien = Thanhtien;
        this.MaHD = MaHD;
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
     * @return the Soluong
     */
    public int getSoluong() {
        return Soluong;
    }

    /**
     * @param Soluong the Soluong to set
     */
    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    /**
     * @return the Donvt
     */
    public String getDonvt() {
        return Donvt;
    }

    /**
     * @param Donvt the Donvt to set
     */
    public void setDonvt(String Donvt) {
        this.Donvt = Donvt;
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
     * @return the Thanhtien
     */
    public int getThanhtien() {
        return Thanhtien;
    }

    /**
     * @param Thanhtien the Thanhtien to set
     */
    public void setThanhtien(int Thanhtien) {
        this.Thanhtien = Thanhtien;
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

    
}
