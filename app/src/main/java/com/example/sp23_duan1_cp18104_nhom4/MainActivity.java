package com.example.sp23_duan1_cp18104_nhom4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sp23_duan1_cp18104_nhom4.Fragment.BanAnFragment;
import com.example.sp23_duan1_cp18104_nhom4.Fragment.DoanhThuFragment;
import com.example.sp23_duan1_cp18104_nhom4.Fragment.HoaDonFragment;
import com.example.sp23_duan1_cp18104_nhom4.Fragment.KhachHangFragment;
import com.example.sp23_duan1_cp18104_nhom4.Fragment.MenuFragment;
import com.example.sp23_duan1_cp18104_nhom4.Fragment.Top10Fragment;
import com.example.sp23_duan1_cp18104_nhom4.Fragment.doiMkFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FrameLayout frameLayout;

    private TextView tv_header;

    public static int maquyen = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        frameLayout = findViewById(R.id.frameLayout);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);




        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        toggle.syncState();



        Intent intent = getIntent();
        String maTT = intent.getStringExtra("TK");
        Bundle bundle = new Bundle();
        bundle.putString("TK", maTT);



        View header = navigationView.getHeaderView(0);
        tv_header = header.findViewById(R.id.txtUsser);
        tv_header.setText("ACE RESTAURANT");




        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }


    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.nav_HD) {
            //hiển thị màn hình bài 1
            toolbar.setTitle("Quản lý phiếu mượn");
            replaceFragment(new HoaDonFragment());
            //  replaceFragment();
        } else if (id == R.id.nav_menu) {
            // hiển thị màn hình bài 2
            toolbar.setTitle("Quản lý loại sách");
            replaceFragment(new MenuFragment());
            // replaceFragment();
        } else if (id == R.id.nav_db) {
            toolbar.setTitle("Quản lý bàn");
            replaceFragment(new BanAnFragment());
        } // replaceFragment();
        else if (id == R.id.nav_dt) {
            toolbar.setTitle("Doanh thu");
            replaceFragment(new DoanhThuFragment());
            // replaceFragment();
        }
        else if (id == R.id.nav_topmonan) {
            toolbar.setTitle("10 Quyển sách mượn nhiều nhất");
            replaceFragment(new Top10Fragment());
            // replaceFragment();
        } else if (id == R.id.nav_KH) {
            toolbar.setTitle("Thêm người dùng");
            // replaceFragment();
            replaceFragment(new KhachHangFragment());
        } else if (id == R.id.nav_dmk) {
            toolbar.setTitle("Đổi mật khẩu");
            replaceFragment(new doiMkFragment());
            // replaceFragment();
        } else if (id == R.id.nav_dx) {
            finish();
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}