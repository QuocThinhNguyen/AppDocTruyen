package vn.iotstar.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.FragmentAdapter;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.Truyen1;
import vn.iotstar.appdoctruyen.model.truyen;

public class CTTruyen extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    ImageView img_truyen;
    int id_truyen;
    List<truyen> truyenList;
    truyen Truyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cttruyen);
        Anhxa();
        Intent intent=getIntent();
        id_truyen=intent.getIntExtra("id_truyen",1);
        getTruyenById();
        FragmentManager fragmentManager=getSupportFragmentManager();
        adapter=new FragmentAdapter(fragmentManager,getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Chi tiết"));
        tabLayout.addTab(tabLayout.newTab().setText("Chapter"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
    private void Anhxa(){
        tabLayout=findViewById(R.id.tab_layout);
        pager2=findViewById(R.id.view_pager2);
        img_truyen=findViewById(R.id.img_truyen);
    }
    private void getTruyenById() {
        APIService.apiService.getTruyenById(id_truyen).enqueue(new Callback<List<vn.iotstar.appdoctruyen.model.truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<truyen>> call, @NonNull Response<List<truyen>> response) {
                truyenList = response.body();
                Truyen = truyenList.get(0);
                Glide.with(CTTruyen.this).load(Truyen.getLinkanh()).into(img_truyen);
            }

            @Override

            public void onFailure(@NonNull Call<List<truyen>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
            }

        });
    }
}