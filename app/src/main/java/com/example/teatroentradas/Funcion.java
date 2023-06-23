package com.example.teatroentradas;

import java.time.LocalDate;

public class Funcion {

    private LocalDate fecha2;
    private String fecha;
    private String dia;
    private String mes;
    private String anio;
    private String hora;
    private Integer entradasDisponibles;
    private Integer idobra;
    private Obra obra;
    Integer id;

    public Funcion(int i, String str) {
        this.setId(i);
        this.setFecha("");
        this.setHora("");
        this.obra = new Obra();
        this.getObra().setTitulo(str);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcion() {
    }

    public LocalDate getFecha2() {
        return fecha2;
    }

    public void setFecha2(LocalDate fecha2) {
        this.fecha2 = fecha2;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Integer getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(Integer entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }

    public Integer getIdobra() {
        return idobra;
    }

    public void setIdobra(Integer idobra) {
        this.idobra = idobra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Función: " + " " + fecha + " " + hora;
    }

}
