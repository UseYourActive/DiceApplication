package bg.tuvarna.oop.core.operations;

import bg.tuvarna.oop.core.api.exceptions.PlayerNotExistsException;
import bg.tuvarna.oop.core.api.newgame.NewGame;
import bg.tuvarna.oop.core.helper.RandomNumberGenerator;
import bg.tuvarna.oop.persistence.entities.Game;
import bg.tuvarna.oop.persistence.entities.Rolls;
import bg.tuvarna.oop.persistence.repositories.GameRepository;
import bg.tuvarna.oop.persistence.repositories.PlayerRepository;
import bg.tuvarna.oop.persistence.repositories.RollsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class NewGameImplementation implements NewGame {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final RollsRepository rollsRepository;

    @Override
    public NewGameResponse process(NewGameInput getInput) {
        var player1 = playerRepository.findById(getInput.getPlayer1Id())
                .orElseThrow(() -> new PlayerNotExistsException(format("Player with this id %s does not exist", getInput.getPlayer1Id().toString())));
        var player2 = playerRepository.findById(getInput.getPlayer2Id())
                .orElseThrow(() -> new PlayerNotExistsException(format("Player with this id %s does not exist", getInput.getPlayer2Id().toString())));

        Game game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        gameRepository.save(game);
        Rolls rolls = Rolls.builder().gameId(game).build();
        //var rollsRepo = rowsRepository.findRollsByGameId(game.getId());
        for (int i = 0; i < getInput.getMaxDiesRows(); i++) {
            int predictionPlayer1 = RandomNumberGenerator.generatePredication();
            int predictionPlayer2 = RandomNumberGenerator.generatePredication();

            int diesRows = RandomNumberGenerator.generateSingleDieRow() + RandomNumberGenerator.generateSingleDieRow();
            markRowedValue(diesRows, rolls);
            rollsRepository.save(rolls);
            if (predictionPlayer1 == diesRows){
                game.setPlayer1WinnedRows(game.getPlayer1WinnedRows()+1);
            }else if( predictionPlayer2 == diesRows){
                game.setPlayer2WinnedRows(game.getPlayer2WinnedRows()+1);
            }
        }

        NewGameResponse response = NewGameResponse.builder().build();
        if(game.getPlayer1WinnedRows() > game.getPlayer2WinnedRows()){
            response.setWinningPlayerId(player1.getId());
        }else{
            response.setWinningPlayerId(player2.getId());
        }
        return response;
    }

    private void markRowedValue(int rowedValue, Rolls rows) {
        switch (rowedValue) {
            case 2: {
                rows.setNumber2(rows.getNumber2() + 1);
            }
            return;
            case 3: {
                rows.setNumber3(rows.getNumber3() + 1);
            }
            return;
            case 4: {
                rows.setNumber4(rows.getNumber4() + 1);
            }
            return;
            case 5: {
                rows.setNumber5(rows.getNumber5() + 1);
            }
            return;
            case 6: {
                rows.setNumber6(rows.getNumber6() + 1);
            }
            return;
            case 7: {
                rows.setNumber7(rows.getNumber7() + 1);
            }
            return;
            case 8: {
                rows.setNumber8(rows.getNumber8() + 1);
            }
            return;
            case 9: {
                rows.setNumber9(rows.getNumber9() + 1);
            }
            return;
            case 10: {
                rows.setNumber10(rows.getNumber10() + 1);
            }
            return;
            case 11: {
                rows.setNumber11(rows.getNumber11() + 1);
            }
            return;
            default: {
                rows.setNumber12(rows.getNumber12() + 1);
            }

        }
    }
}
