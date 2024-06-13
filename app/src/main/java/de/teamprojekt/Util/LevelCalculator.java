package de.teamprojekt.Util;

import static de.teamprojekt.Util.Constants.CalculationConstants.BASE_EXP;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_MUL;
import static de.teamprojekt.Util.Constants.CalculationConstants.INITIAL_EXP;

public class LevelCalculator {
    public static int calculateLevelCost(int level) {
        return (int) Math.floor(BASE_EXP * Math.pow(level, EXP_MUL) + INITIAL_EXP);
    }

    public static int calculateProgress(int level, int exp) {
        int levelCost = calculateLevelCost(level);
        return (int) Math.floor((double) (exp * 100) / levelCost);
    }

}
