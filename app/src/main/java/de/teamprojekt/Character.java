package de.teamprojekt;

import androidx.annotation.NonNull;

public class Character {
    // general
    private String name;
    private int age;
    private String gender;
    private int icon;
    private int level;
    private int experience;

    // stats
    private int strength;
    private int strength_exp;
    private int perception;
    private int perception_exp;
    private int endurance;
    private int endurance_exp;
    private int charisma;
    private int charisma_exp;
    private int intelligence;
    private int intelligence_exp;
    private int agility;
    private int agility_exp;
    private int luck;
    private int luck_exp;

    private Character(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.gender = builder.gender;
        this.icon = builder.icon;
        this.level = builder.level;
        this.experience = builder.experience;
        this.strength = builder.strength;
        this.strength_exp = builder.strength_exp;
        this.perception = builder.perception;
        this.perception_exp = builder.perception_exp;
        this.endurance = builder.endurance;
        this.endurance_exp = builder.endurance_exp;
        this.charisma = builder.charisma;
        this.charisma_exp = builder.charisma_exp;
        this.intelligence = builder.intelligence;
        this.intelligence_exp = builder.intelligence_exp;
        this.agility = builder.agility;
        this.agility_exp = builder.agility_exp;
        this.luck = builder.luck;
        this.luck_exp = builder.luck_exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength_exp() {
        return strength_exp;
    }

    public void setStrength_exp(int strength_exp) {
        this.strength_exp = strength_exp;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getPerception_exp() {
        return perception_exp;
    }

    public void setPerception_exp(int perception_exp) {
        this.perception_exp = perception_exp;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getEndurance_exp() {
        return endurance_exp;
    }

    public void setEndurance_exp(int endurance_exp) {
        this.endurance_exp = endurance_exp;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getCharisma_exp() {
        return charisma_exp;
    }

    public void setCharisma_exp(int charisma_exp) {
        this.charisma_exp = charisma_exp;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getIntelligence_exp() {
        return intelligence_exp;
    }

    public void setIntelligence_exp(int intelligence_exp) {
        this.intelligence_exp = intelligence_exp;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getAgility_exp() {
        return agility_exp;
    }

    public void setAgility_exp(int agility_exp) {
        this.agility_exp = agility_exp;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getLuck_exp() {
        return luck_exp;
    }

    public void setLuck_exp(int luck_exp) {
        this.luck_exp = luck_exp;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }

    public static class Builder {
        // general
        private String name;
        private int age;
        private String gender;
        private int icon;
        private int level;
        private int experience;

        // stats
        private int strength;
        private int strength_exp;
        private int perception;
        private int perception_exp;
        private int endurance;
        private int endurance_exp;
        private int charisma;
        private int charisma_exp;
        private int intelligence;
        private int intelligence_exp;
        private int agility;
        private int agility_exp;
        private int luck;
        private int luck_exp;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder icon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder experience(int experience) {
            this.experience = experience;
            return this;
        }

        public Builder strength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder strength_exp(int strength_exp) {
            this.strength_exp = strength_exp;
            return this;
        }

        public Builder perception(int perception) {
            this.perception = perception;
            return this;
        }

        public Builder perception_exp(int perception_exp) {
            this.perception_exp = perception_exp;
            return this;
        }

        public Builder endurance(int endurance) {
            this.endurance = endurance;
            return this;
        }

        public Builder endurance_exp(int endurance_exp) {
            this.endurance_exp = endurance_exp;
            return this;
        }

        public Builder charisma(int charisma) {
            this.charisma = charisma;
            return this;
        }

        public Builder charisma_exp(int charisma_exp) {
            this.charisma_exp = charisma_exp;
            return this;
        }

        public Builder intelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder intelligence_exp(int intelligence_exp) {
            this.intelligence_exp = intelligence_exp;
            return this;
        }

        public Builder agility(int agility) {
            this.agility = agility;
            return this;
        }

        public Builder agility_exp(int agility_exp) {
            this.agility_exp = agility_exp;
            return this;
        }

        public Builder luck(int luck) {
            this.luck = luck;
            return this;
        }

        public Builder luck_exp(int luck_exp) {
            this.luck_exp = luck_exp;
            return this;
        }

        public Character build() {
            return new Character(this);
        }
    }
}
