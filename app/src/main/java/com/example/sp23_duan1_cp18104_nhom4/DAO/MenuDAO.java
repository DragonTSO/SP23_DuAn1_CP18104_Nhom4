package com.example.sp23_duan1_cp18104_nhom4.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.sp23_duan1_cp18104_nhom4.Database.CreateDatabase;
import com.example.sp23_duan1_cp18104_nhom4.Model.Menu;

import java.util.ArrayList;

public class MenuDAO {
    private Context mContext;
    private SQLiteDatabase database;
    private CreateDatabase db;
    public MenuDAO(Context context){
        this.mContext = context;
        db = new CreateDatabase(mContext);
        database =  db.getWritableDatabase();
    }
    public int Insert(Menu menu){
        ContentValues values = new ContentValues();
        values.put("TenMon",menu.getTenMon());
        values.put("Soluong",menu.getSoluong());
        values.put("Gia",menu.getGia());

        long kq = database.insert("MENU",null,values);
        if(kq<=0){
            return -1;
        }
        return 1;
    }
    public ArrayList<Menu> getAllMenu(){
        Cursor cursor = database.rawQuery("SELECT * FROM MENU",null);
        ArrayList<Menu> list = new ArrayList<>();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new Menu(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3) ));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public int delete(Menu menu){
        int kq = database.delete("MENU","MaMon=?",new String[]{String.valueOf(menu.getMaMon())});
        if(kq<=0){
            return -1;
        }
        return 1;
    }
    public int update(Menu menu){
        ContentValues values = new ContentValues();
        values.put("TenMon",menu.getTenMon());
        values.put("Soluong",menu.getSoluong());
        values.put("Gia",menu.getGia());

        int kq = database.update("MENU",values,"MaMon=?",new String[]{String.valueOf(menu.getMaMon())});
        if(kq<=0){
            return -1;
        }
        return 1;
    }
    public Menu getID(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM MENU WHERE MaMon=?",new String[]{String.valueOf(id)});
        ArrayList<Menu> list = new ArrayList<>();
        cursor.moveToFirst();
        list.add(new Menu(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3) ));
        Menu menu = list.get(0);
        return menu;

    }
}
