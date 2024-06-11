package de.teamprojekt;

import androidx.annotation.NonNull;

public enum Category {
    // Strength
    WEIGHT_TRAINING("Weight Training", Skill.STRENGTH),
    GARDENING("Gardening", Skill.STRENGTH),
    MOVING("Moving", Skill.STRENGTH),
    HOME_IMPROVEMENT("Home Improvement", Skill.STRENGTH),
    SNOW_SHOVELING("Snow Shoveling", Skill.STRENGTH),

    // Perception
    MEDITATION("Meditation", Skill.PERCEPTION),
    NATURAL_OBSERVATION("Natural Observation", Skill.PERCEPTION),
    STARGAZING("Stargazing", Skill.PERCEPTION),
    SOLVING_PUZZLES("Solving Puzzles", Skill.PERCEPTION),
    PHOTOGRAPHY("Photography", Skill.PERCEPTION),

    // Endurance
    RUNNING("Running", Skill.ENDURANCE),
    CYCLING("Cycling", Skill.ENDURANCE),
    SWIMMING("Swimming", Skill.ENDURANCE),
    HIKING("Hiking", Skill.ENDURANCE),
    DANCING("Dancing", Skill.ENDURANCE),

    // Charisma
    PUBLIC_SPEAKING("Public Speaking", Skill.CHARISMA),
    SMALL_TALK("Small Talk", Skill.CHARISMA),
    VOLUNTEERING("Volunteering", Skill.CHARISMA),
    KARAOKE("Karaoke", Skill.CHARISMA),
    NEGOTIATION("Negotiation", Skill.CHARISMA),

    // Intelligence
    LANGUAGE_COURSE("Language Course", Skill.INTELLIGENCE),
    CODING("Coding", Skill.INTELLIGENCE),
    CHESS("Chess", Skill.INTELLIGENCE),
    LANGUAGE_LEARNING("Language Learning", Skill.INTELLIGENCE),
    READING("Reading", Skill.PERCEPTION),

    // Agility
    GYMNASTICS("Gymnastics", Skill.AGILITY),
    CLIMBING("Climbing", Skill.AGILITY),
    ACROBATICS("Acrobatics", Skill.AGILITY),
    STRETCHING("Stretching", Skill.AGILITY),
    YOGA("Yoga", Skill.AGILITY),

    // Luck
    GRATITUDE_EXERCISE("Gratitude Exercise", Skill.LUCK),
    TRAVELING("Traveling", Skill.LUCK),
    ENJOYING_FOOD("Enjoying Food", Skill.LUCK),
    MAKING_MUSIC("Making Music", Skill.LUCK),
    ANIMAL_CARE("Animal Care", Skill.LUCK),
    GAMBLING("Gambling", Skill.LUCK);

    private final String name;
    private final Skill skill;

    Category(String name, Skill skill) {
        this.name = name;
        this.skill = skill;
    }

    public static Category categoryFromName(String name) {
        for (Category category : values()) {
            if (category.name.equals(name)) {
                return category;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Skill getSkill() {
        return skill;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

}
