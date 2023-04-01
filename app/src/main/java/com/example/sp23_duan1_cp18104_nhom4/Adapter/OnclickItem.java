package com.example.sp23_duan1_cp18104_nhom4.Adapter;


import com.example.sp23_duan1_cp18104_nhom4.DTO.ThanhToanDTO;

public interface OnclickItem {
    void OnClickBack(ThanhToanDTO thanhToanDTO);
    void OnClickNext(ThanhToanDTO thanhToanDTO);
    void OnLongClick(ThanhToanDTO thanhToanDTO);
}
