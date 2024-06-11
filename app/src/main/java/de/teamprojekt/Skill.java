package de.teamprojekt;

import androidx.annotation.NonNull;

public enum Skill {
    STRENGTH("Strength"),
    PERCEPTION("Perception"),
    ENDURANCE("Endurance"),
    CHARISMA("Charisma"),
    INTELLIGENCE("Intelligence"),
    AGILITY("Agility"),
    LUCK("Luck");

    private final String name;

    Skill(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
