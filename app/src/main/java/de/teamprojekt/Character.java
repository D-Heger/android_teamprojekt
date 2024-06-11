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

    public Character(String name, int age, String gender, int icon, int level, int experience, int strength, int strength_exp, int perception, int perception_exp, int endurance, int endurance_exp, int charisma, int charisma_exp, int intelligence, int intelligence_exp, int agility, int agility_exp, int luck, int luck_exp) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.icon = icon;
        this.level = level;
        this.experience = experience;
        this.strength = strength;
        this.strength_exp = strength_exp;
        this.perception = perception;
        this.perception_exp = perception_exp;
        this.endurance = endurance;
        this.endurance_exp = endurance_exp;
        this.charisma = charisma;
        this.charisma_exp = charisma_exp;
        this.intelligence = intelligence;
        this.intelligence_exp = intelligence_exp;
        this.agility = agility;
        this.agility_exp = agility_exp;
        this.luck = luck;
        this.luck_exp = luck_exp;
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
}
