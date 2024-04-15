package bg.tuvarna.oop.controller;

import bg.tuvarna.oop.core.api.newgame.NewGame;
import bg.tuvarna.oop.core.helper.GetAllPlayers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static bg.tuvarna.oop.core.api.newgame.NewGame.*;

@Component
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SimulationController implements Initializable {
    @FXML
    private TextArea numberrolls;

    @FXML
    private ChoiceBox<String> player1;

    @FXML
    private ChoiceBox<String> player2;

    @FXML
    private Button startsimulation;

    @FXML
    private Label winner;

    @FXML
    private Label winnertxtbox;

    @Autowired
    private GetAllPlayers getAllPlayers;

    @Autowired
    private NewGame newGame;

    @FXML
    public void initialize() {
        winner.setVisible(false);
        winnertxtbox.setVisible(false);

        ObservableList<String> listPlayers = FXCollections.observableArrayList();
        listPlayers.addAll(getAllPlayers.getPlayers());
        player1.setItems(listPlayers);
        player2.setItems(listPlayers);
    }


    @FXML
    void startSim(ActionEvent event) {
        int numRolls = Integer.valueOf(numberrolls.getText());
        String choosedPlayer1 = player1.getValue();
        String choosedPlayer2 = player2.getValue();
        if(numRolls > 200){
            winnertxtbox.setText("More than possible rolls");
            winnertxtbox.setVisible(true);
        } else if(choosedPlayer1.equals(choosedPlayer2)){
            winnertxtbox.setText("Player 1 and Player 2 should be different");
            winnertxtbox.setVisible(true);
        }
        else{
            NewGameInput input = NewGameInput.builder()
                    .maxDiesRows(numRolls)
                    .player1Id(Long.getLong(choosedPlayer1))
                    .player2Id(Long.getLong(choosedPlayer2))
                    .build();
            NewGameResponse response = newGame.process(input);
            winnertxtbox.setText("The winner is" + response.getWinningPlayerId().toString());
            winnertxtbox.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
