package com.example.teatroentradas.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import com.example.teatroentradas.AdminSQLiteOpenHelper;
import com.example.teatroentradas.Funcion;
import com.example.teatroentradas.Obra;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FuncionController {


    AdminSQLiteOpenHelper adminSQL;

    public FuncionController(Context context) {
        adminSQL = new AdminSQLiteOpenHelper(context, "BDD_entradas_teatro", null, 1);
    }


    public static List<LocalDate> getFechasFunciones(LocalDate inicio, LocalDate fin, DayOfWeek dia) {
        List<LocalDate> fechas = new ArrayList<>();
        LocalDate fecha = inicio;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            while (fecha.isBefore(fin)) {
                if (fecha.getDayOfWeek() == dia) {
                    fechas.add(fecha);
                }
                fecha = fecha.plusDays(1);
            }
        }

        return fechas;
    }

    public DayOfWeek obtenerDia(String diaSeleccionado) {
        DayOfWeek day = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            switch (diaSeleccionado) {
                case "lunes":
                    day = DayOfWeek.MONDAY;
                    break;
                case "martes":
                    day = DayOfWeek.TUESDAY;
                    break;
                case "miércoles":
                    day = DayOfWeek.WEDNESDAY;
                    break;
                case "jueves":
                    day = DayOfWeek.THURSDAY;
                    break;
                case "viernes":
                    day = DayOfWeek.FRIDAY;
                    break;
                case "sábado":
                    day = DayOfWeek.SATURDAY;
                    break;
                case "domingo":
                    day = DayOfWeek.SUNDAY;
                    break;
            }
        }
        return day;
    }

    public List<Funcion> getFunciones() {
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM funcion JOIN obra ON fk_obra = id_obra order by titulo", null);
        List<Funcion> funcionesList = new ArrayList<>();
      //  funcionesList.add(0, new Funcion(0, "Seleccionar funcion"));
        if (cursor.moveToFirst()) {
            do {
                Funcion funcion = new Funcion();
                funcion.setId(cursor.getInt(0));
                funcion.setIdobra(Integer.parseInt(cursor.getString(1)));
                funcion.setFecha(cursor.getString(2));
                funcion.setHora(cursor.getString(3));
                funcion.setEntradasDisponibles(Integer.parseInt(cursor.getString(4)));
                Obra obra = new Obra();
                obra.setId(funcion.getIdobra());
                obra.setTitulo(cursor.getString(6));
                obra.setSinopsis(cursor.getString(7));
                obra.setPrecio(cursor.getDouble(8));

                funcion.setObra(obra);
                funcionesList.add(funcion);
            } while (cursor.moveToNext());
        }
        cursor.close();
        baseDatos.close();
        return funcionesList;
    }

    public void altaFuncion(Obra obra) {

        List<Funcion> funciones = obra.getFunciones();
            SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
            ContentValues cargaFuncion = new ContentValues();
            for (Funcion fun : funciones) {
                cargaFuncion.put("fk_obra", obra.getId());
                cargaFuncion.put("fecha", fun.getFecha());
                cargaFuncion.put("hora", fun.getHora());
                cargaFuncion.put("capacidad", fun.getEntradasDisponibles());
                baseDatos.insert("funcion", null, cargaFuncion);
            }
    }


    public int eliminarFuncion(int id){
            SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
            int filasEliminadas = baseDatos.delete("funcion", "id_funcion = ?", new String[]{String.valueOf(id)});
            baseDatos.close();
            return filasEliminadas;
        }

    public void editarFuncion(String hora, int id) {
                SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
                ContentValues editarFuncion = new ContentValues();
                editarFuncion.put("hora", hora);
                baseDatos.update("funcion", editarFuncion, "id_funcion = ?", new String[]{String.valueOf(id)});
                baseDatos.close();
        }


    public Funcion getFuncionById(int id) {
        Funcion funcion = new Funcion();
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM funcion JOIN obra ON fk_obra = id_obra where id_funcion = " + id, null);
        if (cursor.moveToFirst()) {

          //  funcion = new Funcion();
            funcion.setId(cursor.getInt(0));
            funcion.setIdobra(Integer.parseInt(cursor.getString(1)));
            funcion.setFecha(cursor.getString(2));
            funcion.setHora(cursor.getString(3));
            funcion.setEntradasDisponibles(Integer.parseInt(cursor.getString(4)));
            Obra obra = new Obra();
            obra.setId(funcion.getIdobra());
            obra.setTitulo(cursor.getString(6));
            obra.setSinopsis(cursor.getString(7));
            obra.setPrecio(cursor.getDouble(8));
            funcion.setObra(obra);
        }
        return funcion;
    }

    public void setEntradas(int cantidad, int id){
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        ContentValues setFuncion = new ContentValues();
        setFuncion.put("capacidad", cantidad);
        baseDatos.update("funcion", setFuncion, "id_funcion = ?", new String[]{String.valueOf(id)});
        baseDatos.close();
    }

    public List<Funcion> getFuncionesByIdObra(int id) {
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM funcion where fk_obra = " + id, null);
        List<Funcion> funcionesList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Funcion funcion = new Funcion();
                funcion.setId(cursor.getInt(0));
                funcion.setIdobra(Integer.parseInt(cursor.getString(1)));
                funcion.setFecha(cursor.getString(2));
                funcion.setHora(cursor.getString(3));
                funcion.setEntradasDisponibles(Integer.parseInt(cursor.getString(4)));
                if(funcion.getEntradasDisponibles()>0){
                funcionesList.add(funcion);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        baseDatos.close();
        return funcionesList;
    }


}
