package com.example.sp23_duan1_cp18104_nhom4.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sp23_duan1_cp18104_nhom4.DTO.NhanVien;
import com.example.sp23_duan1_cp18104_nhom4.Fragment.NhanVienFragment;
import com.example.sp23_duan1_cp18104_nhom4.R;

import java.util.ArrayList;


public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
private Context context;
NhanVienFragment fragment;
private ArrayList<NhanVien> list;
TextView tvMaNV,tvTenNV,tvVitri;
ImageView imgDel,imgFix;


    public NhanVienAdapter(@NonNull Context context, @NonNull ArrayList<NhanVien> list) {
        super(context, 0,list);
        this.context=context;
        this.fragment=fragment;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View v =convertView;
    if (v ==null){
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v= inflater.inflate(R.layout.nhan_vien_item,null);
    }
    final NhanVien item = list.get(position);
    if (item !=null){
     tvMaNV=v.findViewById(R.id.tvMaNV);
     tvMaNV.setText("Mã nhân viên:"+item.getMaTV());

     tvTenNV =v.findViewById(R.id.tvTenNV);
     tvTenNV.setText("Tên nhân viên: "+item.getHoTen());

     tvVitri = v.findViewById(R.id.tvViTri);
     tvVitri.setText("Vị Trí: "+item.getViTri());

     imgDel=v.findViewById(R.id.imgDeleteTV);
     imgDel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         fragment.xoa(String.valueOf(item.getMaTV()));
         }
     });

    }
    for (int i=0;i<=list.size();i++){
        if (item.getMaTV()%2==0){
            tvMaNV.setTextColor(Color.RED);
        }else {
            tvMaNV.setTextColor(Color.BLUE);
        }
    }
        return v;
    }
}
