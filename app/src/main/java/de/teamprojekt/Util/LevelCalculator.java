package de.teamprojekt.Util;

import static de.teamprojekt.Util.Constants.CalculationConstants.BASE_EXP;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_HIGH_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_LOW_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_MEDIUM_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.INITIAL_EXP;
import static de.teamprojekt.Util.ExperienceCalculator.updateSkill;

import de.teamprojekt.Entity.Character;
import de.teamprojekt.Entity.Enum.Skill;
import de.teamprojekt.Entity.Todo;

public class LevelCalculator {
    public static int calculateLevelCost(int level) {
        return (int) Math.floor(BASE_EXP * level + INITIAL_EXP);
    }

    public static int calculateProgress(int level, int exp) {
        int levelCost = calculateLevelCost(level);
        return (int) Math.floor((double) (exp * 100) / levelCost);
    }

    public static Character calculateLevel(Todo todo, Character character) {
        Skill type = todo.getCategory().getSkill();
        int exp = EXP;
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
        // TODO include date maybe?
        switch (type) {
            case STRENGTH:
                updateSkill(character, Skill.STRENGTH, exp);
                break;
            case PERCEPTION:
                updateSkill(character, Skill.PERCEPTION, exp);
                break;
            case ENDURANCE:
                updateSkill(character, Skill.ENDURANCE, exp);
                break;
            case CHARISMA:
                updateSkill(character, Skill.CHARISMA, exp);
                break;
            case INTELLIGENCE:
                updateSkill(character, Skill.INTELLIGENCE, exp);
                break;
            case AGILITY:
                updateSkill(character, Skill.AGILITY, exp);
                break;
            case LUCK:
                updateSkill(character, Skill.LUCK, exp);
                break;
        }

        int tmpExp;
        character.setExperience(character.getExperience() + exp);
        tmpExp = character.getExperience();
        if (tmpExp >= calculateLevelCost(character.getLevel())) {
            character.setLevel(character.getLevel() + 1);
            character.setExperience(tmpExp - calculateLevelCost(character.getLevel()));
        }

        return character;
    }

}
