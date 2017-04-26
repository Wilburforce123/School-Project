package com.example.localadmin.WillRecyclerView.model;

import static android.R.attr.name;

/**
 * Created by localadmin on 17/01/17.
 */

public class ListItem {

    private int number;
    private String subName;
    private int ImageResId;
    private boolean booked = false;
    private boolean sentOff = false;
    private boolean scored = false;
    private int goalsScored;

    //Setter and getter for the player position.
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    //Setter and getter for the boolean booked.
    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) { this.booked = booked; }

    //Setter and getter for the boolean sentOff.
    public boolean isSentOff() { return sentOff; }

    public void setSentOff(boolean sentOff) { this.sentOff = sentOff; }

    //Setter and getter for the boolean hasScored.
    public boolean hasScored() { return scored; }

    public void setScored(boolean scored) { this.scored = scored; }

    //Setter and getter for the integer number, which corresponds to the player.
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getImageResId() {
        return ImageResId;
    }

    public void setImageResId(int imageResId) {
        this.ImageResId = imageResId;
    }

    //Setter and getter for the intger goalsScored, which is how many goals that player has scored.
    public int getGoalsScored() { return goalsScored; }

    public void setGoalsScored(int goalsScored) { this.goalsScored = goalsScored; }
}
