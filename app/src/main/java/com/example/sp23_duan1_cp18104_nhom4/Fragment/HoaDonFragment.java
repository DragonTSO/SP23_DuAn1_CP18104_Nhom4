package com.example.sp23_duan1_cp18104_nhom4.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sp23_duan1_cp18104_nhom4.R;


public class HoaDonFragment extends Fragment {

    public HoaDonFragment() {
        // Required empty public constructor
    }


    public static HoaDonFragment newInstance( ) {
        HoaDonFragment fragment = new HoaDonFragment();

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
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }
}