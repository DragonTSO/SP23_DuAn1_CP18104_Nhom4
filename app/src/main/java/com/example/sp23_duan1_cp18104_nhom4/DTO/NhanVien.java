package com.example.sp23_duan1_cp18104_nhom4.DTO;

public class NhanVien {
    private int maNV;
    private String hoTen;
    private String viTri;

    public NhanVien() {
    }

    public int getMaTV() {
        return maNV;
    }

    public void setMaTV(int maTV) {
        this.maNV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public NhanVien(int maNV, String hoTen, String viTri) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.viTri = viTri;
    }
}
