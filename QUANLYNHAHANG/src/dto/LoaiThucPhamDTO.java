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
public class LoaiThucPhamDTO {
    private String MaLTP;
    private String TenLTP;

    public LoaiThucPhamDTO() {
    }

    public LoaiThucPhamDTO(String MaLTP, String TenLTP) {
        this.MaLTP = MaLTP;
        this.TenLTP = TenLTP;
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
     * @return the TenLTP
     */
    public String getTenLTP() {
        return TenLTP;
    }

    /**
     * @param TenLTP the TenLTP to set
     */
    public void setTenLTP(String TenLTP) {
        this.TenLTP = TenLTP;
    }
    
    
}
