package org.example;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.SQLOutput;

public class Skier {
    private Integer time;
    private Integer liftID;
    private String resortID;
    private String seasonID;
    private String dayID;
    private String skierID;

    public Skier(String message){
        String[] info = message.split("/");
        ///skiers/4/seasons/2022/days/1/skiers/49031/body/{"time":324,"liftID":30}
        this.resortID = info[2];
        this.seasonID = info[4];
        this.dayID = info[6];
        this.skierID = info[8];
        String body = info[10];
        Gson gson = new Gson();
        LiftRide liftRide = gson.fromJson(body, LiftRide.class);
        this.liftID = liftRide.getLiftID();
        this.time = liftRide.getTime();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getLiftID() {
        return liftID;
    }

    public void setLiftID(Integer liftID) {
        this.liftID = liftID;
    }

    public String getResortID() {
        return resortID;
    }

    public void setResortID(String resortID) {
        this.resortID = resortID;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public String getDayID() {
        return dayID;
    }

    public void setDayID(String dayID) {
        this.dayID = dayID;
    }

    public String getSkierID() {
        return skierID;
    }

    public void setSkierID(String skierID) {
        this.skierID = skierID;
    }

    @Override
    public String toString() {
        return "Skier{" +
                "time=" + time +
                ", liftID=" + liftID +
                ", resortID='" + resortID + '\'' +
                ", seasonID='" + seasonID + '\'' +
                ", dayID='" + dayID + '\'' +
                ", skierID='" + skierID + '\'' +
                '}';
    }
}
