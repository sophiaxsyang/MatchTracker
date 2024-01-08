package ui;

import model.EventLog;
import model.Event;
import model.Match;
import model.MatchList;

import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// The GUI class create a graphical user interface with buttons that can add a match to list, view
// maps, save and load.
public class GUI extends JFrame implements ActionListener {

    private MatchList listOfMatch = new MatchList();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel("list of matches");
    private JButton button = new JButton("add match");
    private JButton mapButton = new JButton("get maps");
    private JButton saveButton = new JButton("save");
    private JButton loadButton = new JButton("load");
    private String mapList;

    private static final String JSON_STORE = "./data/MatchList.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    String sep = System.getProperty("file.separator");

    private ImageIcon image = new ImageIcon(System.getProperty("user.dir") + sep
            + "data" + sep + "valorant-epilogue-cracked-spray.png");
    private JLabel icon = new JLabel(image);


// EFFECTS: Constructs GUI with default homepage
    // frame, panel with add, map, save, load button, label and picture
    public GUI() {

        button.addActionListener(this);
        mapButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);


        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(mapButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(label);
        panel.add(icon);

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
        frame.setTitle("GameApp");
        frame.pack();
        frame.setVisible(true);

    }

    // EFFECTS: prints closing message and logged events before exiting
    private static void onWindowClosing() {
        System.out.println("Application closed.");
        printLoggedEvents();
        System.exit(0);
    }

    // EFFECTS: prints description of each logged event
    private static void printLoggedEvents() {
        System.out.println("Logged events:");

        for (Event e: EventLog.getInstance()) {
            System.out.println(e.getDescription());
        }
    }

    // EFFECTS: Returns all match states as string
    public String getMatchStats(Match match) {
        String string = "team 1: " + match.getT1() + " ";
        string = string + "team 2: " + match.getT2() + " ";
        string = string + "score: " + match.getScoreTeam1() + "-" + match.getScoreTeam2() + " ";
        string = string + "map: " + match.getMap();
        return string;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        new GUI();
    }

    @Override
    // EFFECTS: if add match button is pressed, ask for input
    // if map button is pressed, show list of map
    // save buttom will save, load button will load
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == button) {
            JOptionPane popUp = new JOptionPane();
            Match match = getMatch(popUp);
            listOfMatch.addMatch(match);
            panel.add(new JLabel(getMatchStats(match)));

        } else if (ev.getSource() == mapButton) {
            JOptionPane popUp = new JOptionPane();
            JPanel panel1 = new JPanel();
            popUp.setLayout(new GridLayout(0,1));
            for (String m : listOfMatch.getMapsPlayed()) {
                JLabel map = new JLabel(m);
                panel1.add(map);
            }
            popUp.showConfirmDialog(null,
                    panel1,
                    "Maps: ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

        } else if (ev.getSource() == saveButton) {
            saveWorkRoom();

        } else if (ev.getSource() == loadButton) {
            loadWorkRoom();
        }
    }



    private static Match getMatch(JOptionPane popUp) {
        Match match = new Match();
        match.setTeam1(popUp.showInputDialog("Input Team1"));
        match.setTeam2(popUp.showInputDialog("Input Team2"));
        match.setScoreTeam1(Integer.parseInt(popUp.showInputDialog("Input Team1 Score")));
        match.setScoreTeam2(Integer.parseInt(popUp.showInputDialog("Input Team2 Score")));
        match.setMap(popUp.showInputDialog("Input Map"));
        return match;
    }

    // MODIFIES: this
    // EFFECTS: saves work to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfMatch);
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
            listOfMatch = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        for (Match m: listOfMatch.getMatchList()) {
            panel.add(new JLabel(getMatchStats(m)));
        }
    }
}
