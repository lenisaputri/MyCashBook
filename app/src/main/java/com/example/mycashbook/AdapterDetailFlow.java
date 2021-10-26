package com.example.mycashbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AdapterDetailFlow extends ArrayAdapter {
    Integer[] Nominal;
    String[] Keterangan, Tanggal, Flow;

    public AdapterDetailFlow(@NonNull Context context, Integer[] Nominal, String[] Keterangan, String[] Tanggal, String[] Flow){
        super(context, R.layout.listview_detailflow, R.id.tanggal, Tanggal);
        this.Nominal = Nominal;
        this.Keterangan = Keterangan;
        this.Tanggal = Tanggal;
        this.Flow = Flow;
    }

    @SuppressLint("SetTextI18n")
    public View getView(final int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.listview_detailflow, parent, false);

        TextView text_nominal = row.findViewById(R.id.text_nominal);
        TextView text_keterangan = row.findViewById(R.id.text_keterangan);
        TextView text_tanggal = row.findViewById(R.id.text_tanggal);
        ImageView img_income = row.findViewById(R.id.img_income);
        ImageView img_outcome = row.findViewById(R.id.img_outcome);

        text_keterangan.setText(Keterangan[position]);
        text_tanggal.setText(Tanggal[position]);
        if(Flow[position].equals("income")){
            text_nominal.setText("[+] Rp. " + Nominal[position]);
            img_income.setVisibility(View.VISIBLE);
            img_outcome.setVisibility(View.GONE);
        } else {
            text_nominal.setText("[-] Rp. " + Nominal[position]);
            img_income.setVisibility(View.GONE);
            img_outcome.setVisibility(View.VISIBLE);
        }

        return row;
    }
}
