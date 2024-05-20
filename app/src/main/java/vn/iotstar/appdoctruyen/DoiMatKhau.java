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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoiMatKhau extends AppCompatActivity implements View.OnClickListener {

    TextView mkht, mkm1,mkm2;
    FirebaseUser user;
    ProgressDialog prg;
    String email;
    Boolean prgisshow = false;

    Button ht,huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        Anhxa();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_xacnhan) {
            String mkht1, mk11, mk21;
            mkht1 = mkht.getText().toString();
            mk11 = mkm1.getText().toString();
            mk21 = mkm2.getText().toString();
            if (TextUtils.isEmpty(mkht1)) {
                Toast.makeText(this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mk11)) {
                Toast.makeText(this, "Vui lòng nhập password!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(mk21)) {
                Toast.makeText(this, "Vui lòng nhập password!", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!mk11.equals(mk21)) {
                Toast.makeText(this, "Xác nhận mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                return;
            }

            user = FirebaseAuth.getInstance().getCurrentUser();
            email = user.getEmail();
            if (user != null) {
                AuthCredential credential = EmailAuthProvider.getCredential(email, mkht1);
                user.reauthenticate(credential).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Người dùng đã được xác thực lại thành công, cập nhật mật khẩu
                        updatePassword(user, mk11);
                    } else {
                        Toast.makeText(this,"Mật khẩu hiện tại không chính xác",Toast.LENGTH_SHORT).show();

                    }

                });
            }
        }

            if (v.getId() == R.id.btn_huy) {
                finish();
            }
        }


    void Anhxa(){
        mkht = findViewById(R.id.edt_matkhauhientai);
        mkm1 = findViewById(R.id.edt_matkhaumoi);
        mkm2 = findViewById(R.id.edt_nhaplaimatkhau);

        ht = findViewById(R.id.btn_xacnhan);
        huy = findViewById(R.id.btn_huy);

        ht.setOnClickListener(this);
        huy.setOnClickListener(this);
    }


    private void updatePassword(FirebaseUser user,String newpass) {
                    user.updatePassword(newpass).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this,"Cập nhật mật khẩu thành công",Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this,"Mật khẩu mới không hợp lệ",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            finish();
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