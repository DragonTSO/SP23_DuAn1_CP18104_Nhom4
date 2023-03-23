package com.example.sp23_duan1_cp18104_nhom4.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
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

    public CreateDatabase(@Nullable Context context) {
        super(context, "ACE RESTAURANT", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblBAN = "CREATE TABLE " +TBL_BAN+ " ( " +TBL_BAN_MABAN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TBL_BAN_TENBAN+ " TEXT, " +TBL_BAN_TINHTRANG+ " TEXT )";
        String tblDONDAT = "CREATE TABLE " +TBL_DONDAT+ " ( " +TBL_DONDAT_MADONDAT+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TBL_DONDAT_MABAN+ " INTEGER, " + " INTEGER, " +TBL_DONDAT_NGAYDAT+ " TEXT, "+TBL_DONDAT_TONGTIEN+" TEXT,"
                +TBL_DONDAT_TINHTRANG+ " TEXT )" ;

        db.execSQL(tblBAN);
        db.execSQL(tblDONDAT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
