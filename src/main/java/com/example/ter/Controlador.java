package com.example.ter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Controlador {

    private static Partida partida = new Partida();

    @FXML
    Button btn_start, btn_stop, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, tmpBtn;

    List<Button> listBtn = new ArrayList<>(); // ? Lista de botones

    @FXML
    AnchorPane innerBorder, cambiarBordeTurno;

    @FXML
    ToolBar toolBar;

    private String colorJugador1 = "#0000FF";
    private String colorJugador2 = "#FF0000";

    @FXML
    RadioButton btn_gamemode0, btn_gamemode1;

    @FXML
    TextField textJugador1, textJugador2;

    public void seleccionarModoJuego(){
        if (btn_gamemode0.isSelected()) {
            System.out.println("Modo de juego 1");
            partida.setModo(1);
            textJugador1.setVisible(true);
            textJugador2.setVisible(true);
        } else if (btn_gamemode1.isSelected()) {
            System.out.println("Modo de juego 2");
            partida.setModo(2);
            textJugador1.setVisible(false);
            textJugador2.setVisible(false);
        }
    }

    @FXML
    public void iniciarJuego(ActionEvent event) {
        boolean gameCorrecto = false;
        partida.reiniciar();
        btn_start = (Button) event.getSource(); // Obtiene el botón del evento

        if (partida.getModo() == 1) {
            if (textJugador1.getText().trim().isEmpty() || textJugador2.getText().trim().isEmpty()) {
                Alertas.mostrarErrorFaltanNombres();
                textJugador1.clear();
                textJugador2.clear();
            } else if (textJugador1.getText().trim().equals(textJugador2.getText().trim())) {
                Alertas.mostrarErrorNombresRepetidos();
            } else {
                gameCorrecto = true;
            }
        } else {
            gameCorrecto = true;
        }

        if (gameCorrecto) {
            addAllBtn();
            btnEnable(); // Activa todos los botones
            switch (partida.getModo()) {
                case 1:
                    partida.randomTurno();
                    enableModes(false);
                    break;
                case 2, 3:
                    partida.setTurno(true);
                    break;
            }
            partida.setNumeroTurno(0);
            btn_start.setDisable(true);
            btn_stop.setDisable(false);
            btnResetColor();
            cambiarTurnoBorde(partida.getTurno());
            enableModes(false);
            enableNames(false);

            switch (partida.getModo()) {
                case 1:
                    System.out.println("START GAME - MODE PLAYER VS PLAYER");
                    break;
                case 2:
                    System.out.println("START GAME - MODE PLAYER VS MACHINE");
                    break;
            }
        }
    }

    @FXML
    public void detenerJuego(ActionEvent event) {
        btn_stop = (Button) event.getSource();
        Boolean confirmacion = Alertas.confirmarAbandonoPartida();

        if (confirmacion) {
            btn_stop.setDisable(true);
            btn_start.setDisable(false);
            enableModes(true);
            enableNames(true);
            btnDisable();
            btnResetColor();
            partida.reiniciar();
            cambiarBordeTurno.setBackground(new Background(new BackgroundFill(Color.web("#807E7D"), CornerRadii.EMPTY, Insets.EMPTY)));
            System.out.println("JUEGO DETENIDO");
        }
    }

    public void btnSelected(ActionEvent event) throws InterruptedException {
        tmpBtn = (Button) event.getSource();
        String bId = tmpBtn.getId().replaceAll("[btn]", "");
        int idBtn = Integer.valueOf(bId);
        tmpBtn.setDisable(true);
        partida.marcar(idBtn);
        cambiarTurnoBoton(idBtn);
        cambiarTurnoBorde();
        partida.cambiarTurno();
        partida.setNumeroTurno(partida.getNumeroTurno() + 1);
        partida.mostrarTableroLog();
        comprobarGanador();

        if (partida.getModo() == 2 && partida.isHayGanador()) {
            btnRandomGamemode2();
        }
    }

    public void btnRandomGamemode2() {
        int randomBtn = (int) ((Math.random() * (9 - 0)) + 0);
        if (partida.obtenerValorEnTablero(randomBtn) == 0) {
            listBtn.get(randomBtn).setDisable(true);
            partida.marcar(randomBtn);
            cambiarTurnoBoton(randomBtn);
            cambiarTurnoBorde();
            partida.cambiarTurno();
            partida.setNumeroTurno(partida.getNumeroTurno() + 1);
            partida.mostrarTableroLog();
            comprobarGanador();
        } else {
            btnRandomGamemode2();
        }
    }

    private void cambiarTurnoBoton(int nBtn) {
        switch (nBtn) {
            case 0:
                btn0.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 1:
                btn1.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 2:
                btn2.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 3:
                btn3.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 4:
                btn4.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 5:
                btn5.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 6:
                btn6.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 7:
                btn7.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case 8:
                btn8.setBackground(new Background(new BackgroundFill(
                        partida.getTurno() ? Color.web(colorJugador1) : Color.web(colorJugador2),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                break;
        }
    }

    private void cambiarTurnoBorde() {
        if (partida.getTurno()) {
            cambiarBordeTurno.setBackground(new Background(new BackgroundFill(
                    Color.web(colorJugador2), CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            cambiarBordeTurno.setBackground(new Background(new BackgroundFill(
                    Color.web(colorJugador1), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private void cambiarTurnoBorde(boolean turno) {
        if (turno) {
            cambiarBordeTurno.setBackground(new Background(new BackgroundFill(
                    Color.web(colorJugador1), CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            cambiarBordeTurno.setBackground(new Background(new BackgroundFill(
                    Color.web(colorJugador2), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private void addAllBtn(){
        listBtn.add(btn0);
        listBtn.add(btn1);
        listBtn.add(btn2);
        listBtn.add(btn3);
        listBtn.add(btn4);
        listBtn.add(btn5);
        listBtn.add(btn6);
        listBtn.add(btn7);
        listBtn.add(btn8);
    }

    private void btnEnable(){
        for(Button tmpBtn : listBtn){
            tmpBtn.setDisable(false);
        }
    }

    public void btnDisable(){
        for(Button tmpBtn : listBtn){
            tmpBtn.setDisable(true);
        }
    }

    public void btnResetColor() {
        for (Button tmpBtn : listBtn) {
            tmpBtn.setBackground(new Background(new BackgroundFill(
                    Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void enableModes(boolean active) {
        btn_gamemode0.setDisable(!active);
        btn_gamemode1.setDisable(!active);
    }

    public void enableNames(boolean active) {
        textJugador1.setDisable(!active);
        textJugador2.setDisable(!active);
    }

    public void comprobarGanador() {
        int resultadoComprobacion = partida.comprobarGanador();

        if (resultadoComprobacion == 1) {
            setGanador(1);
        } else if (resultadoComprobacion == 2) {
            setGanador(2);
        } else if (partida.getNumeroTurno() > 8 && resultadoComprobacion == 0) {
            setGanador(0);
        }
    }

    public void setGanador(int nGanador) {
        btnDisable();

        switch (nGanador) {
            case 0:
                cambiarBordeTurno.setBackground(new Background(new BackgroundFill(
                        Color.web("#807E7D"), CornerRadii.EMPTY, Insets.EMPTY)));
                System.out.println("EMPATE");
                break;
            case 1:
                cambiarBordeTurno.setBackground(new Background(new BackgroundFill(
                        Color.web(colorJugador1), CornerRadii.EMPTY, Insets.EMPTY)));
                System.out.println("GANADOR - Jugador 1");
                break;
            case 2:
                System.out.println("GANADOR - Jugador 2");
                cambiarBordeTurno.setBackground(new Background(new BackgroundFill(
                        Color.web(colorJugador2), CornerRadii.EMPTY, Insets.EMPTY)));
                break;
        }

        partida.setHayGanador(true);
        btn_start.setDisable(false);
        btn_stop.setDisable(true);
        enableModes(true);
        enableNames(true);
    }

}