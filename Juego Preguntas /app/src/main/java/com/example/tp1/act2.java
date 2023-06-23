package com.example.tp1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class act2 extends AppCompatActivity {


    private Pregunta p1 = new Pregunta("SELECT es una consulta SQL de sublenguaje...", "DML" ,"DML", "DDL", "DCL");
    private Pregunta p2 = new Pregunta("en SQL DDL significa...", "Data Definition Language" ,"Data Duration Language", "Data Definition Language", "Data Distortion Language");
    private Pregunta p3 = new Pregunta("SELECT * FROM...", "tabla" ,"campo", "columna", "tabla");
    private Partida partidaA = new Partida();
    private TextView saludito;
    private TextView pregunta;
    private RadioButton op1;
    private RadioButton op2;
    private RadioButton op3;
    private Integer puntaje=0;
    private String nombre;
    private Pregunta random;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);
        saludito = findViewById(R.id.nom);
        Bundle datos = getIntent().getExtras();
        this.nombre = datos.getString("n");
        saludito.setText(this.nombre + ": " + puntaje + " puntos");
        Toast.makeText(this, "Hola " + nombre, Toast.LENGTH_SHORT).show();
        //agrego preguntas a la partida
        pregunta = findViewById(R.id.pregunta);
        this.generarPartida();
        this.preguntar();

    }

    public void generarPartida(){
        partidaA.preguntas.add(p2);
        partidaA.preguntas.add(p1);
        partidaA.preguntas.add(p3);
    }

    public void preguntar(){
        if (!partidaA.preguntas.isEmpty()) {
            //llamo al metodo random
            this.random = partidaA.getPreguntaRandom();
            pregunta.setText(String.valueOf(random));
            op1 = findViewById(R.id.btn1);
            op2 = findViewById(R.id.btn2);
            op3 = findViewById(R.id.btn3);
            op1.setText(random.getRespuesta1());
            op2.setText(random.getRespuesta2());
            op3.setText(random.getRespuesta3());
        } else {
            //la idea es que lleve a una tercer identity
       //     this.generarPartida();
         //   Toast.makeText(this, "Vuelve a empezar", Toast.LENGTH_LONG).show();
            Intent i2 = new Intent(this, Act3.class);
            Integer p = this.puntaje;
            i2.putExtra("p", p);
            startActivity(i2);
        }

    }


    public void responder(View v){

        if (op1.isChecked()){
           if (op1.getText() == this.random.getCorrecta()){
               Toast.makeText(this, "ACERTASTE!", Toast.LENGTH_SHORT).show();
               this.puntaje = this.puntaje + this.random.getPuntaje();
           } else {
               Toast.makeText(this, "UPS!", Toast.LENGTH_SHORT).show();
           }
        } else if (op2.isChecked()){
            if (op2.getText() == this.random.getCorrecta()){
                Toast.makeText(this, "ACERTASTE!", Toast.LENGTH_SHORT).show();
                this.puntaje = this.puntaje + this.random.getPuntaje();
            } else {
                Toast.makeText(this, "UPS!", Toast.LENGTH_SHORT).show();
            }

        } else if (op3.isChecked()){
            if (op3.getText() == this.random.getCorrecta()){
                Toast.makeText(this, "ACERTASTE!", Toast.LENGTH_SHORT).show();
                this.puntaje = this.puntaje + this.random.getPuntaje();
            } else {
                Toast.makeText(this, "UPS!", Toast.LENGTH_SHORT).show();
            }
        }

        saludito.setText(nombre + ": " + puntaje + " puntos");
        this.preguntar();
    }


        public void volver(View v){
                finish();
        }

}