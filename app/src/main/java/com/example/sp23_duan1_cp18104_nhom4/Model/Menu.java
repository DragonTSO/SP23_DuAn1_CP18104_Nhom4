package com.example.sp23_duan1_cp18104_nhom4.Model;

public class Menu {
    private int MaMon;
    private  String TenMon;
    private int Soluong;
    private int  Gia;

    public Menu(int maMon, String tenMon, int soluong, int gia) {
        MaMon = maMon;
        TenMon = tenMon;
        Soluong = soluong;
        Gia = gia;
    }

    public Menu() {
    }

    public int getMaMon() {
        return MaMon;
    }

    public void setMaMon(int maMon) {
        MaMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }
}
