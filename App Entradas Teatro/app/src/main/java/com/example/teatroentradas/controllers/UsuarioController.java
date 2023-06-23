package com.example.teatroentradas.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.teatroentradas.AdminSQLiteOpenHelper;
import com.example.teatroentradas.MainActivity;
import com.example.teatroentradas.RegistroUsuarioActivity;
import com.example.teatroentradas.Usuario;

public class UsuarioController {

    AdminSQLiteOpenHelper adminSQL;

    public UsuarioController(Context context) {
        adminSQL = new AdminSQLiteOpenHelper(context, "BDD_entradas_teatro", null, 1);
    }

    public Usuario validarUsuario(String usu, String pass) {
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        Usuario usuario = new Usuario();
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM usuario WHERE email = '" + usu + "' AND pass = '" + pass + "'", null);
        if (cursor.moveToFirst()) {
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setPass(cursor.getString(4));
            usuario.setIsAdmin(cursor.getInt(5));
            cursor.close();
            baseDatos.close();
        }
        return usuario;
    }

    public void cargarUsuario(String nombre, String apellido, String email, String pass){
            SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
            ContentValues registroUsuario = new ContentValues();
            registroUsuario.put("nombre", nombre);
            registroUsuario.put("apellido", apellido);
            registroUsuario.put("email", email);
            registroUsuario.put("pass", pass);
            baseDatos.insert("usuario", null, registroUsuario);
            baseDatos.close();
    }

    public boolean validarEmail(String email) {
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();

        Cursor cursor = baseDatos.rawQuery("SELECT email FROM usuario WHERE email = '" + email + "'", null);
        boolean validado;

        if (cursor.getCount() > 0) {
            validado = false;
        } else {
            validado = true;
        }
        return validado;
    }

}
