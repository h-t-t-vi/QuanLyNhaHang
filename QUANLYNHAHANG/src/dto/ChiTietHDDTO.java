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
public class ChiTietHDDTO {
    private int MaHD;
    private String MaTP;
    private int Soluong;

    public ChiTietHDDTO() {
    }

    public ChiTietHDDTO(int MaHD, String MaTP, int Soluong) {
        this.MaHD = MaHD;
        this.MaTP = MaTP;
        this.Soluong = Soluong;
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

   
    
}
