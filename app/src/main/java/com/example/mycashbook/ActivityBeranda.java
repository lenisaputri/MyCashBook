package com.example.mycashbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.database.Cursor;

public class ActivityBeranda extends AppCompatActivity{
    LinearLayout income, outcome, detail, setting;
    TextView pengeluaran, pemasukkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        income = findViewById(R.id.income);
        outcome = findViewById(R.id.outcome);
        detail = findViewById(R.id.detail);
        setting = findViewById(R.id.setting);
        pengeluaran = findViewById(R.id.pengeluaran);
        pemasukkan = findViewById(R.id.pemasukkan);

        getSumIncome();
        getSumOutcome();

        //pindah halaman
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PemasukanActivity.class));
            }
        });

        outcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PengeluaranActivity.class));
            }
        });

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, DetailActivity.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PengaturanActivity.class));
            }
        });
    }

    private void getSumIncome() {
        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(MenuActivity.this);
        dataBaseAccess.open();

        Cursor data = dataBaseAccess.Sum("jumlah", "money", "flow = 'income'");

        if(data.getCount() == 0){
            pemasukkan.setText("Pemasukkan : Rp. 0.-");
        } else {
            while(data.moveToNext()){
                if(data.getString(0) != null) {
                    pemasukkan.setText("Pemasukkan : Rp. " + data.getString(0) + ".-");
                } else {
                    pemasukkan.setText("Pemasukkan : Rp. 0.-");
                }
            }
        }
    }

    private void getSumOutcome() {
        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(MenuActivity.this);
        dataBaseAccess.open();

        Cursor data = dataBaseAccess.Sum("jumlah", "money", "flow = 'outcome'");

        if(data.getCount() == 0){
            pengeluaran.setText("Pengeluaran : Rp. 0.-");
        } else {
            while(data.moveToNext()){
                if(data.getString(0) != null) {
                    pengeluaran.setText("Pengeluaran : Rp. " + data.getString(0) + ".-");
                } else {
                    pengeluaran.setText("Pengeluaran : Rp. 0.-");
                }
            }
        }
    }

    //keluar aplikasi
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        moveTaskToBack(true);
    }
}
