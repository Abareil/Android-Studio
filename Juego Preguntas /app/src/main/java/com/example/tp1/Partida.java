package com.example.tp1;

import java.util.ArrayList;

public class Partida {

    public ArrayList<Pregunta> preguntas;
    private Integer puntos;
    private Integer oportunidades;


    //CONSTRUCTOR

    public Partida(ArrayList<Pregunta> preguntas) {
        super();
        this.preguntas = new ArrayList();
        this.puntos = 0;
        this.oportunidades = 5;
    }


    public Partida() {
        super();
        this.preguntas = new ArrayList();
    }

    //obtengo una pregunta random

    public Pregunta getPreguntaRandom() {
        int tamaño = preguntas.size();
        int num = (int)(Math.random() * tamaño + 1);
        Pregunta p = preguntas.get(num-1);
        preguntas.remove(num-1);
        return p;
    }



    //GETTERS Y SETTERS

    public ArrayList<Pregunta> getListadoDePreguntas() {
        return preguntas;
    }


    public Integer getPuntos() {
        return puntos;
    }
    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
    public Integer getOportunidades() {
        return oportunidades;
    }
    public void setOportunidades(Integer oportunidades) {
        this.oportunidades = oportunidades;
    }

    @Override
    public String toString() {
        return "Partida [preguntas=" + preguntas + ", puntos=" + puntos + ", oportunidades="
                + oportunidades + "]";
    }
}
