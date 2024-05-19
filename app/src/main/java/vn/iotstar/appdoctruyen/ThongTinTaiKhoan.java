package vn.iotstar.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.model.TaiKhoanDto;
import vn.iotstar.appdoctruyen.model.Taikhoan;

public class ThongTinTaiKhoan extends AppCompatActivity {

    TextView id,memail,hoten,dienthoai,diemthuong,trangthai;
    public TaiKhoanDto tk;
    Boolean ax = false;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
        Anhxa();
        ax=true;
        gettaikhoan(email);

    }

    public void gettaikhoan(String email){
        APIService.apiService.findByEmail(this.email).enqueue(new Callback<TaiKhoanDto>() {
            @Override
            public void onResponse(Call<TaiKhoanDto> call, Response<TaiKhoanDto> response) {
                    tk = response.body();
                    if (ax==true) {
                        id.setText(tk.getId().toString());
                        memail.setText(tk.getEmail().toString());
                        hoten.setText(tk.getHoten().toString());
                        dienthoai.setText(tk.getDienthoai().toString());
                        diemthuong.setText(tk.getDiemthuong().toString());
                        trangthai.setText("Đang hoạt động");
                    }

            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoanDto> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Anhxa(){
        id = findViewById(R.id.tv_id);
        memail = findViewById(R.id.tv_email);
        hoten=findViewById(R.id.tv_hoten);
        dienthoai=findViewById(R.id.tv_dienthoai);
        diemthuong=findViewById(R.id.tv_diemthuong);
        trangthai=findViewById(R.id.tv_trangthai);
    }
}