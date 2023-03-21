package com.example.sp23_duan1_cp18104_nhom4.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sp23_duan1_cp18104_nhom4.R;


public class BanAnFragment extends Fragment {





    public static BanAnFragment newInstance( ) {
        BanAnFragment fragment = new BanAnFragment();

        return fragment;
    }

    public BanAnFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ban_an, container, false);
    }
}