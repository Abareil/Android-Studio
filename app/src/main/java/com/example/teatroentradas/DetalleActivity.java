package com.example.teatroentradas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teatroentradas.controllers.FuncionController;
import com.example.teatroentradas.controllers.ObraController;

import java.util.ArrayList;
import java.util.List;

public class DetalleActivity extends AppCompatActivity {


    Integer id;
    List<Funcion> funciones = new ArrayList<>();
    Funcion funcion = new Funcion();
    Obra obra = new Obra();
    FuncionController funcionController = new FuncionController(this);
    ObraController obraController = new ObraController(this);
    TextView titulo;
    TextView sinopsis;
    TextView butacas;
    TextView precio;
    TextView fecha;
    Spinner cantidad;
    ArrayAdapter<String> cantidadAdapter;
    Spinner spFunciones;
    ArrayAdapter<Funcion> funcionesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        this.id = id;
        titulo = findViewById(R.id.tit_detalle);
        sinopsis = findViewById(R.id.sinop_detalle);
        butacas = findViewById(R.id.lugares_detalle);
        precio = findViewById(R.id.precio_detalle);
        cantidad = findViewById(R.id.sp_cantidad);


        obra = obraController.getObraById(id);
        titulo.setText(obra.getTitulo());
        sinopsis.setText(obra.getSinopsis());
        precio.setText(String.valueOf(obra.getPrecio()));
        funciones = funcionController.getFuncionesByIdObra(id);
        butacas.setText("0");

        spFunciones = findViewById(R.id.funciones_obra);
        funcionesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, funciones);
        funcionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFunciones.setAdapter(funcionesAdapter);
        actualizarListaFunciones(id);

        cantidadAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Seleccionar cantidad", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        cantidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cantidad.setAdapter(cantidadAdapter);

        spFunciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position > 0) {
                    Funcion func = (Funcion) spFunciones.getSelectedItem();
                    int entradasDisponibles = func.getEntradasDisponibles();
                    butacas.setText(String.valueOf(entradasDisponibles));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });




    }

    public void verMapa(View view){
        Uri map = Uri.parse("geo:0,0?q=" + Uri.encode("Avenida Corrientes 1200, Ciudad Autonoma de Buenos Aires, Argentina"));
        Intent i1 = new Intent(Intent.ACTION_VIEW, map );
        startActivity(i1);
    }

    public void reservar(View view) {
        Intent i1 = new Intent(this, CarteleraActivity.class);
        String ent = (String) cantidad.getSelectedItem();
        Funcion func = (Funcion) spFunciones.getSelectedItem();
        int id = func.getId();
        String butacasValue = butacas.getText().toString();
        int disponibles = Integer.parseInt(butacasValue);

        if (func != null && func.getId() !=0 && !ent.equals("0")) {
            try {
                int cantidadButacas = Integer.parseInt(ent);
                int actualizadas = disponibles - cantidadButacas;
                Toast.makeText(this, "Entradas reservadas, revisa tu casilla de Email", Toast.LENGTH_SHORT).show();
                cantidad.setSelection(0);
                spFunciones.setSelection(0);
                funcionController.setEntradas(actualizadas, id);
                actualizarListaFunciones(this.id);
                startActivity(i1);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else  {
            Toast.makeText(this, "Selecciona todas las opciones", Toast.LENGTH_SHORT).show();
        }
    }



    public void actualizarListaFunciones(int id) {
        funcionesAdapter.clear();
        funciones.add(0, new Funcion(0, "Seleccionar función"));
        try {
            funcionesAdapter.addAll(funcionController.getFuncionesByIdObra(id));
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
        funcionesAdapter.notifyDataSetChanged();

        spFunciones.setAdapter(funcionesAdapter);
    }



}