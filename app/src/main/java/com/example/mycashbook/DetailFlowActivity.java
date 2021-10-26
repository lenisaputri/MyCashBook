package com.example.mycashbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycashbook.database.AksesDB;

public class DetailFlowActivity extends AppCompatActivity {
    Button btn_kembali;
    ListView listView;

    Integer[] ID, Nominal;
    String[] Keterangan, Tanggal, Flow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailflow);

        btn_kembali = findViewById(R.id.btn_kembali);
        listView = findViewById(R.id.listView);

        getData();

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailFlowActivity.this, BerandaActivity.class));
            }
        });
    }

    private void getData() {
        AksesDB aksesDB = AksesDB.getInstance(DetailFlowActivity.this);
        aksesDB.open();

        Cursor data = aksesDB.Get("tb_bukukas");

        if(data.getCount() == 0){
            Toast.makeText(DetailFlowActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            ID = new Integer[data.getCount()];
            Nominal = new Integer[data.getCount()];
            Keterangan = new String[data.getCount()];
            Tanggal = new String[data.getCount()];
            Flow = new String[data.getCount()];
            int i = 0;
            while (data.moveToNext()){
                ID[i] = data.getInt(0);
                Nominal[i] = data.getInt(1);
                Keterangan[i] = data.getString(2);
                Tanggal[i] = data.getString(3);
                Flow[i] = data.getString(4);
                i++;
            }
            listView.setAdapter(new AdapterDetailFlow(DetailFlowActivity.this, Nominal, Keterangan, Tanggal, Flow));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DetailFlowActivity.this, BerandaActivity.class));
    }
}
