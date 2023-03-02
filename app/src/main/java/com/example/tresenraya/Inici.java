package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import java.text.MessageFormat;

public class Inici extends AppCompatActivity {
    ImageView comingsoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inici);
        getSupportActionBar().hide();
        ImageView tutorial = findViewById(R.id.comojugar);
        setupSetMessage();
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inici.this, Tutorial.class));
            }
        });


    }

    private void setupSetMessage() {
        comingsoon = findViewById(R.id.enlinea);
        comingsoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                Comingsoon cm = new Comingsoon();

                cm.show(manager, "MessageDialog");
            }
        });
    }


}