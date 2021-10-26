package com.example.mycashbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycashbook.database.AksesDB;

public class BerandaActivity extends AppCompatActivity{
    LinearLayout income, outcome, detailflow, pengaturan;
    TextView total_pengeluaran, total_pemasukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        income = findViewById(R.id.income);
        outcome = findViewById(R.id.outcome);
        detailflow = findViewById(R.id.detailflow);
        pengaturan = findViewById(R.id.pengaturan);
        total_pengeluaran = findViewById(R.id.total_pengeluaran);
        total_pemasukan = findViewById(R.id.total_pemasukan);

        getSumIncome();
        getSumOutcome();

        //pindah halaman
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this, IncomeActivity.class));
            }
        });

        outcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this, OutcomeActivity.class));
            }
        });

        detailflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this, DetailFlowActivity.class));
            }
        });

        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this, PengaturanActivity.class));
            }
        });
    }

    private void getSumIncome() {
        AksesDB aksesDB = AksesDB.getInstance(BerandaActivity.this);
        aksesDB.open();

        Cursor data = aksesDB.Sum("jumlah", "tb_bukukas", "flow = 'income'");

        if(data.getCount() == 0){
            total_pemasukan.setText("0.-");
        } else {
            while(data.moveToNext()){
                if(data.getString(0) != null) {
                    total_pemasukan.setText(data.getString(0) + ".-");
                } else {
                    total_pemasukan.setText("0.-");
                }
            }
        }
    }

    private void getSumOutcome() {
        AksesDB aksesDB = AksesDB.getInstance(BerandaActivity.this);
        aksesDB.open();

        Cursor data = aksesDB.Sum("jumlah", "tb_bukukas", "flow = 'outcome'");

        if(data.getCount() == 0){
            total_pengeluaran.setText("0.-");
        } else {
            while(data.moveToNext()){
                if(data.getString(0) != null) {
                    total_pengeluaran.setText(data.getString(0) + ".-");
                } else {
                    total_pengeluaran.setText("0.-");
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
