package com.example.sp23_duan1_cp18104_nhom4.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp23_duan1_cp18104_nhom4.Adapter.MenuRecycleAdapter;
import com.example.sp23_duan1_cp18104_nhom4.Adapter.Top10RecycleAdapter;
import com.example.sp23_duan1_cp18104_nhom4.DAO.MenuDAO;
import com.example.sp23_duan1_cp18104_nhom4.Model.Menu;
import com.example.sp23_duan1_cp18104_nhom4.R;

import java.util.ArrayList;


public class Top10Fragment extends Fragment {

    private RecyclerView recyclerView ;
    private Top10RecycleAdapter adapter;
    private ArrayList<Menu> list=new ArrayList<>();

    public Top10Fragment() {
        // Required empty public constructor
    }


    public static Top10Fragment newInstance( ) {
        Top10Fragment fragment = new Top10Fragment();
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
        return inflater.inflate(R.layout.fragment_top10, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_top10);
//        HoaDonDAO = new HoaDonDAO(getActivity());

        list.clear();
//        list = HoaDonDAO.top10();
        adapter = new Top10RecycleAdapter(getActivity());
        adapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}