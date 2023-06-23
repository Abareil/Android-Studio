package com.example.tp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Act3 extends AppCompatActivity {

  //  private TextView tv1;
    private TextView puntos;
    private int puntaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act3);
        puntos = findViewById(R.id.puntos);
        Bundle datos = getIntent().getExtras();
        this.puntaje = datos.getInt("p");
        puntos.setText(String.valueOf(this.puntaje));
    }

    public void inicio(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}