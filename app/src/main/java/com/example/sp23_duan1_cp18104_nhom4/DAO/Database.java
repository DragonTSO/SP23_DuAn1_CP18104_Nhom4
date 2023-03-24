package com.example.sp23_duan1_cp18104_nhom4.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "ACERESTAURANT" , null, 1);
    }
    public  static final String DB_MENU = "create table MENU(" +
            "MaMon INTEGER primary key autoincrement," +
            "TenMon TEXT not null ," +
            "Soluong INTEGER not null ," +
            "Gia INTEGER not null );";



    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(DB_MENU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS MENU");
    }
}
