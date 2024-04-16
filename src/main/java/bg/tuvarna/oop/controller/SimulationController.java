package bg.tuvarna.oop.controller;

import bg.tuvarna.oop.core.api.newgame.NewGame;
import bg.tuvarna.oop.core.helper.GetAllPlayers;
import bg.tuvarna.oop.core.helper.GetRollsForStatistics;
import bg.tuvarna.oop.core.helper.StatiscticsReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static bg.tuvarna.oop.core.api.newgame.NewGame.*;

//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//public class SimulationController implements Initializable {
@Component
public class SimulationController{
    @FXML
    private TextArea numberrolls;

    @FXML
    private ChoiceBox<String> player1;

    @FXML
    private ChoiceBox<String> player2;

    @FXML
    private Button startsimulation;

    @FXML
    private Label player2EGN;

    @FXML
    private Label player2FirstName;

    @FXML
    private Label player2LastName;

    @FXML
    private Label player1EGN;

    @FXML
    private Label player1FirstName;

    @FXML
    private Label player1LastName;


    @FXML
    private Label winnertxtbox;


    @FXML
    private AnchorPane chartPlace;

    @Autowired
    private GetAllPlayers getAllPlayers;

    @Autowired
    private GetRollsForStatistics getRollsForStatistics;

    @Autowired
    private StatiscticsReport statiscticsReport;

    @Autowired
    private NewGame newGame;

    @FXML
    public void initialize() {
        winnertxtbox.setVisible(false);
        player1FirstName.setVisible(false);
        player2FirstName.setVisible(false);
        player1LastName.setVisible(false);
        player2LastName.setVisible(false);
        player1EGN.setVisible(false);
        player2EGN.setVisible(false);

        ObservableList<String> listPlayers = FXCollections.observableArrayList();
        listPlayers.addAll(getAllPlayers.getPlayers());
        player1.setItems(listPlayers);
        player2.setItems(listPlayers);
    }


    @FXML
    void startSim(ActionEvent event) throws IOException {
        int numRolls = Integer.valueOf(numberrolls.getText());
        Long choosedPlayer1 = Long.valueOf(player1.getValue());
        Long choosedPlayer2 = Long.valueOf(player2.getValue());

        if(numRolls > 200){
            winnertxtbox.setText("More than possible rolls");
            winnertxtbox.setVisible(true);
        } else if(choosedPlayer1 == choosedPlayer2){
            winnertxtbox.setText("Player 1 and Player 2 should be different");
            winnertxtbox.setVisible(true);
        }
        else{
            player1FirstName.setVisible(true);
            player2FirstName.setVisible(true);
            player1LastName.setVisible(true);
            player2LastName.setVisible(true);
            player1EGN.setVisible(true);
            player2EGN.setVisible(true);
            Map<String, String> player1info = getAllPlayers.getPlayerInfo(choosedPlayer1);
            Map<String, String> player2info = getAllPlayers.getPlayerInfo(choosedPlayer2);
            player1FirstName.setText("First name: " + player1info.get("firstName"));
            player2FirstName.setText("First name: " + player2info.get("firstName"));
            player1LastName.setText("Last name: " + player1info.get("lastName"));
            player2LastName.setText("Last name: " + player2info.get("lastName"));
            player1EGN.setText("EGN: " + player1info.get("egn"));
            player2EGN.setText("EGN: " + player2info.get("egn"));
            NewGameInput input = NewGameInput.builder()
                    .maxDiesRows(numRolls)
                    .player1Id(choosedPlayer1)
                    .player2Id(choosedPlayer2)
                    .build();
            NewGameResponse response = newGame.process(input);
            winnertxtbox.setText("The winner is: " + response.getWinningPlayerId().toString());
            winnertxtbox.setVisible(true);
            JFreeChart chartToDisplay = statiscticsReport.generateChart(getRollsForStatistics.getRollsForStatistics());
            chartPlace.getChildren().add(new ChartViewer(chartToDisplay));
//            ChartViewer chartViewer = new ChartViewer();
//            chartViewer.setPrefWidth(560);
//            chartViewer.setPrefHeight(260);
//            chartViewer.setLayoutX(20);
//            chartViewer.setLayoutY(260);
//            chartViewer.setVisible(true);
        }
    }
}
