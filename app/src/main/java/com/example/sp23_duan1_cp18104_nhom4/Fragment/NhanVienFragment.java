package com.example.sp23_duan1_cp18104_nhom4.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sp23_duan1_cp18104_nhom4.Adapter.NhanVienAdapter;
import com.example.sp23_duan1_cp18104_nhom4.DAO.NhanVienDAO;
import com.example.sp23_duan1_cp18104_nhom4.DTO.NhanVien;
import com.example.sp23_duan1_cp18104_nhom4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class NhanVienFragment extends Fragment {
    ListView lvThanhVien;
    ArrayList<NhanVien> list;
    static NhanVienDAO dao;
    NhanVienAdapter adapter;
    NhanVien item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV,edViTri;
    Button btnsave, btncancel;
    ImageView imgFix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);

        lvThanhVien = v.findViewById(R.id.lvNhanVien);
        fab = v.findViewById(R.id.addNhanVien);
        dao = new NhanVienDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);//bang = thi insert
            }
        });



        lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                item = list.get(position);
                openDialog(getActivity(), 1);//=1 thi update

                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<NhanVien>) dao.getAll();
        adapter = new NhanVienAdapter(getActivity(), list);
        lvThanhVien.setAdapter(adapter);
    }

    public void xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("bạn có muốn xóa không ?");
        builder.setCancelable(true);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                dao.detele(id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();

    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.nhan_vien_dialog);

        edMaTV = dialog.findViewById(R.id.edMaNV);
        edTenTV = dialog.findViewById(R.id.edTenNV);
        edViTri = dialog.findViewById(R.id.edViTri);
        btncancel = dialog.findViewById(R.id.btnSaveNV);
        btnsave = dialog.findViewById(R.id.btnSaveNV);

        edMaTV.setEnabled(false);
        if (type != 0) {
            //cap nhat
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(String.valueOf(item.getHoTen()));
            edViTri.setText(String.valueOf(item.getViTri()));
        }
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new NhanVien();
                item.setHoTen(edTenTV.getText().toString());
                item.setViTri(edViTri.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        //==- thi them nguoi dung
                        if (dao.insert(item) > 0) {
                            Toast.makeText(getContext(), "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //khac kohn thi update data
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }




    public int validate() {
        int check = 1;
        if (edTenTV.getText().length() == 0 || edViTri.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check = 1;
    }
}