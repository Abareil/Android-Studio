package com.example.tp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.in1);

    }


        public void iniciar(View v){
            Intent i1 = new Intent(this, act2.class);
            String n = nombre.getText().toString();
            i1.putExtra("n", n);
            startActivity(i1);
        }


}