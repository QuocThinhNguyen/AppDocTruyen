package vn.iotstart.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Integer id;
    TextView tv_tongbinhluan;
    RecyclerView rcv_tongbinhluan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream:app/src/main/java/vn/iotstart/appdoctruyen/MainActivity.java
        setContentView(R.layout.login);
        
=======
        setContentView(R.layout.activity_binh_luan_cua_toi);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ThongTinTaiKhoan thongTinTaiKhoan= new ThongTinTaiKhoan();
        thongTinTaiKhoan.gettaikhoan(user.getEmail());
        id = thongTinTaiKhoan.tk.getId();

        tv_tongbinhluan = findViewById(R.id.tv_tongbinhluan);
        rcv_tongbinhluan = findViewById(R.id.rcv_tongbinhluan);

        getBinhLuanById();

    }

    void getBinhLuanById(){
>>>>>>> Stashed changes:app/src/main/java/vn/iotstar/appdoctruyen/BinhLuanCuaToi.java

    }
}