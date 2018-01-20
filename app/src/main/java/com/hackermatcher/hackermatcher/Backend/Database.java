package com.hackermatcher.hackermatcher.Backend;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final String TAG = "DebugDatabase";

    public DatabaseReference mDatabase;

    private List<String> hackerIDs;
    private List<String> hackathonIDs;
    private List<String> teamIDs;

    public Database(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.setValue(null);

        hackerIDs = new ArrayList<String>();
        hackathonIDs = new ArrayList<String>();
        teamIDs = new ArrayList<String>();

        mDatabase.child("Hackers").setValue("HACKERS");
        mDatabase.child("Events").setValue("EVENTS");
        mDatabase.child("Teams").setValue("TEAMS");
    }

    public void addHacker(Hacker hacker){
        hackerIDs.add(hacker.getName());
        mDatabase.child("Hackers").child(hacker.getName()).setValue(hacker);
    }

    public void addHackathon(Hackathon hackathon){
        hackathonIDs.add(hackathon.getHackathonID());
        mDatabase.child("Events").child(hackathon.getHackathonID()).setValue(hackathon);
    }

    public void addTeam(HackerTeam team){
        String teamID = team.getTeamID();
        for(String str : teamIDs){
            if(str.equals(teamID)){
                Log.d(TAG, "Hacker id already exists");
                return;
            }
        }
        teamIDs.add(teamID);
        mDatabase.child("Teams").child(team.getTeamID()).setValue(team);
    }

    public boolean isValidHackerName(String hackerName){
        for(String str : hackerIDs){
            if(str.equals(hackerName)){
                return false;
            }
        }
        return true;
    }

    public boolean isValidHackathon(String hackathonName){
        for(String str : hackathonIDs){
            if(str.equals(hackathonName)){
                return false;
            }
        }
        return true;
    }
}
