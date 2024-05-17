package vn.iotstar.appdoctruyen;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.FragmentAdapterTheLoai;
import vn.iotstar.appdoctruyen.model.truyen;

public class TheLoaiFragment extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterTheLoai adapter;
    //Database db;
    APIService api;

    ArrayList<String> listtheloai;
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;
    String textTheLoai;

    TheLoaiNewFragment theLoaiNewFragment;
    TheLoaiVoteFragment theLoaiVoteFragment;
    TheLoaiLuotXemFragment theLoaiLuotXemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_theloai);

        AnhXa();

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new FragmentAdapterTheLoai(fragmentManager, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Mới nhất"));
        tabLayout.addTab(tabLayout.newTab().setText("BXH Votes"));
        tabLayout.addTab(tabLayout.newTab().setText("BXH Lượt Xem"));

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

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        getTheLoai();
    }

    public interface OnTheLoaiSelectedListener {
        void onTheLoaiSelected(String theLoai);
    }

    private OnTheLoaiSelectedListener onTheLoaiSelectedListener;

    public void setOnTheLoaiSelectedListener(OnTheLoaiSelectedListener listener) {
        this.onTheLoaiSelectedListener = listener;
    }

    private void getTheLoai() {
        APIService.apiService.getTheLoai().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> theLoaiList = response.body();
                    // Đặt dữ liệu vào AutoCompleteTextView

                    adapterItems = new ArrayAdapter<String>(TheLoaiFragment.this, R.layout.list_item, theLoaiList);
                    autoCompleteTextView.setText(theLoaiList.get(0));
                    autoCompleteTextView.setAdapter(adapterItems);
                } else {
                    Log.e("API_CALL", "Failed to fetch data from API");
                    Toast.makeText(getApplicationContext(), "Failed to fetch data from API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), "Thể loại: "+item,Toast.LENGTH_SHORT).show();
//            }
//        });


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (onTheLoaiSelectedListener != null) {
                    onTheLoaiSelectedListener.onTheLoaiSelected(item);
                }
                Toast.makeText(getApplicationContext(), "Thể loại: "+item,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }
    private void AnhXa() {
        tabLayout = findViewById(R.id.tab_layout);
        pager2=findViewById(R.id.view_pager2_tl);
        autoCompleteTextView=findViewById(R.id.auto_complete_txt);
    }
}