package com.example.sp23_duan1_cp18104_nhom4.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp23_duan1_cp18104_nhom4.Adapter.AdapterDisplayTable;
import com.example.sp23_duan1_cp18104_nhom4.AddTableActivity;
import com.example.sp23_duan1_cp18104_nhom4.DAO.BanAnDAO;
import com.example.sp23_duan1_cp18104_nhom4.DTO.BanAnDTO;
import com.example.sp23_duan1_cp18104_nhom4.EditTableActivity;
import com.example.sp23_duan1_cp18104_nhom4.MainActivity;
import com.example.sp23_duan1_cp18104_nhom4.R;

import java.util.List;


public class BanAnFragment extends Fragment {
    public static BanAnFragment newInstance( ) {
        BanAnFragment fragment = new BanAnFragment();

        return fragment;
    }

    GridView GVDisplayTable;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    AdapterDisplayTable adapterDisplayTable;

    ActivityResultLauncher<Intent> resultLauncherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        boolean ktra = intent.getBooleanExtra("ketquathem",false);
                        if(ktra){
                            HienThiDSBan();
                            Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    ActivityResultLauncher<Intent> resultLauncherEdit = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        boolean ktra = intent.getBooleanExtra("ketquasua",false);
                        if(ktra){
                            HienThiDSBan();
                            Toast.makeText(getActivity(),getResources().getString(R.string.edit_sucessful),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(),getResources().getString(R.string.edit_failed),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ban_an,container,false);
        setHasOptionsMenu(true);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Quản lý bàn");

        GVDisplayTable = (GridView)view.findViewById(R.id.gvDisplayTable);
        banAnDAO = new BanAnDAO(getActivity());

        HienThiDSBan();

        registerForContextMenu(GVDisplayTable);
        return view;
    }



    //tạo ra context menu khi longclick
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    //Xử lí cho từng trường hợp trong contextmenu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = menuInfo.position;
        int maban = banAnDTOList.get(vitri).getMaBan();
        switch(id){
            case R.id.itEdit:
                Intent intent = new Intent(getActivity(), EditTableActivity.class);
                intent.putExtra("maban",maban);
                resultLauncherEdit.launch(intent);
                break;

            case R.id.itDelete:
                boolean ktraxoa = banAnDAO.XoaBanTheoMa(maban);
                if(ktraxoa){
                    HienThiDSBan();
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.delete_sucessful),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.delete_failed),Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itAddTable = menu.add(1,R.id.itAddTable,1,R.string.addTable);
        itAddTable.setIcon(R.drawable.ic_baseline_add_24);
        itAddTable.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.itAddTable:
                Intent iAddTable = new Intent(getActivity(), AddTableActivity.class);
                resultLauncherAdd.launch(iAddTable);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterDisplayTable.notifyDataSetChanged();
    }

    private void HienThiDSBan(){
        banAnDTOList = banAnDAO.LayTatCaBanAn();
        adapterDisplayTable = new AdapterDisplayTable(getActivity(),R.layout.custom_layout_displaytable,banAnDTOList);
        GVDisplayTable.setAdapter(adapterDisplayTable);
        adapterDisplayTable.notifyDataSetChanged();
    }
}