package de.teamprojekt.Util;

import static de.teamprojekt.Util.Constants.CalculationConstants.BASE_EXP;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_HIGH_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_LOW_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_MEDIUM_PRIORITY;
import static de.teamprojekt.Util.Constants.CalculationConstants.EXP_MUL;
import static de.teamprojekt.Util.Constants.CalculationConstants.INITIAL_EXP;
import static de.teamprojekt.Util.ExperienceCalculator.updateSkill;

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
        int tmpSkillExp;
        switch (type) {
            case STRENGTH:
                updateSkill(character, Skill.STRENGTH, exp);
                break;
            case PERCEPTION:
                character.setPerceptionExp(character.getPerceptionExp() + exp);
                tmpSkillExp = character.getPerceptionExp();
                if (tmpSkillExp >= calculateLevelCost(character.getPerception())) {
                    character.setPerception(character.getPerception() + 1);
                    character.setPerceptionExp(tmpSkillExp - calculateLevelCost(character.getPerception()));
                }
                break;
            case ENDURANCE:
                character.setEnduranceExp(character.getEnduranceExp() + exp);
                tmpSkillExp = character.getEnduranceExp();
                if (tmpSkillExp >= calculateLevelCost(character.getEndurance())) {
                    character.setEndurance(character.getEndurance() + 1);
                    character.setEnduranceExp(tmpSkillExp - calculateLevelCost(character.getEndurance()));
                }
                break;
            case CHARISMA:
                character.setCharismaExp(character.getCharismaExp() + exp);
                tmpSkillExp = character.getCharismaExp();
                if (tmpSkillExp >= calculateLevelCost(character.getCharisma())) {
                    character.setCharisma(character.getCharisma() + 1);
                    character.setCharismaExp(tmpSkillExp - calculateLevelCost(character.getCharisma()));
                }
                break;
            case INTELLIGENCE:
                character.setIntelligenceExp(character.getIntelligenceExp() + exp);
                tmpSkillExp = character.getIntelligenceExp();
                if (tmpSkillExp >= calculateLevelCost(character.getIntelligence())) {
                    character.setIntelligence(character.getIntelligence() + 1);
                    character.setIntelligenceExp(tmpSkillExp - calculateLevelCost(character.getIntelligence()));
                }
                break;
            case AGILITY:
                character.setAgilityExp(character.getAgilityExp() + exp);
                tmpSkillExp = character.getAgilityExp();
                if (tmpSkillExp >= calculateLevelCost(character.getAgility())) {
                    character.setAgility(character.getAgility() + 1);
                    character.setAgilityExp(tmpSkillExp - calculateLevelCost(character.getAgility()));
                }
                break;
            case LUCK:
                character.setLuckExp(character.getLuckExp() + exp);
                tmpSkillExp = character.getLuckExp();
                if (tmpSkillExp >= calculateLevelCost(character.getLuck())) {
                    character.setLuck(character.getStrength() + 1);
                    character.setLuckExp(tmpSkillExp - calculateLevelCost(character.getLuck()));
                }
                break;
        }

        return character;
    }

}
