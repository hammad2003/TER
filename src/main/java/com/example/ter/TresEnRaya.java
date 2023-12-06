package com.example.ter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TresEnRaya extends Application {

    private Jugador jugador1 = new Jugador("Jugador 1");
    private Jugador jugador2 = new Jugador("Jugador 2");
    private Jugador jugadorActual = jugador1;

    private Partida partida = new Partida();
    private Stats stats = new Stats();

    private Button[][] botones = new Button[3][3];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button boton = new Button();
                boton.setMinSize(100, 100);
                boton.setStyle("-fx-font-size: 2em;");
                int finalI = i;
                int finalJ = j;
                boton.setOnAction(e -> manejarClic(boton, finalI * 3 + finalJ));

                botones[i][j] = boton;
                gridPane.add(boton, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setTitle("Tres en Raya");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void manejarClic(Button boton, int nBtn) {
        if (boton.getText().equals("")) {
            boton.setText(jugadorActual.getNombre());
            partida.marcar(nBtn);
            int ganador = partida.comprobarGanador();
            if (ganador != 0) {
                System.out.println("¡Jugador " + jugadorActual.getNombre() + " ha ganado!");
                stats.updatePlayerStat(jugadorActual.getNombre(), "win", 1);
                reiniciarJuego();
            } else if (tableroLleno()) {
                System.out.println("¡Empate!");
                stats.updatePlayerStat(jugadorActual.getNombre(), "draw", 1);
                reiniciarJuego();
            } else {
                cambiarJugador();
            }
        }
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }

    private boolean tableroLleno() {
        for (Button[] fila : botones) {
            for (Button boton : fila) {
                if (boton.getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void reiniciarJuego() {
        for (Button[] fila : botones) {
            for (Button boton : fila) {
                boton.setText("");
            }
        }
        jugadorActual = jugador1;
        partida.restart();
    }
}
