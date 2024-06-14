package de.teamprojekt.Util;

import static de.teamprojekt.Util.LevelCalculator.calculateLevelCost;

import android.util.Log;

import java.lang.reflect.Method;

import de.teamprojekt.Entity.Character;
import de.teamprojekt.Entity.Enum.Skill;

public class ExperienceCalculator {
    public static Character increaseSkillExp(Character character, Skill skill, int exp) throws ReflectiveOperationException {
        String skillName = skill.getName();
        Method getExp = Character.class.getMethod("get" + skillName + "Exp");
        Method setExp = Character.class.getMethod("set" + skillName + "Exp", int.class);
        Method getLevel = Character.class.getMethod("get" + skillName);
        Method setLevel = Character.class.getMethod("set" + skillName, int.class);

        int currentExp = (Integer) getExp.invoke(character);
        currentExp += exp;
        setExp.invoke(character, currentExp);

        int currentLevel = (Integer) getLevel.invoke(character);
        if (currentExp >= calculateLevelCost(currentLevel)) {
            setLevel.invoke(character, currentLevel + 1);
            currentLevel = (Integer) getLevel.invoke(character);
            setExp.invoke(character, currentExp - calculateLevelCost(currentLevel));
        }

        return character;
    }

    public static Character updateSkill(Character character, Skill skill, int exp) {
        try {
            increaseSkillExp(character, skill, exp);
            Log.d("ExperienceCalculator", "Updated skill " + skill.getName() + " for character " + character.getName() + " with " + exp + " experience");
        } catch (ReflectiveOperationException e) {
            Log.e("ExperienceCalculator", "Error while updating skill", e);
        }

        return character;
    }
}
