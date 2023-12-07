package com.example.ter;
import java.util.Random;

public class Partida {

    private boolean hayGanador;
    private int modo = 0;
    private boolean turno;
    private int numeroTurno;
    private int[][] tablero = new int[3][3];

    public Partida(){
        turno = true;
        modo = 1;
        hayGanador = false;
    }

    public int getModo() {
        return modo;
    }
    public void setModo(int tmpMode) {
        modo = tmpMode;
    }

    public boolean getTurno() {
        return turno;
    }
    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getNumeroTurno() {
        return numeroTurno;
    }
    public void setNumeroTurno(int numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public boolean isHayGanador() {
        return hayGanador;
    }
    public void setHayGanador(boolean hayGanador) {
        this.hayGanador = hayGanador;
    }

    public void cambiarTurno(){
        turno = !turno;
    }
    public void reiniciar(){
        tablero = new int[3][3];
        numeroTurno = 0;
        hayGanador = false;
    }
    public void marcar(int numeroBoton){
            int fila = numeroBoton / 3;
            int columna = numeroBoton % 3;
            tablero[fila][columna] = turnoMarcar();
    }

    public int obtenerValorEnTablero(int n){
        if (n >= 0 && n < 9) {
            int fila = n / 3;
            int columna = n % 3;
            return tablero[fila][columna];
        } else {
            return 99;
        }
    }

    public int turnoMarcar(){
        return turno ? 1 : 2;
    }

    public int comprobarGanador() {
        for (int i = 0; i < 3; i++) {
            if (comprobarLinea(tablero[i][0], tablero[i][1], tablero[i][2])) {
                return tablero[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (comprobarLinea(tablero[0][j], tablero[1][j], tablero[2][j])) {
                return tablero[0][j];
            }
        }

        if (comprobarLinea(tablero[0][0], tablero[1][1], tablero[2][2])) {
            return tablero[0][0];
        }
        if (comprobarLinea(tablero[0][2], tablero[1][1], tablero[2][0])) {
            return tablero[0][2];
        }

        return 0;
    }

    private boolean comprobarLinea(int a, int b, int c) {
        return a == b && b == c && a != 0;
    }

    public void randomTurno() {
        Random rd = new Random();
        turno = rd.nextBoolean();
    }
}