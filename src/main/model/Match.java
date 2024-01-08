package model;

import org.json.JSONObject;
import persistence.Writable;

// A match has team1, team2, t1score, t2score, and map played.

public class Match implements Writable {
    private String team1;
    private String team2;
    private String map;
    private int t1score;
    private int t2score;

    // REQUIRES: teams must exist in listOfTeams
    // EFFECTS: match with no teams, no map and score of 0
    public Match() {
        t1score = 0;
        t2score = 0;
    }

    public void setTeam1(String t1) {
        team1 = t1;
        EventLog.getInstance().logEvent(new Event("Team 1 added"));
    }

    public void setTeam2(String t2) {
        team2 = t2;
        EventLog.getInstance().logEvent(new Event("Team 2 added"));
    }

    // REQUIRES: map must exist in Valorant
    public void setMap(String map) {
        this.map = map;
        EventLog.getInstance().logEvent(new Event("Map added"));
    }

    // REQUIRES: score <= 0
    public void setScoreTeam1(int score) {
        t1score = score;
        EventLog.getInstance().logEvent(new Event("Team 1 score added"));
    }

    // REQUIRES: score <= 0
    public void setScoreTeam2(int score) {
        t2score = score;
        EventLog.getInstance().logEvent(new Event("Team 2 score added"));
    }


    public int getScoreTeam1() {
        return t1score;
    }

    public int getScoreTeam2() {
        return t2score;
    }

    public String getT1() {
        return team1;
    }

    public String getT2() {
        return team2;
    }

    public String getMap() {
        return map;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("team 1", getT1());
        json.put("team 2", getT2());
        json.put("team 1 score", t1score);
        json.put("team 2 score", t2score);
        json.put("map", map);
        return json;
    }
}
