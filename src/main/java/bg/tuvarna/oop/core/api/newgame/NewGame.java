package bg.tuvarna.oop.core.api.newgame;

import bg.tuvarna.oop.core.api.base.Operation;
import bg.tuvarna.oop.core.api.base.OperationInput;
import bg.tuvarna.oop.core.api.base.OperationResult;
import lombok.*;

public interface NewGame extends Operation<NewGame.NewGameInput, NewGame.NewGameResponse> {
    @Builder
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    class NewGameInput implements OperationInput {
        private Long player1Id;
        private Long player2Id;
        private int maxDiesRows;
    }

    @Builder
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    class NewGameResponse implements OperationResult {
        private Long winningPlayerId;
    }
}
