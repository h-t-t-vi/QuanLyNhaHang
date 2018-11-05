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
public class BanDTO {
    private String MaBan;
    private String LoaiBan;
    private boolean TinhTrang;

    public BanDTO() {
    }

    public BanDTO(String MaBan, String LoaiBan, boolean TinhTrang) {
        this.MaBan = MaBan;
        this.LoaiBan = LoaiBan;
        this.TinhTrang = TinhTrang;
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
     * @return the LoaiBan
     */
    public String getLoaiBan() {
        return LoaiBan;
    }

    /**
     * @param LoaiBan the LoaiBan to set
     */
    public void setLoaiBan(String LoaiBan) {
        this.LoaiBan = LoaiBan;
    }

    /**
     * @return the TinhTrang
     */
    public boolean isTinhTrang() {
        return TinhTrang;
    }

    /**
     * @param TinhTrang the TinhTrang to set
     */
    public void setTinhTrang(boolean TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
}
