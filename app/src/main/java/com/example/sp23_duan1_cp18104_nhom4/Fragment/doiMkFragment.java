package com.example.sp23_duan1_cp18104_nhom4.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sp23_duan1_cp18104_nhom4.R;


public class doiMkFragment extends Fragment {


    public doiMkFragment() {
        // Required empty public constructor
    }


    public static doiMkFragment newInstance(String param1, String param2) {
        doiMkFragment fragment = new doiMkFragment();

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
        return inflater.inflate(R.layout.fragment_doi_mk, container, false);
    }
}