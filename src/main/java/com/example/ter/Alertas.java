package com.example.ter;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alertas {
    public static boolean abandonarPartida() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setHeaderText("¿Deseas abandonar la partida?");
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.YES;
    }

    public static void faltaNombres() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("¡Debes introducir el nombre de los jugadores antes de iniciar la partida!");
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        alert.showAndWait();
    }

    public static void nombresIguales() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("¡Los nombres de los jugadores no pueden ser iguales!");
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        alert.showAndWait();
    }
}
