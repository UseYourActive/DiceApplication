package bg.tuvarna.oop.controller;

import bg.tuvarna.oop.core.api.newgame.NewGame;
import bg.tuvarna.oop.core.helper.GetAllPlayers;
import bg.tuvarna.oop.core.helper.GetRollsForStatistics;
import bg.tuvarna.oop.core.helper.StatisticsReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static bg.tuvarna.oop.core.api.newgame.NewGame.*;

//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//public class SimulationController implements Initializable {
@Component
public class SimulationController {
    @FXML
    private TextArea numberRolls;

    @FXML
    private ChoiceBox<String> player1;

    @FXML
    private ChoiceBox<String> player2;

    @FXML
    private Button startSimulation;

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
    private Label winnerTextBox;

    @FXML
    private BarChart chart;

    @FXML
    private AnchorPane chartPlace;

    @Autowired
    private GetAllPlayers getAllPlayers;

    @Autowired
    private GetRollsForStatistics getRollsForStatistics;

    @Autowired
    private StatisticsReport statisticsReport;

    @Autowired
    private NewGame newGame;

    @FXML
    public void initialize() {
        winnerTextBox.setVisible(false);
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
        int numRolls = Integer.parseInt(numberRolls.getText());
        long chosenPlayer1 = Long.parseLong(player1.getValue());
        long chosenPlayer2 = Long.parseLong(player2.getValue());

        if (numRolls > 200) {
            winnerTextBox.setText("More than possible rolls");
            winnerTextBox.setVisible(true);
        } else if (chosenPlayer1 == chosenPlayer2) {
            winnerTextBox.setText("Player 1 and Player 2 should be different");
            winnerTextBox.setVisible(true);
        } else {
            player1FirstName.setVisible(true);
            player2FirstName.setVisible(true);
            player1LastName.setVisible(true);
            player2LastName.setVisible(true);
            player1EGN.setVisible(true);
            player2EGN.setVisible(true);
            Map<String, String> player1info = getAllPlayers.getPlayerInfo(chosenPlayer1);
            Map<String, String> player2info = getAllPlayers.getPlayerInfo(chosenPlayer2);
            player1FirstName.setText("First name: " + player1info.get("firstName"));
            player2FirstName.setText("First name: " + player2info.get("firstName"));
            player1LastName.setText("Last name: " + player1info.get("lastName"));
            player2LastName.setText("Last name: " + player2info.get("lastName"));
            player1EGN.setText("EGN: " + player1info.get("egn"));
            player2EGN.setText("EGN: " + player2info.get("egn"));
            NewGameInput input = NewGameInput.builder()
                    .maxDiesRows(numRolls)
                    .player1Id(chosenPlayer1)
                    .player2Id(chosenPlayer2)
                    .build();
            NewGameResponse response = newGame.process(input);
            winnerTextBox.setText("The winner is: " + response.getWinningPlayerId().toString());
            winnerTextBox.setVisible(true);
            chart = statisticsReport.generateBarChart(getRollsForStatistics.getRollsForStatistics(), chart);
            statisticsReport.addTrendLine(chart, getRollsForStatistics.getRollsForStatistics());

        }
    }
}
