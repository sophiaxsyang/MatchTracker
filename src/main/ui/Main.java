package ui;

import java.io.FileNotFoundException;

// Main class to start the entire program
public class Main {
    public static void main(String[] args) {
        try {
            new GameApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
