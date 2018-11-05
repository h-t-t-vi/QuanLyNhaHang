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
public class ThucPhamDTO {
    private String MaTP;
    private String TenTP;
    private String MaLTP;
    private int Gia;
    private String Donvt;

    public ThucPhamDTO() {
    }

    public ThucPhamDTO(String MaTP, String TenTP, String MaLTP, int Gia, String Donvt) {
        this.MaTP = MaTP;
        this.TenTP = TenTP;
        this.MaLTP = MaLTP;
        this.Gia = Gia;
        this.Donvt = Donvt;
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
     * @return the MaLTP
     */
    public String getMaLTP() {
        return MaLTP;
    }

    /**
     * @param MaLTP the MaLTP to set
     */
    public void setMaLTP(String MaLTP) {
        this.MaLTP = MaLTP;
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

    @Override
    public boolean equals(Object obj) {
        ThucPhamDTO TP =(ThucPhamDTO) obj;
        return this.getMaTP().equals(TP.getMaTP()); 
    }

    @Override
    public String toString() {
        return "MaTP=" + MaTP + ", TenTP=" + TenTP + ", MaLTP="
                + MaLTP + ", Gia=" + Gia + ", Donvt=" + Donvt + '}';
    }
}
