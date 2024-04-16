package bg.tuvarna.oop.core.helper;

import bg.tuvarna.oop.persistence.repositories.GameRepository;
import bg.tuvarna.oop.persistence.repositories.RollsRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetRollsForStatistics {
    private final RollsRepository rollsRepository;
    private final GameRepository gameRepository;

    public Map<Integer, Integer> getRollsForStatistics() {
        Map<Integer, Integer> allRollsCount = new HashMap<>();
        allRollsCount.put(2, 0);
        allRollsCount.put(3, 0);
        allRollsCount.put(4, 0);
        allRollsCount.put(5, 0);
        allRollsCount.put(6, 0);
        allRollsCount.put(7, 0);
        allRollsCount.put(8, 0);
        allRollsCount.put(9, 0);
        allRollsCount.put(10, 0);
        allRollsCount.put(11, 0);
        allRollsCount.put(12, 0);
        var gameIds = gameRepository.findAll();
        gameIds.forEach(gameId ->{
            var rolls = rollsRepository.findRollsByGameId(gameId).orElseThrow();
            allRollsCount.put(2, allRollsCount.get(2) + rolls.getNumber2());
            allRollsCount.put(3, allRollsCount.get(3) + rolls.getNumber3());
            allRollsCount.put(4, allRollsCount.get(4) + rolls.getNumber4());
            allRollsCount.put(5, allRollsCount.get(5) + rolls.getNumber5());
            allRollsCount.put(6, allRollsCount.get(6) + rolls.getNumber6());
            allRollsCount.put(7, allRollsCount.get(7) + rolls.getNumber7());
            allRollsCount.put(8, allRollsCount.get(8) + rolls.getNumber8());
            allRollsCount.put(9, allRollsCount.get(9) + rolls.getNumber9());
            allRollsCount.put(10, allRollsCount.get(10) + rolls.getNumber10());
            allRollsCount.put(11, allRollsCount.get(11) + rolls.getNumber11());
            allRollsCount.put(12, allRollsCount.get(12) + rolls.getNumber12());
        });
     return allRollsCount;
    }
}
