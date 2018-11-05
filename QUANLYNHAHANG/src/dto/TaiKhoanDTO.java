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
public class TaiKhoanDTO {
    private String TenTK;
    private String LoaiTK;
    private String MatKhau;
    

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String TenTK, String LoaiTK, String MatKhau) {
        this.TenTK = TenTK;
        this.LoaiTK = LoaiTK;
        this.MatKhau = MatKhau;
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

    /**
     * @return the LoaiTK
     */
    public String getLoaiTK() {
        return LoaiTK;
    }

    /**
     * @param LoaiTK the LoaiTK to set
     */
    public void setLoaiTK(String LoaiTK) {
        this.LoaiTK = LoaiTK;
    }

    /**
     * @return the MatKhau
     */
    public String getMatKhau() {
        return MatKhau;
    }

    /**
     * @param MatKhau the MatKhau to set
     */
    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    
   
}
