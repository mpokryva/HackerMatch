package com.hackermatcher.hackermatcher.Backend;

import java.util.Date;

public class Hackathon {

    private String hackathonID;
    private String hackathonName;
    private String location;
    private Date hackathonDate;

    public Hackathon(){}

    public Hackathon(String hackathonID, String hackathonName){
        this.hackathonID = hackathonID;
        this.hackathonName = hackathonName;
    }

    /////////////////
    //Getters
    /////////////////

    public String getHackathonID(){return hackathonID;}
    public String getHacakathonName(){return hackathonName;}
    public String getLocation(){return location;}
    public Date getHackathonDate(){return hackathonDate;}

}
