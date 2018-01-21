package com.hackermatcher.hackermatcher.Backend;

public class Interest {

    private String interest;
    private int interestLevel;
    public static final int MIN_INTEREST_LEVEL = 0;
    public static int MAX_INTEREST_LEVEL = 10;

    public Interest() {

    }

    public Interest(String interest){
        this.interest = interest;
    }

    public Interest(String interest, int interestLevel){
        this.interest = interest;
        this.interestLevel = interestLevel;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getInterestLevel() {
        return interestLevel;
    }

    public void setInterestLevel(int interestLevel) {
        this.interestLevel = interestLevel;
    }
}
