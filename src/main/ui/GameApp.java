package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
The GameApp implements an application where matches are created and viewable.
Stats for all matches are inputted, and can be edited. All maps played can also be viewable

GameApp attributes TellerApp and JsonSerializationDemo
 */

public class GameApp {
    private Scanner input;
    private MatchList listOfMatches;
    private static final String JSON_STORE = "./data/MatchList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs GameApp
    public GameApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGameApp();
    }

    // EFFECTS: runs game application by starting at menu
    private void runGameApp() {
        init();
        goHome();
        System.out.println("\nGood game!");
    }

    // EFFECTS: go back to main menu selection
    private void goHome() {
        boolean keepGoing = true;
        String command;
        while (keepGoing) {
            displayHomeMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize matches and teams and scanner
    public void init() {
        listOfMatches = new MatchList();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes input command
    private void processCommand(String command) {
        if (command.equals("1")) {
            createMatch();
        } else if (command.equals("2")) {
            viewMatch();
        } else if (command.equals("3")) {
            editMatch();
        } else if (command.equals("4")) {
            removeMatch();
        } else if (command.equals("5")) {
            viewMapsPlayed();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Invalid selection, try again!");
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfMatches);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            listOfMatches = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayHomeMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add match");
        System.out.println("\t2 -> view match");
        System.out.println("\t3 -> edit match");
        System.out.println("\t4 -> remove match");
        System.out.println("\t5 -> view maps played");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }


    // REQUIRES: at least 2 teams must be made, not the same team
    // MODIFIES: this
    // EFFECTS: adds match by asking score, teams, map
    private void createMatch() {
        System.out.println("Input team 1 name");
        String team1 = input.next();
        System.out.println("Input team 2 name");
        String team2 = input.next();
        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        listOfMatches.addMatch(match);

        setMatchScore(match);

        chooseMap(match);
    }


    // MODIFIES: this
    // EFFECTS: displays map options and sets given map
    private void chooseMap(Match match) {
        List<String> mapList = new ArrayList<>();
        Collections.addAll(mapList, "Ascent", "Split", "Lotus", "Bind", "Icebox", "Haven");
        Collections.addAll(mapList, "Sunset", "Pearl", "Fracture", "Breeze");
        System.out.println("Select Map [0-9]:");
        String map;
        for (String m : mapList) {
            System.out.println(m);
        }

        map = (mapList.get(input.nextInt()));
        match.setMap(map);
        System.out.println("Map selected: " + map);
    }

    // REQUIRES: if winning team score > 13, match score difference == 2, no ties
    // MODIFIES: this
    // EFFECTS: asks and sets score for team 1 and team 2
    private void setMatchScore(Match match) {
        System.out.println("Enter Team 1 score:");
        match.setScoreTeam1(input.nextInt());

        System.out.println("Enter Team 2 score:");
        match.setScoreTeam2(input.nextInt());
    }


    // EFFECTS: view all matches made
    private void viewMatch() {
        if (listOfMatches.getMatchList().isEmpty()) {
            System.out.println("No matches to view!");
        } else {
            System.out.println("Select match to view [0-" + (listOfMatches.getMatchList().size() - 1) + "]:");
            for (Match m : listOfMatches.getMatchList()) {
                System.out.println(m.getT1() + " vs. " + m.getT2());
            }
            printMatch(listOfMatches.getMatchList().get(input.nextInt()));
        }
    }

    // REQUIRES: must have matches played
    // EFFECTS: view all maps played
    private void viewMapsPlayed() {
        System.out.println(listOfMatches.getMapsPlayed());
    }


    // EFFECTS: prints stats of match
    private void printMatch(Match m) {
        System.out.println(m.getT1() + " vs. " + m.getT2());
        System.out.println("\nScore: " + m.getScoreTeam1() + "-" + m.getScoreTeam2());
        System.out.println("\nMap: " + m.getMap());
    }


    // MODIFIES: this
    // EFFECTS: select which match to remove
    private void removeMatch() {
        if (listOfMatches.getMatchList().isEmpty()) {
            System.out.println("No matches to remove!");
        } else {
            System.out.println("Select match to remove: [0-" + (listOfMatches.getMatchList().size() - 1) + "]");
            for (Match m : listOfMatches.getMatchList()) {
                System.out.println(m.getT1() + " vs. " + m.getT2());
            }
            listOfMatches.getMatchList().remove(input.nextInt());
            System.out.println("Removed selected match");
        }
    }

    // MODIFIES: this
    // EFFECTS: select and edit match
    private void editMatch() {
        System.out.println("Select match to edit:");
        for (Match m : listOfMatches.getMatchList()) {
            System.out.println(m.getT1() + " vs. " + m.getT2());
        }
        Match m = listOfMatches.getMatchList().get(input.nextInt());
        System.out.println("Input team 1 name");
        String team1 = input.next();
        System.out.println("Input team 2 name");
        String team2 = input.next();
        m.setTeam1(team1);
        m.setTeam2(team2);
        setMatchScore(m);
        chooseMap(m);
    }
}

