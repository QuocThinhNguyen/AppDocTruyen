package vn.iotstar.appdoctruyen.AdminController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.AdminAdapter.QuanLyTaiKhoanAdapter;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Taikhoan;

public class QuanLyTaiKhoan extends AppCompatActivity implements View.OnClickListener {

    Taikhoan taiKhoan;
    String email;
    private RecyclerView rcv;
    private QuanLyTaiKhoanAdapter adapter;
    ImageView img_newtk;
    Button bt_them, bt_huy;
    EditText edt_email, edt_matkhau, edt_ten, edt_dienthoai;
    CardView cv_themtaikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan);

        AnhXa();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuanLyTaiKhoan.this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(QuanLyTaiKhoan.this, DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        setOnClickListener();
        showTaiKhoan();
    }

    private void showTaiKhoan() {
        APIService.apiService.getTaiKhoan().enqueue(new Callback<List<Taikhoan>>() {
            @Override
            public void onResponse(Call<List<Taikhoan>> call, Response<List<Taikhoan>> response) {
                List<Taikhoan> list = response.body();
                adapter = new QuanLyTaiKhoanAdapter(QuanLyTaiKhoan.this, list);
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Taikhoan>> call, Throwable throwable) {

            }
        });
    }

    private void AnhXa() {
        rcv = findViewById(R.id.rcv_quanlytaikhoan);
        img_newtk = findViewById(R.id.img_newtaikhoan);
        bt_huy = findViewById(R.id.bt_huy_newtk);
        bt_them = findViewById(R.id.bt_them_newtk);
        edt_email = findViewById(R.id.edt_email_newtk);
        edt_matkhau = findViewById(R.id.edt_mk_newtk);
        edt_ten = findViewById(R.id.edt_ten_newtk);
        edt_dienthoai = findViewById(R.id.edt_dienthoai_newtk);
        cv_themtaikhoan = findViewById(R.id.cv_themtk);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.img_newtaikhoan) {
            cv_themtaikhoan.setVisibility(View.VISIBLE);
        }
        if (v.getId() == R.id.bt_huy_newtk) {
            cv_themtaikhoan.setVisibility(View.GONE);
        }
        if (v.getId() == R.id.bt_them_newtk) {
            String email = edt_email.getText().toString();
            String matkhau = edt_matkhau.getText().toString();
            String ten = edt_ten.getText().toString();
            String dienthoai = edt_dienthoai.getText().toString();

            Taikhoan taikhoan = new Taikhoan(email, matkhau, ten, dienthoai, 0, 0);
            APIService.apiService.addTaiKhoan(taikhoan).enqueue(new Callback<Taikhoan>() {
                @Override
                public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {

                    Toast.makeText(QuanLyTaiKhoan.this, "Thêm thành công", Toast.LENGTH_SHORT);
                    showTaiKhoan();
                    cv_themtaikhoan.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<Taikhoan> call, Throwable t) {

                }
            });
        }
    }

    private void setOnClickListener() {
        bt_them.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
        img_newtk.setOnClickListener(this);
    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}