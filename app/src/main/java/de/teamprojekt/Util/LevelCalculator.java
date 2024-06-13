package de.teamprojekt.Util;

import static de.teamprojekt.Util.Constants.CalculationConstants.BASE_EXP;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_HIGH_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_LOW_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_MEDIUM_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_MUL;
import static de.teamprojekt.Util.Constants.CalculationConstants.INITIAL_EXP;

import de.teamprojekt.Entity.Character;
import de.teamprojekt.Entity.Enum.Skill;
import de.teamprojekt.Entity.Todo;

public class LevelCalculator {
    public static int calculateLevelCost(int level) {
        return (int) Math.floor(BASE_EXP * Math.pow(level, EXP_MUL) + INITIAL_EXP);
    }

    public static int calculateProgress(int level, int exp) {
        int levelCost = calculateLevelCost(level);
        return (int) Math.floor((double) (exp * 100) / levelCost);
    }

    public static Character calculateLevel(Todo todo, Character character) {
        Skill type = todo.getCategory().getSkill();
        int exp = character.getExperience();
        switch (todo.getPriority()) {
            case LOW:
                exp = exp * EXP_LOW_PRIORITY;
                break;
            case MEDIUM:
                exp = exp * EXP_MEDIUM_PRIORITY;
                break;
            case HIGH:
                exp = exp * EXP_HIGH_PRIORITY;
                break;
        }

        switch (type) {
            case STRENGTH:
                character.setStrengthExp(character.getStrengthExp() + exp);
                int tmpSkillExp = character.getStrengthExp();
                if (tmpSkillExp >= calculateLevelCost(character.getStrength())) {
                    character.setStrength(character.getStrength() + 1);
                    character.setStrengthExp(tmpSkillExp - calculateLevelCost(character.getStrength()));
                }
                break;
            case PERCEPTION:
                break;
            case ENDURANCE:
                break;
            case CHARISMA:
                break;
            case INTELLIGENCE:
                break;
            case AGILITY:
                break;
            case LUCK:
                break;
        }

        return character;
    }

}
