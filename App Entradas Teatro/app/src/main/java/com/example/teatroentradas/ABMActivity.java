package com.example.teatroentradas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ABMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abmactivity);
    }

    public void irABMObras(View view){
        Intent i1 = new Intent(this, ABMObrasActivity.class);
        startActivity(i1);
    }

    public void irABMFunciones(View view){
        Intent i2 = new Intent(this, ABMFuncionesActivity.class);
        startActivity(i2);
    }

    public void verVistaPrevia(View view){
        Intent i3 = new Intent(this, CarteleraActivity.class);
        startActivity(i3);
    }

}