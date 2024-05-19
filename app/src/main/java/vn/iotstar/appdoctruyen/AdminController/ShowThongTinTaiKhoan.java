package vn.iotstar.appdoctruyen.AdminController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.AccountPicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Taikhoan;

public class ShowThongTinTaiKhoan extends AppCompatActivity {

    ImageView img;
    TextView tv_id,tv_email,tv_matkhau,tv_ten,tv_dienthoai,tv_trangthai,tv_diem;

    private List<Taikhoan>taikhoan;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_thong_tin_tai_khoan);

        AnhXa();

        Intent intent=getIntent();
        email=intent.getStringExtra("email");

        taikhoan = new ArrayList<>();

        setData();
    }

    private void setData() {
        APIService.apiService.getTaiKhoanByEmail(email).enqueue(new Callback<List<Taikhoan>>() {
            @Override
            public void onResponse(Call<List<Taikhoan>> call, Response<List<Taikhoan>> response) {
                taikhoan = response.body();
                if (taikhoan != null){
                    String linkanh=taikhoan.get(0).getLinkanh();
                    if(linkanh!=null){
                        Glide.with(ShowThongTinTaiKhoan.this).load(linkanh).into(img);
                    }else {
                        img.setImageResource(R.drawable.avatar);
                    }
                    tv_id.setText(""+taikhoan.get(0).getId());
                    tv_email.setText(taikhoan.get(0).getEmail());
                    tv_matkhau.setText(taikhoan.get(0).getMatkhau());
                    tv_ten.setText(taikhoan.get(0).getHoten());
                    tv_dienthoai.setText(taikhoan.get(0).getDienthoai());
                    tv_diem.setText(""+taikhoan.get(0).getDiemthuong());

                    int trangthai=taikhoan.get(0).getLoaitk();
                    if(trangthai!=2){
                        tv_trangthai.setText("Hoạt động");
                    }else {
                        tv_trangthai.setText("Bị khóa");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Taikhoan>> call, Throwable throwable) {

            }
        });
    }

    private void AnhXa() {
        img=findViewById(R.id.img_qltk);
        tv_dienthoai=findViewById(R.id.tv_qltk_dienthoai);
        tv_email=findViewById(R.id.tv_qltk_email);
        tv_id=findViewById(R.id.tv_qltk_id);
        tv_matkhau=findViewById(R.id.tv_qltk_matkhau);
        tv_trangthai=findViewById(R.id.tv_qltk_trangthai);
        tv_ten=findViewById(R.id.tv_qltk_ten);
        tv_diem=findViewById(R.id.tv_qltk_diem);
    }
}