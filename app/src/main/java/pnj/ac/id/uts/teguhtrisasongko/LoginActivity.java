package pnj.ac.id.uts.teguhtrisasongko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    EditText edtEmail,edtPassword;
    Button actionLogin;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("UTS - Teguh Tri Sasongko - 18072421005", MODE_PRIVATE);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        actionLogin = findViewById(R.id.actionLogin);

        actionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().length() > 0 && edtPassword.getText().toString().length()>0) {
                    //cek login
                    if(edtEmail.getText().toString().equals("teguh@s.id") && edtPassword.getText().toString().equals("1234")) {
                        //sukses login
                        Intent intent = new Intent(pnj.ac.id.uts.teguhtrisasongko.LoginActivity.this, pnj.ac.id.uts.teguhtrisasongko.HomeActivity.class);
                        startActivity(intent);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email","teguh@s.id");
                        editor.putString("nim","1807421005");
                        editor.putString("nama","Teguh Tri Sasongko");
                        editor.putString("kelas","TMJ 5");
                        editor.apply();
                        finish();
                    }else {
                        Toast.makeText(pnj.ac.id.uts.teguhtrisasongko.LoginActivity.this, "Mohon Maaf Email dan Password Salah", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(pnj.ac.id.uts.teguhtrisasongko.LoginActivity.this, "Mohon Lengkapi Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}