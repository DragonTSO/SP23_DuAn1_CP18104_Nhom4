package com.example.sp23_duan1_cp18104_nhom4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp23_duan1_cp18104_nhom4.DAO.MenuDAO;
import com.example.sp23_duan1_cp18104_nhom4.Model.Menu;
import com.example.sp23_duan1_cp18104_nhom4.R;

import java.util.ArrayList;

public class Top10RecycleAdapter extends RecyclerView.Adapter<Top10RecycleAdapter.userViewhodel>{
    private Context context;
    private ArrayList<Menu>list ;
    private MenuDAO menuDAO ;

    public Top10RecycleAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Menu> list){
        this.list = list;
        menuDAO =new MenuDAO(context);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public userViewhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top10,parent,false);
        return new userViewhodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewhodel holder, int position) {
        Menu menu = list.get(position);
        if (menu==null){
            return;
        }
        holder.ma.setText("Mã Món :"+menu.getMaMon());
        holder.ten.setText("Tên Món :"+menu.getTenMon());
        holder.soluong.setText("Số Lượng :"+menu.getSoluong());
        holder.gia.setText("Giá :"+menu.getGia());

    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class userViewhodel extends RecyclerView.ViewHolder {
        private ImageView img_monan;
        private TextView ma,ten,soluong,gia;
        public userViewhodel(@NonNull View itemView) {
            super(itemView);
            img_monan= itemView.findViewById(R.id.img_mon);
            ma = itemView.findViewById(R.id.txt_Mamon);
            ten = itemView.findViewById(R.id.txt_Tenmon);
            soluong = itemView.findViewById(R.id.txt_Soluong);
            gia = itemView.findViewById(R.id.txt_Gia);
        }
    }
}
