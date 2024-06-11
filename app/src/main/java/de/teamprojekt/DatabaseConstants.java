package de.teamprojekt;

import androidx.annotation.NonNull;

public class DatabaseConstants {
    public enum Table {
        TODO_TABLE("TODO_TABLE"),
        CHARACTER_TABLE("CHARACTER_TABLE");

        private final String name;

        Table(String name) {
            this.name = name;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
        }
    }

    public enum TodoColumn {
        ID("ID"),
        PRIORITY_ID("PRIORITY_ID"),
        CATEGORY_ID("CATEGORY_ID"),
        STATUS("STATUS"),
        START_DATE("START_DATE"),
        END_DATE("END_DATE"),
        TITLE("TITLE"),
        DESCRIPTION("DESCRIPTION");

        private final String name;

        TodoColumn(String name) {
            this.name = name;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
        }
    }

    public enum CharacterColumn {
        ID("CHARACTER_ID"),
        NAME("CHARACTER_NAME"),
        AGE("CHARACTER_AGE"),
        GENDER("CHARACTER_GENDER"),
        ICON("CHARACTER_ICON"),
        LEVEL("CHARACTER_LEVEL"),
        EXP("CHARACTER_EXP"),
        STRENGTH("STRENGTH"),
        STRENGTH_EXP("STRENGTH_EXP"),
        PERCEPTION("PERCEPTION"),
        PERCEPTION_EXP("PERCEPTION_EXP"),
        ENDURANCE("ENDURANCE"),
        ENDURANCE_EXP("ENDURANCE_EXP"),
        CHARISMA("CHARISMA"),
        CHARISMA_EXP("CHARISMA_EXP"),
        INTELLIGENCE("INTELLIGENCE"),
        INTELLIGENCE_EXP("INTELLIGENCE_EXP"),
        AGILITY("AGILITY"),
        AGILITY_EXP("AGILITY_EXP"),
        LUCK("LUCK"),
        LUCK_EXP("LUCK_EXP");

        private final String name;

        CharacterColumn(String name) {
            this.name = name;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
        }
    }
}
