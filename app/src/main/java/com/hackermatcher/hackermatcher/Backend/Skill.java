package com.hackermatcher.hackermatcher.Backend;


import java.io.Serializable;

public class Skill implements Serializable {

    private String skill;
    private int skillLevel;
    public static final int MIN_SKILL_LEVEL = 0;
    public static int MAX_SKILL_LEVEL = 10;

    public Skill() {

    }

    public Skill(String skill, int skillLevel){
        this.skill = skill;
        this.skillLevel = skillLevel;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }
}
