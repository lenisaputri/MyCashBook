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

public class PengaturanActivity extends AppCompatActivity {
    Button btn_simpan, btn_kembali;
    EditText input_passwordbaru, input_passwordlama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_kembali = findViewById(R.id.btn_kembali);
        input_passwordlama = findViewById(R.id.input_passwordlama);
        input_passwordbaru = findViewById(R.id.input_passwordbaru);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AksesDB aksesDB = AksesDB.getInstance(PengaturanActivity.this);
                aksesDB.open();

                Cursor data = aksesDB.Where("tb_login", "username = 'USER' AND password ='" + input_passwordlama.getText().toString() + "'");
                if (data.getCount() == 0) {
                    Toast.makeText(PengaturanActivity.this, "Password Lama yang Anda Masukkan Salah", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = aksesDB.updateUser(input_passwordbaru.getText().toString(), "USER");
                    if(isUpdated){
                        Toast.makeText(PengaturanActivity.this, "Password Anda Berhasil Diganti", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PengaturanActivity.this, BerandaActivity.class));
                    } else {
                        Toast.makeText(PengaturanActivity.this, "Password Anda Gagal Diganti", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PengaturanActivity.this, BerandaActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PengaturanActivity.this, BerandaActivity.class));
    }
}
