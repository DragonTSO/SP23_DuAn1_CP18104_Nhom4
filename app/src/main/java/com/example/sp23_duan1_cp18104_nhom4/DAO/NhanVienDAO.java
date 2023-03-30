package com.example.sp23_duan1_cp18104_nhom4.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sp23_duan1_cp18104_nhom4.DTO.NhanVien;
import com.example.sp23_duan1_cp18104_nhom4.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    SQLiteDatabase db;
    public NhanVienDAO(Context context) {
        CreateDatabase dbhelper =new CreateDatabase(context);
        db=dbhelper.getWritableDatabase();
    }


    public long insert(NhanVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("viTri",obj.getViTri());
        return db.insert("QLNV",null,values);
    }
    public int update(NhanVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("viTri",obj.getViTri());
        return db.update("QLNV",values,"maNV=?",new String[]{String.valueOf(obj.getMaTV())});
    }
    public int detele(String id){
        return db.delete("QLNV","maNV=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<NhanVien> getData(String sql, String...selectionArgs){
        List<NhanVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            NhanVien obj = new NhanVien();
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maNV"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setViTri(c.getString(c.getColumnIndex("viTri")));
            list.add(obj);
        }
        return list;
    }
    public List<NhanVien> getAll(){
        String sql = "SELECT * FROM QLNV";
        return getData(sql);
    }
    public NhanVien getID(String id)   {
        String sql = "SELECT * FROM  QLNV WHERE maNV=?" ;
        List<NhanVien> list = getData(sql,id);
        return list.get(0);
    }
}
