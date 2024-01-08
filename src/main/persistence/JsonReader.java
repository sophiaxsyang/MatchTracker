package persistence;

import model.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads and parses through a file

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MatchList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMatch(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses matchlist from JSON object and returns it
    private MatchList parseMatch(JSONObject jsonObject) { //parse matchlist
        MatchList t = new MatchList();
        addMatches(t, jsonObject);
        return t;
    }

    // MODIFIES: t
    // EFFECTS: parses matches from JSON object and adds them to matchlist
    private void addMatches(MatchList t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("matches");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addMatch(t, nextThingy);
        }
    }

    // MODIFIES: t
    // EFFECTS: parses match from JSON object and adds it to workroom
    private void addMatch(MatchList t, JSONObject jsonObject) {
        String t1 = jsonObject.getString("team 1");
        String t2 = jsonObject.getString("team 2");
        String map = jsonObject.getString("map");
        int t1score = jsonObject.getInt("team 1 score");
        int t2score = jsonObject.getInt("team 2 score");
        Match match = new Match();
        match.setTeam1(t1);
        match.setTeam2(t2);
        match.setScoreTeam1(t1score);
        match.setScoreTeam2(t2score);
        match.setMap(map);
        t.addMatch(match);
    }

}
