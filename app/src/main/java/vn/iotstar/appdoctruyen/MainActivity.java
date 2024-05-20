package vn.iotstar.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    Fragment fragment=null;
    ChipNavigationBar chipNavigationBar;

    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) email = null;
        else email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        chipNavigationBar = findViewById(R.id.NavigationBar);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if (i == R.id.home){
                    fragment = new HomeFragment();
                }
                else if (i == R.id.store){
                    if(email==null)
                        Toast.makeText(getApplicationContext(), "Vui Lòng đăng nhập để sử dụng chức năng này", Toast.LENGTH_SHORT).show();
                    else  fragment = new TuSachFragment();
                }
                else if (i == R.id.cart){
                    if(email==null)
                        Toast.makeText(getApplicationContext(), "Vui Lòng đăng nhập để sử dụng chức năng này", Toast.LENGTH_SHORT).show();
                    else fragment = new ThongBaoFragment();
                }
                else {
                    if(email==null)
                        Toast.makeText(getApplicationContext(), "Vui Lòng đăng nhập để sử dụng chức năng này", Toast.LENGTH_SHORT).show();
                    else fragment = new TaiKhoanFragment();
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });
    }
}