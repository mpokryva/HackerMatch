package com.hackermatcher.hackermatcher.Backend;


import java.util.List;

public class Hacker {

    private String name;
    private List<Interest> interests;
    private String university;
    private String teamID;
    private List<Skill> skills;

    public Hacker(){

    }

    public Hacker(String name, String university){
        this.name = name;
        this.university = university;
        teamID = null;
    }

    public Hacker(String name, List<Interest> interests, String university, List<Skill> skills){
        this.name = name;
        this.interests = interests;
        this.university = university;
        teamID = null;
    }




    //////////////////////
    //Getters & Setters
    /////////////////////
    public String getName(){
        return name;
    }
    public List<Interest> getInterests(){
        return interests;
    }
    public String getUniversity(){ return university;}
    public List<Skill> getSkills(){return skills;}


}
