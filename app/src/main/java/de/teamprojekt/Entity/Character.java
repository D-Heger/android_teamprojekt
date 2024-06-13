package de.teamprojekt.Entity;

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
    private int strengthExp;
    private int perception;
    private int perceptionExp;
    private int endurance;
    private int enduranceExp;
    private int charisma;
    private int charismaExp;
    private int intelligence;
    private int intelligenceExp;
    private int agility;
    private int agilityExp;
    private int luck;
    private int luckExp;

    /**
     * ONLY USE WHEN YOU KNOW WHAT YOU ARE DOING
     */
    public Character() {
    }

    private Character(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.gender = builder.gender;
        this.icon = builder.icon;
        this.level = builder.level;
        this.experience = builder.experience;
        this.strength = builder.strength;
        this.strengthExp = builder.strength_exp;
        this.perception = builder.perception;
        this.perceptionExp = builder.perception_exp;
        this.endurance = builder.endurance;
        this.enduranceExp = builder.endurance_exp;
        this.charisma = builder.charisma;
        this.charismaExp = builder.charisma_exp;
        this.intelligence = builder.intelligence;
        this.intelligenceExp = builder.intelligence_exp;
        this.agility = builder.agility;
        this.agilityExp = builder.agility_exp;
        this.luck = builder.luck;
        this.luckExp = builder.luck_exp;
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

    public int getStrengthExp() {
        return strengthExp;
    }

    public void setStrengthExp(int strengthExp) {
        this.strengthExp = strengthExp;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getPerceptionExp() {
        return perceptionExp;
    }

    public void setPerceptionExp(int perceptionExp) {
        this.perceptionExp = perceptionExp;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getEnduranceExp() {
        return enduranceExp;
    }

    public void setEnduranceExp(int enduranceExp) {
        this.enduranceExp = enduranceExp;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getCharismaExp() {
        return charismaExp;
    }

    public void setCharismaExp(int charismaExp) {
        this.charismaExp = charismaExp;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getIntelligenceExp() {
        return intelligenceExp;
    }

    public void setIntelligenceExp(int intelligenceExp) {
        this.intelligenceExp = intelligenceExp;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getAgilityExp() {
        return agilityExp;
    }

    public void setAgilityExp(int agilityExp) {
        this.agilityExp = agilityExp;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getLuckExp() {
        return luckExp;
    }

    public void setLuckExp(int luckExp) {
        this.luckExp = luckExp;
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
