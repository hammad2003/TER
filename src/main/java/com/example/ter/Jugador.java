    package com.example.ter;

public class Jugador {
    private String nombre;
    private int ganadas;
    private int perdidas;
    private int empatadas;

    public Jugador(String nombre, int ganadas, int perdidas, int empatadas) {
        this.nombre = nombre;
        this.ganadas = ganadas;
        this.perdidas = perdidas;
        this.empatadas = empatadas;
    }

    public Jugador(String nombre){
        this(nombre, 0, 0, 0);
    }

    public String getNombre() {
        return nombre;
    }

    public int getGanadas() {
        return ganadas;
    }

    public void incrementarGanadas() {
        this.ganadas++;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void incrementarPerdidas() {
        this.perdidas++;
    }

    public int getEmpatadas() {
        return empatadas;
    }

    public void incrementarEmpatadas() {
        this.empatadas++;
    }
}

