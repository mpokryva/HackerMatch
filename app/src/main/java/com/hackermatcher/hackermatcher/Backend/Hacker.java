package com.hackermatcher.hackermatcher.Backend;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Hacker implements Serializable {

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
        this.skills = skills;
        teamID = null;
    }

    public List<Interest> getInterestScores(){
        double totalScore = 0.0;
        for(Interest i : interests){
            totalScore += i.getInterestLevel();
        }
        List<Interest> interestScores = new ArrayList<Interest>();

        for(int index = 0; index < interests.size(); index++){
            String interest = interests.get(index).getInterest();
            double interestLevel = interests.get(index).getInterestLevel();
            double interestScore = (interestLevel/totalScore) * 100;
            int score = (int)interestScore;
            Interest i = new Interest(interest, score);
            interestScores.add(i);
        }
        return interestScores;
    }

    public double getCommonInterestPoints(Hacker h){
        List<Interest> myInterestScores = getInterestScores();
        List<Interest> hInterestScores = h.getInterestScores();
        double commonInterestPoints = 0;
        for(int i = 0; i < myInterestScores.size(); i++){
            String myInterest = myInterestScores.get(i).getInterest();
            for(int j = i; j < hInterestScores.size(); j++){
                String hInterest = hInterestScores.get(j).getInterest();
                if(myInterest.equals(hInterest)){
                    double CIP = Math.abs(myInterestScores.get(i).getInterestLevel()-hInterestScores.get(i).getInterestLevel());
                    commonInterestPoints += CIP;
                }
            }
        }
        return 100-commonInterestPoints;
    }

    /**
     * Alg:
     * 1. Find all hackers attending the event
     * 3. Get common interest points between this hacker and a hacker in allHackersInEvent
     * 4. sort the array
     */
    public HashMap<Hacker, Double> rankMatches(final String hackathonID, List<Hacker> allHackerInEvent, boolean normalize){
        double[] CIP = new double[allHackerInEvent.size()];
        double cipSum = 0;
        double minCip = Double.MAX_VALUE;
        for(int i = 0; i < allHackerInEvent.size(); i++){
            CIP[i] = getCommonInterestPoints(allHackerInEvent.get(i));
            cipSum += CIP[i];
            if (CIP[i] < minCip) {
                minCip = CIP[i];
            }
        }
        if (minCip < 0) {
            for (int i = 0; i < CIP.length; i++) {
                CIP[i] = CIP[i] - minCip + 1;
            }
        }
        HashMap<Hacker, Double> matchingRanks = new HashMap<>();
        for(int i = 0; i < CIP.length; i++){
            Hacker h = allHackerInEvent.get(i);
            matchingRanks.put(h, CIP[i]);
        }
        if (normalize) {
            for(Hacker hacker : matchingRanks.keySet()) {
                matchingRanks.put(hacker, matchingRanks.get(hacker) / cipSum);
            }
        }
        return matchingRanks;
        //Log.d(TAG, "CIP:"+CIP[1]);
        //pass to visulization the CIP and hacker
    }

    public int getSkillScores(){
        int totalScore = 0;
        for(Skill i : skills){
            totalScore += i.getSkillLevel();
        }
        return totalScore;
    }

    //high score means similiar skill level
    public HashMap<Hacker, Double> skillMatches(final String hackathonID, List<Hacker> allHackerInEvent, boolean normalize){
        double[] CIP = new double[allHackerInEvent.size()];
        double cipSum = 0;
        double minCip = Double.MAX_VALUE;
        for(int i = 0; i < allHackerInEvent.size(); i++){
            int hSkillLevel = allHackerInEvent.get(i).getSkillScores();
            CIP[i] = (100-Math.abs(hSkillLevel-getSkillScores()));
            cipSum += CIP[i];
            if (CIP[i] < minCip) {
                minCip = CIP[i];
            }
        }

        HashMap<Hacker, Double> matchingSkillRanks = new HashMap<>();
        if (minCip < 0) {
            for (int i = 0; i < CIP.length; i++) {
                CIP[i] = CIP[i] + (minCip + 1);
            }
        }
        for(int i = 0; i < CIP.length; i++){
            Hacker h = allHackerInEvent.get(i);
            matchingSkillRanks.put(h, CIP[i]);
        }
        if (normalize) {
            for (Hacker hacker : matchingSkillRanks.keySet()) {
                matchingSkillRanks.put(hacker, matchingSkillRanks.get(hacker) / cipSum);
            }
        }

        return matchingSkillRanks;
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
