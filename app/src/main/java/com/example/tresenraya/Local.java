package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Local extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        getSupportActionBar().hide();
        ImageView pvp = findViewById(R.id.pvp);
        ImageView ia = findViewById(R.id.ia);
        pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Local.this, Pantallacargapvp.class));
            }
        });
        ia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Local.this, Pantallacargaia.class));
            }
        });
    }
}