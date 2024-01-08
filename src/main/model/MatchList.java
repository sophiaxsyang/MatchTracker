package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// A MatchList contains a list of matches played, and a list of matches or maps can be returned.

public class MatchList implements Writable {
    private List<Match> matchList;

    public MatchList() {
        matchList = new ArrayList<>();
    }

    // REQUIRES: same match cannot exist
    // MODIFIES: this
    // EFFECTS: adds match to list of matches
    public void addMatch(Match match) {
        matchList.add(match);
        EventLog.getInstance().logEvent(new Event("Match added"));
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    // REQUIRES: at least 1 match
    // EFFECTS: return list of maps played
    public List<String> getMapsPlayed() {
        List<String> mapsPlayed = new ArrayList<>();
        for (Match m : matchList) {
            mapsPlayed.add(m.getMap());
        }
        EventLog.getInstance().logEvent(new Event("List of Maps played were displayed."));
        return mapsPlayed;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("matches", matchesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray matchesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Match m : matchList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
