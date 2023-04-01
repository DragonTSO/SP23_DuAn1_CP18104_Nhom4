package com.example.sp23_duan1_cp18104_nhom4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sp23_duan1_cp18104_nhom4.DAO.NhanVienDAO;
import com.example.sp23_duan1_cp18104_nhom4.DAO.ThanhToanDAO;
import com.example.sp23_duan1_cp18104_nhom4.DTO.ThanhToanDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Bill extends AppCompatActivity {
    int madon, manv, maban;
    String ngaydat, tongtien,tennv;
    NhanVienDAO nhanVienDAO;
    ImageView img_detailstatistic_backbtn;
    private ArrayList<String> tenmon = new ArrayList<String>();
    private ArrayList<String> soluong = new ArrayList<String>();
    private ArrayList<String> giathanh = new ArrayList<String>();
    private ArrayList<String> thanhtien = new ArrayList<String>();

    TextView tvIDHOADON,tvTongTien,tvHovatenNV,tvNgayDat;
    TableLayout tableLayout;

    List<ThanhToanDTO> thanhToanDTOList;
    ThanhToanDAO thanhToanDAO;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        AnhXa();

        img_detailstatistic_backbtn = (ImageView)findViewById(R.id.img_detailstatistic_backbtn);
        img_detailstatistic_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        nhanVienDAO = new NhanVienDAO(this);
        thanhToanDAO = new ThanhToanDAO(this);

        Intent intent = getIntent();
        madon = intent.getIntExtra("madon",0);
        manv = intent.getIntExtra("manv",0);
        maban = intent.getIntExtra("maban",0);
        ngaydat = intent.getStringExtra("ngaydat");
        tongtien = intent.getStringExtra("tongtien");


        thanhToanDTOList = thanhToanDAO.LayDSMonTheoMaDon(madon);

        tvIDHOADON.setText("ID HÓA ĐƠN : "+madon);

        DecimalFormat formatter = new DecimalFormat("###,###,###,###");
        tvTongTien.setText("TỔNG TIỀN : "+formatter.format(Long.valueOf(tongtien))+" VND");
        tvNgayDat.setText("Ngày đặt : "+ngaydat);
        tvHovatenNV.setText("Nhân viên : "+tennv);
        tenmon.add("Tên món");
        soluong.add("Số lượng");
        giathanh.add("Đơn giá");
        thanhtien.add("Thành tiền");
        for (ThanhToanDTO object :thanhToanDTOList ){
            tenmon.add(object.getTenMon());
            soluong.add(String.valueOf(object.getSoLuong()));
            giathanh.add(String.valueOf(object.getGiaTien()));
            thanhtien.add(formatter.format(Long.valueOf(object.getGiaTien())*object.getSoLuong()));
        }


        }

    private void AnhXa() {
        tvIDHOADON = findViewById(R.id.tvIDHOADON);
        tvTongTien = findViewById(R.id.tvTongTien);
        tvHovatenNV = findViewById(R.id.tvHovatenNV);
        tvNgayDat = findViewById(R.id.tvNgayDat);
        tableLayout = findViewById(R.id.tbLyaout);
    }
}