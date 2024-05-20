package vn.iotstar.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.BinhLuanCuaToiAdapter;
import vn.iotstar.appdoctruyen.Adapter.DanhGiaCuaToiAdapter;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.BinhLuanCuaToiDto;
import vn.iotstar.appdoctruyen.model.DanhGiaCuaToiDto;
import vn.iotstar.appdoctruyen.model.TaiKhoanDto;

public class DanhGiaCuaToi extends AppCompatActivity {
    List<DanhGiaCuaToiDto> dg;
    String email;
    Integer idtk;
    TextView tv_tongdanhgia;
    RecyclerView rcv_tongdanhgia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia_cua_toi);

        tv_tongdanhgia = findViewById(R.id.tv_tongdanhgia);
        rcv_tongdanhgia = findViewById(R.id.rcv_tongdanhgia);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email= user.getEmail();
        ThongTinTaiKhoan thongTinTaiKhoan = new ThongTinTaiKhoan();
        thongTinTaiKhoan.email= email;
        thongTinTaiKhoan.gettaikhoan(email);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhGiaCuaToi.this, RecyclerView.VERTICAL, false);
        rcv_tongdanhgia.setLayoutManager(linearLayoutManager);

        DividerItemDecoration item = new DividerItemDecoration(DanhGiaCuaToi.this, DividerItemDecoration.VERTICAL);
        rcv_tongdanhgia.addItemDecoration(item);

        dg = new ArrayList<>();


        // Sử dụng Handler để trì hoãn hành động trong luồng chính
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                idtk = thongTinTaiKhoan.tk.getId();
                getDanhGiaCuaToi();
            }
        }, 500);




    }

    public void getDanhGiaCuaToi(){
        APIService.apiService.findDanhGiaByIdn(this.idtk).enqueue(new Callback<List<DanhGiaCuaToiDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<DanhGiaCuaToiDto>> call, @NonNull Response<List<DanhGiaCuaToiDto>> response) {
                dg = response.body();
                tv_tongdanhgia.setText("Tổng đánh giá: "+ dg.size());
                DanhGiaCuaToiAdapter danhGiaCuaToiAdapter = new DanhGiaCuaToiAdapter(DanhGiaCuaToi.this,R.layout.item_rcv_danhgiacuatoi,dg);
                rcv_tongdanhgia.setAdapter(danhGiaCuaToiAdapter);
            }
            @Override
            public void onFailure(@NonNull Call<List<DanhGiaCuaToiDto>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}