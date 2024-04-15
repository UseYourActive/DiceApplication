package bg.tuvarna.oop.core.helper;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class RandomNumberGenerator {
    private static Random random;
    private static int minPredictions = 2;
    private static int maxPredictions = 12;
    private static int minSingleDieRow = 1;
    private static int maxSingleDieRow = 6;

    public static int generatePredication(){
       return random.nextInt(maxPredictions - minPredictions + 1) + minPredictions;
    }

    public static int generateSingleDieRow(){
        return random.nextInt(maxSingleDieRow - minSingleDieRow + 1) + minSingleDieRow;
    }
}
