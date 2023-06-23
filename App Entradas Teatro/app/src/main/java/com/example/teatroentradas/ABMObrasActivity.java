package com.example.teatroentradas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teatroentradas.controllers.ObraController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ABMObrasActivity extends AppCompatActivity {

    Obra obra = new Obra();
    EditText titulo, sinopsis, precio, txt_buscador, inicio, fin;
    AdminSQLiteOpenHelper adminSQL;
    TextView id;
    ObraController obraController = new ObraController(this);
    ImageView imgObra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abm_obras);
        id = findViewById(R.id.txt_id);
        titulo = findViewById(R.id.txt_titulo);
        sinopsis = findViewById(R.id.txt_sinopsis);
        precio = findViewById(R.id.txt_precio);
        txt_buscador = findViewById(R.id.txt_buscador);
        inicio = findViewById(R.id.txt_inicio);
        fin = findViewById(R.id.txt_fin);
        adminSQL = new AdminSQLiteOpenHelper(this, "BDD_entradas_teatro", null, 1);
      //  ImageView imageButton = findViewById(R.id.btn_buscador);

    }


    public void cargarObra(View view){
      String tit = this.titulo.getText().toString();
        String sinop = this.sinopsis.getText().toString();
        String prec = this.precio.getText().toString();
        String desde = this.inicio.getText().toString();
        String hasta  = this.fin.getText().toString();
        String tituloObraExistente;
        this.obra = obraController.getObraByTitulo(tit);

        if (this.obra != null) {
            tituloObraExistente = this.obra.getTitulo();

        } else {
            tituloObraExistente = "";
            obra = new Obra();
        }
        if(!tituloObraExistente.equalsIgnoreCase(tit)) {
            if (!tit.isEmpty() && !sinop.isEmpty() && !prec.isEmpty() && !desde.isEmpty() && !hasta.isEmpty()) {
                //insertar en BDD
                obraController.cargarObra(tit, sinop, prec,desde,hasta);
                this.id.setText("Id");
                titulo.setText("");
                sinopsis.setText("");
                precio.setText("");
                inicio.setText("");
                fin.setText("");
                Toast.makeText(this, "Obra creada con exito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Ya existe una obra con ese titulo", Toast.LENGTH_LONG).show();

        }

    }

    public void editarObra(View view) {

            String idObra = id.getText().toString();
            String tit = this.titulo.getText().toString();
            String sinop = this.sinopsis.getText().toString();
            String prec = this.precio.getText().toString();
            String desde = this.inicio.getText().toString();
            String hasta = this.fin.getText().toString();

        if (!tit.isEmpty() && !sinop.isEmpty() && !prec.isEmpty() && !desde.isEmpty() && !hasta.isEmpty() && !idObra.equals("Id")) {
            int id = Integer.parseInt(idObra);
            int filasActualizadas = obraController.editarObra(tit, sinop, prec, desde, hasta, id);
            if (filasActualizadas > 0) {
                Toast.makeText(this, "Obra actualizada con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No se pudo actualizar la obra", Toast.LENGTH_LONG).show();
            }
            this.id.setText("Id");
            titulo.setText("");
            sinopsis.setText("");
            precio.setText("");
            txt_buscador.setText("");
            inicio.setText("");
            fin.setText("");
        } else {
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarObra(View view){
        String idObra = id.getText().toString();

        if (!idObra.equals("Id") && !idObra.isEmpty()) {

            int id = Integer.parseInt(idObra);

            int filasEliminadas = obraController.eliminarObra(id);
            if (filasEliminadas == 1) {
                Toast.makeText(this, "Obra eliminada con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No se pudo eliminar la obra", Toast.LENGTH_LONG).show();
            }
            this.id.setText("Id");
            titulo.setText("");
            sinopsis.setText("");
            precio.setText("");
            txt_buscador.setText("");
            inicio.setText("");
            fin.setText("");
        } else {
            Toast.makeText(this, "Debe seleccionar una obra", Toast.LENGTH_LONG).show();

        }
    }

    public void buscar(View view){
        String tit = txt_buscador.getText().toString();
        if(!tit.isEmpty()) {
            this.obra = obraController.getObraByTitulo(tit);
            if (obra != null) {
                id.setText(String.valueOf(obra.getId()));
                titulo.setText(obra.getTitulo());
                sinopsis.setText(obra.getSinopsis());
                precio.setText(String.valueOf(obra.getPrecio()));
                inicio.setText(String.valueOf(obra.getInicio()));
                fin.setText(String.valueOf(obra.getFin()));
                txt_buscador.setText("");
            } else {
                this.id.setText("Id");
                titulo.setText("");
                sinopsis.setText("");
                precio.setText("");
                txt_buscador.setText("");
                inicio.setText("");
                fin.setText("");
            }
        } else {
            this.id.setText("Id");
            titulo.setText("");
            sinopsis.setText("");
            precio.setText("");
            txt_buscador.setText("");
            inicio.setText("");
            fin.setText("");
        }
    }

    public void subirImagen(View view) {

    }



}