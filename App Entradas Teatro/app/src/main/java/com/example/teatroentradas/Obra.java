package com.example.teatroentradas;

import java.time.LocalDate;
import java.util.List;

public class Obra {

    private Integer id;
    private String titulo;
    private List<Funcion> funciones;
    private String sinopsis;
    private Double precio;
    private LocalDate inicio;
    private LocalDate fin;
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Obra() {
    }

    public Obra(String titulo, List<Funcion> funciones, String sinopsis, Double precio) {
        this.titulo = titulo;
        this.funciones = funciones;
        this.sinopsis = sinopsis;
        this.precio = precio;
    }


    public Obra(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
