package com.example.teatroentradas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BDD_Entradas_teatro) {
        BDD_Entradas_teatro.execSQL("create table teatro (id_teatro integer primary key autoincrement, nombre varchar(100), calle varchar(100), numero integer, capacidad_sala integer)");
        BDD_Entradas_teatro.execSQL("create table obra (id_obra integer primary key autoincrement, titulo varchar(100), sinopsis varchar(300), valor_entrada real, inicio text, fin text)");
        BDD_Entradas_teatro.execSQL("create table funcion (id_funcion integer primary key autoincrement, fk_obra integer, fecha text, hora text, capacidad int, foreign key (fk_obra) references obra(id_obra))");
        BDD_Entradas_teatro.execSQL("create table usuario (id_usuario integer primary key autoincrement, nombre varchar(50), apellido varchar(50), email varchar(50), pass varchar(10), is_admin integer default 1)");
        BDD_Entradas_teatro.execSQL("create table reserva (num_reserva integer primary key autoincrement, fk_funcion integer, fk_usuario integer, cantidad_entradas integer, foreign key (fk_funcion) references funcion(id_funcion), foreign key (fk_usuario) references usuario(id_usuario))");
        BDD_Entradas_teatro.execSQL("create table obra_por_teatro (fk_obra integer, fk_teatro integer, foreign key (fk_obra) references obra(id_obra), foreign key (fk_teatro) references teatro(id_teatro))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
