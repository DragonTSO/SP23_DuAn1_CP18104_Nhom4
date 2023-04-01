package com.example.sp23_duan1_cp18104_nhom4.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp23_duan1_cp18104_nhom4.Adapter.MenuRecycleAdapter;
import com.example.sp23_duan1_cp18104_nhom4.DAO.MenuDAO;
import com.example.sp23_duan1_cp18104_nhom4.Model.Menu;
import com.example.sp23_duan1_cp18104_nhom4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment implements View.OnClickListener{
    private FloatingActionButton floating;
    private MenuDAO menuDAO;
    private RecyclerView recyclerView ;
//    private ListView listView;
    private MenuRecycleAdapter adapter;
    private ArrayList<Menu>list=new ArrayList<>();
    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance( ) {
        MenuFragment fragment = new MenuFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_monan);
//        listView  = view.findViewById(R.id.list_item_monan);
        floating = view.findViewById(R.id.add_monan);
        floating.setOnClickListener(this);
    }
    public void dialog_Menu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update_menu, null);
        TextInputEditText  ed_MaMon,ed_TenMon,ed_Soluong,ed_gia;
        Button btn_luu,btn_huy;
        ed_MaMon= v.findViewById(R.id.edt_Mamon);
        ed_TenMon = v.findViewById(R.id.edt_Tenmon);
        ed_Soluong = v.findViewById(R.id.edt_Soluong);
        ed_gia = v.findViewById(R.id.edt_Gia);
        btn_luu = v.findViewById(R.id.btnokmenu);
        btn_huy = v.findViewById(R.id.btncanclemenu);
        builder.setView(v);
        AlertDialog alertDialog = builder.create();
        btn_luu.setOnClickListener(v1 -> {
            if (ed_TenMon.length()==0){
                Toast.makeText(getActivity(), "Bạn phải điền tên món ăn ", Toast.LENGTH_SHORT).show();
            }else if (ed_Soluong.length()==0){
                Toast.makeText(getActivity(), "Bạn phải điền số lượng  món ăn ", Toast.LENGTH_SHORT).show();
            }else if (ed_gia.length()==0){
                Toast.makeText(getActivity(), "Bạn phải điền giá  món ăn ", Toast.LENGTH_SHORT).show();
            }else {
                menuDAO = new MenuDAO(getActivity());
                Menu menu = new Menu();
                menu.setTenMon(ed_TenMon.getText().toString());
                menu.setSoluong(Integer.parseInt(ed_Soluong.getText().toString()));
                menu.setGia(Integer.parseInt(ed_gia.getText().toString()));
                int kq = menuDAO.Insert(menu);
                if (kq == -1) {
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                if (kq == 1) {
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                onResume();
                alertDialog.cancel();

            }
        });
        alertDialog.show();

    }

    @Override
    public void onResume() {
        menuDAO= new MenuDAO(getActivity());
        list.clear();
        list = menuDAO.getAllMenu();
        adapter = new MenuRecycleAdapter(getActivity());
        adapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);


        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_monan:
                dialog_Menu();
                break;
        }
    }
}