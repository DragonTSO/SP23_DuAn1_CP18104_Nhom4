package com.example.sp23_duan1_cp18104_nhom4.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    public CreateDatabase(@Nullable Context context) {
        super(context, "zzzz", null, 1);
    }

    public static String TBL_CHITIETDONDAT = "CHITIETDONDAT";
    public static String TBL_BAN = "BAN";
    public static String TBL_DONDAT = "DONDAT";
    //Bảng bàn
    public static String TBL_BAN_MABAN = "MABAN";
    public static String TBL_BAN_TENBAN = "TENBAN";
    public static String TBL_BAN_TINHTRANG = "TINHTRANG";
    //Bảng đơn đặt
    public static String TBL_DONDAT_MADONDAT = "MADONDAT";
    public static String TBL_DONDAT_NGAYDAT = "NGAYDAT";
    public static String TBL_DONDAT_TINHTRANG = "TINHTRANG";
    public static String TBL_DONDAT_TONGTIEN = "TONGTIEN";
    public static String TBL_DONDAT_MABAN = "MABAN";
    //bảng menu
    public  static final String DB_MENU=" create table MENU (" +
            "MaMon INTEGER primary key autoincrement," +
            "TenMon TEXT not null ," +
            "Soluong INTEGER not null ," +
            "Gia INTEGER not null );";
    //bảng nhân viên
    public static final String DB_QLNV = " CREATE TABLE QLNV(" +
            "maNV INTEGER primary key autoincrement," +
            "hoTen TEXT not null, " +
            "viTri TEXT not null);";
    //Bảng chi tiết đơn đặt
    public static String TBL_CHITIETDONDAT_MADONDAT = "MADONDAT";
    public static String TBL_CHITIETDONDAT_MAMON = "MAMON";
    public static String TBL_CHITIETDONDAT_SOLUONG = "SOLUONG";



    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblBAN = "CREATE TABLE " +TBL_BAN+ " ( " +TBL_BAN_MABAN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TBL_BAN_TENBAN+ " TEXT, " +TBL_BAN_TINHTRANG+ " TEXT )";
        String tblDONDAT = "CREATE TABLE " +TBL_DONDAT+ " ( " +TBL_DONDAT_MADONDAT+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TBL_DONDAT_MABAN+ " INTEGER, " + " INTEGER, " +TBL_DONDAT_NGAYDAT+ " TEXT, "+TBL_DONDAT_TONGTIEN+" TEXT,"
                +TBL_DONDAT_TINHTRANG+ " TEXT )" ;
        String tblCHITIETDONDAT = "CREATE TABLE " +TBL_CHITIETDONDAT+ " ( " +TBL_CHITIETDONDAT_MADONDAT+ " INTEGER, "
                +TBL_CHITIETDONDAT_MAMON+ " INTEGER, " +TBL_CHITIETDONDAT_SOLUONG+ " INTEGER, "
                + " PRIMARY KEY ( " +TBL_CHITIETDONDAT_MADONDAT+ "," +TBL_CHITIETDONDAT_MAMON+ "))";
        db.execSQL(tblBAN);
        db.execSQL(tblDONDAT);
        db.execSQL(DB_MENU);
        db.execSQL(DB_QLNV);
        db.execSQL(tblCHITIETDONDAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MENU");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS QLNV");

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
