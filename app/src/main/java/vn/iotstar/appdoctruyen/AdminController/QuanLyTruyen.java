package vn.iotstar.appdoctruyen.AdminController;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.AdminAdapter.QuanLyTruyenAdapter;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.truyen;

public class QuanLyTruyen extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rcv;
    private QuanLyTruyenAdapter adapter;
    ImageView img_newtruyen;
    Button bt_them, bt_huy;
    EditText edt_tentruyen, edt_theloai, edt_tacgia, edt_mota, edt_linkanh;
    CardView cv_themtruyen;
    private List<truyen> truyenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_truyen);

        AnhXa();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuanLyTruyen.this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(QuanLyTruyen.this, DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        truyenList = new ArrayList<>();
        showAllTruyen();

        setOnClickListener();
    }

    private void showAllTruyen() {

        APIService.apiService.getTruyenAll().enqueue(new Callback<List<truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<truyen>> call, @NonNull Response<List<truyen>> response) {
                truyenList = response.body();
                QuanLyTruyenAdapter adapter = new QuanLyTruyenAdapter(QuanLyTruyen.this, truyenList);
                rcv.setAdapter(adapter);
            }

            @Override

            public void onFailure(@NonNull Call<List<truyen>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(QuanLyTruyen.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_newtruyen) {
            cv_themtruyen.setVisibility(View.VISIBLE);
        }
        if (view.getId() == R.id.bt_huy_newtruyen) {
            cv_themtruyen.setVisibility(View.GONE);
        }
        if (view.getId() == R.id.bt_them_newtruyen) {
            String tentruyen = edt_tentruyen.getText().toString();
            String theloai = edt_theloai.getText().toString();
            String tacgia = edt_tacgia.getText().toString();
            String mota = edt_mota.getText().toString();
            String linkanh = edt_linkanh.getText().toString();
            String key_search = removeAccent(tentruyen).trim();
            if (tentruyen.isEmpty() || theloai.isEmpty() || tacgia.isEmpty() || mota.isEmpty() || linkanh.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                if (checkTenTruyen(tentruyen)) {
                    Toast.makeText(this, "Truyện đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    //db.insertTruyen(tentruyen,theloai,tacgia,mota,linkanh);
                    truyen truyen1 = new truyen(tentruyen, tacgia,mota,theloai,linkanh, 0,key_search);
                    APIService.apiService.addTruyen(truyen1).enqueue(new Callback<truyen>() {
                        @Override
                        public void onResponse(Call<truyen> call, Response<truyen> response) {
                            //Toast.makeText(QuanLyTruyen.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                            truyen result = response.body();
                            if(result != null){
                                Toast.makeText(QuanLyTruyen.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<truyen> call, Throwable throwable) {
                            Toast.makeText(QuanLyTruyen.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                //Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                cv_themtruyen.setVisibility(View.GONE);
                reload();
            }
        }
    }

    private void setOnClickListener() {
        bt_them.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
        img_newtruyen.setOnClickListener(this);
    }

    public static String removeAccent(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public boolean checkTenTruyen(String tentruyen) {
        final boolean[] isExist = new boolean[1];
        final CountDownLatch latch = new CountDownLatch(1);
        APIService.apiService.getTenTruyen().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> tenTruyenList = response.body();
                for (String ten : tenTruyenList) {
                    if (tentruyen.equals(ten)) {
                        isExist[0] = true;
                        break;
                    }
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);

            }
        });

        return isExist[0];
    }

//    public void checkTenTruyen(String tentruyen) {
//        APIService.apiService.getTenTruyen().enqueue(new Callback<List<String>>() {
//            @Override
//            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//                List<String> tenTruyenList = response.body();
//                for (String ten : tenTruyenList) {
//                    if (tentruyen.equals(removeAccent(ten).trim())) {
//                        Log.d("checkTenTruyen", "Tên truyện đã tồn tại");
//                        return;
//                    }
//                }
//                Log.d("checkTenTruyen", "Tên truyện không tồn tại");
//            }
//
//            @Override
//            public void onFailure(Call<List<String>> call, Throwable t) {
//                Log.e("API_CALL", "Failed to fetch data from API", t);
//            }
//        });
//    }

//    public int checkTenTruyen(String tentruyen){
//        // @GET("truyen/tentruyen")
//        //    Call<List<String>> getTenTruyen();
//
//        for (int i = 0; i < listtruyen.size(); i++) {
//            if (tentruyen.equals(removeAccent(listtruyen.get(i).getTentruyen()).trim())) {
//                return 1;
//            }
//        }
//        return 0;
//    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void AnhXa() {
        rcv = findViewById(R.id.rcv_quanlytruyen);
        img_newtruyen = findViewById(R.id.img_newtruyen);
        bt_huy = findViewById(R.id.bt_huy_newtruyen);
        bt_them = findViewById(R.id.bt_them_newtruyen);
        edt_tentruyen = findViewById(R.id.edt_tentruyen_newtruyen);
        edt_theloai = findViewById(R.id.edt_theloai_newtruyen);
        edt_tacgia = findViewById(R.id.edt_tg_newtruyen);
        edt_mota = findViewById(R.id.edt_mota_newtruyen);
        edt_linkanh = findViewById(R.id.edt_linkanh_newtruyen);
        cv_themtruyen = findViewById(R.id.cv_themtruyen);
    }
}