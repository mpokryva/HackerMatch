package com.hackermatcher.hackermatcher.Backend;

public class Interest {

    private String interest;
    private int interestLevel;

    public Interest(String interest){
        this.interest = interest;
    }

    public Interest(String interest, int interestLevel){
        this.interest = interest;
        this.interestLevel = interestLevel;
    }
}
