package com.example.sp23_duan1_cp18104_nhom4.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sp23_duan1_cp18104_nhom4.DTO.HomeDTO;
import com.example.sp23_duan1_cp18104_nhom4.R;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {
    private ArrayList<HomeDTO> list;
    private Context context;

    public HomeAdapter(ArrayList<HomeDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewMot0{
        TextView txtTen,txtGia;
        ImageView ivHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewMot0 viewMot0;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        if (view == null){
            view = inflater.inflate(R.layout.item_mon_angv,viewGroup,false);
            viewMot0 = new ViewMot0();
            viewMot0.txtTen = view.findViewById(R.id.txtTen);
            viewMot0.ivHinh = view.findViewById(R.id.ivHinh);
            viewMot0.txtGia = view.findViewById(R.id.txtGiaa);
            view.setTag(viewMot0);
        }else {
            viewMot0 = (ViewMot0) view.getTag();
        }
        viewMot0.txtTen.setText(list.get(i).getTen());
        viewMot0.txtGia.setText(list.get(i).getGia());
        viewMot0.ivHinh.setImageResource(list.get(i).getHinh());
        return view;
    }
}
