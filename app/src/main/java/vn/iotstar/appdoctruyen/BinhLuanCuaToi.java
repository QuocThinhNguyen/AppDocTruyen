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
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.BinhLuanCuaToiDto;
import vn.iotstar.appdoctruyen.model.TaiKhoanDto;

public class BinhLuanCuaToi extends AppCompatActivity {
    List<BinhLuanCuaToiDto> bl;
    String email;
    Integer idtk;
    TextView tv_tongbinhluan;
    RecyclerView rcv_tongbinhluan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan_cua_toi);

        tv_tongbinhluan = findViewById(R.id.tv_tongbinhluan);
        rcv_tongbinhluan = findViewById(R.id.rcv_tongbinhluan);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email= user.getEmail();
        ThongTinTaiKhoan thongTinTaiKhoan = new ThongTinTaiKhoan();
        thongTinTaiKhoan.email= email;
        thongTinTaiKhoan.gettaikhoan(email);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BinhLuanCuaToi.this, RecyclerView.VERTICAL, false);
        rcv_tongbinhluan.setLayoutManager(linearLayoutManager);

        DividerItemDecoration item = new DividerItemDecoration(BinhLuanCuaToi.this, DividerItemDecoration.VERTICAL);
        rcv_tongbinhluan.addItemDecoration(item);

        bl = new ArrayList<>();


        // Sử dụng Handler để trì hoãn hành động trong luồng chính
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                idtk = thongTinTaiKhoan.tk.getId();
                getBinhLuanCuaToi();
            }
        }, 5000);




    }

    public void getBinhLuanCuaToi(){
        APIService.apiService.findByIdn(this.idtk).enqueue(new Callback<List<BinhLuanCuaToiDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<BinhLuanCuaToiDto>> call, @NonNull Response<List<BinhLuanCuaToiDto>> response) {
                bl = response.body();
                tv_tongbinhluan.setText("Tổng bình luận: "+ bl.size());
                BinhLuanCuaToiAdapter binhLuanCuaToiAdapter = new BinhLuanCuaToiAdapter(BinhLuanCuaToi.this,R.layout.item_binhluancuatoi,bl);
                rcv_tongbinhluan.setAdapter(binhLuanCuaToiAdapter);
            }
            @Override
            public void onFailure(@NonNull Call<List<BinhLuanCuaToiDto>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}