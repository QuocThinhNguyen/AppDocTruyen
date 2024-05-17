package vn.iotstar.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
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

public class SignUp extends AppCompatActivity {

    private EditText email,pass, confpass;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    FirebaseUser user;

    ProgressDialog prg;
    Boolean prgisshow = false;

    @Override
    protected void onStop() {
        super.onStop();
        if (prg.isShowing() || prgisshow == false) user.delete();
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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String emailtype, passtype,confpasstype;
        emailtype = email.getText().toString();
        passtype = pass.getText().toString();
        confpasstype = confpass.getText().toString();
        if (TextUtils.isEmpty(emailtype)){
            Toast.makeText(this,"Vui lòng nhập email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passtype)){
            Toast.makeText(this,"Vui lòng nhập password!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passtype.equals(confpasstype)){
            Toast.makeText(this,"Xác nhận mật khẩu không chính xác",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailtype,passtype).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    user = mAuth.getCurrentUser();
                    if (user != null) {
                        sendEmailVerification(user);
                    }
                }
                else Toast.makeText(getApplicationContext(),"Đăng kí thất bại!",Toast.LENGTH_SHORT).show();
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
}
