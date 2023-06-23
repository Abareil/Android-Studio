package com.example.teatroentradas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teatroentradas.controllers.UsuarioController;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, passLogin;
    AdminSQLiteOpenHelper adminSQL;
    Usuario usu = new Usuario();
    UsuarioController usuarioController = new UsuarioController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminSQL = new AdminSQLiteOpenHelper(this, "BDD_entradas_teatro", null, 1);
        usuario = (EditText)findViewById(R.id.txt_usuarioLogin);
        passLogin = (EditText)findViewById(R.id.txt_passLogin);
    }

     public void iniciarSesion(View view) {
        String usu = usuario.getText().toString();
        String pass = passLogin.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MainActivity.this.usu = usuarioController.validarUsuario(usu, pass);
                    if (MainActivity.this.usu != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                            usuario.setText("");
                            passLogin.setText("");
                            if (MainActivity.this.usu.getIsAdmin() == 1) {
                                Intent i3 = new Intent(MainActivity.this, ABMActivity.class);
                                startActivity(i3);
                            } else if (MainActivity.this.usu.getIsAdmin() == 0) {
                                Intent i1 = new Intent(MainActivity.this, CarteleraActivity.class);
                                startActivity(i1);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

    }



    public void registrarUsuario(View view){
        Intent i2 = new Intent(this, RegistroUsuarioActivity.class);
        startActivity(i2);
    }



}