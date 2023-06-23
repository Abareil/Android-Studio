package com.example.teatroentradas;

import static com.example.teatroentradas.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teatroentradas.controllers.FuncionController;
import com.example.teatroentradas.controllers.ObraController;

import java.util.ArrayList;

public class CarteleraActivity extends AppCompatActivity {

    ArrayList<Funcion> funciones;
    ArrayList<Obra> obras;
    RecyclerView rvCartelera;
    FuncionController funcionController = new FuncionController(this);
    ObraController obraController = new ObraController(this);
    TextView idObra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_cartelera);
        rvCartelera = (RecyclerView) findViewById(id.rv_cartelera);
        rvCartelera.setLayoutManager(new LinearLayoutManager(this));
        //funciones
        funciones = new ArrayList<>();
        funciones.addAll(funcionController.getFunciones());

        //obras
        obras = new ArrayList<>();
        obras.addAll(obraController.getObras());
        AdapterObras adapterObras = new AdapterObras(obras);
        rvCartelera.setAdapter(adapterObras);
    }

    public void irDetalle(View view){
        idObra = findViewById(id.id_obra_card);
        Intent i1 = new Intent(this, DetalleActivity.class);
        int id = Integer.parseInt(idObra.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        i1.putExtras(bundle);
        startActivity(i1);
    }



}