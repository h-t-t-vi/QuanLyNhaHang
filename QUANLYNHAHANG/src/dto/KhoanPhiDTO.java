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
public class KhoanPhiDTO {
    private int ID;
    private String TenLoaiHinh;
    private String LoaiHinh;
    private int GiaTri;
    private Date NgayBatDau;
    private Date NgayKetThuc;

    public KhoanPhiDTO() {
    }

    public KhoanPhiDTO(int ID, String TenLoaiHinh, String LoaiHinh, int GiaTri, Date NgayBatDau, Date NgayKetThuc) {
        this.ID = ID;
        this.TenLoaiHinh = TenLoaiHinh;
        this.LoaiHinh = LoaiHinh;
        this.GiaTri = GiaTri;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the TenLoaiHinh
     */
    public String getTenLoaiHinh() {
        return TenLoaiHinh;
    }

    /**
     * @param TenLoaiHinh the TenLoaiHinh to set
     */
    public void setTenLoaiHinh(String TenLoaiHinh) {
        this.TenLoaiHinh = TenLoaiHinh;
    }

    /**
     * @return the LoaiHinh
     */
    public String getLoaiHinh() {
        return LoaiHinh;
    }

    /**
     * @param LoaiHinh the LoaiHinh to set
     */
    public void setLoaiHinh(String LoaiHinh) {
        this.LoaiHinh = LoaiHinh;
    }

    /**
     * @return the GiaTri
     */
    public int getGiaTri() {
        return GiaTri;
    }

    /**
     * @param GiaTri the GiaTri to set
     */
    public void setGiaTri(int GiaTri) {
        this.GiaTri = GiaTri;
    }

    /**
     * @return the NgayBatDau
     */
    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    /**
     * @param NgayBatDau the NgayBatDau to set
     */
    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    /**
     * @return the NgayKetThuc
     */
    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    /**
     * @param NgayKetThuc the NgayKetThuc to set
     */
    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

   
}
