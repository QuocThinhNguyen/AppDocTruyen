package vn.iotstar.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText email,pass, confpass;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.txt_email);
        pass = findViewById(R.id.txt_password);
        confpass = findViewById(R.id.txt_confirmpassword);

        btnRegister = findViewById(R.id.btn_logingg);


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
                    Toast.makeText(getApplicationContext(),"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    intent.putExtra("email",emailtype);
                    startActivity(intent);
                }
                else Toast.makeText(getApplicationContext(),"Đăng kí thất bại!",Toast.LENGTH_SHORT).show();
            }
        });


    }

}