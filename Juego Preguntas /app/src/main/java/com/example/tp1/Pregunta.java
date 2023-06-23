package com.example.tp1;

public class Pregunta {

    // private String dificultad;
    private String pregunta;
    private String respuesta1;
    private String respuesta2;
    private String respuesta3;
    private Integer puntaje;
    private String correcta;



    public Pregunta() {
        super();
        this.puntaje = 1;
    }

    public Pregunta(String pregunta, String correcta, String respuesta1, String respuesta2, String respuesta3) {
        super();
        this.pregunta = pregunta;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.correcta = correcta;
        this.puntaje = 1;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Integer getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }


    @Override
    public String toString() {
        return  pregunta ;
    }
}
