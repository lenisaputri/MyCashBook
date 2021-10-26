package com.example.mycashbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycashbook.database.AksesDB;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        cekUser();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                    username.setError("");
                    password.setError("");
                    Toast.makeText(LoginActivity.this, "Harap Lengkapi Data Anda", Toast.LENGTH_SHORT).show();
                } else {
                    AksesDB aksesDB = AksesDB.getInstance(LoginActivity.this);
                    aksesDB.open();

                    Cursor data = aksesDB.Where("tb_login", "username = '" + username.getText().toString().toUpperCase() + "' AND password = '" + password.getText().toString() + "'");

                    if(data.getCount() == 0){
                        Toast.makeText(LoginActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(LoginActivity.this, BerandaActivity.class)); //pindah ke halaman menu
                    }
                }
            }
        });
    }

    private void cekUser() {
        AksesDB aksesDB = AksesDB.getInstance(LoginActivity.this);
        aksesDB.open();

        Cursor data = aksesDB.Get("tb_login");

        if(data.getCount() == 0){
            boolean isInserted = aksesDB.insertUser("USER", "user");

            if(isInserted){
                Toast.makeText(LoginActivity.this, "User Dibuat", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "User Gagal Dibuat", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        moveTaskToBack(true);
    }
}