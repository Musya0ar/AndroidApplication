package com.uas.a082111001_umar_musyaffa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView textViewNameDetail = findViewById(R.id.textViewNameDetail);
        TextView textViewHargaDetail = findViewById(R.id.textViewHargaDetail);
        TextView textViewDeskripsiDetail = findViewById(R.id.textViewDeskripsiDetail);

        Intent intent = getIntent();
        String namaBarang = intent.getStringExtra("namaBarang");
        String hargaBarang = intent.getStringExtra("hargaBarang");
        String deskripsiBarang = intent.getStringExtra("deskripsiBarang");
        String gambarBarang = getIntent().getStringExtra("gambarBarang");


        textViewNameDetail.setText(namaBarang);
        textViewHargaDetail.setText(hargaBarang);
        textViewDeskripsiDetail.setText(deskripsiBarang);


        Glide.with(this)
                .load(gambarBarang)
                .into(imageViewDetail);

    }

}
