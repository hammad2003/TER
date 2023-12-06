package com.example.ter;

public class Partida {

    private boolean turno = true;
    private int[][] tablero = new int[3][3];

    public void cambiarTurno(){
        turno = !turno;
    }

    public void restart(){
        tablero = new int[3][3];
    }

    public void marcar(int nBtn){
        int fila = nBtn / 3;
        int columna = nBtn % 3;
        tablero[fila][columna] = turno ? 1 : 2;
    }

    public int nTablero(int n){
        int fila = n / 3;
        int columna = n % 3;
        return tablero[fila][columna];
    }

    public int comprobarGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return tablero[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return tablero[0][j];
            }
        }

        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return tablero[0][0];
        }
        if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return tablero[0][2];
        }

        return 0;
    }
}
