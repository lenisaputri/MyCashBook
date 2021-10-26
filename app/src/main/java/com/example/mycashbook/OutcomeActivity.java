package com.example.mycashbook;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycashbook.database.AksesDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class OutcomeActivity extends AppCompatActivity {
    Button btn_simpan, btn_kembali;
    EditText input_nominal, input_keterangan;
    ImageButton btn_tanggal;
    TextView tanggal;

    String tanggalIndo = "";

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdfTanggalIndo = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_kembali = findViewById(R.id.btn_kembali);
        input_nominal = findViewById(R.id.input_nominal);
        input_keterangan = findViewById(R.id.input_keterangan);
        btn_tanggal = findViewById(R.id.btn_tanggal);
        tanggal = findViewById(R.id.tanggal);

        tanggalIndo = sdfTanggalIndo.format(new Date());

        tanggal.setText(tanggalIndo);

        btn_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(OutcomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        try {
                            tanggalIndo = sdfTanggalIndo.format(Objects.requireNonNull(sdfTanggalIndo.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tanggal.setText(tanggalIndo);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_nominal.getText().toString().equals("") || input_keterangan.getText().toString().equals("")){
                    Toast.makeText(OutcomeActivity.this, "Data Harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    Integer jumlah = Integer.valueOf(input_nominal.getText().toString());

                    AksesDB aksesDB = AksesDB.getInstance(OutcomeActivity.this);
                    aksesDB.open();

                    boolean isInserted = aksesDB.insertMoney(jumlah, input_keterangan.getText().toString(), tanggal.getText().toString(), "outcome");

                    if(isInserted){
                        Toast.makeText(OutcomeActivity.this, "Data Pengeluaran Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OutcomeActivity.this, BerandaActivity.class));
                    } else {
                        Toast.makeText(OutcomeActivity.this, "Data Pengeluaran Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OutcomeActivity.this, BerandaActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(OutcomeActivity.this, BerandaActivity.class));
    }
}
