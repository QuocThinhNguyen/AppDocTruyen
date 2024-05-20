package vn.iotstar.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.AdminController.QuanLyTaiKhoan;
import vn.iotstar.appdoctruyen.model.TaiKhoanDto;
import vn.iotstar.appdoctruyen.model.Taikhoan;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText email, pass, confpass;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    FirebaseUser user;

    ProgressDialog prg;
    Boolean prgisshow = false;

    @Override
    protected void onStop() {
        super.onStop();
        if (prg.isShowing() || !prgisshow) user.delete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.txt_email);
        pass = findViewById(R.id.txt_password);
        confpass = findViewById(R.id.txt_confirmpassword);

        btnRegister = findViewById(R.id.btn_logingg);

        prg = new ProgressDialog(this);
        prg.setMessage("Đang đợi xác nhận email!");
        prg.setCancelable(false);

        //setOnClickListener();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                getData();
            }
        });
    }

    private void register() {
        String emailtype, passtype, confpasstype;
        emailtype = email.getText().toString();
        passtype = pass.getText().toString();
        confpasstype = confpass.getText().toString();
        if (TextUtils.isEmpty(emailtype)) {
            Toast.makeText(this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passtype)) {
            Toast.makeText(this, "Vui lòng nhập password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passtype.equals(confpasstype)) {
            Toast.makeText(this, "Xác nhận mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailtype, passtype).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = mAuth.getCurrentUser();
                    if (user != null) {
                        sendEmailVerification(user);
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendEmailVerification(FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Email xác minh đã được gửi
                        prg.show();
                        prgisshow = true;
                        Toast.makeText(this, "Email xác nhận đã được gửi tới " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        checkEmailVerification(user);
                    } else {
                        // Gửi email xác minh thất bại
                        user.delete();
                        Toast.makeText(this, "Gửi email xác nhận lỗi! Hủy đăng ký!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkEmailVerification(FirebaseUser user) {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable checkEmailVerified = new Runnable() {
            @Override
            public void run() {
                user.reload().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (user.isEmailVerified()) {
                            // Email đã được xác minh
                            prg.dismiss();
                            Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("email", email.getText().toString());
                            startActivity(intent);


                        } else {
                            // Tiếp tục kiểm tra sau một khoảng thời gian định kỳ
                            handler.postDelayed(this, 3000); // 3 giây
                        }
                    } else {
                        // Xử lý lỗi
                        user.delete();
                        Toast.makeText(getApplicationContext(), "Xảy ra lỗi! Hủy đăng ký!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        handler.post(checkEmailVerified);
    }

    private void setOnClickListener() {
        btnRegister.setOnClickListener(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_logingg) {
            String emailt = email.getText().toString();
            String matkhaut = pass.getText().toString();


            Taikhoan taikhoan = new Taikhoan(emailt, matkhaut, "Chuaxacdinh", "Chuaxacdinh", 0, 0);
            APIService.apiService.addTaiKhoan(taikhoan).enqueue(new Callback<Taikhoan>() {
                @Override
                public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {

                   Toast.makeText(SignUp.this, "Thêm thành công", Toast.LENGTH_SHORT);
//                    showTaiKhoan();
//                    cv_themtaikhoan.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<Taikhoan> call, Throwable t) {
                    Toast.makeText(SignUp.this, "Thêm thất bại", Toast.LENGTH_SHORT);
                }
            });
        }
    }

    public void getData(){
        String emailt = email.getText().toString();
        String matkhaut = pass.getText().toString();


        Taikhoan taikhoan = new Taikhoan(emailt, matkhaut, "Chuaxacdinh", "Chuaxacdinh", 0, 0);
        APIService.apiService.addTaiKhoan(taikhoan).enqueue(new Callback<Taikhoan>() {
            @Override
            public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {

                Toast.makeText(SignUp.this, "Thêm thành công", Toast.LENGTH_SHORT);
//                    showTaiKhoan();
//                    cv_themtaikhoan.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Taikhoan> call, Throwable t) {
                Toast.makeText(SignUp.this, "Thêm thất bại", Toast.LENGTH_SHORT);
            }
        });
    }
}
