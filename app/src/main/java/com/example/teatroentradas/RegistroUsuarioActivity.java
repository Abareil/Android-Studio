package com.example.teatroentradas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teatroentradas.controllers.UsuarioController;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText nombre, apellido, email, pass, pass2;
    AdminSQLiteOpenHelper adminSQL;
    UsuarioController usuarioController = new UsuarioController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        adminSQL = new AdminSQLiteOpenHelper(this, "BDD_entradas_teatro", null, 1);
        nombre = (EditText)findViewById(R.id.txt_nombre);
        apellido = (EditText)findViewById(R.id.txt_apellido);
        email = (EditText)findViewById(R.id.txt_email);
        pass = (EditText)findViewById(R.id.txt_pass);
        pass2 = (EditText)findViewById(R.id.txt_pass2);
    }

    //registrar un usuario en la base de datos
    public void registrarUsuario(View view){
        String nom = nombre.getText().toString();
        String ape = apellido.getText().toString();
        String mail = email.getText().toString();
        String password1 = pass.getText().toString();
        String password2 = pass2.getText().toString();

        boolean passValidada = this.compararPass(password1, password2);
        boolean emailValidado = usuarioController.validarEmail(mail);
        if(!emailValidado){
            Toast.makeText(this, "Ya existe una cuenta registrada con este email", Toast.LENGTH_SHORT).show();
        }
        if (!nom.isEmpty() && !ape.isEmpty() && !mail.isEmpty() && !password1.isEmpty() && !password2.isEmpty() && passValidada && emailValidado){
            usuarioController.cargarUsuario(nom, ape, mail, password1);
            nombre.setText("");
            apellido.setText("");
            email.setText("");
            pass.setText("");
            pass2.setText("");
            Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_LONG).show();
        }

    }

    //valida contraseñas
    public boolean compararPass(String pass1, String pass2){
        if (pass1.equals(pass2)){
            return true;
        } else {
            Toast.makeText(this, "Contraseñas distintas", Toast.LENGTH_SHORT).show();
            return false;
        }
    }



}