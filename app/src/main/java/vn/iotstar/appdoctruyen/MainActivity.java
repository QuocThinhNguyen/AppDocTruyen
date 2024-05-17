package vn.iotstar.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    Fragment fragment=null;
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String email = getIntent().getExtras().getString("email");
        Toast.makeText(this,email,Toast.LENGTH_SHORT).show();

        chipNavigationBar = findViewById(R.id.NavigationBar);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if (i == R.id.home){
                    fragment = new HomeFragment();
                }else if (i == R.id.store){
                    fragment = new TuSachFragment();
                }else if (i == R.id.cart){
                    fragment = new ThongBaoFragment();
                }
                else {
                    fragment = TaiKhoanFragment.newInstance(email);
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });
    }
}