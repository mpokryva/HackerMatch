package com.hackermatcher.hackermatcher.Backend;

import android.util.Log;

import java.util.List;
import java.util.PriorityQueue;

public class HackerTeam {

    private final String TAG = "DebugHackerTeam";

    private List<Hacker> hackerList;
    private String teamName;
    private String teamID;
    private String eventID;
    private int maxHackers;

    public HackerTeam(String teamID, String teamName, List<Hacker> hackers) throws IllegalArgumentException{
        this.teamID = teamID;
        this.teamName = teamName;
        if(hackers.size() <= 1){
            throw new IllegalArgumentException("Team size must be larger than 1 hacker");
        }
        hackerList = hackers;
    }

    public HackerTeam(String teamID, String teamName, List<Hacker> hackers, String eventID, int maxHackers){
        this.teamID = teamID;
        this.teamName = teamName;
        hackerList = hackers;
        this.eventID = eventID;
        this.maxHackers = maxHackers;
    }

    public void addHacker(Hacker hacker){
        if(hackerList.size() == maxHackers){
            Log.d(TAG, "Max hacker size reached for team"+teamID+". Cannot add hacker");
            return;
        }
        hackerList.add(hacker);
    }


    /////////////////////////
    //Getters
    ////////////////////////
    public String getTeamID(){return teamID;}
    public String getTeamName(){return teamName;}
    public String getEventID(){return eventID;}
    public int getMaxHackers(){return maxHackers;}
    public List<Hacker> getHackerList(){return hackerList;}
}
