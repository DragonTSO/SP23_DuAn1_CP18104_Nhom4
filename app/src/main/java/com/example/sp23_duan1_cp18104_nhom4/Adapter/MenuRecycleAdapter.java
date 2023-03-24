package com.example.sp23_duan1_cp18104_nhom4.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import com.example.sp23_duan1_cp18104_nhom4.Model.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sp23_duan1_cp18104_nhom4.DAO.MenuDAO;
import com.example.sp23_duan1_cp18104_nhom4.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MenuRecycleAdapter extends RecyclerView.Adapter<MenuRecycleAdapter.UserViewHodel>{
    private Context context;
    private ArrayList<Menu>list ;

private MenuDAO menuDAO ;
    public MenuRecycleAdapter(Context context) {
        this.context = context;
    }
    public void setData(ArrayList<Menu>list){
        this.list = list;
        menuDAO =new MenuDAO(context);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        return new UserViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHodel holder, int position) {
    Menu menu = list.get(position);
    if (menu==null){
        return;
    }
        holder.ma.setText("Mã Món :"+menu.getMaMon());
        holder.ten.setText("Tên Món :"+menu.getTenMon());
        holder.soluong.setText("Số Lượng :"+menu.getSoluong());
        holder.gia.setText("Giá :"+menu.getGia());

    holder.img_delete.setOnClickListener(v -> {
        dialogDelete(menu);
    });
    holder.itemView.setOnClickListener(v ->{
        dialogUpdate(menu);
    });
    }
    private void dialogDelete(Menu menu) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                menuDAO.delete(menu);
                list = menuDAO.getAllMenu( );
                setData(list);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
    public void dialogUpdate(Menu menu){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update_menu,null);
        TextInputEditText ed_mamon = v.findViewById(R.id.edt_Mamon);
        TextInputEditText ed_tenmon = v.findViewById(R.id.edt_Tenmon);
        TextInputEditText ed_soluong = v.findViewById(R.id.edt_Soluong);
        TextInputEditText ed_gia = v.findViewById(R.id.edt_Gia);

        ed_mamon.setText(String.valueOf(menu.getMaMon()));
        ed_tenmon.setText(String.valueOf(menu.getTenMon()));
        ed_soluong.setText(String.valueOf(menu.getSoluong()));
        ed_gia.setText(String.valueOf(menu.getGia()));
        builder.setView(v);
        AlertDialog alertDialog = builder.create();
        v.findViewById(R.id.btnokmenu).setOnClickListener(v1 ->{
            menuDAO = new MenuDAO(context);
            menu.setTenMon(ed_tenmon.getText().toString());
            menu.setSoluong(Integer.parseInt(ed_soluong.getText().toString()));
            menu.setGia(Integer.parseInt(ed_gia.getText().toString()));

            int kq = menuDAO.update(menu);
            if (kq == -1) {
                Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
            if (kq == 1) {
                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                list = menuDAO.getAllMenu();
                setData(list);
            }
            alertDialog.cancel();
        });
        v.findViewById(R.id.btncanclemenu).setOnClickListener(v1 -> {
            alertDialog.cancel();
        });
        alertDialog.show();
    }


    @Override
    public int getItemCount() {
        if (list!=null){
return list.size();
        }
        return 0;
    }

    public  class UserViewHodel extends RecyclerView.ViewHolder{
        private ImageView img_monan,img_delete;
        private TextView ma,ten,soluong,gia;
        public UserViewHodel(@NonNull View itemView) {
            super(itemView);
            img_monan= itemView.findViewById(R.id.img_mon);
            img_delete= itemView.findViewById(R.id.img_delete);
            ma = itemView.findViewById(R.id.txt_Mamon);
            ten = itemView.findViewById(R.id.txt_Tenmon);
            soluong = itemView.findViewById(R.id.txt_Soluong);
            gia = itemView.findViewById(R.id.txt_Gia);

        }
    }
}
