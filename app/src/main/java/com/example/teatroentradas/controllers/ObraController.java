package com.example.teatroentradas.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.Toast;

import com.example.teatroentradas.AdminSQLiteOpenHelper;
import com.example.teatroentradas.Obra;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ObraController {

    AdminSQLiteOpenHelper adminSQL;

    public ObraController(Context context) {
        adminSQL = new AdminSQLiteOpenHelper(context, "BDD_entradas_teatro", null, 1);
    }


    public Obra getObraByTitulo(String tit) {
        Obra obra = new Obra();
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM obra WHERE titulo LIKE '" + tit + "%' LIMIT 1", null);

        if (cursor.moveToFirst()) {
            int idObtenido = cursor.getInt(0);
            String tituloObtenido = cursor.getString(1);
            String sinopsisObtenida = cursor.getString(2);
            Double entradaObtenida = cursor.getDouble(3);
            String desde = cursor.getString(4);
            String hasta = cursor.getString(5);

            obra.setId(idObtenido);
            obra.setTitulo(tituloObtenido);
            obra.setSinopsis(sinopsisObtenida);
            obra.setPrecio(entradaObtenida);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter formatter = null;
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                obra.setInicio(LocalDate.parse(desde, formatter));
                obra.setFin(LocalDate.parse(hasta, formatter));
            }
            cursor.close();
        } else {
            obra = null;
        }
        return obra;
    }

    public void cargarObra(String titulo, String sinopsis, String precio, String inicio, String fin) {
        Obra obra = new Obra();
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();

        obra.setTitulo(titulo);
        obra.setSinopsis(sinopsis);
        obra.setPrecio(Double.parseDouble(precio));
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            obra.setInicio(LocalDate.parse(inicio, formatter));
            obra.setFin(LocalDate.parse(fin, formatter));
        }
        ContentValues registroObra = new ContentValues();
        registroObra.put("titulo", obra.getTitulo());
        registroObra.put("sinopsis", obra.getSinopsis());
        registroObra.put("valor_entrada", obra.getPrecio());
        registroObra.put("inicio", String.valueOf(obra.getInicio()));
        registroObra.put("fin", String.valueOf(obra.getFin()));
        baseDatos.insert("obra", null, registroObra);
        baseDatos.close();

    }

    public int editarObra(String titulo, String sinopsis, String precio, String inicio, String fin, int id) {

        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();

        ContentValues editarObra = new ContentValues();
        editarObra.put("titulo", titulo);
        editarObra.put("sinopsis", sinopsis);
        editarObra.put("valor_entrada", precio);
        editarObra.put("inicio", inicio);
        editarObra.put("fin", fin);

        int filasActualizadas = baseDatos.update("obra", editarObra, "id_obra = ?", new String[]{String.valueOf(id)});
        return filasActualizadas;
    }

    public int eliminarObra(int id) {
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();

        int filasEliminadas = baseDatos.delete("obra", "id_obra = ?", new String[]{String.valueOf(id)});
        baseDatos.close();
        return filasEliminadas;
    }


    public List<Obra> getObras() {
        List<Obra> obras = new ArrayList<>();
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM obra", null);

        if (cursor.moveToFirst()) {
            do {
                Obra obra = new Obra();
                int id = cursor.getInt(0);
                String titulo = cursor.getString(1);
                obra.setId(id);
                obra.setTitulo(titulo);
                obra.setSinopsis(cursor.getString(2));
                obra.setPrecio(Double.parseDouble(cursor.getString(3)));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    obra.setInicio(LocalDate.parse(cursor.getString(4), formatter));
                    obra.setFin(LocalDate.parse(cursor.getString(5), formatter));
                }
                obras.add(obra);
            } while (cursor.moveToNext());
            cursor.close();
            baseDatos.close();
        }
        return obras;
    }

    public Obra getObraById(int id) {
        Obra obra = new Obra();
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM obra WHERE id_obra = " + id, null);

        if (cursor.moveToFirst()) {
            int idObtenido = cursor.getInt(0);
            String tituloObtenido = cursor.getString(1);
            String sinopsisObtenida = cursor.getString(2);
            Double entradaObtenida = cursor.getDouble(3);
            String desde = cursor.getString(4);
            String hasta = cursor.getString(5);

            obra.setId(idObtenido);
            obra.setTitulo(tituloObtenido);
            obra.setSinopsis(sinopsisObtenida);
            obra.setPrecio(entradaObtenida);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter formatter = null;
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                obra.setInicio(LocalDate.parse(desde, formatter));
                obra.setFin(LocalDate.parse(hasta, formatter));
            }
            cursor.close();
        } else {
            obra = null;
        }
        return obra;
    }


}
