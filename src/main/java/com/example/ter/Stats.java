package com.example.ter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;

public class Stats {
    private TableView<Jugador> table;
    private Stage stage;
    private ObservableList<Jugador> data;

    public Stats(){
        data = FXCollections.observableArrayList();
    }

    public void updatePlayerStat(String name, String stat, int value) {
        Jugador player = data.stream().filter(p -> p.getNombre().equals(name)).findFirst().orElseGet(() -> {
            Jugador newPlayer = new Jugador(name);
            data.add(newPlayer);
            return newPlayer;
        });

        switch (stat) {
            case "win":
                player.incrementarGanadas();
                break;
            case "lost":
                player.incrementarPerdidas();
                break;
            case "draw":
                player.incrementarEmpatadas();
                break;
            default:
                System.out.println("La estadística especificada no es válida");
        }
    }

    public void showTable(boolean theme){
        stage = new Stage();
        table = new TableView<>();
        table.setItems(this.data);

        data.sort(Comparator.comparing(Jugador::getGanadas).reversed());

        table.getColumns().addAll(
                createColumn("Nombre", "nombre", 200),
                createColumn("Ganadas", "ganadas", 100),
                createColumn("Perdidas", "perdidas", 100),
                createColumn("Empatadas", "empatadas", 100)
        );

        Scene scene = new Scene(new VBox(table), 502, 400);

        stage.setScene(scene);
        stage.setTitle("Estadísticas de Jugadores");
        stage.setResizable(false);
        stage.show();
    }

    private <S,T> TableColumn<S,T> createColumn(String title, String property, int width) {
        TableColumn<S,T> column = new TableColumn<>(title);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }
}